package mx.itesm.thinkinggreen.Models;

import mx.itesm.thinkinggreen.R;

public class RewardsItems {


    private int title;
    private int code;
    private int imgID;
    private int price;
    private int category;

    public RewardsItems(int title, int code, int imgID, int price, int category) {
        this.title = title;
        this.code = code;
        this.imgID = imgID;
        this.price = price;
        this.category = category;
    }

    public RewardsItems(){

    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public static RewardsItems[] getRewItems(int category){
        RewardsItems[] arrRewItems;
        int[] titles = {R.string.strDctEns, R.string.strDctMc, R.string.strDctCoffee,
                R.string.strThemeDefault, R.string.strThemeDark, R.string.strThemeLight,
                R.string.strThemeInv, R.string.strThemeSea};

        int[] codes = {R.string.strDctEnsCode, R.string.strDctMcCode, R.string.strDctCoffeeCode,
                R.string.strThemeDefaultCode, R.string.strThemeDarkCode, R.string.strThemeLightCode,
                R.string.strThemeInvCode, R.string.strThemeSeaCode};

        int[] imgs = {R.drawable.grgrlogo, R.drawable.mclogo, R.drawable.coffeehlogo,
                R.drawable.themecat, R.drawable.themecat, R.drawable.themecat,
                R.drawable.themecat, R.drawable.themecat,};
        int[] prices = {30, 52, 25,
                10, 20, 30, 40, 50};
        int[] categories = {0,0,0,
                1, 1, 1, 1, 1};


        if (category == 0){ // Descuentos
            arrRewItems = new RewardsItems[3];
            for (int i = 0; i<3; i++){
                arrRewItems[i] = new RewardsItems(titles[i], codes[i], imgs[i], prices[i], categories[i]);
            }
        }
        else {
            arrRewItems = new RewardsItems[5];
            int j = 0;
            for (int i = 3; i<8; i++){
                arrRewItems[j] = new RewardsItems(titles[i], codes[i], imgs[i], prices[i], categories[i]);
                j++;
            }
        }
        return arrRewItems;
    }

    /*public static int getNextCategory(int currCat){
        for(int i = 0; i < categories.length; i++ ){
            if(categories[i] == currCat && i < categories.length-1){
                return categories[i+1];
            }
        }
        return -1;
    }*/
}
