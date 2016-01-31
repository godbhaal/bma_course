package jon.com.bma_dia4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jon on 28/01/16.
 */
public class TopFragment extends Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Crear un view desde un layout. Container es el 'pare'
        View view = inflater.inflate(R.layout.top_fragment, container, false);
        // Aquí podem extreure els recursos desde el view asociat
        textView = (TextView) view.findViewById(R.id.textView);



        return view;
    }
}
