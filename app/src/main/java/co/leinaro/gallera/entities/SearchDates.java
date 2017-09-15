package co.leinaro.gallera.entities;


import java.io.Serializable;

public class SearchDates implements Serializable {
    private int id;
    private String token;
    private String group_date;
    private String date_created;
    private int count;

    public String getGroupDate() {
        return group_date;
    }

    public String getDateCreated() {
        return date_created;
    }

    public int getCount() {
        return count;
    }

    public int getID() {
        return id;
    }

}


// {
//         "id": 4,
//         "token": "s-pl0bieyh971tlnmhcyj28dcxdukwi1",
//         "group_date": "September - 2017",
//         "date_created": "2017-09-13T02:30:56.944658Z",
//         "count": 2
//         }