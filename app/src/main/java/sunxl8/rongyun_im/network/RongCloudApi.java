package sunxl8.rongyun_im.network;


import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;
import sunxl8.rongyun_im.entity.GetTokenEntityResponse;

/**
 * Created by sunxl8 on 2016/12/23.
 */

public interface RongCloudApi {

    ///////////////////////////////////////////////////////////////////////////
    // 获取token
    ///////////////////////////////////////////////////////////////////////////
    @POST("user/getToken.json")
    @FormUrlEncoded
    Observable<GetTokenEntityResponse> doGetToken(@FieldMap Map<String, String> params);

}
