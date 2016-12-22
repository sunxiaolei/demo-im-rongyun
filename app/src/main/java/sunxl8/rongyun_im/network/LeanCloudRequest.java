package sunxl8.rongyun_im.network;

import rx.Observable;
import sunxl8.android_lib.network.NetworkManager;
import sunxl8.android_lib.network.SchedulersCompat;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.entity.LoginEntityRequest;
import sunxl8.rongyun_im.entity.LoginEntityResponse;
import sunxl8.rongyun_im.entity.RegisterEntityRequest;
import sunxl8.rongyun_im.entity.RegisterEntityResponse;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class LeanCloudRequest {

    /**
     * 注册
     *
     * @param request
     * @return
     */
    public static Observable<RegisterEntityResponse> doRegister(RegisterEntityRequest request) {
        return NetworkManager.getCommonClient(Constant.BASE_URL_LEANCLOUD)
                .create(LeanCloudApi.class)
                .doRegister(request)
                .compose(SchedulersCompat.applyIoSchedulers());
    }

    /**
     * 登录
     *
     * @param request
     * @return
     */
    public static Observable<LoginEntityResponse> doLogin(LoginEntityRequest request) {
        return NetworkManager.getCommonClient(Constant.BASE_URL_LEANCLOUD)
                .create(LeanCloudApi.class)
                .doLogin(request)
                .compose(SchedulersCompat.applyIoSchedulers());
    }
}
