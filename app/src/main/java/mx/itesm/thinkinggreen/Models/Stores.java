package mx.itesm.thinkinggreen.Models;

import mx.itesm.thinkinggreen.R;

public class Stores {

    private int imgId;
    private String name;
    private String address;
    private String description;
    private String mail;
    private String phone;
    private static Stores[] arrStores;

    public Stores(int imgId, String name, String address, String description, String mail, String phone) {
        this.imgId = imgId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.mail = mail;
        this.phone = phone;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
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

    public static Stores[] getArrStores() {
        String[] arrNames = {"Primer Tienda", "Segunda Tienda", "Tercer Tienda"};
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
        }
        return arrStores;
    }
}
