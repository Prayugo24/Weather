package com.sunshine.weather;

import android.content.Context;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class Util {

    /**
     * Convert kelvin to celcius and add degree symbol
     * @param temperature
     * @return string value of celcius and degree symbol
     */
    public static String setFormatTemperature(double temperature){
        DecimalFormat decimalFormat = new DecimalFormat(".#");
        String suffix = "\u00B0";
        double celcius = temperature - 273.15;
        return String.valueOf(decimalFormat.format(celcius)) + suffix + "C";
    }



    /**
     * Given a day, returns just the name to use for that day.
     * E.g "today", "tomorrow", "wednesday".
     * @param context
     * @param dateInMilis
     * @return
     */
    public static String getDayName(Context context, long dateInMilis){

        Calendar myDate = Calendar.getInstance();
        myDate.setTimeInMillis(dateInMilis*1000);

        Date date = new Date();
        Calendar myCurrentDate = Calendar.getInstance();
        myCurrentDate.setTime(date);
        int julianDay = myDate.get(Calendar.DAY_OF_MONTH);
        int currentJulianDay = myCurrentDate.get(Calendar.DAY_OF_MONTH);

        if (julianDay == currentJulianDay){
            return "Sekarang";
        } else if (julianDay == currentJulianDay + 1 ){
            return "Besok";
        } else {
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", new Locale("id","ID"));
            return dayFormat.format(myDate.getTime());
        }
    }


    /**
     * Helper method to provide the art resource id according to the weather condition id returned
     * by the OpenWeatherMap call.
     * @param weatherId
     * @return get resource id to corresponden icon open weather art
     */
    public static int getArtResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        }
        return -1;
    }
}
