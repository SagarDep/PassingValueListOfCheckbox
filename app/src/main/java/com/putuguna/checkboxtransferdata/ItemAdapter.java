package com.putuguna.checkboxtransferdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by putuguna on 04/12/16.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {

    private List<String> itemList;
    private Context context;
    private List<String> itemSelected = new ArrayList<>();

    public ItemAdapter(List<String> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detail, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        final String itemName = itemList.get(position);

        holder.tvItemName.setText(itemName);

        final List<String> listItemChosen = new ArrayList<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.MODE_SHARED, Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        final Gson gson = new Gson();

        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    itemSelected.add(itemName);

                    String jsonString = gson.toJson(getSelectedString());
                    edit.putString(Constants.KEY_SHARED,jsonString);
                    edit.commit();
                    System.out.println("JSON : " + jsonString);
                    System.out.println("SIZE : " + listItemChosen.size());
                }else{
                    itemSelected.remove(itemName);
                    String jsonString = gson.toJson(getSelectedString());
                    edit.putString(Constants.KEY_SHARED,jsonString);
                    edit.commit();
                    System.out.println("JSON : " + jsonString);
                    System.out.println("SIZE : " + listItemChosen.size());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * create inner class for custom holder
     */
    public static class Holder extends RecyclerView.ViewHolder{

        public TextView tvItemName;
        public CheckBox cbItem;

        public Holder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            cbItem = (CheckBox) itemView.findViewById(R.id.checkbox_item);
        }
    }

    /**
     * create this method for return new value in list item selected
     * @return
     */
    private List<String> getSelectedString(){
        return itemSelected;
    }
}
