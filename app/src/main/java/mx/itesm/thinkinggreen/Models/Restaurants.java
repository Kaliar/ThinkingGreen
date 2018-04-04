package mx.itesm.thinkinggreen.Models;

import mx.itesm.thinkinggreen.R;

public class Restaurants {

    private int imgId;
    private String name;
    private String address;
    private String description;
    private String mail;
    private String phone;
    private static Restaurants[] arrRestaurants;

    public Restaurants(int imgId, String name, String address, String description, String mail, String phone) {
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

    public static Restaurants[] getArrRestaurants() {
        String[] arrNames = {"Primer Restaurante", "Segundo Restaurante", "Tercer Restaurante"};
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
        }
        return arrRestaurants;
    }
}
