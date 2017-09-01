package co.leinaro.gallera;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiGalleraServices {
    @GET("get_chick")
    Call<ApiGallera.Responses> getChicks();

    @POST("register_chick")
    Call<List<Chick>> registerChick(Chick chick);

    @Multipart
    @POST("/")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);

    @GET("delete_chick")
    Call<List<Chick>> deleteChick(String token);
}
