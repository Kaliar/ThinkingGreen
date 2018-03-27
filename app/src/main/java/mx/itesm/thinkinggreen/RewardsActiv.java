package mx.itesm.thinkinggreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class RewardsActiv extends AppCompatActivity {

    private RecyclerView rvRewards;
    private TextView tvDescription;
    private TextView tvUsrLeaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        rvRewards = findViewById(R.id.rvRewards);
        tvDescription = findViewById(R.id.tvTitleReward);
        tvUsrLeaves = findViewById(R.id.tvLeaves);
    }

    // TODO: Link RecyclerView with fragments
}
