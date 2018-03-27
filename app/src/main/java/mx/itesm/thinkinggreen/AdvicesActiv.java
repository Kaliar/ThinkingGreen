package mx.itesm.thinkinggreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class AdvicesActiv extends AppCompatActivity {

    private TextView tvMessage;
    private ImageButton btnAdvices;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_weekly_advice:
                    tvMessage.setText("Consejo Semanal:");
                    loadWeekAdviceFrag();
                    return true;

                case R.id.menu_advice_list:
                    tvMessage.setText("Lista de Consejos:");
                    loadAdvicesListFrag();
                    return true;

                case R.id.menu_advices_preferences:
                    tvMessage.setText("Preferencias de Consejos:");
                    loadAdvicePreferenceFrag();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advices);

        tvMessage = (TextView) findViewById(R.id.tvMessageAdvices);
        btnAdvices = findViewById(R.id.btnAdvices);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    // TODO: Load Fragment of Advices Preferences
    private void loadAdvicePreferenceFrag() {

    }

    // TODO: Load Fragment of Advices Lists
    private void loadAdvicesListFrag() {

    }

    // TODO: Load Fragment of Weekly Advice
    private void loadWeekAdviceFrag() {


    }

}
