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
import sunxl8.rongyun_im.ui.fragment.ContactFragment;
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
        int[] icons = {R.drawable.ic_navigation_conversation, R.drawable.ic_navigation_contact,
                R.drawable.ic_navigation_find, R.drawable.ic_navigation_mine};
        int[] selectedIcons = {R.drawable.ic_navigation_conversation_selected, R.drawable.ic_navigation_contact_selected,
                R.drawable.ic_navigation_find_selected, R.drawable.ic_navigation_mine_selected};
        for (int i = 0; i < titles.length; i++) {
            tabNavigation.addTab(tabNavigation.newTab().setText(titles[i]).setIcon(getResources().getDrawable(icons[i])));
        }
        fragmentManager = getFragmentManager();
        RxTabLayout.selections(tabNavigation)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(tab -> {
                    for (int i = 0; i < titles.length; i++) {
                        tabNavigation.getTabAt(i).setIcon(getResources().getDrawable(icons[i]));
                    }
                    tab.setIcon(getResources().getDrawable(selectedIcons[tab.getPosition()]));
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
        ContactFragment contactFragment = null;
        MineFragment mineFragment = null;
        switch (position) {
            case 0:
                return contactFragment == null ? new ContactFragment() : contactFragment;
            case 1:
                return contactFragment == null ? new ContactFragment() : contactFragment;
            case 2:
                return contactFragment == null ? new ContactFragment() : contactFragment;
            case 3:
                return mineFragment == null ? new MineFragment() : mineFragment;
        }
        return null;
    }
}
