package com.example.wetaherapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetaherapp.databinding.WeatherListItemBinding;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.WeatherItemViewHolder> {
    List<WeatherItem> data;
    public Adapter(List<WeatherItem> data){
        this.data = data;
    }

    @NonNull
    @Override
    public WeatherItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherListItemBinding binding = WeatherListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new WeatherItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherItemViewHolder holder, int position) {
        holder.dateTime.setText( data.get(position).dt_txt);
        holder.temp.setText(Long.toString(Math.round(data.get(position).main.temp - 273.15)));
        holder.weather.setText(data.get(position).weather.get(0).main);
        holder.humidity.setText(data.get(position).main.humidity.toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class WeatherItemViewHolder extends RecyclerView.ViewHolder{
        TextView dateTime;
        TextView temp;
        TextView weather;
        TextView humidity;
        public WeatherItemViewHolder(WeatherListItemBinding binding){
            super(binding.getRoot());
            dateTime = binding.dateTime;
            temp = binding.temp;
            weather = binding.weather;
            humidity = binding.humidity;
        }
    }

}
