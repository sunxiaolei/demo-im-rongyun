package sunxl8.rongyun_im.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.android_lib.utils.SPUtils;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseActivity;
import sunxl8.rongyun_im.event.DestroySplashEvent;

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
        if (sp.getString(Constant.SP_USER_ACCOUNT_KEY) != null) {
            //do login
        } else {
            layoutBtn.setVisibility(View.VISIBLE);
        }
    }

}
