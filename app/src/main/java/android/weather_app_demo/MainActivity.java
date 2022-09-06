package android.weather_app_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.weather_app_demo.adapter.HourlyAdapter;
import android.weather_app_demo.hourly.HourlyModel;
import android.weather_app_demo.network.ApiManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHour;
    private TextView tvStatus, tvTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvTemperature = findViewById(R.id.tvTemperature);

        getHours();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        rvHour = (RecyclerView) findViewById(R.id.rvHour);
        rvHour.setLayoutManager(layoutManager);
    }

    private void getHours() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);
        service.getHourlyApi().enqueue(new Callback<List<HourlyModel>>() {
            @Override
            public void onResponse(Call<List<HourlyModel>> call, Response<List<HourlyModel>> response) {
                if (response.body() == null) return;

                List<HourlyModel> hourlyModelList = response.body();
                HourlyAdapter adapter = new HourlyAdapter(MainActivity.this, hourlyModelList);
                rvHour.setAdapter(adapter);
                HourlyModel weather = hourlyModelList.get(0);
                tvTemperature.setText(weather.getTemperature().getValue().intValue() + "°");
                tvStatus.setText(weather.getIconPhrase());
            }

            @Override
            public void onFailure(Call<List<HourlyModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lost Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}