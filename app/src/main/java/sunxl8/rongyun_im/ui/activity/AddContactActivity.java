package sunxl8.rongyun_im.ui.activity;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.android_lib.utils.RegexUtils;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.base.ImBaseSwipeBackActivity;
import sunxl8.rongyun_im.entity.CheckUserEntityResponse;
import sunxl8.rongyun_im.entity.LeanCloudException;
import sunxl8.rongyun_im.network.LeanCloudExceptionEngine;
import sunxl8.rongyun_im.network.LeanCloudRequest;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2016/12/25.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class AddContactActivity extends ImBaseSwipeBackActivity {

    @BindView(R.id.et_addcontact_account)
    EditText etAccount;
    @BindView(R.id.btn_addcontact)
    TextView btnAdd;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_addcontact;
    }

    @Override
    protected void initView() {
        toolbarTitle.setText("添加朋友");
        RxView.clicks(btnAdd)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    if (!RegexUtils.isMobileSimple(etAccount.getText().toString())) {
                        showToast("请输入正确手机号码");
                        return;
                    }
                    doAddContact(etAccount.getText().toString());
                });
    }

    @Override
    protected void initData() {

    }

    private void doAddContact(String account) {
        showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("where", "{username:" + account + "}");
        LeanCloudRequest.doCheckUser(map)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(checkUserEntityResponse -> {
                    dismissDialog();
                }, new LeanCloudExceptionEngine() {
                    @Override
                    public void call(LeanCloudException entity) {
                        dismissDialog();
                        showDialog(entity.getCode() + entity.getError());
                    }
                });
    }
}
