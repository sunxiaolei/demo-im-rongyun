package sunxl8.rongyun_im.ui.activity;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Subscriber;
import sunxl8.android_lib.utils.RegexUtils;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseSwipeBackActivity;
import sunxl8.rongyun_im.entity.RegisterEntityRequest;
import sunxl8.rongyun_im.entity.RegisterEntityResponse;
import sunxl8.rongyun_im.network.LeanCloudRequest;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class RegisterActivity extends ImBaseSwipeBackActivity {

    @BindView(R.id.et_register_nickname)
    EditText etNickname;
    @BindView(R.id.iv_register_headportrait)
    ImageView ivHearportrait;
    @BindView(R.id.et_register_account)
    EditText etAccount;
    @BindView(R.id.et_register_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    TextView btnRegister;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        RxTextView.afterTextChangeEvents(etNickname)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    btnRegister.setEnabled(isBtnEnalbed());
                });
        RxTextView.afterTextChangeEvents(etAccount)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    btnRegister.setEnabled(isBtnEnalbed());
                });
        RxTextView.afterTextChangeEvents(etPassword)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    btnRegister.setEnabled(isBtnEnalbed());
                });
        RxView.clicks(btnRegister)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    doRegister(etNickname.getText().toString(), etAccount.getText().toString(), etPassword.getText().toString());
                });
    }

    private boolean isBtnEnalbed() {
        boolean isEnabled = !TextUtils.isEmpty(etNickname.getText().toString())
                && !TextUtils.isEmpty(etAccount.getText().toString())
                && !TextUtils.isEmpty(etPassword.getText().toString());
        if (isEnabled) {
            btnRegister.setBackground(getResources().getDrawable(R.drawable.selecter_btn_blue));
        } else {
            btnRegister.setBackground(getResources().getDrawable(android.R.color.darker_gray));
        }
        return isEnabled;
    }

    private void doRegister(String nickname, String accountname, String password) {
        if (!RegexUtils.isMobileSimple(etAccount.getText().toString())) {
            showToast("请输入正确手机号码");
            return;
        }
        showLoading();
        RegisterEntityRequest entityRequest = new RegisterEntityRequest();
        entityRequest.setUsername(nickname);
        entityRequest.setPhone(accountname);
        entityRequest.setPassword(password);
        LeanCloudRequest.doRegister(entityRequest)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Subscriber<RegisterEntityResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissDialog();
                        showDialog(e.toString());
                    }

                    @Override
                    public void onNext(RegisterEntityResponse response) {
                        dismissDialog();
                        showDialog(response.toString());
                    }
                });
    }

    @Override
    protected void initData() {

    }
}
