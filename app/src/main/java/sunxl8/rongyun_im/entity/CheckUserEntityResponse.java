package sunxl8.rongyun_im.entity;

import java.util.List;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2016/12/25.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class CheckUserEntityResponse {

    private List<LoginEntityResponse> result;

    public List<LoginEntityResponse> getResult() {
        return result;
    }

    public void setResult(List<LoginEntityResponse> result) {
        this.result = result;
    }
}
