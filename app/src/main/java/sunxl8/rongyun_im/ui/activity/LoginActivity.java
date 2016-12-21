package sunxl8.rongyun_im.ui.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseActivity;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class LoginActivity extends ImBaseActivity {

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
        RxView.clicks(btnLogin)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    //success
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                });

    }

    @Override
    protected void initData() {
    }

}