package sunxl8.rongyun_im.ui.activity;

import android.view.View;

import sunxl8.android_lib.base.BaseSwipeBackActivity;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseSwipeBackActivity;

/**
 * Created by sunxl8 on 2016/12/27.
 */

public class ConversationActivity extends ImBaseSwipeBackActivity {

    @Override
    protected int setContentViewId() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void initView() {
        toolbarTitle.setText("聊天");
    }

    @Override
    protected void initData() {

    }
}
