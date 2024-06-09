package com.example.i_burger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    public static int totalPrice = 0;

    ArrayList<OrderItems> orderList = new ArrayList<>();
    ImageButton back_btn;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageButton nav_btn,checkout_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.orders_rv);
        checkout_btn = findViewById(R.id.order_check_out);
        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutOrder(v);
            }
        });

        back_btn = findViewById(R.id.order_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity(v);
            }});

        getOrderItemData();

        recyclerView.addItemDecoration(new OrderItemDecoration(200));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        //hooks navigation drawer
        drawerLayout = findViewById(R.id.order_drawer_layout);
        navigationView = findViewById(R.id.order_nav_view);
        nav_btn = findViewById(R.id.order_navigation_btn);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.menu_profile);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });


    }


    private void getOrderItemData() {
        //orderList.clear();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String orderItems = extras.getString("orderItems", null);
            if (orderItems != null && orderItems.length() > 0) {
                try {
                    JSONArray jsonOrderItems = new JSONArray(orderItems);

                    for (int i = 0; i < jsonOrderItems.length(); i++) {
                        JSONObject jsonObject = new JSONObject(jsonOrderItems.getString(i));
                        OrderItems items = new OrderItems( jsonObject.getString("ProductName"),
                                jsonObject.getInt("ProductLogoImg"),
                                jsonObject.getInt("ProductImage"),
                                jsonObject.getInt("ProductPrice"));
                        items.CartQuantity = jsonObject.getInt("CartQuantity");
                        orderList.add(items);
                    }


                    if (orderList.size() > 0) {
                        OrdersRecyclerViewAdapter adapter = new OrdersRecyclerViewAdapter(this,orderList);
                        recyclerView.setAdapter(adapter);
                    }

                } catch (Exception e) {
                }
            }
        }
    }


    private void openHomeActivity(View view) {
        Intent intent = new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }

    private void checkOutOrder(View view) {
        Intent intent = new Intent(this,CheckOutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_profile){
            Intent intent=new Intent(OrderActivity.this,ProfileActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_burger) {
            Intent intent=new Intent(OrderActivity.this,BurgerActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_snacks) {
            Intent intent=new Intent(OrderActivity.this,SnaksActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_orders) {
            Intent intent=new Intent(OrderActivity.this, OrderActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_location) {
            Intent intent=new Intent(OrderActivity.this,LocationsActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.menu_logout) {
            Intent intent=new Intent(OrderActivity.this,LoginActivity.class);
            startActivity(intent);
        }


        drawerLayout.closeDrawer(Gravity.RIGHT);
        return true;
    }
}