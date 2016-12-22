package sunxl8.rongyun_im.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;
import sunxl8.rongyun_im.ui.activity.MainActivity;

/**
 * Created by sunxl8 on 2016/12/22.
 */

public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPushReceiver";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (nm == null) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Logger.t(TAG).d("JPush用户注册成功");
            Logger.t(TAG).d(bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID));
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Logger.t(TAG).d("接受到推送下来的自定义消息");
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
            String file = bundle.getString(JPushInterface.EXTRA_RICHPUSH_FILE_PATH);
            String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);
            Logger.t(TAG).d("title->" + title + "\r\n"
                    + "message->" + message + "\r\n"
                    + "extras->" + extras + "\r\n"
                    + "type->" + type + "\r\n"
                    + "msgId->" + msgId + "\r\n"
                    + "file->" + file);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Logger.d("接受到推送下来的通知");
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
            String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);
            String fileStr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);
            String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
            Logger.t(TAG).d("title->" + title + "\r\n"
                    + "content->" + content + "\r\n"
                    + "extras->" + extras + "\r\n"
                    + "notificationId->" + notificationId + "\r\n"
                    + "type->" + type + "\r\n"
                    + "fileHtml->" + fileHtml + "\r\n"
                    + "fileStr->" + fileStr + "\r\n"
                    + "file->" + file);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Logger.t(TAG).d("用户点击打开了通知");
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            String type = bundle.getString(JPushInterface.EXTRA_EXTRA);
            int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
            Logger.t(TAG).d("title->" + title + "\r\n"
                    + "content->" + content + "\r\n"
                    + "type->" + type + "\r\n"
                    + "notificationId->" + notificationId + "\r\n"
                    + "file->" + file);

            Intent i = new Intent(context, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Logger.t(TAG).d("Unhandled intent - " + intent.getAction());
        }
    }
}
