package android.weather_app_demo.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.weather_app_demo.R;
import android.weather_app_demo.daily.DailyModel;
import android.weather_app_demo.hourly.HourlyModel;
import android.weather_app_demo.util.DateTimeConverter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<HourlyModel> hourlyModelList;
    private List<DailyModel> dailyModels;

    public HourlyAdapter(Activity activity, List<HourlyModel> hourlyModelList) {
        this.activity = activity;
        this.hourlyModelList = hourlyModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_hour, parent, false);
        HourlyViewHolder holder = new HourlyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HourlyModel model = hourlyModelList.get(position);

        HourlyViewHolder vh = (HourlyViewHolder) holder;
        vh.tvTime.setText(DateTimeConverter.convertTime(model.getDateTime()));
        vh.tvTemperature.setText(model.getTemperature().getValue() + "");
        String url = "";

        if (model.getWeatherIcon() > 10) {
            url = "https://developer.accuweather.com/sites/default/files/" + model.getWeatherIcon() + "-s.png";
        }else {
            url = "https://developer.accuweather.com/sites/default/files/0" + model.getWeatherIcon() + "-s.png";
        }
        Glide.with(activity).load(url).into(vh.ivWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return hourlyModelList.size();
    }

    public class HourlyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvTemperature;
        ImageView ivWeatherIcon;
        public HourlyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            ivWeatherIcon = itemView.findViewById(R.id.ivWeatherIcon);
        }
    }
}
