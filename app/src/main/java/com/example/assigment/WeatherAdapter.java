package com.example.assigment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter {
    private Activity activity;


    private List<Wheather> listWeather;
    public WeatherAdapter(Activity activity, List<Wheather> listWeather) {
        this.activity = activity;
        this.listWeather = listWeather;
    }
    public void reloadData(List<Wheather> list){
        this.listWeather=list;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.item_layout,parent,false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     ViewHolder vh = (ViewHolder) holder;
     Wheather wheather = listWeather.get(position);
     vh.tvTime.setText(convertTime(wheather.getDateTime()));
     vh.tvTem.setText(wheather.getTemperature().getValue()+"");
     String url = "";
     if(wheather.getWeatherIcon()<10){
         url="https://developer.accuweather.com/sites/default/files/0"+wheather.getWeatherIcon() +"-s.png";

     }else {
         url="https://developer.accuweather.com/sites/default/files/"+wheather.getWeatherIcon() +"-s.png";
     }
     Glide.with(activity).load(url).into(vh.IvIcon);

    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
         private TextView tvTime;
         private ImageView IvIcon;
         private TextView tvTem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IvIcon=(ImageView) itemView.findViewById(R.id.Ivicon);
            tvTem=(TextView)itemView.findViewById(R.id.tvTem);
            tvTime=(TextView)itemView.findViewById(R.id.tvTime);


        }
    }
    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }


}
