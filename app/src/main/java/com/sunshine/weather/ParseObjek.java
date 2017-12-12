package com.sunshine.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class ParseObjek {

    private List<WeatherModel> listWeatherModel;

    public void getWeatherDataFromJson(String jsonForecast) throws JSONException{

        final String OW_LIST = "list";
        final String OW_DT = "dt";
        final String OW_MAIN = "main";
        final String OW_TEMP_MAX = "temp_max";
        final String OW_TEMP_MIN = "temp_min";
        final String OW_ID = "id";
        final String OW_WEATHER = "weather";
        final String OW_DESCRIPTION = "description";

        try {
            JSONObject forecastJson = new JSONObject(jsonForecast);
            JSONArray listJson = forecastJson.getJSONArray(OW_LIST);

            listWeatherModel = new ArrayList<>();

            for (int i = 0;i<listJson.length();i++){

                long dt;
                double temp_max;
                double temp_min;
                int id;
                String desc;

                JSONObject listForecastParse = listJson.getJSONObject(i);
                dt = listForecastParse.getLong(OW_DT);
                JSONObject mainForecast = listForecastParse.getJSONObject(OW_MAIN);
                temp_max = mainForecast.getDouble(OW_TEMP_MAX);
                temp_min = mainForecast.getDouble(OW_TEMP_MIN);

                JSONArray listWeather = listForecastParse.getJSONArray(OW_WEATHER);
                id = listWeather.getJSONObject(0).getInt(OW_ID);
                desc = listWeather.getJSONObject(0).getString(OW_DESCRIPTION);

                WeatherModel weatherModel = new WeatherModel();
                weatherModel.setDateTime(dt);
                weatherModel.setDescription(desc);
                weatherModel.setHigh(temp_max);
                weatherModel.setLow(temp_min);
                weatherModel.setWeatherid(id);

                listWeatherModel.add(weatherModel);
            }

        } catch (JSONException e){
            Log.e("JsonParseError",e.getMessage());
        }

    }

    public List<WeatherModel> getListWeatherModel() {
        return listWeatherModel;
    }

}
