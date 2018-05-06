package mx.itesm.thinkinggreen.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.LogInCallback;

import java.util.List;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class LoginActiv extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private ProgressDialog pDiag;
    private Button btnLogIn;
    private Button btnSignUp;
    private final int INTERNET = 10;
    private final int FINE_LOCATION = 20;
    private final int COARSE_LOCATION = 30;
    private final int GPS = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Settings.getPrefs(this);
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);

        //Initialize parse
        Parse.initialize(this);

        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUserNameLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        btnLogIn = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        //Change the color of the buttons
        //btnLogIn.setBackgroundColor(getResources().getColor(Settings.setBtnOK()));
        //btnSignUp.setBackgroundColor(getResources().getColor(Settings.setBtnAlternate()));
        pDiag = new ProgressDialog(this);
        if(Settings.isUserLogged()){    // Previously Logon
            etUsername.setText(Settings.getUsrName());
            etPassword.setText(Settings.getPwd());
            quickLogin(Settings.getUsrName(), Settings.getPwd());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case INTERNET: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("onRequest:", "TENGO INTERNET");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.i("onRequest:", "NO TENGO INTERNET");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            }
            case FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("onRequest:", "TENGO FINE");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.i("onRequest:", "NO TENGO FINE");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            }
            case COARSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("onRequest:", "TENGO COARSE");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.i("onRequest:", "NO TENGO COARSE");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            }
            case (GPS):
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.i("onRequest:", "TENGO GPS");
                        // Contestó que SI, otorga el permiso. Iniciar actualizaciones.
                        //gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                    } else {
                    Log.i("onRequestPerm...", "Contestó NO, indicarle cómo dar permiso...");
                }
                break;
                }

            // other 'case' lines to check for other
            // permissions this app might request
    }

    private boolean requestPermission(){
        int internetPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);
        int coarsePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int finePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        Log.i("requestPermission:","PERMISSION GRANTED: "+ PackageManager.PERMISSION_GRANTED);
        Log.i("requestPermission:","INTERNET GRANTED: "+ internetPermission);
        Log.i("requestPermission:","COARSE GRANTED: "+ coarsePermission);
        Log.i("requestPermission:","FINE GRANTED: "+ finePermission);

            if (internetPermission == PackageManager.PERMISSION_GRANTED ){  // All necessary permissions are granted

                if (coarsePermission != PackageManager.PERMISSION_GRANTED) {    // Optional permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            COARSE_LOCATION);
                }
                if (finePermission != PackageManager.PERMISSION_GRANTED) {  // Optional permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            FINE_LOCATION);
                }
            return true;
        }
        else {  // Ask for a required permission
            if (internetPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        INTERNET);
            }
            return false;
        }
    }

    public void loginBtn(View v){
        boolean fieldsAreEmpty = false;
        if (etUsername.getText().toString().equals("")){
            etUsername.setError(getString(R.string.strFieldError));
            fieldsAreEmpty = true;
        }
        if (etPassword.getText().toString().equals("")){
            etPassword.setError(getString(R.string.strFieldError));
            fieldsAreEmpty = true;
        }

        if (requestPermission() && !fieldsAreEmpty) {   // If Network and Location permissions are granted
            if (Settings.isNetAvailable((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                    && Settings.isOnline()) {    // If device is online

                login(etUsername.getText().toString(), etPassword.getText().toString());
            } else {
                Toast.makeText(LoginActiv.this, getString(R.string.strNoNetwork), Toast.LENGTH_LONG).show();
            }
        }

    }

    private void quickLogin(String username, String pwd){   // Previously logon
        if (requestPermission()){   // Permissions granted
            if (Settings.isNetAvailable((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                    && Settings.isOnline()){
                login(username, pwd);   // Has connection
            }
            else {
                Toast.makeText(LoginActiv.this, getString(R.string.strNoNetwork), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void login(String username, String pwd){
        final String strUser = username;
        final String strPwd = pwd;
        // Configure and display the progress dialog
        pDiag.setMessage(getString(R.string.strLoading));
        pDiag.setTitle(getString(R.string.strLoginIn));
        pDiag.setIndeterminate(false);
        pDiag.setCancelable(true);
        pDiag.show();

        new Handler().postDelayed(new Runnable() {  // Delay login by one second
            @Override
            public void run() {
                ParseUser.logInInBackground(strUser, strPwd, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Settings.savePrefsUser(strUser, strPwd, LoginActiv.this, user);
                            loginSuccessAlert(getString(R.string.strSuccessTitle), getString(R.string.str_Welcome) + " " + strUser + "!");
                            pDiag.dismiss(); // Hide dialog
                        } else {
                            /*ParseQuery<ParseObject> queryUs = ParseQuery.getQuery("Institution");
                            queryUs.whereFullText("name", strUser);
                            queryUs.whereEqualTo("password", strPwd);

                            queryUs.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (e == null) {
                                        if (objects.size() == 1) {
                                            Settings.savePrefsUser(strUser, strPwd, LoginActiv.this, );
                                            loginSuccessAlert(getString(R.string.strSuccessTitle), getString(R.string.str_Welcome) + " " + objects.get(0).get("name") + "!");
                                            pDiag.dismiss();
                                        } else {
                                            pDiag.dismiss();
                                            Toast.makeText(LoginActiv.this, getString(R.string.strLogFail), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        pDiag.dismiss();
                                        Toast.makeText(LoginActiv.this, getString(R.string.strFailTitle), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });*/
                        }
                    }
                });
            }
        }, 1000);
    }

    // Change Activity to Sign Up
    public void signUp(View v){
        Intent intSignUp = new Intent(this, CreateUserActiv.class);
        startActivity(intSignUp);
    }

    private void loginSuccessAlert(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intMenu=new Intent(LoginActiv.this,MainMenuUserActiv.class);
                        startActivity(intMenu);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
