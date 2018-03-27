package mx.itesm.thinkinggreen;


import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateUserActiv extends AppCompatActivity {
    private TextView tvMessage;
    private ImageView imgSignUp;
    private View fragPer;
    private View fragInst;

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
        //we get the fragment id for person sign up and make it invisible
        fragPer = findViewById(R.id.fragmentPer);
        fragPer.setVisibility(View.INVISIBLE);
        //get the fragment id for the institution sign up and make it invisible
        fragInst=findViewById(R.id.fragmentInst);
        fragInst.setVisibility(View.INVISIBLE);
    }


    // TODO: Load Add User Fragment
    private void loadPersonFrag() {
        //Change t
        imgSignUp.setVisibility(View.INVISIBLE);
        fragInst.setVisibility(View.INVISIBLE);
        if(fragPer.getVisibility()==View.INVISIBLE)
            fragPer.setVisibility(View.VISIBLE);

    }

    // TODO: Load Add Institution Fragment
    private void loadInstitutionFrag() {
        imgSignUp.setVisibility(View.INVISIBLE);
        fragPer.setVisibility(View.INVISIBLE);
        if(fragInst.getVisibility()==View.INVISIBLE)
            fragInst.setVisibility(View.VISIBLE);

    }

}
