package sunxl8.rongyun_im.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextSwitcher;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.android_lib.utils.SPUtils;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseSwipeBackActivity;
import sunxl8.rongyun_im.event.DestroyMainEvent;
import sunxl8.rongyun_im.event.DestroySplashEvent;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class SetActivity extends ImBaseSwipeBackActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.switch_set_message)
    Switch switchMessage;
    @BindView(R.id.layout_set_logout)
    RelativeLayout layoutLogout;

    private SPUtils sp;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle.setText("设置");
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
        RxView.clicks(layoutLogout)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    SPUtils sp = new SPUtils(this, Constant.SP_USER);
                    sp.clear();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    RxBus.getInstance().post(new DestroyMainEvent());
                    finish();
                });
    }

    @Override
    protected void initData() {

    }
}
