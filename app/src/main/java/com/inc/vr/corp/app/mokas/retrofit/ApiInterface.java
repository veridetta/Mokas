package com.inc.vr.corp.app.mokas.retrofit;

import com.inc.vr.corp.app.mokas.adapter.Motor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("volley_array.json")
    Call<List<Motor>> getMotor();
}
