package sunxl8.rongyun_im.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sunxl8.android_lib.base.BaseActivity;
import sunxl8.android_lib.base.BaseFragment;
import sunxl8.rongyun_im.R;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public abstract class ImBaseFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    protected TextView toolbarTitle;
    @BindView(R.id.iv_toolbar_icon)
    protected ImageView toolbarPlus;

    protected BaseActivity mActivity;

    @Override
    protected void init(View view) {
        ButterKnife.bind(this, view);
        mActivity = (BaseActivity) getActivity();
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}
