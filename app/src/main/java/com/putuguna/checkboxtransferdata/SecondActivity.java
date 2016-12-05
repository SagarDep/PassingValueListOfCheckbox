package com.putuguna.checkboxtransferdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView lvItemChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvItemChosen = (ListView) findViewById(R.id.listview_detail_item);

        //start convert the string value to array
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MODE_SHARED, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString = sharedPreferences.getString(Constants.KEY_SHARED, null);
        String[] listItem = gson.fromJson(jsonString, String[].class);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
        lvItemChosen.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
