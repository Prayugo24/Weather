package com.sunshine.weather;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class WeatherModel {
    private long dateTime;
    private double pressure;
    private int humidity;
    private double windspeedp;
    private double winddirection;

    private double high;
    private double low;

    private String description;
    private int weatherid;

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindspeedp() {
        return windspeedp;
    }

    public void setWindspeedp(double windspeedp) {
        this.windspeedp = windspeedp;
    }

    public double getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(double winddirection) {
        this.winddirection = winddirection;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeatherid() {
        return weatherid;
    }

    public void setWeatherid(int weatherid) {
        this.weatherid = weatherid;
    }
}
