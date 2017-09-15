package co.leinaro.gallera;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.leinaro.gallera.entities.Chick;
import co.leinaro.gallera.entities.SearchDates;

public class Storage {

    private static final String PREF_GENERAL_PANIC_ALERT = "lastPanic";
    private static final String PREF_SHARE_LOCATION = "shareLocationEnable";
    private static final String PREF_DATES = "dates";
    private static final String PREF_CHICKS = "chikens";

    private static SharedPreferences.Editor editor;
    private static SharedPreferences.Editor systemEditor;
    private static SharedPreferences prefs;
    private static SharedPreferences systemPrefs;
    private static AssetManager assetManager;


    public static void initializeStorage(Context ctx) {
        prefs = new ObscuredSharedPreferences(ctx, ctx.getSharedPreferences("TPaga Wallet", Context.MODE_PRIVATE));
        systemPrefs = new ObscuredSharedPreferences(ctx, ctx.getSharedPreferences("TPaga Wallet Deeplinks", Context.MODE_PRIVATE));
        editor = prefs.edit();
        systemEditor = systemPrefs.edit();
        assetManager = ctx.getAssets();
    }

    public static void setChickens(ArrayList<Chick> chickens) {
        editor.putString(PREF_CHICKS, new Gson().toJson(chickens));
        editor.commit();
    }

    public static ArrayList<Chick> getChickens() {
        String ringJson = prefs.getString(PREF_CHICKS, "");
        if (ringJson.equals("")) return new ArrayList<>();
        else {
            Type listType = new TypeToken<ArrayList<Chick>>() {
            }.getType();
            return new Gson().fromJson(ringJson, listType);
        }

//        ArrayList<Chick> chickens = new Gson().fromJson(prefs.getString(PREF_CHICKS, ""), UserData.class);
//        if (userData == null) {
//            userData = new UserData();
//        }
//        if (userData.data == null) {
//            userData.data = new UserData.Data();
//        }
//        return userData;
    }

    public static List<Chick> getChickensByDate(SearchDates selectedSarchDate) {
        try {
            ArrayList<Chick> chickens = Storage.getChickens();
            ArrayList<Chick> filterChickens = new ArrayList<>();
            for (Chick c :
                    chickens) {
                if (c.getSearchGroup() == selectedSarchDate.getID()) {
                    filterChickens.add(c);
                }
            }

//            // Sorting
//            Collections.sort(filterChickens, new Comparator<Chick>() {
//                @Override
//                public int compare(Chick chick2, Chick chick1) {
//                    int owner2 = ISODateTimeFormat.dateTimeParser().parseDateTime(chick2.getDateCreated());
//                    int owner1 = ISODateTimeFormat.dateTimeParser().parseDateTime(chick1.getDateCreated());
//                    if (owner1.isBefore(dateTime2)) {
//                        return -1;
//                    } else if (dateTime2.isBefore(dateTime1)) {
//                        return 1;
//                    } else {
//                        return 0;
//                    }
//                }
//            });


            return filterChickens;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

//    public static ArrayList<Account.Data> getAccounts() {
//        String ringJson = prefs.getString(PREF_ACCOUNTS, "");
//        if (ringJson.equals("")) return new ArrayList<>();
//        else {
//            Type listType = new TypeToken<ArrayList<Account.Data>>() {
//            }.getType();
//            return new Gson().fromJson(ringJson, listType);
//        }
//    }
//
//
//    public static ArrayList<Promotion> getPromotions(String type) {
//        try {
//            ArrayList<Promotion> promotions = Storage.getPromotions();
//            ArrayList<Promotion> filterPromotions = new ArrayList<>();
//            for (Promotion p :
//                    promotions) {
//                if (!p.promotionPaymentMethods.isEmpty() && p.promotionPaymentMethods.contains(type)) {
//                    filterPromotions.add(p);
//                }
//            }
//
//            return filterPromotions;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//
//    public static ArrayList<Promotion> getPromotionsByType(String type) {
//        try {
//            ArrayList<Promotion> promotions = Storage.getPromotions();
//            ArrayList<Promotion> filterPromotions = new ArrayList<>();
//            for (Promotion p :
//                    promotions) {
//                if (!p.promotionType.isEmpty() && p.promotionType.contains(type)) {
//                    filterPromotions.add(p);
//                }
//            }
//
//            return filterPromotions;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//
//    public static ArrayList<Promotion> getPromotions(String type, boolean investFilter) {
//        try {
//            ArrayList<Promotion> promotions = Storage.getPromotions();
//            ArrayList<Promotion> filterPromotions = new ArrayList<>();
//
//            for (Promotion p : promotions) {
//                if (investFilter) {
//                    if (!p.promotionPaymentMethods.isEmpty() && !p.promotionPaymentMethods.contains(type)) {
//                        filterPromotions.add(p);
//                    }
//                } else {
//                    if (!p.promotionPaymentMethods.isEmpty() && p.promotionPaymentMethods.contains(type)) {
//                        filterPromotions.add(p);
//                    }
//                }
//            }
//            return filterPromotions;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
}
