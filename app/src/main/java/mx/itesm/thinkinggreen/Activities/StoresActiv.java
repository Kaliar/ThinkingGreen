package mx.itesm.thinkinggreen.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import mx.itesm.thinkinggreen.R;

public class StoresActiv extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rvStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
    }

    //TODO: Link RecyclerView with fragments
}
