package com.daffaalam.biomaker.network;

import com.daffaalam.biomaker.helper.DatabaseHelper;
import com.daffaalam.biomaker.model.ReadPublicModel;
import com.daffaalam.biomaker.model.ShareModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestAPI {

    @GET("api_read.php/")
    Call<List<ReadPublicModel>> readPublicBio();

    @FormUrlEncoded
    @POST("api_create.php/")
    Call<ShareModel> sharePublicBio(
            @Field(DatabaseHelper.COLUMN_ID) String id,
            @Field(DatabaseHelper.COLUMN_NAME) String name,
            @Field(DatabaseHelper.COLUMN_BDAY) String bday,
            @Field(DatabaseHelper.COLUMN_GENDER) String gender,
            @Field(DatabaseHelper.COLUMN_ADDRESS) String address,
            @Field(DatabaseHelper.COLUMN_PHONE) String phone,
            @Field(DatabaseHelper.COLUMN_MAIL) String mail,
            @Field(DatabaseHelper.COLUMN_RELIGION) String religion,
            @Field(DatabaseHelper.COLUMN_BLOOD) String blood,
            @Field(DatabaseHelper.COLUMN_WEBSITE) String website,
            @Field(DatabaseHelper.COLUMN_SHOES) String shoes,
            @Field(DatabaseHelper.COLUMN_FACEBOOK) String facebook,
            @Field(DatabaseHelper.COLUMN_TWITTER) String twitter,
            @Field(DatabaseHelper.COLUMN_LINE) String line,
            @Field(DatabaseHelper.COLUMN_TELEGRAM) String telegram,
            @Field(DatabaseHelper.COLUMN_INSTAGRAM) String instagram,
            @Field(DatabaseHelper.COLUMN_WHATSAPP) String whatsapp
    );
}
