package mx.itesm.thinkinggreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }

    public void saveChanges(View v){
        user = etUser.getText().toString();
        mail = etMail.getText().toString();
        password = etPassword.getText().toString();

        // TODO: Código para actualizar la cuenta del user
        Intent intMenu = new Intent(this, MainMenuUserActiv.class);
        startActivity(intMenu);
    }

    public void logout(View v){
        // TODO: Código para cerrar la sesión

        Intent intLogin = new Intent(this, LoginActiv.class);
        startActivity(intLogin);
    }
}
