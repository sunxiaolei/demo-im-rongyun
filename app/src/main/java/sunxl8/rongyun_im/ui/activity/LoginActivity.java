package sunxl8.rongyun_im.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import io.rong.imlib.RongIMClient;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RegexUtils;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.android_lib.utils.SPUtils;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImApplication;
import sunxl8.rongyun_im.base.ImBaseSwipeBackActivity;
import sunxl8.rongyun_im.entity.LeanCloudException;
import sunxl8.rongyun_im.entity.LoginEntityRequest;
import sunxl8.rongyun_im.entity.LoginEntityResponse;
import sunxl8.rongyun_im.event.DestroySplashEvent;
import sunxl8.rongyun_im.network.LeanCloudExceptionEngine;
import sunxl8.rongyun_im.network.LeanCloudRequest;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class LoginActivity extends ImBaseSwipeBackActivity {

    @BindView(R.id.et_login_account)
    EditText etAccount;
    @BindView(R.id.et_login_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        RxTextView.afterTextChangeEvents(etAccount)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    btnLogin.setEnabled(isBtnEnalbed());
                });
        RxTextView.afterTextChangeEvents(etPassword)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    btnLogin.setEnabled(isBtnEnalbed());
                });
        RxView.clicks(btnLogin)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    doLogin(etAccount.getText().toString(), etPassword.getText().toString());
                });

    }

    private boolean isBtnEnalbed() {
        boolean isEnabled = !TextUtils.isEmpty(etAccount.getText().toString())
                && !TextUtils.isEmpty(etPassword.getText().toString());
        if (isEnabled) {
            btnLogin.setBackground(getResources().getDrawable(R.drawable.selecter_btn_blue));
        } else {
            btnLogin.setBackground(getResources().getDrawable(android.R.color.darker_gray));
        }
        return isEnabled;
    }

    @Override
    protected void initData() {
    }

    private void doLogin(String account, String password) {
        if (!RegexUtils.isMobileSimple(etAccount.getText().toString())) {
            showToast("请输入正确手机号码");
            return;
        }
        showLoading();
        LoginEntityRequest request = new LoginEntityRequest();
        request.setUsername(account);
        request.setPassword(password);
        LeanCloudRequest.doLogin(request)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(response -> {
                    dismissDialog();
                    SPUtils sp = new SPUtils(this, Constant.SP_USER);
                    sp.putString(Constant.SP_USER_ACCOUNT_KEY, account);
                    sp.putString(Constant.SP_USER_PWD_KEY, password);
                    sp.putString(Constant.SP_USER_NICKNAME_KEY, response.getNickname());
                    ImApplication.lcToken = response.getSessionToken();
                    ImApplication.userNickName = response.getNickname();
                    connect(Constant.TEST_TOKEN);
                }, new LeanCloudExceptionEngine() {
                    @Override
                    public void call(LeanCloudException entity) {
                        dismissDialog();
                        showDialog(entity.getError());
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
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                RxBus.getInstance().post(new DestroySplashEvent());
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