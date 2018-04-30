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
    private Button btnLogin;
    private Button btnSignUp;
    private String username;
    private String password;
    private ProgressDialog pDiag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);

        //Initialize parse
        Parse.initialize(this);

        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUserNameLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        pDiag = new ProgressDialog(this);
    }

    public void login(View v){
        // Configure and display the progress dialog
        pDiag.setMessage(getString(R.string.strLoading));
        pDiag.setTitle(getString(R.string.strLoginIn));
        pDiag.setIndeterminate(false);
        pDiag.setCancelable(true);
        pDiag.show();

        final String strUser = etUsername.getText().toString();
        final String strPassword = etPassword.getText().toString();

        new Handler().postDelayed(new Runnable() {  // Delay login by one second
            @Override
            public void run() {

                // Login process
                ParseUser.logInInBackground(strUser, strPassword, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null) {
                            alertDisplayer(getString(R.string.strSuccessTitle), getString(R.string.str_Welcome) + " " + strUser + "!");
                            pDiag.dismiss(); // Hide dialog
                        } else{
                            ParseQuery<ParseObject> queryUs=ParseQuery.getQuery("Institution");
                            queryUs.whereFullText("name",strUser);
                            queryUs.whereEqualTo("password",strPassword);

                            queryUs.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if(e ==null){
                                        alertDisplayer(getString(R.string.strSuccessTitle), getString(R.string.str_Welcome) + " " + objects.size());
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

}
