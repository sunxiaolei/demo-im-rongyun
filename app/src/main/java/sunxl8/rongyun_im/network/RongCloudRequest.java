package sunxl8.rongyun_im.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rx.Observable;
import sunxl8.android_lib.network.NetworkManager;
import sunxl8.android_lib.network.SchedulersCompat;
import sunxl8.android_lib.utils.EncryptUtils;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.entity.GetTokenEntityResponse;

/**
 * Created by sunxl8 on 2016/12/23.
 */

public class RongCloudRequest {

    /**
     * 获取token
     *
     * @param params
     * @return
     */
    public static Observable<GetTokenEntityResponse> doGetToken(Map<String, String> params) {
        return NetworkManager.getCommonClient(Constant.BASE_URL_RONGCLOUD, getRongCloudHeaders())
                .create(RongCloudApi.class)
                .doGetToken(params)
                .compose(SchedulersCompat.applyIoSchedulers());
    }

    private static Map<String, String> getRongCloudHeaders() {
        Map<String, String> headers = new HashMap<>();
        Random random = new Random(1000);
        String nonce = random.nextInt() * 666 + "";
        String timeStamp = System.currentTimeMillis() + "";
        String signature = EncryptUtils.encryptSHA1ToString(Constant.RONG_CLOUD_SECRET + nonce + timeStamp);
        headers.put("App-Key", Constant.RONG_CLOUD_KEY);
        headers.put("Nonce", nonce);
        headers.put("Timestamp", timeStamp);
        headers.put("Signature", signature);
        return headers;
    }

}
