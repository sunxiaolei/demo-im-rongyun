package sunxl8.rongyun_im.base;

import android.widget.Toast;

import butterknife.ButterKnife;
import sunxl8.android_lib.base.BaseActivity;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class ImBaseActivity extends BaseActivity {

    @Override
    protected void init() {
        ButterKnife.bind(this);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
