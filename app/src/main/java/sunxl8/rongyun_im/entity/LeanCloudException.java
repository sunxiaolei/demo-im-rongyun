package sunxl8.rongyun_im.entity;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class LeanCloudException {

    /**
     * code : 202
     * error : Username has already been taken
     */

    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
