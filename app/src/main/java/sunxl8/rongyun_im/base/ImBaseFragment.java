package sunxl8.rongyun_im.base;

import android.view.View;
import butterknife.ButterKnife;
import sunxl8.android_lib.base.BaseActivity;
import sunxl8.android_lib.base.BaseFragment;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public abstract class ImBaseFragment extends BaseFragment {

    protected BaseActivity mActivity;

    @Override
    protected void init(View view) {
        ButterKnife.bind(this, view);
        mActivity = (BaseActivity) getActivity();
    }

}
