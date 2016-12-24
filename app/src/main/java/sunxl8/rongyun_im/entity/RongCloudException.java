package sunxl8.rongyun_im.entity;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class RongCloudException {


    /**
     * url : /user/getToken.json?name=gm&userId=13312345678
     * code : 1003
     * errorMessage : Your post is illegal!
     */

    private String url;
    private int code;
    private String errorMessage;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
