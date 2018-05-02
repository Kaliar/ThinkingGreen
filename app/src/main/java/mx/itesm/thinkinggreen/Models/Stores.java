package mx.itesm.thinkinggreen.Models;

import android.content.Context;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import mx.itesm.thinkinggreen.R;

public class Stores {

    private byte[] imgId;
    private String name;
    private String address;
    private String description;
    private String mail;
    private String phone;
    private ParseGeoPoint location;
    private static Stores[] arrStores;

    public Stores(byte[] imgId, String name, String address, String description, String mail, String phone, ParseGeoPoint location) {
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

    public static Stores[] getArrStores(ParseGeoPoint usrLocation, final Context con) {

        ParseQuery<ParseObject> queryRes = ParseQuery.getQuery("Stores");
        queryRes.whereNear("location", usrLocation);
        queryRes.setLimit(20);

        queryRes.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    arrStores = new Stores[objects.size()];
                    ParseObject objActual;
                    Stores storeActual;
                    for(int i = 0; i < objects.size(); i++){
                        objActual = objects.get(i);
                        storeActual = new Stores(objActual.getBytes("imgId"), objActual.getString("name"), objActual.getString("address"),
                                objActual.getString("description"), objActual.getString("mail"), objActual.getString("phone"), objActual.getParseGeoPoint("location"));
                        arrStores[i] = storeActual;
                    }
                } else {
                    Toast.makeText(con, "Ocurrió un error: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        /*String[] arrNames = {"Primer Tienda", "Segunda Tienda", "Tercer Tienda"};
        String[] arrAdd = {"ALVARO OBREGON 250, AGUA BLANCA INDUSTRIAL , ZAPOPAN , JAL , C.P.45235",
               "ALVARO OBREGON 250, AGUA BLANCA INDUSTRIAL , ZAPOPAN , JAL , C.P.45235",
        "EMILIO CARRANZA, SAN ANDRES TETEPILCO , DF , C.P"};
        String[] arrDescriptions = {"Vendedora de frutas y verduras rechazadas por \"estar feas\"",
                "Buenos para ti. Mejores para el planeta",
                "Feito pero sanito"};
        int[] arrImgs = {R.drawable.storelogo, R.drawable.storelogo, R.drawable.storelogo};
        String[] arrMail = {"tiendaUno@gmail.com", "tiendaDos@gmail.com", "tiendaTres@gmail.com"};
        String[] arrPhone = {"(55)2107-9101", "(33)3345-5100", "(55)5532-3033"};

        arrStores = new Stores[3];

        for (int i = 0; i<arrStores.length; i++){
            arrStores[i] = new Stores(arrImgs[i], arrNames[i], arrAdd[i],
                    arrDescriptions[i], arrMail[i], arrPhone[i]);
        }*/
        return arrStores;
    }
}
