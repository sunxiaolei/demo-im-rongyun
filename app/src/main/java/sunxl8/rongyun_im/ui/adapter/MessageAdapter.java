package sunxl8.rongyun_im.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sunxl8.rongyun_im.R;

/**
 * Created by sunxl8 on 2016/12/26.
 */

public class MessageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public MessageAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addData(List<String> list) {
        if (mList != null) {
            mList.addAll(list);
        } else {
            mList = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup group) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_message, group, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_item_message);
        tv.setText(mList.get(position));
        return view;
    }
}
