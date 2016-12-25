package sunxl8.rongyun_im.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;


import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.ui.activity.AddContactActivity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2016/12/24.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class AddPopupWindow extends PopupWindow implements View.OnClickListener {

    private Context mContext;

    public AddPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_popupwindow, null);
        setOutsideTouchable(true);
        setFocusable(true);
        view.findViewById(R.id.layout_add_contact).setOnClickListener(this);

        setContentView(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_add_contact:
                mContext.startActivity(new Intent(mContext,AddContactActivity.class));
                break;
        }
    }
}
