package mx.itesm.thinkinggreen.Models;

// TODO: Create the Advices model (image, title, description etc...)

import android.content.Context;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import mx.itesm.thinkinggreen.R;

// Advices defining class (Model/Data Set)
public class Advices {

    private int imgId;
    private String title;
    private String description;
    private static Advices[] arrAdvs;
    private String advCat;
    private String id;
    private String advType;

    public Advices(int imgId, String id, String advCat, String title, String description, String advType) {
        this.imgId = imgId;
        this.title = title;
        this.description = description;
        this.id = id;
        this.advCat = advCat;
        this.advType = advType;
    }

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

    public String getAdvCat() {
        return advCat;
    }

    public void setAdvCat(String advCat) {
        this.advCat = advCat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public static Advices[] getArrAdvs(ParseGeoPoint usrLocation, final Context con) {


        ParseQuery<ParseObject> queryRes = ParseQuery.getQuery("Advices");
        queryRes.whereNear("location", usrLocation);
        queryRes.setLimit(20);

        try {
            List<ParseObject> objects = queryRes.find();
            arrAdvs = new Advices[objects.size()];
            ParseObject objActual;
            Advices advActual;
            int imageId;
            String advType;
            for(int i = 0; i < objects.size(); i++) {
                objActual = objects.get(i);
                advType = objActual.getString("Category");

                if (advType.equals("Reciclaje")) {
                    imageId = R.drawable.consejos;
                } else if (advType.equals("DIY")) {
                    imageId = R.drawable.consejos;
                } else if (advType.equals("Reducir")) {
                    imageId = R.drawable.consejos;
                } else if (advType.equals("Zero Waste")) {
                    imageId = R.drawable.consejos;
                }
                imageId = R.drawable.consejos;
                advActual = new Advices(imageId, objActual.getString("URL"), objActual.getString("Category"),
                        objActual.getString("title"), objActual.getString("description"), objActual.getString("type"));
                arrAdvs[i] = advActual;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*queryRes.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    arrAdvs = new Advices[objects.size()];
                    ParseObject objActual;
                    Advices advActual;
                    int imageId;
                    String advType;
                    for(int i = 0; i < objects.size(); i++){
                        objActual = objects.get(i);
                        advType = objActual.getString("Category");

                        if(advType.equals("Reciclaje")){
                            imageId = R.drawable.consejos;
                        }else if(advType.equals("DIY")){
                            imageId = R.drawable.consejos;
                        }else if(advType.equals("Reducir")){
                            imageId = R.drawable.consejos;
                        }else if(advType.equals("Zero Waste")){
                            imageId = R.drawable.consejos;
                        }
                        imageId = R.drawable.consejos;
                        advActual = new Advices(imageId, objActual.getString("URL"), objActual.getString("Category"),
                                objActual.getString("title"), objActual.getString("description"), objActual.getString("type"));
                        arrAdvs[i] = advActual;
                    }
                } else {
                    Toast.makeText(con, "Ocurrió un error: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });*/
        /*String[] arrTitles = {"Primer Consejo", "Segundo Consejo", "Tercer Consejo"};
        String[] arrDescriptions = {"Necesitas leer los mejores consejos de mis ecobloggers favoritos para una vida zero waste",
                "Necesitas leer los mejores consejos de mis ecobloggers favoritos para una vida zero waste",
                "Necesitas leer los mejores consejos de mis ecobloggers favoritos para una vida zero waste"};
        int[] arrImgs = {R.drawable.recompensa, R.drawable.ayudarecompensas, R.drawable.consejos};

        arrAdvs = new Advices[3];

        for (int i = 0; i<arrAdvs.length; i++){
            arrAdvs[i] = new Advices(arrImgs[i], arrTitles[i], arrDescriptions[i]);
        }*/
        return arrAdvs;
    }
}
