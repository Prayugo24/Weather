package com.sunshine.weather.SqliteContentProvider;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunshine.weather.R;

import java.util.List;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class SqliteRecyclerAdapter extends RecyclerView.Adapter<SqliteRecyclerAdapter.SqliteViewHolder> {

    private List<User> list;

    SqliteRecyclerAdapter(List<User> list){
        this.list = list;
    }

    @Override
    public SqliteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sqldata,parent,false);
        return new SqliteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SqliteViewHolder holder, int position) {
        holder.textViewNama.setText(list.get(position).getNama());
        holder.textViewHobby.setText(list.get(position).getHobby());
    }

    @Override
    public int getItemCount() {
        Log.e("lis",String.valueOf(list.size()));
        return list.size();
    }

    public class SqliteViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNama;
        TextView textViewHobby;

        public SqliteViewHolder(View itemView) {
            super(itemView);
            textViewNama = (TextView) itemView.findViewById(R.id.text_nama);
            textViewHobby = (TextView) itemView.findViewById(R.id.text_hobby);
        }
    }
}
