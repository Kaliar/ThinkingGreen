package mx.itesm.thinkinggreen.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import mx.itesm.thinkinggreen.Fragments.AdviceListFrag;
import mx.itesm.thinkinggreen.Fragments.AdviceSettingsFrag;
import mx.itesm.thinkinggreen.Fragments.AdviceWebFrag;
import mx.itesm.thinkinggreen.Fragments.RestaurantsListFrag;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;
import mx.itesm.thinkinggreen.Fragments.YoutubeFragment;
import mx.itesm.thinkinggreen.Models.Advices;

public class AdvicesActiv extends AppCompatActivity {

    private BottomNavigationItemView tvMessage;
    private ImageButton btnAdvices;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            btnAdvices.setVisibility(View.INVISIBLE);
            switch (item.getItemId()) {
                case R.id.menu_weekly_advice:
                    if(Settings.getFrequency().equals("weekly")){
                        changeWeekly();
                    }else{
                        changeDaily();
                    }
                    if(Settings.getAdvType().equals("WEB")){
                        Log.i("OnCreate","Se creo web");
                        loadWebAdviceFrag(Settings.getAdvID());
                    }
                    else{
                        Log.i("OnCreate","Se creo youtube");
                        loadYoutubeFragment(Settings.getAdvID());
                    }

                    //loadYoutubeFragment();
                    return true;

                case R.id.menu_advice_list:

                    loadAdvicesListFrag();
                    return true;

                case R.id.menu_advices_preferences:
                    loadAdviceSettingsFrag();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(Settings.getCurrTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advices);

        tvMessage = findViewById(R.id.menu_weekly_advice);
        btnAdvices = findViewById(R.id.btnAdvices);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(Settings.getFrequency().equals("daily"))
            changeDaily();
        else
            changeWeekly();
        if(Settings.getAdvType().equals("WEB")){
            Log.i("OnCreate","Se creo web");
            loadWebAdviceFrag(Settings.getAdvID());
        }
        else{
            Log.i("OnCreate","Se creo youtube");
            loadYoutubeFragment(Settings.getAdvID());
        }
        btnAdvices.setVisibility(View.INVISIBLE);
        //loadYoutubeFragment();
    }

    private void loadAdviceSettingsFrag() {
        AdviceSettingsFrag fragSetAdv = new AdviceSettingsFrag(); // Fragment of the advices preferences
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameAdvices, fragSetAdv); // Set the AdvicePreference Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread

    }

    private void loadAdvicesListFrag() {
        AdviceListFrag fragListFrag = new AdviceListFrag(); // Fragment of the advices list
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameAdvices, fragListFrag); // Set the AdviceList Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread

    }

    // Web Frag
    private void loadWebAdviceFrag(String id) {
        AdviceWebFrag fragWeekAdvice = AdviceWebFrag.newInstance(id); // Fragment of the advices of the week
        //AdviceWebFrag fragWeekAdvice = AdviceWebFrag.newInstance("https://stackoverflow.com/questions/2662531/launching-google-maps-directions-via-an-intent-on-android");
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
        fragTrans.replace(R.id.frameAdvices, fragWeekAdvice); // Set the AdviceWeek Layout
        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragTrans.commit(); // Schedule the operation into thread


    }

    //Video View
    private void  loadYoutubeFragment(String id){
        //YoutubeFragment fragment = new YoutubeFragment();
        YoutubeFragment fragment = YoutubeFragment.newInstance(id);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frameAdvices, fragment)
                //.addToBackStack(null)
                .commit();
    }


    public void changeDaily(){

        Calendar c = Calendar.getInstance();
        int thisDay = c.get(Calendar.DAY_OF_YEAR); //You can chose something else to compare too, such as DATE..
        long todayMillis = c.getTimeInMillis(); //We might need this a bit later.

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        long last = prefs.getLong("date", 0); //If we don't have a saved value, use 0.
        c.setTimeInMillis(last);
        int lastDay = c.get(Calendar.DAY_OF_YEAR);

        if(lastDay != thisDay ){
            //New day, update View and preference:
            SharedPreferences.Editor edit = prefs.edit();
            Settings.setPerAdv(getPeriodicAdv(), this);
            edit.putLong("date", todayMillis);
            edit.commit();

            //YoutubeFragment video = new YoutubeFragment();
            //video.setVideoID("BT59rohv6jw");
        }

    }

    public void changeWeekly(){

        Calendar c = Calendar.getInstance();
        int thisWeek = c.get(Calendar.WEEK_OF_YEAR); //You can chose something else to compare too, such as DATE..
        long todayMillis = c.getTimeInMillis(); //We might need this a bit later.

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        long last = prefs.getLong("date", 0); //If we don't have a saved value, use 0.
        c.setTimeInMillis(last);
        int lastWeek = c.get(Calendar.WEEK_OF_YEAR);

        if(lastWeek != thisWeek ){
            //New week, update View and preference:
            SharedPreferences.Editor edit = prefs.edit();
            edit.putLong("date", todayMillis);
            Settings.setPerAdv(getPeriodicAdv(), this);
            edit.putLong("date", todayMillis);
            edit.commit();
        }

    }

    private Advices getPeriodicAdv(){
        ParseQuery<ParseObject> queryRes = ParseQuery.getQuery("Advices");
        queryRes.whereContainedIn("Category", Arrays.asList(Settings.getAdvCategory()));
        queryRes.selectKeys(Arrays.asList("URL","type"));
        String arrObjetos[];
        Random rand = new Random();

        try {
            List<ParseObject> objects = queryRes.find();
            Log.i("Query de Advices: ",""+objects.size());
            ParseObject objActual;
            arrObjetos = new String[objects.size()];
            Log.i("Query","Consejos jalados para hacer random " + objects.size());
            int imageId;
            String advType;
            for(int i = 0; i < objects.size(); i++) {
                arrObjetos[i] = (String)objects.get(i).getObjectId();
            }
        } catch (ParseException e) {
            arrObjetos = new String[0];
            Toast.makeText(this, "Algo salió mal con la conexión, inténtalo más tarde", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        int id = rand.nextInt(arrObjetos.length);
        ParseQuery<ParseObject> queryAdv = ParseQuery.getQuery("Advices");
        //queryAdv.get(arrObjetos[id]);
        //queryAdv.selectKeys(Arrays.asList("URL", "type"));

        try {
            //List<ParseObject> advice = queryAdv.find();
            ParseObject advice = queryAdv.get(arrObjetos[id]);
            Log.i("Consejo rand","id de query " + advice.getObjectId());
            Advices adv = new Advices((String)advice.get("URL"), (String)advice.get("type"));
            return adv;

        } catch (ParseException e){
            Log.i("segundo Query","La vida vale verga con el consejo malo");
            Toast.makeText(this, "Algo salió mal con la BD, inténtalo más tarde", Toast.LENGTH_SHORT).show();
        }

        return new Advices("https://orgranico.com/recopilatorio-11-alternativas-zero-waste/", "WEB");

    }


}
