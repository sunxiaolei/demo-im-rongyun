package sunxl8.rongyun_im.entity;

/**
 * Created by sunxl8 on 2016/12/23.
 */

public class GetTokenEntityResponse {

    private String userId;

    private String code;

    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
