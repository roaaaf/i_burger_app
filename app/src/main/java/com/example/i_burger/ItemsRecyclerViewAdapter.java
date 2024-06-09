package com.example.i_burger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<ItemsRecyclerViewAdapter.SnaksViewHolder> {


    ArrayList<Items> itemList;
    Context context;

    public ItemsRecyclerViewAdapter(ArrayList<Items> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public SnaksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items, parent, false);
        SnaksViewHolder snaksViewHolder = new SnaksViewHolder(view);
        return snaksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SnaksViewHolder holder, int position) {

        Items sPosition = itemList.get(position);
        holder.counterBackgroundImg.setImageResource(sPosition.getBackgroundBtn());
        holder.price1_tv.setText(sPosition.getItemPrice1() + " JD");
        holder.price2_tv.setText(sPosition.getItemPrice2() + " JD");
        holder.counter_tv.setText(sPosition.getCartQuantity() + "");
        holder.radioButton.setText(sPosition.getItemName1());
        holder.radioButton2.setText(sPosition.getItemName2());
        holder.backgroundImage.setImageResource(sPosition.getBackgroungImg());

        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateQuantity(position, holder.counter_tv, 1);
            }
        });

        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(position, holder.counter_tv, -1);

            }
        });

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_btn) {
                    sPosition.setItemNameSelected(sPosition.getItemName1());
                    sPosition.setPrice(sPosition.getItemPrice1());

                } else {
                    sPosition.setItemNameSelected(sPosition.getItemName2());
                    sPosition.setPrice(sPosition.getItemPrice2());
                }
                //Toast.makeText(context, EXTRA_SNACK_NAME_SELECTED, Toast.LENGTH_LONG).show();

            }
        });
    }

    private void updateQuantity(int position, TextView counter_tv, int value) {
        Items snackItem = getItem(position);
        if (value > 0) {
            snackItem.CartQuantity = snackItem.CartQuantity + 1;
        } else {
            if (snackItem.CartQuantity > 0) {
                snackItem.CartQuantity = snackItem.CartQuantity - 1;
            }
        }
        counter_tv.setText(snackItem.CartQuantity + "");
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public Items getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class SnaksViewHolder extends RecyclerView.ViewHolder {

        RadioGroup radioGroup;
        RadioButton radioButton, radioButton2;
        ImageView backgroundImage, counterBackgroundImg;
        ImageButton plusBtn, minusBtn;
        TextView counter_tv, price1_tv, price2_tv;

        public SnaksViewHolder(@NonNull View itemView) {
            super(itemView);

            radioGroup = itemView.findViewById(R.id.radioGroup);
            radioButton = itemView.findViewById(R.id.radio_btn);
            radioButton2 = itemView.findViewById(R.id.radio_btn2);
            backgroundImage = itemView.findViewById(R.id.background_image);
            counterBackgroundImg = itemView.findViewById(R.id.imageback);
            plusBtn = itemView.findViewById(R.id.increase_button);
            minusBtn = itemView.findViewById(R.id.decrease_button);
            counter_tv = itemView.findViewById(R.id.counter_tv);
            price1_tv = itemView.findViewById(R.id.snak_price1);
            price2_tv = itemView.findViewById(R.id.snak_price2);

        }
    }
    }
