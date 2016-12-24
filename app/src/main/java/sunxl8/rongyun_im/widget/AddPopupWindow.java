package sunxl8.rongyun_im.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import sunxl8.rongyun_im.R;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2016/12/24.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class AddPopupWindow extends PopupWindow {

    public AddPopupWindow(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_popupwindow, null);
        setOutsideTouchable(true);
        setFocusable(true);
        setContentView(view);
    }
}
