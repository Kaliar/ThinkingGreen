package mx.itesm.thinkinggreen.Models;

import mx.itesm.thinkinggreen.R;

public class StoreItemsDataSet {
    private static int[] titles = {R.string.strDctEns, R.string.strDctMc, R.string.strDctCoffee, R.string.strThemeDefault, R.string.strThemeDark, R.string.strThemeLight, R.string.strThemeInv, R.string.strThemeSea};
    private static int[] codes = {R.string.strDctEnsCode, R.string.strDctMcCode, R.string.strDctCoffeeCode};
    private static int[] imgs = {R.drawable.grgrlogo, R.drawable.mclogo, R.drawable.coffeehlogo};
    private static int[] price;
    private static int[] categories = {0,3};

    public StoreItemsDataSet(){

    }

    public static int[] getTitles() {
        return titles;
    }

    public static int[] getCodes() {
        return codes;
    }

    public static int[] getImgs() {
        return imgs;
    }

    public static int[] getPrice() {
        return price;
    }

    public static int[] getCategories() {
        return categories;
    }


    public static int getNextCategory(int currCat){
        for(int i = 0; i < categories.length; i++ ){
            if(categories[i] == currCat && i < categories.length-1){
                return categories[i+1];
            }
        }
        return -1;
    }
}
