package sunxl8.rongyun_im.entity;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class RegisterEntityRequest {

    /**
     * nickname : jack
     * username : 18612340000
     * password : f32@ds*@&dsa
     * phone : 18612340000
     */

    private String nickname;
    private String username;
    private String password;
    private String phone;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
