package sunxl8.rongyun_im.ui.activity;

import android.widget.Switch;

import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextSwitcher;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import rx.functions.Action1;
import sunxl8.android_lib.utils.SPUtils;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseSwipeBackActivity;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class SetActivity extends ImBaseSwipeBackActivity {

    @BindView(R.id.switch_set_message)
    Switch switchMessage;

    private SPUtils sp;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        sp = new SPUtils(this, Constant.SP_SET);
        boolean status = sp.getBoolean(Constant.SP_SET_MESSAGE_KEY, true);
        switchMessage.setChecked(status);
        RxCompoundButton.checkedChanges(switchMessage)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aBoolean -> {
                    switchMessage.setChecked(aBoolean);
                    if (aBoolean) {
                        JPushInterface.resumePush(this);
                    } else {
                        JPushInterface.stopPush(this);
                    }
                    sp.putBoolean(Constant.SP_SET_MESSAGE_KEY, aBoolean);
                });
    }

    @Override
    protected void initData() {

    }
}
