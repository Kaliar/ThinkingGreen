package mx.itesm.thinkinggreen;

public class Settings {

    private static int currTheme = R.style.AppTheme;

    public static int getCurrTheme() {
        return currTheme;
    }

    public static void setThemeDefault() {
        currTheme = R.style.AppTheme;
    }

    public static void setThemeLight() {
        // TODO: ARREGLAR VAINA COOLORES
        //currTheme = R.style.AppThemeLight;
    }

    public static void setThemeDark() {
        // TODO: ARREGLAR VAINA COOLORES
        //currTheme = R.style.AppThemeDark;
    }

    public static void setThemeInv() {
    }

    public static void setThemeSea() {

    }


}
