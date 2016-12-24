package sunxl8.rongyun_im.network;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;
import sunxl8.rongyun_im.entity.LeanCloudException;
import sunxl8.rongyun_im.entity.RongCloudException;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public abstract class RongCloudExceptionEngine implements Action1<Throwable> {

    public void call(Throwable throwable) {
        call(handleException(throwable));
    }

    public abstract void call(RongCloudException entity);

    private RongCloudException handleException(Throwable throwable) {
        RongCloudException entity = null;
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            try {
                String body = exception.response().errorBody().string();
                entity = JSON.parseObject(body, RongCloudException.class);
            } catch (IOException e) {
                entity = new RongCloudException();
                entity.setCode(10086);
                entity.setErrorMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return entity;
    }
}
