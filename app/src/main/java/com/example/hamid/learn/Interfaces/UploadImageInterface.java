package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.UploadObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Hamid on 8/1/2017.
 */

public interface UploadImageInterface {
    @Multipart
    @POST("upload.php")
    Call<UploadObject> uploadFile(@Part MultipartBody.Part file,
                                  @Part("name") RequestBody name,
                                  @PartMap() Map<String, RequestBody> partMap,
                                  @PartMap() Map<String, Integer> partmap,
                                  @PartMap() Map<String, Double> locationmap);

}
