package sunxl8.rongyun_im.network;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.entity.RegisterEntityRequest;
import sunxl8.rongyun_im.entity.RegisterEntityResponse;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public interface LeanCloudApi {

    @Headers({"X-LC-Id:" + Constant.LEAN_CLOUD_ID,
            "X-LC-Key:" + Constant.LEAN_CLOUD_KEY,
            "Content-Type:application/json"})
    @POST("users")
    Observable<RegisterEntityResponse> doRegister(@Body RegisterEntityRequest register);
}