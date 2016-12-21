package sunxl8.rongyun_im.ui.activity;

import android.widget.EditText;

import butterknife.BindView;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseActivity;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class RegisterActivity extends ImBaseActivity {

    @BindView(R.id.et_register_account)
    EditText etAccount;
    @BindView(R.id.et_register_password)
    EditText etPassword;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
