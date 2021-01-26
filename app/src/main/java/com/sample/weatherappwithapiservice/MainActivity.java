package com.sample.weatherappwithapiservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final int LAUNCH_SECOND_ACTIVITY = 1;
    private TextView textViewTemp, textViewHumidity, textViewPressure, textViewWind, textViewDescription, textViewCity;
    private ImageView weatherImageIcon, settingsImageIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCity = findViewById(R.id.text_view_city);
        textViewTemp = findViewById(R.id.text_view_show_temp);
        textViewHumidity = findViewById(R.id.humidity_lvl);
        textViewPressure = findViewById(R.id.pressure_lvl);
        textViewWind = findViewById(R.id.wind_lvl);
        textViewDescription = findViewById(R.id.description_weather);
        weatherImageIcon = findViewById(R.id.icon_weather);
        settingsImageIcon = findViewById(R.id.settings_button_icon);


        settingsImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
            }
        });

    }

    public int showIcon(Post post){
        if(post != null){
            if(post.getWeather().get(0).getMain().equals("Clouds")){
                return R.drawable.ic_cloud_black_24dp;
            }else if(post.getWeather().get(0).getMain().equals("Sunny")){
                return R.drawable.ic_wb_sunny_black_24dp;
            }else if(post.getWeather().get(0).getMain().equals("Rain")){
                return R.drawable.ic_opacity_black_24dp;
            }
        }
        return R.drawable.ic_cloud_black_24dp;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY){
            if (resultCode == Activity.RESULT_OK){

                String city = data.getStringExtra("city");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.openweathermap.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                JsonPlaceHoldeApi jsonPlaceHoldeApi = retrofit.create(JsonPlaceHoldeApi.class);

                Call<Post> call = jsonPlaceHoldeApi.getPost(city,"68441eefb97a4348b78d463bffec9edc");

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(!response.isSuccessful()){
                            textViewTemp.setText("Code: " + response.code());
                            return;
                        }

                        Post currentPost = response.body();

                        double currentTemp =  currentPost.getPostMain().getTemp() - 273.15;
                        double currentWindSpeed = currentPost.getWind().getSpeed() * 3.6;

                        String content = String.format("%.0f", currentTemp) + "°C" ;
                        String content2 = String.format("%.2f", currentWindSpeed) + "KM/H";

                        textViewCity.setText(currentPost.getName());
                        textViewTemp.setText(content);
                        textViewHumidity.setText(String.valueOf(currentPost.getPostMain().getHumidity()) + "%");
                        textViewPressure.setText(String.valueOf(currentPost.getPostMain().getPressure()));
                        textViewWind.setText(content2);
                        textViewDescription.setText(currentPost.getWeather().get(0).getMain());
                        weatherImageIcon.setImageResource(showIcon(currentPost));

                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        textViewTemp.setText(t.getMessage());
                    }
                });
            }
        }
    }
}
