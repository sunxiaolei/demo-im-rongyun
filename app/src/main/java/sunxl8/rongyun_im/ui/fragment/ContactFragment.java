package sunxl8.rongyun_im.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.FragmentEvent;

import rx.functions.Action1;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseFragment;
import sunxl8.rongyun_im.widget.AddPopupWindow;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2016/12/24.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class ContactFragment extends ImBaseFragment {

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView() {
        toolbarTitle.setText("通讯录");
        toolbarPlus.setVisibility(View.VISIBLE);
        RxView.clicks(toolbarPlus)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(aVoid -> {
                    showPopupwindow();
                });
    }

    @Override
    protected void initData() {

    }

    private void showPopupwindow() {
        AddPopupWindow popupWindow = new AddPopupWindow(mActivity);
        popupWindow.showAsDropDown(toolbar, 0, 0, Gravity.RIGHT);
    }
}
