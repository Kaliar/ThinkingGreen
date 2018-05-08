package mx.itesm.thinkinggreen;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Settings {

    private static int currTheme;
    private static String[] advCategory =  new String[4];
    private static String usrName;
    private static String pwd;

    public static void getPrefs(Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = settings.getString("user",null);
        pwd = settings.getString("pwd", null);
        currTheme = settings.getInt("theme", R.style.AppTheme);
        advCategory = settings.getString("advCat", "Reciclaje Reducir DIY ZeroWaste").split(" ");
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
