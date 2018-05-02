package mx.itesm.thinkinggreen;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

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
        advCategory[0] = settings.getString("catReciclaje", "Reciclaje");
        advCategory[1] = settings.getString("catReducir", "Reducir");
        advCategory[2] = settings.getString("catDIY", "DIY");
        advCategory[3] = settings.getString("catZero", "Zero Waste");
    }

    public static boolean isUserLogged(){
        return usrName != null || pwd != null;
    }

    public static void savePrefsUser(String usr, String password, Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = usr;
        pwd = password;
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user",usrName);
        editor.putString("pwd",pwd);
        editor.apply();
    }

    public static void saveAdvPrefs(String catReciclaje, String catReducir, String catDIY, String catZero, Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        advCategory[0] = catReciclaje;
        advCategory[1] = catReducir;
        advCategory[2] = catDIY;
        advCategory[3] = catZero;
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("catReciclaje",advCategory[0]);
        editor.putString("catReducir",advCategory[1]);
        editor.putString("catDIY",advCategory[2]);
        editor.putString("catZero",advCategory[3]);
        editor.apply();
    }

    public static void logOut(Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = null;
        pwd = null;
        currTheme = R.style.AppTheme;
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user",usrName);
        editor.putString("pwd",pwd);
        editor.putInt("theme", currTheme);
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

    public static int setBtnOK(){
        switch (currTheme){
            case R.style.AppTheme:
                return R.color.colorPrimaryDarkLight;
            case R.style.AppThemeDark:
                return R.color.colorPrimaryDarkLight;
            case R.style.AppThemePastel:
                return R.color.colorPrimaryDarkDark;
            case R.style.AppThemeInverse:
                return 0;
            case R.style.AppThemeAqua:
                return 0;
        }
        return 0;
    }

    public static int setBtnAlternate(){
        switch (currTheme){
            case R.style.AppTheme:
                return R.color.colorPrimaryDarkLight;
            case R.style.AppThemeDark:
                return R.color.colorPrimaryDarkLight;
            case R.style.AppThemePastel:
                return R.color.colorPrimaryDarkDark;
            case R.style.AppThemeInverse:
                return 0;
            case R.style.AppThemeAqua:
                return 0;
        }
        return 0;
    }

}
