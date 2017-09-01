package co.leinaro.gallera;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ChickListPresenter {
    private final GalleraListView mView;
    private final ApiGallera mApi;

    public ChickListPresenter(GalleraListView mView, ApiGallera mApi) {
        this.mView = mView;
        this.mApi = mApi;
    }

    public void getChicks() {
        mApi.getChicks().enqueue(new Callback<ApiGallera.Responses>() {
            @Override
            public void onResponse(Call<ApiGallera.Responses> call, Response<ApiGallera.Responses> response) {
                mView.getChickSuccess(response.body().chickens);
            }

            @Override
            public void onFailure(Call<ApiGallera.Responses> call, Throwable t) {

            }
        });
    }
}
