package mx.itesm.thinkinggreen;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Settings {

    private static int currTheme;

    private static String usrName;
    private static String pwd;

    public static void getPrefs(Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = settings.getString("user",null);
        pwd = settings.getString("pwd", null);
        currTheme = settings.getInt("theme", R.style.AppTheme);
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

    public static void logOut(Context con){
        SharedPreferences settings = con.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        usrName = null;
        pwd = null;
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user",usrName);
        editor.putString("pwd",pwd);
        editor.commit();
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
                return R.color.colorPrimaryLightLight;
            case R.style.AppThemeLight:
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
                return R.color.colorPrimaryLightLight;
            case R.style.AppThemeLight:
                return R.color.colorPrimaryDarkDark;
            case R.style.AppThemeInverse:
                return 0;
            case R.style.AppThemeAqua:
                return 0;
        }
        return 0;
    }

}
