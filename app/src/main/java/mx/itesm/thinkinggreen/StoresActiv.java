package mx.itesm.thinkinggreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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
