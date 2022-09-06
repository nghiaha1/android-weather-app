package android.weather_app_demo.network;

import android.weather_app_demo.hourly.HourlyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String URL = "http://dataservice.accuweather.com";

    @GET("/forecasts/v1/daily/5day/353412?apikey=Pqs1RotgKlpHfOV05V4IQOSkXqkiLoQM&language=vi-vn&metric=true")
    Call<List<HourlyModel>> getDailyApi();

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=Pqs1RotgKlpHfOV05V4IQOSkXqkiLoQM&language=vi-vn&metric=true")
    Call<List<HourlyModel>> getHourlyApi();
}
