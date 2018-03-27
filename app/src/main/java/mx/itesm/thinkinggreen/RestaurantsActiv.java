package mx.itesm.thinkinggreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class RestaurantsActiv extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rvRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        rvRestaurants = findViewById(R.id.rvRestaurants);
    }

    //TODO: Link RecyclerView with fragments
}
