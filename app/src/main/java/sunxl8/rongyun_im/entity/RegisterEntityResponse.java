package sunxl8.rongyun_im.entity;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class RegisterEntityResponse {


    /**
     * sessionToken : qmdj8pdidnmyzp0c7yqil91oc
     * createdAt : 2015-07-14T02:31:50.100Z
     * objectId : 55a47496e4b05001a7732c5f
     */

    private String sessionToken;
    private String createdAt;
    private String objectId;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
