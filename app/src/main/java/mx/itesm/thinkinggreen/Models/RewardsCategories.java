package mx.itesm.thinkinggreen.Models;

import android.graphics.drawable.Drawable;

import mx.itesm.thinkinggreen.R;

public class RewardsCategories {

    private int title;
    private int imgID;

    public RewardsCategories(int title, int imgID) {
        this.title = title;
        this.imgID = imgID;
    }

    public RewardsCategories(){

    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public static RewardsCategories[] getRewCate(){
        int[] titles = {R.string.strCatDcts, R.string.strCatThemes};
        int[] imgs = {R.drawable.descuento, R.drawable.themecat};

        RewardsCategories[] arrCategories = new RewardsCategories[2];
        for (int i=0; i<2; i++){
            arrCategories[i] = new RewardsCategories(titles[i], imgs[i]);
        }
        return arrCategories;

    }

}
