package sunxl8.rongyun_im.base;

import android.view.View;

import butterknife.ButterKnife;
import sunxl8.android_lib.base.BaseActivity;
import sunxl8.android_lib.base.BaseSwipeBackActivity;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class ImBaseSwipeBackActivity extends BaseSwipeBackActivity {

    @Override
    protected void init() {
        ButterKnife.bind(this);
    }

}
