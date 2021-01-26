package com.sample.weatherappwithapiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private Button changeBtn;
    private EditText editTextCityChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextCityChange= findViewById(R.id.edit_text_enter_city);
        changeBtn = findViewById(R.id.change_city_button);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = editTextCityChange.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("city",city);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });
    }





}
