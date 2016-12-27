package sunxl8.rongyun_im.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.android_lib.utils.SPUtils;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImApplication;
import sunxl8.rongyun_im.base.ImBaseActivity;
import sunxl8.rongyun_im.entity.LeanCloudException;
import sunxl8.rongyun_im.entity.LoginEntityRequest;
import sunxl8.rongyun_im.entity.LoginEntityResponse;
import sunxl8.rongyun_im.entity.RongCloudException;
import sunxl8.rongyun_im.event.DestroySplashEvent;
import sunxl8.rongyun_im.network.LeanCloudExceptionEngine;
import sunxl8.rongyun_im.network.LeanCloudRequest;
import sunxl8.rongyun_im.network.RongCloudExceptionEngine;
import sunxl8.rongyun_im.network.RongCloudRequest;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class SplashActivity extends ImBaseActivity {

    @BindView(R.id.layout_splash_button)
    LinearLayout layoutBtn;
    @BindView(R.id.btn_splash_login)
    TextView btnLogin;
    @BindView(R.id.btn_splash_register)
    TextView btnRegister;

    @Override
    public int setContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        RxView.clicks(btnLogin)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> {
                    //start login activity
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                });
        RxView.clicks(btnRegister)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    //start register activity
                    Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    startActivity(intent);
                });
        RxBus.getInstance().onEvent(DestroySplashEvent.class)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    finish();
                });
    }

    @Override
    public void initData() {
        SPUtils sp = new SPUtils(this, Constant.SP_USER);
        if (sp.getString(Constant.SP_USER_ACCOUNT_KEY) != null && sp.getString(Constant.SP_USER_PWD_KEY) != null) {
            //do login
            LoginEntityRequest request = new LoginEntityRequest();
            request.setUsername(sp.getString(Constant.SP_USER_ACCOUNT_KEY));
            request.setPassword(sp.getString(Constant.SP_USER_PWD_KEY));
            LeanCloudRequest.doLogin(request)
                    .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribe(response -> {
                        ImApplication.lcToken = response.getSessionToken();
                        ImApplication.userNickName = response.getNickname();
                        //不支持客户端直接获取token...
//                        doGetToken(response);
                        connect(Constant.TEST_TOKEN);
                    }, new LeanCloudExceptionEngine() {
                        @Override
                        public void call(LeanCloudException entity) {
                            layoutBtn.setVisibility(View.VISIBLE);
                        }
                    });
        } else {
            layoutBtn.setVisibility(View.VISIBLE);
        }
    }

    private void doGetToken(LoginEntityResponse response) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", response.getUsername());
        params.put("name", response.getNickname());
        RongCloudRequest.doGetToken(params)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(response1 -> {
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
                }, new RongCloudExceptionEngine() {
                    @Override
                    public void call(RongCloudException entity) {
                        layoutBtn.setVisibility(View.VISIBLE);
                    }
                });
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {

        /**
         * IMKit SDK调用第二步,建立与服务器的连接
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            @Override
            public void onTokenIncorrect() {
                showDialog("Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(String userid) {
                Log.d("LoginActivity", "--onSuccess---" + userid);
                ImApplication.isUserLogin = true;
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
                Map<String, Boolean> supportedConversation = new HashMap<String, Boolean>();
                supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
                RongIM.getInstance().startConversationList(SplashActivity.this, supportedConversation);
                finish();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                showDialog("连接融云失败 error code:" + errorCode);
            }
        });
    }

}
