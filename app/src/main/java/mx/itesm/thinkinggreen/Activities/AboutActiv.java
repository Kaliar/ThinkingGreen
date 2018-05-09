package mx.itesm.thinkinggreen.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class AboutActiv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
