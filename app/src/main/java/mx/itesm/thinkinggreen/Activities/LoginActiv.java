package mx.itesm.thinkinggreen.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.LogInCallback;

import java.util.List;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class LoginActiv extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private ProgressDialog pDiag;
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
        pDiag = new ProgressDialog(this);
        if(Settings.isUserLogged()){
            login(Settings.getUsrName(), Settings.getPwd());
        }
    }

    public void loginBtn(View v){
        login(etUsername.getText().toString(),etPassword.getText().toString());
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
                            Settings.savePrefsUser(strUser, strPwd, LoginActiv.this);
                            alertDisplayer(getString(R.string.strSuccessTitle), getString(R.string.str_Welcome) + " " + strUser + "!");
                            pDiag.dismiss(); // Hide dialog
                        } else {
                            ParseQuery<ParseObject> queryUs = ParseQuery.getQuery("Institution");
                            queryUs.whereFullText("name", strUser);
                            queryUs.whereEqualTo("password", strPwd);

                            queryUs.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (e == null) {
                                        if(objects.size() == 1) {
                                            Settings.savePrefsUser(strUser, strPwd, LoginActiv.this);
                                            alertDisplayer(getString(R.string.strSuccessTitle), getString(R.string.str_Welcome) + " " + objects.get(0).get("name") + "!");
                                            pDiag.dismiss();
                                        }
                                        else {
                                            pDiag.dismiss();
                                            Toast.makeText(LoginActiv.this, getString(R.string.strLogFail), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(LoginActiv.this, getString(R.string.strFailTitle), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
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

    private void alertDisplayer(String title,String message){
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
