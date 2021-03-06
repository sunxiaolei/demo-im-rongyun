package sunxl8.rongyun_im.ui.fragment;

import android.content.Intent;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.FragmentEvent;

import butterknife.BindView;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseFragment;
import sunxl8.rongyun_im.ui.activity.SetActivity;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class MineFragment extends ImBaseFragment {


    @BindView(R.id.layout_mine_set)
    RelativeLayout layoutSet;

    @Override
    public int setContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

        RxView.clicks(layoutSet)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(aVoid -> {
                    Intent intent = new Intent(getActivity(), SetActivity.class);
                    startActivity(intent);
                });
    }

    @Override
    protected void initData() {

    }

}
