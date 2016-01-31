package jon.com.bma_dia3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jon on 27/01/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;

    // Aporta referencia als Views
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView5);
        }
    }

    // Constructor
    public MyAdapter(String[] myDataset){
        mDataset = myDataset;
    }

    // Crea nous Views
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        //modify textView
//        TextView tv = (TextView) v.findViewById(R.id.textView5);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace contents
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount(){
        return mDataset.length;
    }
}
