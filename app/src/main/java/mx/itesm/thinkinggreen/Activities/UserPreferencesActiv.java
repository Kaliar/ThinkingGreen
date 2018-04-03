package mx.itesm.thinkinggreen.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import mx.itesm.thinkinggreen.R;

public class UserPreferencesActiv extends AppCompatActivity {

    private EditText etUser;
    private EditText etPassword;
    private EditText etMail;
    private Button btnSave;
    private Button btnLogOut;
    private String user;
    private String mail;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        etUser = findViewById(R.id.etNameUsrPref);
        etPassword = findViewById(R.id.etPasswordUsrPref);
        etMail = findViewById(R.id.etMailUsrPref);
        btnSave = findViewById(R.id.btnSaveUsrPref);
        btnLogOut = findViewById(R.id.btnLogoutUsrPref);
        ParseUser user = ParseUser.getCurrentUser();
        etUser.setText(user.getUsername());
        etMail.setText(user.getEmail());
    }

    public void saveChanges(View v){
        user = etUser.getText().toString();
        mail = etMail.getText().toString();
        password = etPassword.getText().toString();
        //check if the fields arent empty
        if(user.equals("")){
            etUser.setError(getResources().getString(R.string.strFieldError));
        }
        if(mail.equals("")){
            etMail.setError(getResources().getString(R.string.strFieldError));
        }
        if(password.equals("")){
            etPassword.setError(getResources().getString(R.string.strFieldError));
        } else {

            ParseUser.getCurrentUser().setEmail(mail);
            ParseUser.getCurrentUser().setUsername(user);
            ParseUser.getCurrentUser().setPassword(password);
            ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        Intent intMenu = new Intent(getApplicationContext(), MainMenuUserActiv.class);
                        startActivity(intMenu);
                    } else {
                        alertDisplayer("Error", e.getMessage());
                    }
                }
            });

        }
    }

    public void logout(View v){
        ParseUser.logOut();
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
