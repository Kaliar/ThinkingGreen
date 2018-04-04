package mx.itesm.thinkinggreen.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Fragments.RestaurantsListFrag;
import mx.itesm.thinkinggreen.Fragments.RewardsCategoryItemListFrag;
import mx.itesm.thinkinggreen.R;

public class RewardsActiv extends AppCompatActivity {

    private RecyclerView rvRewards;
    private TextView tvDescription;
    private TextView tvUsrLeaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        RewardsCategoryItemListFrag fragRestList = RewardsCategoryItemListFrag.newInstance(true); // Fragment of a Person
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.frameRewards, fragRestList); // Set the PersonFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragTrans.commit(); // Schedule the operation into thread
        //Toast.makeText(this,"AAAAHHH",Toast.LENGTH_LONG).show();
    }

    // TODO: Link RecyclerView with fragments
}
