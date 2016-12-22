package sunxl8.rongyun_im.base;

import com.avos.avoscloud.AVOSCloud;

import sunxl8.android_lib.base.BaseApplication;
import sunxl8.rongyun_im.Constant;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class ImApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, Constant.LEAN_CLOUD_ID, Constant.LEAN_CLOUD_KEY);
    }
}
