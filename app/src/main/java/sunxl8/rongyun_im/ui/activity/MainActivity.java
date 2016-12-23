package sunxl8.rongyun_im.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.design.widget.RxTabLayout;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseActivity;
import sunxl8.rongyun_im.event.DestroyMainEvent;
import sunxl8.rongyun_im.ui.fragment.MineFragment;

public class MainActivity extends ImBaseActivity {

    @BindView(R.id.tab_main_navigation)
    TabLayout tabNavigation;
    @BindView(R.id.layout_main_container)
    FrameLayout layoutContainer;

    private android.app.FragmentManager fragmentManager;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tabNavigation.setTabMode(TabLayout.MODE_FIXED);
        String[] titles = {"会话", "联系人", "发现", "我"};
        for (int i = 0; i < titles.length; i++) {
            tabNavigation.addTab(tabNavigation.newTab().setText(titles[i]).setIcon(getResources().getDrawable(R.mipmap.ic_launcher)));
        }
        fragmentManager = getFragmentManager();
        RxTabLayout.selections(tabNavigation)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(tab -> {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    Fragment fragment = getFragment(tab.getPosition());
                    transaction.replace(R.id.layout_main_container, fragment);
                    transaction.commit();
                });
        RxBus.getInstance().onEvent(DestroyMainEvent.class)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(event -> {
                    finish();
                });
    }

    @Override
    protected void initData() {

    }

    private Fragment getFragment(int position) {
        return new MineFragment();

    }
}
