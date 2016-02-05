package bma.android.projectefinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jon on 05/02/16.
 */
public class InitialFragment extends Fragment {

    public static InitialFragment newInstance() {
        Bundle args = new Bundle();
        InitialFragment fragment = new InitialFragment();
//        Log.d("InitialFragment","NEW INSTANCE");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.initial_fragment, container, false);
//        Log.d("InitialFragment","NEW INSTANCE");
        return view;
    }
}
