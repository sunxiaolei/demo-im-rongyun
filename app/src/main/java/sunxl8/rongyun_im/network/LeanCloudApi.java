package sunxl8.rongyun_im.network;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import sunxl8.rongyun_im.Constant;
import sunxl8.rongyun_im.entity.CheckUserEntityResponse;
import sunxl8.rongyun_im.entity.LoginEntityRequest;
import sunxl8.rongyun_im.entity.LoginEntityResponse;
import sunxl8.rongyun_im.entity.RegisterEntityRequest;
import sunxl8.rongyun_im.entity.RegisterEntityResponse;

import static android.R.attr.path;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public interface LeanCloudApi {

    ///////////////////////////////////////////////////////////////////////////
    // 注册
    ///////////////////////////////////////////////////////////////////////////
    @Headers({"X-LC-Id:" + Constant.LEAN_CLOUD_ID,
            "X-LC-Key:" + Constant.LEAN_CLOUD_KEY,
            "Content-Type:application/json"})
    @POST("users")
    Observable<RegisterEntityResponse> doRegister(@Body RegisterEntityRequest register);

    ///////////////////////////////////////////////////////////////////////////
    // 登录
    ///////////////////////////////////////////////////////////////////////////
    @Headers({"X-LC-Id:" + Constant.LEAN_CLOUD_ID,
            "X-LC-Key:" + Constant.LEAN_CLOUD_KEY,
            "Content-Type:application/json"})
    @POST("login")
    Observable<LoginEntityResponse> doLogin(@Body LoginEntityRequest login);

    ///////////////////////////////////////////////////////////////////////////
    // 查询用户
    ///////////////////////////////////////////////////////////////////////////
    @Headers({"X-LC-Id:" + Constant.LEAN_CLOUD_ID,
            "X-LC-Key:" + Constant.LEAN_CLOUD_KEY,
            "Content-Type:application/json"})
    @GET("users")
    Observable<CheckUserEntityResponse> doCheckUser(@QueryMap Map<String, String> map);


}
