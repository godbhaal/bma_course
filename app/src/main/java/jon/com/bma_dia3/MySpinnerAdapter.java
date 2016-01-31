package jon.com.bma_dia3;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * Created by jon on 27/01/16. Using spinners: Adapters and Lists
 */
public class MySpinnerAdapter implements SpinnerAdapter {

    private final Context context;
    private final int resId;
    private final String[] titles, headers;

    public MySpinnerAdapter(Context context) {
        this.context = context;
        this.resId = R.layout.my_spinner;
        this.titles = new String[]{"title1", "title2", "title3", "title4", "title5", "title6", "title7", "title8", "title9", "title10"};
        this.headers = new String[]{"header1", "header2", "header3", "header4", "header5", "header6", "header7", "header8", "header9", "header10"};
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

        // indicar parent pq pugui calcular els tamanys  bé
        View view = inflater.inflate(resId, parent, false);

        TextView tv_title = (TextView) view.findViewById(R.id.textView);
        TextView tv_header = (TextView) view.findViewById(R.id.textView2);
        tv_title.setText(titles[position]);
        tv_header.setText(headers[position]);

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

    // Normalment es crea sola retornant un 0, cambiarho a 1 (sinó dona error)
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    // El seguent es crea per defecte
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