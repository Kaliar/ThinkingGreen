package mx.itesm.thinkinggreen.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.FileInputStream;
import java.util.List;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

public class Restaurants {

    private byte[] imgId;
    private String name;
    private String address;
    private String description;
    private String mail;
    private String phone;
    private ParseGeoPoint location;
    private static Restaurants[] arrRestaurants;

    public Restaurants(byte[] imgId, String name, String address, String description, String mail, String phone, ParseGeoPoint location) {
        this.imgId = imgId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.mail = mail;
        this.phone = phone;
        this.location = location;
    }

    public byte[] getImgId() {
        return imgId;
    }

    public void setImgId(byte[] imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ParseGeoPoint getLocation() {
        return location;
    }

    public static Restaurants[] getArrRestaurants(ParseGeoPoint usrLocation, final Context con) {
        ParseQuery<ParseObject> queryRes = ParseQuery.getQuery("Restaurants");
        queryRes.whereNear("location", usrLocation);
        queryRes.setLimit(5);
        Log.i("Restaurants get","aki tooooooy");

        try {
            List<ParseObject> objects = queryRes.find();
            Log.i("Restaurants get","TAMANO OBJS: " + objects.size());
            arrRestaurants = new Restaurants[objects.size()];
            Log.i("Restaurants get","TAMANO REST: " + arrRestaurants.length);
            ParseObject objActual;
            Restaurants resActual;
            for(int i = 0; i < objects.size(); i++){
                objActual = objects.get(i);
                resActual = new Restaurants(objActual.getParseFile("imgId").getData(), objActual.getString("name"), objActual.getString("address"),
                        objActual.getString("description"), objActual.getString("mail"), objActual.getString("phone"), objActual.getParseGeoPoint("location"));
                arrRestaurants[i] = resActual;
            }
            Log.i("Restaurants get","TERMINE DE ITERAR: " + arrRestaurants.length);
        } catch (ParseException e) {
            Log.i("Restaurant","Fallo el query");
            e.printStackTrace();
        }



        // QUERY DESCACA TODO ASINCRONO
        /*queryRes.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Restaurants get","TAMANO OBJS: " + objects.size());
                    arrRestaurants = new Restaurants[objects.size()];
                    Log.i("Restaurants get","TAMANO REST: " + arrRestaurants.length);
                    ParseObject objActual;
                    Restaurants resActual;
                    for(int i = 0; i < objects.size(); i++){
                        objActual = objects.get(i);
                        resActual = new Restaurants(objActual.getBytes("imgId"), objActual.getString("name"), objActual.getString("address"),
                                objActual.getString("description"), objActual.getString("mail"), objActual.getString("phone"), objActual.getParseGeoPoint("location"));
                        arrRestaurants[i] = resActual;
                    }
                    Log.i("Restaurants get","TERMINE DE ITERAR: " + arrRestaurants.length);
                } else {
                    Log.i("Restaurant","Fallo el query");
                    Toast.makeText(con, "Ocurrió un error: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });*/

        /*String[] arrNames = {"Primer Restaurante", "Segundo Restaurante", "Tercer Restaurante"};
        String[] arrAdd = {"ALVARO OBREGON 250, AGUA BLANCA INDUSTRIAL , ZAPOPAN , JAL , C.P.45235",
                "ALVARO OBREGON 250, AGUA BLANCA INDUSTRIAL , ZAPOPAN , JAL , C.P.45235",
                "EMILIO CARRANZA, SAN ANDRES TETEPILCO , DF , C.P"};
        String[] arrDescriptions = {"Convertimos los alimentos \"feos\" en platillos de alta calidad",
                "Nada se desperdiciará",
                "En alianza con el Banco de Alimentos Nacional"};
        int[] arrImgs = {R.drawable.restaurant, R.drawable.restaurant, R.drawable.restaurant};
        String[] arrMail = {"restauranteUno@gmail.com", "restauranteDos@gmail.com", "restauranteTres@gmail.com"};
        String[] arrPhone = {"(55)2107-9100", "(33)3345-5103", "(55)5532-3035"};

        arrRestaurants = new Restaurants[3];

        for (int i = 0; i< arrRestaurants.length; i++){
            arrRestaurants[i] = new Restaurants(arrImgs[i], arrNames[i], arrAdd[i],
                    arrDescriptions[i], arrMail[i], arrPhone[i]);
        }*/
        return arrRestaurants;
    }
}
