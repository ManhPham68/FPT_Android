package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rvWeather;
    private TextView tvIconPhrase;
    private  TextView tvTemperature;
    List<Wheather> listWeather;
    WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvIconPhrase =findViewById(R.id.tvIconPhrase);
        tvTemperature=findViewById(R.id.tvTemperature);
        rvWeather=(RecyclerView)findViewById(R.id.rvWeather);
        getWeather();
        listWeather=new ArrayList<>();
        adapter = new WeatherAdapter(MainActivity.this,listWeather);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        rvWeather.setLayoutManager(linearLayout);

    }
    private void getWeather(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);
        service.getWheather().enqueue(new Callback<List<Wheather>>() {
            @Override
            public void onResponse(Call<List<Wheather>> call, Response<List<Wheather>> response) {
                if(response.body()!=null){
                    List<Wheather> listWeather = response.body();
                    WeatherAdapter adapter = new WeatherAdapter(MainActivity.this,listWeather);
                    adapter.reloadData(listWeather);
                    rvWeather.setAdapter(adapter);
                    Wheather wheather = listWeather.get(0);
                    tvTemperature.setText(wheather.getTemperature().getValue().intValue()+"°");
                    tvIconPhrase.setText(wheather.getIconPhrase());

                }
            }

            @Override
            public void onFailure(Call<List<Wheather>> call, Throwable t) {
                Log.d("Test", "onFailure: "+t);
            }
        });

    }
}