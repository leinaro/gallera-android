package co.leinaro.gallera.api.client;


import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.ArrayList;
import java.util.List;

import co.leinaro.gallera.entities.Chick;
import co.leinaro.gallera.entities.SearchDates;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGallera {

    private static final String TAG = ApiGallera.class.getSimpleName();

    private final ApiGalleraServices mApiGalleraServices;
    private Context context;

    public ApiGallera(Context context) {
        this.context = context;
        mApiGalleraServices = getRetrofit().create(ApiGalleraServices.class);
    }

    public Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(new ChuckInterceptor(context));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gallera.herokuapp.com/api/gallera/v1/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public Call<Responses> getChicks() {
        return mApiGalleraServices.getChicks();
    }

    public Call<Responses> registerChick(RequestBody image, MultipartBody.Part body, RequestBody chick) {
        return mApiGalleraServices.registerChick(image, body, chick);
    }

    public Call<List<Chick>> deleteChicks(String token) {
        return mApiGalleraServices.deleteChick(token);
    }

    public class Responses {
        public List<SearchDates> dates;
        public Chick new_chicken;
        public ArrayList<Chick> chickens;

        private List<String> owner_name;
        private List<String> breeder_plate_number;
        private List<String> breeder_name;
        //    private List<String> register_date;
        private List<String> coliseo_plate_number;
        private List<String> coliseo_responsible;
        //    private List<String> weight;
        private List<String> color;
        //    private List<String> owner;
        private List<String> cresta;
        private List<String> pata;

    }


}
