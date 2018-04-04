package mx.itesm.thinkinggreen.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Fragments.RestaurantsListFrag;
import mx.itesm.thinkinggreen.Fragments.RewardsCategoryItemListFrag;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.LogInCallback;

public class RewardsActiv extends AppCompatActivity {

    private RecyclerView rvRewards;
    private TextView tvDescription;
    private TextView tvUsrLeaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        ParseUser user = ParseUser.getCurrentUser();
        int points = (Integer) user.get("points");
        tvUsrLeaves = findViewById(R.id.tvLeaves);
        tvUsrLeaves.setText(""+points);

        RewardsCategoryItemListFrag fragRestList = RewardsCategoryItemListFrag.newInstance(true); // Fragment of a Person
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.frameRewards, fragRestList); // Set the PersonFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragTrans.commit(); // Schedule the operation into thread
        //Toast.makeText(this,"AAAAHHH",Toast.LENGTH_LONG).show();
    }



    /*switch(position) {
         case 0:
     *       alertDisplayer("¡Felicidades!","Código: " + getString(R.string.strDctEnsCode));
     *       break;
     *   case 1:
     *       alertDisplayer("¡Felicidades!","Código: " + getString(R.string.strDctMcCode));
     *       break;
     *   case 2:
     *       alertDisplayer("¡Felicidades!","Código: " + getString(R.string.strDctCoffeeCode));
     *       break;
     *   default:
     *       break;
     * }*/

    // TODO: Link RecyclerView with fragments
}
