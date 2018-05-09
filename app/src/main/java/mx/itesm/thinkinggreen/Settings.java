package mx.itesm.thinkinggreen;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.StaticLayout;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import mx.itesm.thinkinggreen.Models.Advices;

public class Settings {

    private static int currTheme;
    private static String[] advCategory =  new String[4];
    private static String usrName;
    private static String pwd;
    private static String frequency;
    private static String advType;
    private static String advID;

    public static void getPrefs(Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = settings.getString("user",null);
        pwd = settings.getString("pwd", null);
        currTheme = settings.getInt("theme", R.style.AppTheme);
        advCategory = settings.getString("advCat", "Reciclaje Reducir DIY ZeroWaste").split(" ");
        frequency = settings.getString("frequency", "weekly");
        advID = settings.getString("ID","https://orgranico.com/recopilatorio-11-alternativas-zero-waste/");
        advType = settings.getString("type","WEB");
    }

    public static boolean isUserLogged(){
        return usrName != null || pwd != null;
    }

    public static void savePrefsUser(String usr, String password, Context con, ParseUser user){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = usr;
        pwd = password;
        advCategory = user.getString("catAdv").split(" ");
        currTheme = user.getInt("savedTheme");
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user",usrName);
        editor.putString("pwd",pwd);
        editor.putInt("theme", currTheme);
        editor.putString("advCat", user.getString("catAdv"));
        editor.putString("frequency", user.getString("frequency"));
        editor.apply();

    }

    public static void saveAdvPrefs(String catPrefs, final Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        advCategory = catPrefs.split(" ");
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("advCat", catPrefs);
        editor.apply();
        ParseUser.getCurrentUser().put("catAdv", catPrefs);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(con, "Categorías guardadas correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("Error Guardar CatAdv: ", " " + e.getMessage());
                }
            }
        });
    }

    public static void logOut(Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = null;
        pwd = null;
        currTheme = R.style.AppTheme;
        Log.i("Logout: ", "He hecho logout");
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user",usrName);
        editor.putString("pwd",pwd);
        editor.putInt("theme", currTheme);
        editor.putString("catAdv", "Reciclaje Reducir DIY ZeroWaste");
        editor.commit();
    }


    public static String[] getAdvCategory() {
        return advCategory;
    }

    public static String getUsrName() {
        return usrName;
    }

    public static String getPwd() {
        return pwd;
    }

    public static int getCurrTheme() {
        return currTheme;
    }

    public static void setCurrTheme(int theme, Context con){
        currTheme = theme;
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("theme", currTheme);
        editor.commit();
        ParseUser.getCurrentUser().put("savedTheme", currTheme);
        try {
            ParseUser.getCurrentUser().save();
        } catch (ParseException e) {
            Log.i("Error guardar CurrTheme", " " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setPerAdv(Advices adv, Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        advType = adv.getAdvType();
        advID = adv.getId();
        //editor.putString("ID", "https://orgranico.com/recopilatorio-11-alternativas-zero-waste/");
        //editor.putString("type", "WEB");
        editor.putString("ID", adv.getId());
        editor.putString("type", adv.getAdvType());
        Log.i("ConsejoTipo",""+advType);
        Log.i("ConsejoID", ""+advID);
        editor.commit();
    }

    public static String getFrequency() {
        return frequency;
    }

    public static void setFrequency(String frequency) {
        Settings.frequency = frequency;
    }

    public static String getAdvType() {
        return advType;
    }

    public static void setAdvType(String advType) {
        Settings.advType = advType;
    }

    public static String getAdvID() {
        return advID;
    }

    public static void setAdvID(String advID) {
        Settings.advID = advID;
    }

    // Verfication of network
    public static boolean isNetAvailable(ConnectivityManager connectivityManager) {  // Network is Enabled
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    public static boolean isOnline() { // Has Internet Connection
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
