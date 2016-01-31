package jon.com.bma_dia3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by jon on 27/01/16. Using ListView
 */
public class MyListViewAdapter extends BaseAdapter{

    private final Context context;
    private final int resId;
    private final String[] titles, headers;

    public MyListViewAdapter(Context context){
        this.context = context;
        this.resId = R.layout.my_spinner;
        this.titles = new String[]{"title1", "title2", "title3", "title4", "title5", "title6", "title7", "title8", "title9", "title10"};
        this.headers = new String[]{"header1", "header2", "header3", "header4", "header5", "header6", "header7", "header8", "header9", "header10"};
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resId, parent, false);
        TextView tv_title = (TextView) view.findViewById(R.id.textView);
        TextView tv_header = (TextView) view.findViewById(R.id.textView2);
        tv_title.setText(titles[position]);
        tv_header.setText(headers[position]);
        return view;
    }
}
