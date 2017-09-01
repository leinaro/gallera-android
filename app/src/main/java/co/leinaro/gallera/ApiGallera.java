package co.leinaro.gallera;


import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;
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
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new ChuckInterceptor(context));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.13:8000/api/gallera/v1/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public Call<Responses> getChicks() {
        return mApiGalleraServices.getChicks();
    }

    public Call<List<Chick>> registerChick(Chick chick) {
        return mApiGalleraServices.registerChick(chick);
    }

    public Call<List<Chick>> deleteChicks(String token) {
        return mApiGalleraServices.deleteChick(token);
    }

    public class Responses {
        public List<Chick> chickens;
    }


}
