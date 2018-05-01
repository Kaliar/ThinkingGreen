package mx.itesm.thinkinggreen.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class UserPreferencesActiv extends AppCompatActivity {

    private EditText etUser;
    private EditText etPassword;
    private EditText etMail;
    private EditText etNewPwd;
    private EditText etConfPwd;

    private Button btnSave;
    private Button btnLogOut;

    private String user;
    private String mail;
    private String password;
    private String newPwd;
    private String confPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        etUser = findViewById(R.id.etNameUsrPref);
        etPassword = findViewById(R.id.etPasswordUsrPref);
        etMail = findViewById(R.id.etMailUsrPref);
        etNewPwd = findViewById(R.id.ETNewPwd);
        etConfPwd = findViewById(R.id.ETConfirmPwd);
        btnSave = findViewById(R.id.btnSaveUsrPref);
        btnLogOut = findViewById(R.id.btnLogoutUsrPref);
        //btnSave.setBackgroundColor(getResources().getColor(Settings.setBtnOK()));
       // btnLogOut.setBackgroundColor(getResources().getColor(Settings.setBtnAlternate()));
        ParseUser user = ParseUser.getCurrentUser();
        etUser.setText(user.getUsername());
        etMail.setText(user.getEmail());
    }

    public void saveChanges(View v){
        if(Settings.isNetAvailable((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                && Settings.isOnline()){
            user = etUser.getText().toString();
            mail = etMail.getText().toString();
            password = etPassword.getText().toString();
            newPwd = etNewPwd.getText().toString();
            confPwd = etConfPwd.getText().toString();
            //check if the fields arent empty
            if(user.equals("")){
                etUser.setError(getResources().getString(R.string.strFieldError));
            }
            if(mail.equals("")){
                etMail.setError(getResources().getString(R.string.strFieldError));
            }
            if(password.equals("")){
                etPassword.setError(getResources().getString(R.string.strFieldError));
            } if(!password.equals(Settings.getPwd())){
                etPassword.setError(getString(R.string.strWrongPwd));
            } else{
                if((!newPwd.equals("") || !confPwd.equals("")) && !newPwd.equals(confPwd)){
                    etNewPwd.setError(getString(R.string.strWrongPwd));
                    etConfPwd.setError(getString(R.string.strWrongPwd));
                }else {
                    ParseUser.getCurrentUser().setEmail(mail);
                    ParseUser.getCurrentUser().setUsername(user);
                    if(newPwd.equals("")) {
                        Settings.savePrefsUser(user, password, this);
                        ParseUser.getCurrentUser().setPassword(password);
                    }
                    else {
                        Settings.savePrefsUser(user, newPwd, this);
                        ParseUser.getCurrentUser().setPassword(newPwd);
                    }
                    ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Intent intMenu = new Intent(getApplicationContext(), MainMenuUserActiv.class);
                                startActivity(intMenu);
                            } else {
                                alertDisplayer("Error", e.getMessage());
                            }
                        }
                    });
                }
            }
        }
        else {
            Toast.makeText(this, R.string.strNoNetwork, Toast.LENGTH_LONG).show();
        }
    }

    public void logout(View v){
        Settings.logOut(this);
        if(Settings.isNetAvailable((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                && Settings.isOnline()) {
            ParseUser.logOut();
        }
        else {
            // TODO: Cerrar sesión sin internet .-.
            Log.i("Logout","NO HAY INTERNET. NO SÉ QUE PASE CON PARSE");
        }
        Intent intLogin = new Intent(this, LoginActiv.class);
        startActivity(intLogin);
    }

    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}
