package mx.itesm.thinkinggreen;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateUserActiv extends AppCompatActivity {
    private TextView tvMessage;
    private ImageView imgSignUp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_add_person:
                    tvMessage.setText("Persona");
                    loadPersonFrag();
                    return true;

                case R.id.menu_add_institution:
                    tvMessage.setText("Institución");
                    loadInstitutionFrag();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        tvMessage = (TextView) findViewById(R.id.tvSignUp);
        imgSignUp = findViewById(R.id.imgSignUp);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    // TODO: Load Add User Fragment
    private void loadPersonFrag() {

    }

    // TODO: Load Add Institution Fragment
    private void loadInstitutionFrag() {

    }

}
