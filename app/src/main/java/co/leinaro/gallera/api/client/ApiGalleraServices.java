package co.leinaro.gallera.api.client;


import java.util.List;

import co.leinaro.gallera.entities.Chick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiGalleraServices {
    @GET("get_chick")
    Call<ApiGallera.Responses> getChicks();

    @Multipart
    @POST("register_chick")
    Call<ApiGallera.Responses> registerChick(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file,
            @Part("chick") RequestBody chick);

    @GET("delete_chick")
    Call<List<Chick>> deleteChick(String token);
}
