package com.example.corona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {
    @GET("patient")
    Call<List<PatientData>> getPatient();

    @GET("sum")
    Call<SumData> getSum();

    @GET("total")
    Call<TotalData> getTotal();
}
