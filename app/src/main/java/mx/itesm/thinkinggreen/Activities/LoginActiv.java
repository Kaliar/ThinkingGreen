package mx.itesm.thinkinggreen.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.LogInCallback;

import mx.itesm.thinkinggreen.R;

public class LoginActiv extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize parse
        Parse.initialize(this);

        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUserNameLogin);
        etPassword = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    public void login(View v){
        boolean isUser = true;
        boolean isInst = true;
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        // TODO: Validate user login
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    alertDisplayer("¡Bienvenido de nuevo","¡Sesión iniciada con éxito!");
                    // User Login
                    Intent intUserMainMenu = new Intent(getApplicationContext(), MainMenuUserActiv.class);
                    startActivity(intUserMainMenu);
                } else {
                    ParseUser.logOut();
                }
            }
        });
        isUser = false;
        if(!isUser) {
            //Login of a BA/Restaurant
            // TODO: Create intent for BA/Restaurants

            //isInst = false;
            if(!isInst) alertDisplayer("Error con inicio de sesión", "Por favor intente de nuevo");
        }



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

                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

}
