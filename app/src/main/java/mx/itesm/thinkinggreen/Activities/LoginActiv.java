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
        final String strUsuario = etUsername.getText().toString();
        final String strContrasena = etPassword.getText().toString();

        ParseUser.logInInBackground(strUsuario, strContrasena, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user !=null){
                    alertDisplayer("Successful Login", "Welcome back"+ strUsuario + "!");
                }else{
                    ParseQuery<ParseObject> queryUs=ParseQuery.getQuery("Institution");
                    queryUs.whereFullText("name",strUsuario);
                    queryUs.whereEqualTo("password",strContrasena);

                    queryUs.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e ==null){
                                alertDisplayer("Succesfull login", "Welcome "+objects.size());
                            }
                            else {
                                Toast.makeText(LoginActiv.this, "Wrong Password /Username", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
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
