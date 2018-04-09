package mx.itesm.thinkinggreen.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Fragments.AdviceListFrag;
import mx.itesm.thinkinggreen.Fragments.AdviceSettingsFrag;
import mx.itesm.thinkinggreen.Fragments.AdviceWebFrag;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;
import mx.itesm.thinkinggreen.Fragments.YoutubeFragment;

public class AdvicesActiv extends AppCompatActivity {

    private TextView tvMessage;
    private ImageButton btnAdvices;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            btnAdvices.setVisibility(View.INVISIBLE);
            tvMessage.setVisibility(View.INVISIBLE);
            switch (item.getItemId()) {
                case R.id.menu_weekly_advice:
                    tvMessage.setText("Consejo Semanal:");
                    //loadWeekAdviceFrag();
                    loadYoutubeFragment();
                    return true;

                case R.id.menu_advice_list:

                    tvMessage.setText("Lista de Consejos:");
                    loadAdvicesListFrag();
                    return true;

                case R.id.menu_advices_preferences:
                    tvMessage.setText("Preferencias de Consejos:");
                    loadAdviceSettingsFrag();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advices);

        tvMessage = findViewById(R.id.tvMessageAdvices);
        btnAdvices = findViewById(R.id.btnAdvices);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void loadAdviceSettingsFrag() {
        AdviceSettingsFrag fragSetAdv = new AdviceSettingsFrag(); // Fragment of the advices preferences
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameAdvices, fragSetAdv); // Set the AdvicePreference Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread

    }

    private void loadAdvicesListFrag() {
        AdviceListFrag fragListFrag = new AdviceListFrag(); // Fragment of the advices list
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameAdvices, fragListFrag); // Set the AdviceList Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread

    }

    // Web Frag
    private void loadWeekAdviceFrag() {
        AdviceWebFrag fragWeekAdvice = new AdviceWebFrag(); // Fragment of the advices of the week
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameAdvices, fragWeekAdvice); // Set the AdviceWeek Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread


    }

    //Video View
    private void  loadYoutubeFragment(){
        YoutubeFragment fragment = new YoutubeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frameAdvices, fragment)
                .addToBackStack(null)
                .commit();
    }


}
