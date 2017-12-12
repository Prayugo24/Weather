package com.sunshine.weather.SqliteContentProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sunshine.weather.R;
import com.sunshine.weather.SqliteContentProvider.Sqlite.DatabaseImp;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class FormData extends AppCompatActivity {

    private EditText editTextNama;
    private EditText editTextHobby;
    private Button buttonAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data);

        editTextNama = (EditText) findViewById(R.id.edit_text_nama);
        editTextHobby = (EditText) findViewById(R.id.edit_text_hobby);

        buttonAdd = (Button) findViewById(R.id.button_add);
        final User user = new User();


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("editText",editTextNama.getText().toString());
                user.setNama(editTextNama.getText().toString());
                user.setHobby(editTextHobby.getText().toString());
                DatabaseImp.getInstance(getBaseContext()).insertDatabase(user);
                startActivity(new Intent(FormData.this,ActivityMainMenu.class));
                finish();
            }
        });


    }
}
