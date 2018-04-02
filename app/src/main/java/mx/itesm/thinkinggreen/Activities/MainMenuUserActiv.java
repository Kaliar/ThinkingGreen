package mx.itesm.thinkinggreen.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import mx.itesm.thinkinggreen.R;

public class MainMenuUserActiv extends AppCompatActivity {

    private TextView tvMessage;
    private ImageButton btnStores;
    private ImageButton btnRewards;
    private ImageButton btnAdvices;
    private ImageButton btnRestaurants;
    private ImageButton btnPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_user);

        tvMessage = findViewById(R.id.tvMessageUserMenu);

        btnStores = findViewById(R.id.btnStoreUserMenu);
        btnRewards = findViewById(R.id.btnRewardsUserMenu);
        btnAdvices = findViewById(R.id.btnAdvicesUserMenu);
        btnRestaurants = findViewById(R.id.btnRestaurantsUserMenu);
        btnPreferences = findViewById(R.id.btnPreferencesUserMenu);

    }

    public void showStores(View v){
        // TODO: Crear código para mostrar las tiendas cercanas al usuario
        Intent intStores = new Intent(this, StoresActiv.class);
        startActivity(intStores);
    }

    public void showRewards(View v){
        // TODO: Crear código para mostrar las recompensas del usuario
        Intent intRewards = new Intent(this, RewardsActiv.class);
        startActivity(intRewards);
    }

    public void showAdvices(View v){
        // TODO: Crear código para mostrar los consejos del usuario
        Intent intAdvices = new Intent(this, AdvicesActiv.class);
        startActivity(intAdvices);
    }

    public void showRestaurants(View v){
        // TODO: Crear código para mostrar los restaurantes cercanos al usuario
        Intent intRestaurants = new Intent(this, RestaurantsActiv.class);
        startActivity(intRestaurants);
    }

    public void showPreferences(View v){
        // TODO: Crear código para mostrar las preferencias del usuario
        Intent intPreferences = new Intent(this, UserPreferencesActiv.class);
        startActivity(intPreferences);
    }
}
