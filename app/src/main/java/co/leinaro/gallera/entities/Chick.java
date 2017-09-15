package co.leinaro.gallera.entities;


import java.io.Serializable;

public class Chick implements Serializable {
    private String token;
    private String image_url;
    private int owner_id;
    private String owner_name;
    private String breeder_plate_number;
    private String breeder_name;

    private int search_group;
    //    private String date_created;
    private String coliseo_plate_number;
    private String coliseo_responsible;
    //    private String weight;
    private String color;
    //    private String owner;
    private String cresta;
    private String pata;


    public Chick(String owner_name, String breeder_plate_number, String breeder_name, String coliseo_plate_number, String coliseo_responsible, String color, String cresta, String pata) {
        this.owner_name = owner_name;
        this.breeder_plate_number = breeder_plate_number;
        this.breeder_name = breeder_name;
        this.coliseo_plate_number = coliseo_plate_number;
        this.coliseo_responsible = coliseo_responsible;
        this.color = color;
        this.cresta = cresta;
        this.pata = pata;
    }

    public String getToken() {
        return token;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getBreeder_plate_number() {
        return breeder_plate_number;
    }

    public String getBreeder_name() {
        return breeder_name;
    }

    public String getColiseo_plate_number() {
        return coliseo_plate_number;
    }

    public String getColiseo_responsible() {
        return coliseo_responsible;
    }

    public String getColor() {
        return color;
    }

    public String getCresta() {
        return cresta;
    }

    public String getPata() {
        return pata;
    }

    public int getSearchGroup() {
        return search_group;
    }
}
