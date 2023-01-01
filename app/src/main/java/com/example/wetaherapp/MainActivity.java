package com.example.wetaherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wetaherapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        disposable = weatherAPI.getWeather(WeatherAPI.CITY, WeatherAPI.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError,this::onComplete, this::onSubscribe);

//        List<WeatherItem> list = new ArrayList<>();
//        Adapter adapter = new Adapter();
    }

    private void onComplete(){
        Toast.makeText(this, "New query", Toast.LENGTH_SHORT).show();
    }

    private void  onSubscribe(Disposable d){

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        disposable.dispose();
    }

    private void onSuccess(WeatherResponse response){

        Adapter adapter = new Adapter(response.list);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        binding.recyclerView.setAdapter(adapter);
        binding.progressBar.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
    }

    private void onError(Throwable t){
        binding.textView.setText(t.toString());
        Log.wtf("MyError", t.toString());
        binding.textView.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility((View.GONE));
    }
}