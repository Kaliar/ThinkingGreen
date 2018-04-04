package mx.itesm.thinkinggreen.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import mx.itesm.thinkinggreen.Fragments.StoresListFrag;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class StoresActiv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        StoresListFrag fragStoreList = new StoresListFrag(); // Fragment of a Person
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.add(R.id.frameStores, fragStoreList); // Set the PersonFrag Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragTrans.commit(); // Schedule the operation into thread
        //Toast.makeText(this,"AAAAHHH",Toast.LENGTH_LONG).show();

    }

    //TODO: Link RecyclerView with fragments
}
