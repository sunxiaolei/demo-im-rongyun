package sunxl8.android_lib.network;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class NetworkManager {
    private static Retrofit commonClient;

    public static Retrofit getCommonClient() {
        if (commonClient == null) {
            commonClient = new Retrofit.Builder()
                    .baseUrl(NetworkConstant.URL_BASE)
                    .client(getHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return commonClient;
    }

    private static OkHttpClient getHttpClient() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Logger.d("Request==>" + request.toString());
                long t1 = System.nanoTime();
                okhttp3.Response response = chain.proceed(chain.request());
                long t2 = System.nanoTime();
                Logger.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers(), response.code()));
                okhttp3.MediaType mediaType = response.body().contentType();
                String content = response.body().string();
                Logger.d("Response==>" + content);
                return response.newBuilder()
                        .body(okhttp3.ResponseBody.create(mediaType, content))
                        .build();
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(NetworkConstant.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(NetworkConstant.HTTP_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(NetworkConstant.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);

        builder.addInterceptor(interceptor);

        return builder.build();
    }

}