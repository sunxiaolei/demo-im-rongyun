package sunxl8.rongyun_im.ui.activity;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;
import rx.functions.Action1;
import sunxl8.rongyun_im.R;
import sunxl8.rongyun_im.R2;
import sunxl8.rongyun_im.base.ImBaseSwipeBackActivity;
import sunxl8.rongyun_im.ui.adapter.MessageAdapter;

import static android.R.attr.targetId;
import static android.R.id.message;

/**
 * Created by sunxl8 on 2016/12/26.
 */

public class ChatActivity extends ImBaseSwipeBackActivity {

    @BindView(R.id.lv_chat_message)
    ListView lvMessage;
    @BindView(R.id.et_chat_content)
    EditText etContent;
    @BindView(R.id.btn_chat_send)
    TextView btnSend;

    private MessageAdapter mAdapter;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {
        mAdapter = new MessageAdapter(this);
        RxView.clicks(btnSend)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    sendMessage(etContent.getText().toString());
                });
    }

    @Override
    protected void initData() {
        getLastedMessage();
    }

    private void sendMessage(String message) {
        /**
         * 发送普通消息
         *
         * @param conversationType      会话类型
         * @param targetId              会话ID
         * @param content               消息的内容，一般是MessageContent的子类对象
         * @param pushContent           接收方离线时需要显示的push消息内容
         * @param pushData              接收方离线时需要在push消息中携带的非显示内容
         * @param SendMessageCallback   发送消息的回调
         * @param ResultCallback        消息存库的回调，可用于获取消息实体
         *
         */
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, "12212345678",
                TextMessage.obtain(message), null, null, new IRongCallback.ISendMessageCallback() {
                    @Override
                    public void onAttached(Message message) {
                        Logger.t("SEND MESSAGE").d("onAttached");
                    }

                    @Override
                    public void onSuccess(Message message) {
                        Logger.t("SEND MESSAGE").d("onSuccess");
                    }

                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode code) {
                        Logger.t("SEND MESSAGE").d("onError:" + code.getMessage());
                    }
                });
    }

    private void getLastedMessage() {
        /**
         * 根据会话类型的目标 Id，回调方式获取最新的 N 条消息实体。
         *
         * @param conversationType 会话类型。
         * @param targetId         目标 Id。根据不同的 conversationType，可能是用户 Id、讨论组 Id、群组 Id 或聊天室 Id。
         * @param count            要获取的消息数量。
         * @param callback         获取最新消息记录的回调，按照时间顺序从新到旧排列。
         */
        RongIMClient.getInstance().getLatestMessages(Conversation.ConversationType.PRIVATE, "12212345678", 20,
                new RongIMClient.ResultCallback<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> messages) {
                        Logger.t("GET MESSAGE").d("onSuccess:" + messages.size());
                        List<String> listData = new ArrayList<String>();
                        for (Message message : messages) {
                            if (message.getContent() instanceof TextMessage) {
                                listData.add(((TextMessage) message.getContent()).getContent());
                            }
                        }
                        mAdapter.setData(listData);
                        lvMessage.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode code) {
                        Logger.t("GET MESSAGE").d("onError:" + code.getMessage());
                    }
                });
    }

}
