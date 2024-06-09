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
import android.widget.TextView;

import java.util.ArrayList;

public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<OrdersRecyclerViewAdapter.OrdersViewHolder> {

    Context context;
    ArrayList<OrderItems> ordersList;
    public  static int checkoutPrice = 0 ;

    public OrdersRecyclerViewAdapter(Context context, ArrayList<OrderItems> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_orders_items, null, false);
        OrdersViewHolder ordersViewHolder =
                new OrdersViewHolder(view);
        return ordersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        OrderItems oPosition = ordersList.get(position);
        oPosition.TotalPrice = oPosition.CartQuantity * oPosition.price;
        holder.ordername.setText(oPosition.getOrderName());
        holder.orderbackgroundimg.setImageResource(oPosition.getOrderBackgroundImg());
        holder.ordercount_tv.setText(oPosition.getCartQuantity()+"");
        holder.ordertotalprice.setText(oPosition.getTotalPrice()+" JD");
        holder.orderlogoimg.setImageResource(oPosition.getOrderlogoImg());

        checkoutPrice =checkoutPrice + oPosition.getTotalPrice();
        holder.plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateQuantity(position, holder.ordercount_tv,holder.ordertotalprice, 1);
            }
        });
        holder.minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(position, holder.ordercount_tv,holder.ordertotalprice, -1);
            }
        });
    }
    public OrderItems getItem(int position) {
        return ordersList.get(position);
    }


    private void updateQuantity(int position, TextView ordercount_tv,TextView ordertotalprice, int value) {
        OrderItems orderItem = getItem(position);
        if (value > 0) {
            orderItem.CartQuantity = orderItem.CartQuantity + 1;
            orderItem.TotalPrice = orderItem.CartQuantity * orderItem.price;
            checkoutPrice =checkoutPrice + orderItem.price;
        } else {
            if (orderItem.CartQuantity > 0) {
                orderItem.CartQuantity = orderItem.CartQuantity - 1;
                orderItem.TotalPrice = orderItem.TotalPrice - orderItem.price;
                checkoutPrice =checkoutPrice - orderItem.price;

            }
        }
        ordertotalprice.setText(orderItem.getTotalPrice()+" JD");
        ordercount_tv.setText(orderItem.CartQuantity + "");

    }


    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    class OrdersViewHolder extends RecyclerView.ViewHolder {

        ImageView orderbackgroundimg, orderlogoimg;
        TextView ordername, ordercount_tv ,ordertotalprice;
        ImageButton plus_btn,minus_btn;


        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            orderbackgroundimg = itemView.findViewById(R.id.order_background_image);
            ordertotalprice = itemView.findViewById(R.id.order_snack_Price);
            orderlogoimg = itemView.findViewById(R.id.order_logo_image);
            ordername = itemView.findViewById(R.id.order_snack_name);
            ordercount_tv = itemView.findViewById(R.id.order_snack_count);
            plus_btn = itemView.findViewById(R.id.order_increse_btn);
            minus_btn = itemView.findViewById(R.id.order_decrese_btn);
        }
    }
}
