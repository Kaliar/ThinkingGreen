package mx.itesm.thinkinggreen.Models;

// TODO: Create the Advices model (image, title, description etc...)

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Arrays;
import java.util.List;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

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

    public Advices(String url, String advType){
        this.id = url;
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
    public static Advices[] getArrAdvs() {
        ParseQuery<ParseObject> queryRes = ParseQuery.getQuery("Advices");
        queryRes.whereContainedIn("Category", Arrays.asList(Settings.getAdvCategory()));

        try {
            List<ParseObject> objects = queryRes.find();
            Log.i("Query de Advices: ",""+objects.size());
            arrAdvs = new Advices[objects.size()];
            ParseObject objActual;
            int imageId;
            String advType;
            for(int i = 0; i < objects.size(); i++) {
                objActual = objects.get(i);
                advType = objActual.getString("Category");

                if (advType.equals("Reciclaje")) {
                    imageId = R.drawable.icons8_recycle_48;
                } else if (advType.equals("DIY")) {
                    imageId = R.drawable.icons8_drill_48;
                } else if (advType.equals("Reducir")) {
                    imageId = R.drawable.icons8_decline_48;
                } else {
                    imageId = R.drawable.icons8_circled_0_c_48;
                }
                arrAdvs[i] = new Advices(imageId, objActual.getString("URL"), objActual.getString("Category"), objActual.getString("title"),
                        objActual.getString("description"), objActual.getString("type"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return arrAdvs;
    }
}
