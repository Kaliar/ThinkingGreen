package mx.itesm.thinkinggreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        setContentView(R.layout.activity_login);
    }
}
