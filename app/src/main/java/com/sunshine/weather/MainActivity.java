package com.sunshine.weather;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<WeatherModel> listWeather = new ArrayList<>();
    private ImageView img_desc;
    private TextView text_desc;
    private TextView text_temp_max;
    private TextView text_temp_min;
    private TextView text_date;
    private Typeface typeface;
    private TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set typeface
        typeface = Typeface.createFromAsset(getAssets(),"dancing.ttf");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        img_desc = (ImageView) findViewById(R.id.image_desc);
        text_desc = (TextView) findViewById(R.id.text_desc);
        text_temp_max = (TextView) findViewById(R.id.text_temp_max);
        text_temp_min = (TextView) findViewById(R.id.text_temp_min);
        text_date = (TextView) findViewById(R.id.text_date);
        textTitle = (TextView) findViewById(R.id.text_title);

        textTitle.setTypeface(typeface);


        new JsonBuilder(new JsonBuilder.JsonBuilderCallback() {
            @Override
            public void onRespons(String json) {
                ParseObjek parseObjek = new ParseObjek();
                try {
                    parseObjek.getWeatherDataFromJson(json);

                    int imageRes = Util.getArtResourceForWeatherCondition(parseObjek.getListWeatherModel().get(0).getWeatherid());
                    img_desc.setImageResource(imageRes);

                    String textDesc = parseObjek.getListWeatherModel().get(0).getDescription();
                    text_desc.setText(textDesc);

                    String textTempMax = Util.setFormatTemperature(parseObjek.getListWeatherModel().get(0).getHigh());
                    text_temp_max.setText(textTempMax);

                    String textTempMin = Util.setFormatTemperature(parseObjek.getListWeatherModel().get(0).getLow());
                    text_temp_min.setText(textTempMin);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(parseObjek.getListWeatherModel().get(0).getDateTime()*1000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd",new Locale("id","ID"));


                    String hari = Util.getDayName(getBaseContext(),parseObjek.getListWeatherModel().get(0).getDateTime());
                    text_date.setText(hari + ", " + simpleDateFormat.format(calendar.getTime()));


                    listWeather.addAll(parseObjek.getListWeatherModel());
                    recyclerAdapter = new RecyclerAdapter(listWeather,getBaseContext());
                    recyclerView.setAdapter(recyclerAdapter);

                } catch (JSONException e){
                    Log.e("JsonExceptionError",e.getMessage());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("ErrorGeneral",throwable.getMessage());
            }
        }).Process();
    }
}
