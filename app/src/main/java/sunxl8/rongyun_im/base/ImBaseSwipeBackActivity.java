package sunxl8.rongyun_im.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import sunxl8.android_lib.base.BaseActivity;
import sunxl8.android_lib.base.BaseSwipeBackActivity;
import sunxl8.rongyun_im.R;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class ImBaseSwipeBackActivity extends BaseSwipeBackActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @Nullable
    @BindView(R.id.tv_toolbar_title)
    protected TextView toolbarTitle;
    @Nullable
    @BindView(R.id.iv_toolbar_icon)
    protected ImageView toolbarPlus;

    @Override
    protected void init() {
        ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

}
