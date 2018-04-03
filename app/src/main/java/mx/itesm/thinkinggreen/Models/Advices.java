package mx.itesm.thinkinggreen.Models;

// TODO: Create the Advices model (image, title, description etc...)

import mx.itesm.thinkinggreen.R;

// Advices defining class (Model/Data Set)
public class Advices {

    private int imgId;
    private String title;
    private String description;
    private static Advices[] arrAdvs;

    public Advices(int imgId, String title, String description) {
        this.imgId = imgId;
        this.title = title;
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Dummy method for generating an advice list
    public static Advices[] getArrAdvs() {
        String[] arrTitles = {"Primer Consejo", "Segundo Consejo", "Tercer Consejo"};
        String[] arrDescriptions = {"Necesitas leer los mejores consejos de mis ecobloggers favoritos para una vida zero waste",
                "Necesitas leer los mejores consejos de mis ecobloggers favoritos para una vida zero waste",
                "Necesitas leer los mejores consejos de mis ecobloggers favoritos para una vida zero waste"};
        int[] arrImgs = {R.drawable.recompensa, R.drawable.ayudarecompensas, R.drawable.consejos};

        arrAdvs = new Advices[3];

        for (int i = 0; i<arrAdvs.length; i++){
            arrAdvs[i] = new Advices(arrImgs[i], arrTitles[i], arrDescriptions[i]);
        }
        return arrAdvs;
    }
}
