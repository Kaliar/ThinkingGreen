package mx.itesm.thinkinggreen.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.parse.ParseUser;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class MainMenuUserActiv extends AppCompatActivity {

    private TextView tvMessage;
    private ImageButton btnStores;
    private ImageButton btnRewards;
    private ImageButton btnAdvices;
    private ImageButton btnRestaurants;
    private ImageButton btnPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_user);
        //setTitle("Menu");
        tvMessage = findViewById(R.id.tvMessageUserMenu);
        tvMessage.setText(getString(R.string.strWelcome) + " " +ParseUser.getCurrentUser().getUsername());
        btnStores = findViewById(R.id.btnStoreUserMenu);
        btnRewards = findViewById(R.id.btnRewardsUserMenu);
        btnAdvices = findViewById(R.id.btnAdvicesUserMenu);
        btnRestaurants = findViewById(R.id.btnRestaurantsUserMenu);
        btnPreferences = findViewById(R.id.btnPreferencesUserMenu);

    }

    public void showStores(View v){
        Intent intStores = new Intent(this, StoresActiv.class);
        startActivity(intStores);
    }

    public void showRewards(View v){
        Intent intRewards = new Intent(this, RewardsActiv.class);
        startActivity(intRewards);
    }

    public void showAdvices(View v){
        Intent intAdvices = new Intent(this, AdvicesActiv.class);
        startActivity(intAdvices);
    }

    public void showRestaurants(View v){
        Intent intRestaurants = new Intent(this, RestaurantsActiv.class);
        startActivity(intRestaurants);
    }

    public void showPreferences(View v){
        Intent intPreferences = new Intent(this, UserPreferencesActiv.class);
        startActivity(intPreferences);
    }

    public void showAbout(View v){
        Intent intAbout = new Intent(this, AboutActiv.class);
        startActivity(intAbout);
    }


    @Override
    public void onBackPressed() {
        alertDisplayer("¿"+getString(R.string.str_Exit)+"?",getString(R.string.strSure),
                getString(R.string.str_Logout),
                getString(R.string.strCancel),
                getString(R.string.str_Exit));
    }


    private void alertDisplayer(String title,String message, String btnLogout, String btnCancel, String btnExit){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(btnLogout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParseUser.logOut();
                        Settings.logOut(MainMenuUserActiv.this);
                        dialog.cancel();
                        Intent intMenu=new Intent(MainMenuUserActiv.this,LoginActiv.class);
                        startActivity(intMenu);
                        //finish();

                    }
                })
                .setNeutralButton(btnCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton(btnExit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ParseUser.logOut();
                        dialogInterface.cancel();
                        Intent intLogin = new Intent(MainMenuUserActiv.this,LoginActiv.class);
                        startActivity(intLogin);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                |Intent.FLAG_ACTIVITY_CLEAR_TOP
                        |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}
