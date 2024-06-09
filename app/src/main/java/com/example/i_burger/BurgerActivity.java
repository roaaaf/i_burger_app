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
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;

import java.util.ArrayList;

public class BurgerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static JSONArray jsonArray = new JSONArray();
    RecyclerView recyclerView;
    public static ItemsRecyclerViewAdapter adapter;
    ArrayList<Items> itemsList = new ArrayList<>();
    ImageButton back_btn;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageButton nav_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_burger);

        recyclerView = findViewById(R.id.burger_rv);
        itemsList.add(new Items(R.drawable.item2,R.drawable.burger, "CHESS BURGER", "MEAL", 5, 6));
        itemsList.add(new Items(R.drawable.item3,R.drawable.burger, "BRF BURGER", "MEAL", 4, 6));
        itemsList.add(new Items(R.drawable.iteam1,R.drawable.burger, "BEEF BURGER", "MEAL", 6, 7));
        itemsList.add(new Items(R.drawable.item2,R.drawable.burger, "MASH BURGER", "MEAL", 3, 4));

        adapter = new ItemsRecyclerViewAdapter(itemsList, this);
        recyclerView.setAdapter(adapter);

        back_btn = findViewById(R.id.burger_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
                Intent intent = new Intent(BurgerActivity.this,MainPageActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.addItemDecoration(new SpaceItemDecoration(200));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        //hooks navigation drawer
        drawerLayout = findViewById(R.id.burger_drawer_layout);
        navigationView = findViewById(R.id.burger_nav_view);
        nav_btn = findViewById(R.id.burger_navigation_btn);
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
    public static void placeOrder(){
        for(int i = 0; i< adapter.itemList.size(); i++){
            if(adapter.itemList.get(i).CartQuantity > 0){
                Items Items = new Items(adapter.itemList.get(i).getBackgroungImg(),
                        adapter.itemList.get(i).getItemNameSelected(),
                        adapter.itemList.get(i).getPrice(),
                        adapter.itemList.get(i).getImglogo() );
                Items.CartQuantity = adapter.itemList.get(i).CartQuantity;
                SnaksActivity.itemsListOrder.add(Items);
                SnaksActivity.ordersList.add(Items.getJsonObject().toString());
            }
        }

        jsonArray = new JSONArray(SnaksActivity.ordersList);
    }

    public void openOrderActivity(String orderItems) {
        Intent intent = new Intent(this,OrderActivity.class);
        intent.putExtra("orderItems",orderItems);
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
            Intent intent=new Intent(BurgerActivity.this,ProfileActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_burger) {
            Intent intent=new Intent(BurgerActivity.this,BurgerActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_snacks) {
            Intent intent=new Intent(BurgerActivity.this,SnaksActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_orders) {
            Intent intent=new Intent(BurgerActivity.this, OrderActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_location) {
            Intent intent=new Intent(BurgerActivity.this,LocationsActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.menu_logout) {
            Intent intent=new Intent(BurgerActivity.this,LoginActivity.class);
            startActivity(intent);
        }


        drawerLayout.closeDrawer(Gravity.RIGHT);
        return true;
    }
}