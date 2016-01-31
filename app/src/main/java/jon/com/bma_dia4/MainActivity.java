package jon.com.bma_dia4;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jon on 28/01/16. Working with Statics Fragments
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // BottomFragment fragment = new BottomFragment();
        String title = getResources().getString(R.string.fragment_title);

        // Es una funcio estatica, per tant no cal fer 'new BottomFragment() ...)
        BottomFragment fragment = BottomFragment.newInstance(title);

        // Gestor per posar i treure fragments
        FragmentManager fm = getSupportFragmentManager();

        BottomFragment fragmentBottom = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.textView2);

        // Comencem una transacció
        FragmentTransaction ft = fm.beginTransaction();
        // Reemplacem un per l'altre
        ft.replace(R.id.fragment_container, fragment);
        // Executem canvis
        ft.commit();
    }
}

