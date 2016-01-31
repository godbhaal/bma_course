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
public class BottomFragment extends Fragment {

    // Posar els arguments com atributs per evitar problemes
    private final static String ARG_TITLE="argTitle";

    // Truc per trobar rapid a tot el codi si ha patit alguna modificació (si un dia decidim que li volem passar més parametres, per ex)
    public static BottomFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        BottomFragment fragment = new BottomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.textView2);

        // Comprova que no estigui buit, i que haguin arribat <-- SEMPRE
        if(getArguments()!=null && getArguments().containsKey(ARG_TITLE)) {
//            textView.setText(getArguments().getString(ARG_TITLE));
            // si externitzem la funcio la podrem cridar desde l'activity.
            setText(getArguments().getString(ARG_TITLE));
        }

        return view;
    }

    public void setText(String text){
        textView.setText(text);
    }
}
