package com.sunshine.weather.SqliteContentProvider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunshine.weather.R;
import com.sunshine.weather.SqliteContentProvider.Sqlite.DatabaseImp;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class ActivityMainMenu extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private SqliteRecyclerAdapter sqliteRecyclerAdapter;
    private List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_sqlite);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_sqldata);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list.addAll(DatabaseImp.getInstance(this).getAllUser());
        sqliteRecyclerAdapter = new SqliteRecyclerAdapter(list);
        recyclerView.setAdapter(sqliteRecyclerAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMainMenu.this,FormData.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
