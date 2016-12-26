package sunxl8.rongyun_im.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.FragmentEvent;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseFragment;
import sunxl8.rongyun_im.ui.activity.ChatActivity;
import sunxl8.rongyun_im.widget.AddPopupWindow;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2016/12/24.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class ContactFragment extends ImBaseFragment {

    @BindView(R.id.tv_test_contact)
    TextView tvContact;

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
        RxView.clicks(tvContact)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(mActivity, ChatActivity.class));
                    }
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
