package mx.itesm.thinkinggreen.Models;

import android.graphics.drawable.Drawable;

import mx.itesm.thinkinggreen.R;

public class StoreCategoriesDataSet {
    private static int[] titles = {R.string.strCatDcts, R.string.strCatThemes};
    private static int[] imgs = {R.drawable.descuento, R.drawable.themecat};

    public StoreCategoriesDataSet(){

    }

    public static int[] getTitles(){
        return titles;
    }

    public static int[] getImgs() {
        return imgs;
    }

}
