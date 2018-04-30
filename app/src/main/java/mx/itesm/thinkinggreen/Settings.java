package mx.itesm.thinkinggreen;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

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

    public static void setThemeDefault() {
        currTheme = R.style.AppTheme;
    }

    public static void setThemeLight() {
        // TODO: ARREGLAR VAINA COOLORES
        currTheme = R.style.AppThemeLight;
    }

    public static void setThemeDark() {
        // TODO: ARREGLAR VAINA COOLORES
        currTheme = R.style.AppThemeDark;
    }

    public static void setThemeInv() {
    }

    public static void setThemeAqua() {

    }


}
