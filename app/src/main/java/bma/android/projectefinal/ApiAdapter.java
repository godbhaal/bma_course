package bma.android.projectefinal;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * Created by jon on 04/02/16.
 */
public class ApiAdapter implements SpinnerAdapter {

    private final Context context;
    private final int resId;
    private final String[] titles;

    public ApiAdapter(Context context, String[] titles) {
        this.context = context;
        this.resId = R.layout.my_spinner;
        this.titles = titles;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resId, parent, false);
        TextView tv_title = (TextView) view.findViewById(R.id.textView_spinner);
        tv_title.setText(titles[position]);

        return view;
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
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

