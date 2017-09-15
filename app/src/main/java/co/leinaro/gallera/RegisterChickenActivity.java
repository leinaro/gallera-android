package co.leinaro.gallera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.File;

import co.leinaro.gallera.api.client.ApiGallera;
import co.leinaro.gallera.entities.Chick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterChickenActivity extends AppCompatActivity {

    public static final int READ_EXTERNAL_STORAGE = 200;
    public static final int PICK_IMAGE = 100;

    ImageView image;
    private File file;
    private MaterialEditText coliseoResponsible;
    private MaterialEditText coliseoPlateNumber;
    private MaterialEditText breederPlateNumber;
    private MaterialEditText cresta;
    private MaterialEditText pata;
    private MaterialEditText ownerName;
    private MaterialEditText weight;
    private MaterialEditText color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_chicken);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.btn_upload);
        image = (ImageView) findViewById(R.id.chicken_image);
        coliseoResponsible = (MaterialEditText) findViewById(R.id.coliseo_responsible);
        coliseoPlateNumber = (MaterialEditText) findViewById(R.id.coliseo_plate_number);
        breederPlateNumber = (MaterialEditText) findViewById(R.id.breeder_plate_number);
        ownerName = (MaterialEditText) findViewById(R.id.owner_name);
        weight = (MaterialEditText) findViewById(R.id.weight);
        color = (MaterialEditText) findViewById(R.id.color);
        pata = (MaterialEditText) findViewById(R.id.pata);
        cresta = (MaterialEditText) findViewById(R.id.cresta);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                registerChick();

            }
        });

        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getImageFromGallery();
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            file = new File(filePath);
            Log.d("THIS", data.getData().getPath());
//            Picasso.with(this).load(
//                    data.getData().getPath()
//            ).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher_round).into(image);
        }
    }

    public void registerChick() {
        ApiGallera api = new ApiGallera(this);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "image");

        Gson gson = new Gson();

        RequestBody chick = RequestBody.create(
                MediaType.parse("application/json"),
                gson.toJson(new Chick("Raul", "715", "Espartaco", "09042017123", "Adela", "PINTO", "ROSA", "PATIBLANCO")));


//        register_date:2012-09-04T00:00:00
//        coliseo_responsible:Adela
//        coliseo_plate_number:09042017123
//        breeder_plate_number:715
//        owner_name:Raul
//        weight:2.15
//        color:PINTO
//        pata:PATIBLANCO
//        breeder_name:Espartaco
//        cresta:ROSA

        api.registerChick(name, body, chick).enqueue(new Callback<ApiGallera.Responses>() {
            @Override
            public void onResponse(Call<ApiGallera.Responses> call, Response<ApiGallera.Responses> response) {

                Log.d("THIS", response.code() + "");
                Log.d("THIS", response.body() + "");


//                    Picasso.with(imageView.getContext()).load(
//                            "https://oggiscienza.files.wordpress.com/2010/01/215310099_5ccec08156.jpg?w=350&h=232"
//                    ).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher_round).into(imageView);
            }

            @Override
            public void onFailure(Call<ApiGallera.Responses> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getImageFromGallery();
                }
                return;
        }
    }

    public void getImageFromGallery() {
        int permissionReadSms = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionReadSms != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
            return;
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
        }
    }

}
