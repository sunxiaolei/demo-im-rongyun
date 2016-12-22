package sunxl8.rongyun_im.network;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;
import sunxl8.rongyun_im.entity.LeanCloudException;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public abstract class LeanCloudExceptionEngine implements Action1<Throwable> {

    public void call(Throwable throwable) {
        call(handleException(throwable));
    }

    public abstract void call(LeanCloudException entity);

    private LeanCloudException handleException(Throwable throwable) {
        LeanCloudException entity = null;
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            try {
                String body = exception.response().errorBody().string();
                entity = JSON.parseObject(body, LeanCloudException.class);
            } catch (IOException e) {
                entity = new LeanCloudException();
                entity.setError(e.getLocalizedMessage());
                entity.setCode(10086);
                e.printStackTrace();
            }
        }
        return entity;
    }
}
