package mx.itesm.thinkinggreen.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Fragments.CreateInstitutionFrag;
import mx.itesm.thinkinggreen.Fragments.CreatePersonFrag;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class CreateUserActiv extends AppCompatActivity {
    private TextView tvMessage;
    private ImageView imgSignUp;

    // Detect which menu is selected
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            imgSignUp.setVisibility(View.INVISIBLE); // Hide Image
            tvMessage.setText(getString(R.string.strInstSingUp)); // Set Instructions string

            switch (item.getItemId()) {
                case R.id.menu_add_person: // Person Selected
                    loadPersonFrag();
                    return true;

                case R.id.menu_add_institution: // Institute Selected
                    loadInstitutionFrag();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        tvMessage = findViewById(R.id.tvSignUp);
        imgSignUp = findViewById(R.id.imgSignUp);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    // TODO: Load Add User Fragment
    private void loadPersonFrag() {
        CreatePersonFrag fragPer = new CreatePersonFrag(); // Fragment of a Person
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameSignUp, fragPer); // Set the PersonFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread
    }

    // TODO: Load Add Institution Fragment
    private void loadInstitutionFrag() {
        CreateInstitutionFrag fragInst = new CreateInstitutionFrag(); // Fragment of a Institution
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameSignUp, fragInst); // Set the InstitutionFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread
    }
}