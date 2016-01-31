package jon.com.bma_dia3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Created by jon on 27/01/16.  Adapters and Lists
 */
public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private int position;

//    private ListView listview;

//    private String[] myDataset = {"test1", "test2", "test3"};
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
//
//        // Spinner: desplegable
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MySpinnerAdapter(this));
//
//        // ListView: llista d'objectes View que es visualitzen tots
//        listview = (ListView) findViewById(R.id.listView);
//        listview.setAdapter(new MyListViewAdapter(this));
//
//        // RecyclerView
//        setContentView(R.layout.my_recycler_view);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);
    }
}
