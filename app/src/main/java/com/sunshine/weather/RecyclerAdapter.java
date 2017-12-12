package com.sunshine.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderRecyclerView> {

    private List<WeatherModel> listWeather;
    private Context context;

    RecyclerAdapter(List<WeatherModel> list, Context context){
        this.listWeather = list;
        this.context = context;
    }

    @Override
    public ViewHolderRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new ViewHolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRecyclerView holder, int position) {
        holder.imgView.setImageResource(R.drawable.art_clear);

        //set Image from resId position
        int resId = Util.getArtResourceForWeatherCondition(listWeather.get(position).getWeatherid());
        holder.imgView.setImageResource(resId);

        //set text field String for day or status
        String dayStatus = Util.getDayName(context,listWeather.get(position).getDateTime());
        holder.textView1.setText(dayStatus);

        //set text field String for weather description
        String description = listWeather.get(position).getDescription();
        holder.textView2.setText(description);

        //set text field String to celcius degree high
        String celciusDegreeHigh = Util.setFormatTemperature(listWeather.get(position).getHigh());
        holder.textView3.setText(celciusDegreeHigh);

        //set text field String to celcius degree low
        String celciusDegreeLow = Util.setFormatTemperature(listWeather.get(position).getLow());
        holder.textView4.setText(celciusDegreeLow);
    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    class ViewHolderRecyclerView extends RecyclerView.ViewHolder{

        private ImageView imgView;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;

        public ViewHolderRecyclerView(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);

        }
    }
}
