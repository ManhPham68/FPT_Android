package com.example.assigment;
import retrofit2.Call;
import java.util.List;
import retrofit2.http.GET;

public interface ApiManager {
    public static String BASE_URL="https://dataservice.accuweather.com/";
    @GET("forecasts/v1/hourly/12hour/353412?apikey=J6w1GNt477nVj2dPDBYfBn386A7TbEBf&language=vi-vn&metric=true")
    Call<List<Wheather>> getWheather();

}
