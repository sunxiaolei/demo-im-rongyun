package sunxl8.rongyun_im.ui.activity;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.design.widget.RxTabLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;

import butterknife.BindView;
import io.rong.imkit.fragment.ConversationListFragment;
import sunxl8.android_lib.utils.RxBus;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseActivity;
import sunxl8.rongyun_im.event.DestroyMainEvent;
import sunxl8.rongyun_im.ui.fragment.ContactFragment;
import sunxl8.rongyun_im.ui.fragment.MineFragment;
import sunxl8.rongyun_im.widget.AddPopupWindow;

public class MainActivity extends ImBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_toolbar_icon)
    ImageView toolbarPlus;

    @BindView(R.id.tab_main_navigation)
    TabLayout tabNavigation;
    @BindView(R.id.layout_main_container)
    FrameLayout layoutContainer;

    private FragmentManager fragmentManager;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        RxView.clicks(toolbarPlus)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    showPopupwindow();
                });
        tabNavigation.setTabMode(TabLayout.MODE_FIXED);
        String[] titles = {"会话", "联系人", "发现", "我"};
        int[] icons = {R.drawable.ic_navigation_conversation, R.drawable.ic_navigation_contact,
                R.drawable.ic_navigation_find, R.drawable.ic_navigation_mine};
        int[] selectedIcons = {R.drawable.ic_navigation_conversation_selected, R.drawable.ic_navigation_contact_selected,
                R.drawable.ic_navigation_find_selected, R.drawable.ic_navigation_mine_selected};
        for (int i = 0; i < titles.length; i++) {
            tabNavigation.addTab(tabNavigation.newTab().setText(titles[i]).setIcon(getResources().getDrawable(icons[i])));
        }
        fragmentManager = getSupportFragmentManager();
        RxTabLayout.selections(tabNavigation)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(tab -> {
                    for (int i = 0; i < titles.length; i++) {
                        tabNavigation.getTabAt(i).setIcon(getResources().getDrawable(icons[i]));
                    }
                    toolbarTitle.setText(titles[tab.getPosition()]);
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
        ConversationListFragment conversationFragment = null;
        ContactFragment contactFragment = null;
        MineFragment mineFragment = null;
        switch (position) {
            case 0:
                toolbarPlus.setVisibility(View.VISIBLE);
                return conversationFragment == null ? new ConversationListFragment() : conversationFragment;
            case 1:
                toolbarPlus.setVisibility(View.VISIBLE);
                return contactFragment == null ? new ContactFragment() : contactFragment;
            case 2:
                toolbarPlus.setVisibility(View.GONE);
                return contactFragment == null ? new ContactFragment() : contactFragment;
            case 3:
                toolbarPlus.setVisibility(View.GONE);
                return mineFragment == null ? new MineFragment() : mineFragment;
        }
        return null;
    }

    private void showPopupwindow() {
        AddPopupWindow popupWindow = new AddPopupWindow(this);
        popupWindow.showAsDropDown(toolbar, 0, 0, Gravity.RIGHT);
    }

}
