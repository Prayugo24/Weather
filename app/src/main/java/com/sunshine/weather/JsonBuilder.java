package com.sunshine.weather;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class JsonBuilder {

    public interface JsonBuilderCallback{
        void onRespons(String json);
        void onError(Throwable throwable);
    }

    private JsonBuilderCallback jsonBuilderCallback;

    JsonBuilder(JsonBuilderCallback jsonBuilderCallback){
        this.jsonBuilderCallback = jsonBuilderCallback;
    }

    public void Process(){
        String jsonString = BuilrURL();
        new ProcessFetchJson().execute(jsonString);

    }


    public String BuilrURL(){
        final String CITY_NAME = "yogyakarta,ID";
        final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast?";
        final String PARAM_Q = "q";
        final String PARAM_APPID = "appid";

        Uri buildUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_Q,CITY_NAME)
                .appendQueryParameter(PARAM_APPID,BuildConfig.API_KEY)
                .build();

        Log.e("url",buildUri.toString());

        return buildUri.toString();
    }

    public class ProcessFetchJson extends AsyncTask<String,String,String>{

        private HttpURLConnection httpURLConnection;
        private BufferedReader bufferedReader;
        private String json;


        @Override
        protected String doInBackground(String... strings) {
            try {
                URL urls = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) urls.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();

                if (inputStream == null){
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line + "\n");
                }

                json = stringBuffer.toString();


            }catch (MalformedURLException e){
                Log.e("MalfofmedError",e.getMessage());
                jsonBuilderCallback.onError(e);
            } catch (IOException ex){
                Log.e("IOException",ex.getMessage());
                jsonBuilderCallback.onError(ex);
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            jsonBuilderCallback.onRespons(s);
        }
    }
}
