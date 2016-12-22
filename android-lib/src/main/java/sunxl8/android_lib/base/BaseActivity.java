package sunxl8.android_lib.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.RxActivity;


/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class BaseActivity extends RxActivity {

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
        init();
        initView();
        initData();
    }

    protected abstract int setContentViewId();

    protected abstract void init();

    protected abstract void initView();

    protected abstract void initData();

}
