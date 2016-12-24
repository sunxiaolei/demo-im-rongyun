package sunxl8.rongyun_im.base;

import com.avos.avoscloud.AVOSCloud;

import cn.jpush.android.api.JPushInterface;
import io.rong.imlib.RongIMClient;
import sunxl8.android_lib.base.BaseApplication;
import sunxl8.android_lib.utils.AndroidUtils;
import sunxl8.rongyun_im.Constant;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class ImApplication extends BaseApplication {

    public static boolean isUserLogin = false;
    public static String userNickName;
    public static String lcToken;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, Constant.LEAN_CLOUD_ID, Constant.LEAN_CLOUD_KEY);
        //JPush
        JPushInterface.init(this);
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(AndroidUtils.getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(AndroidUtils.getCurProcessName(getApplicationContext()))) {
            RongIMClient.init(this);
        }
    }
}
