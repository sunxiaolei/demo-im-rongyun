package sunxl8.rongyun_im.ui.activity;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.widget.FrameLayout;

import butterknife.BindView;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseActivity;

public class MainActivity extends ImBaseActivity {

    @BindView(R.id.tab_main_navigation)
    TabLayout tabNavigation;
    @BindView(R.id.layout_main_container)
    FrameLayout layoutContainer;

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
    }

    @Override
    protected void initData() {

    }
}
