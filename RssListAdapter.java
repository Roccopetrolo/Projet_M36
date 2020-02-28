package fr.univ_artois.rtbethune;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RssListAdapter extends BaseAdapter {
    private List<RssItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public RssListAdapter(Context aContext,  List<RssItem> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();

            holder.rsstitle = (TextView) convertView.findViewById(R.id.textView_rsstitle);
            holder.rsstext = (TextView) convertView.findViewById(R.id.textView_rsstext);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RssItem item = this.listData.get(position);
        holder.rsstitle.setText(item.title);
        holder.rsstext.setText(Html.fromHtml(item.text));
        Log.i("RssItem",  holder.rsstitle.getText().toString());

        return convertView;
    }


    static class ViewHolder {
        TextView rsstitle;
        TextView rsstext;
    }
}

