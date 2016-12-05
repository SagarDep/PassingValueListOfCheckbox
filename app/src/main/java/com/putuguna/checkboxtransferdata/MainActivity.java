package com.putuguna.checkboxtransferdata;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvItem;
    private List<String> listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvItem = (RecyclerView) findViewById(R.id.rv_item_electronic);

        listItem = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add_item){
            addItem();
        }else if(id==R.id.done_add_item){
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * this method used to add item
     */
    private void addItem(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_item);

        final EditText etInputItem = (EditText) dialog.findViewById(R.id.edit_text_add_item);
        Button btnSave = (Button) dialog.findViewById(R.id.btn_save);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = etInputItem.getText().toString();
                if(TextUtils.isEmpty(item)){
                    Toast.makeText(MainActivity.this, "Item name is required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listItem.add(item);
                ItemAdapter adapter = new ItemAdapter(listItem, MainActivity.this);
                rvItem.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvItem.setItemAnimator(new DefaultItemAnimator());
                rvItem.setAdapter(adapter);
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
