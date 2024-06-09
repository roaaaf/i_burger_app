package com.example.i_burger;

import static com.example.i_burger.BurgerActivity.adapter;

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

public class SnaksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static JSONArray jsonArray;
    RecyclerView recyclerView;
    ArrayList<Items> itemsList = new ArrayList<>();
    public static ArrayList<Items> itemsListOrder = new ArrayList<>();
    public static ArrayList<String> ordersList = new ArrayList<String>();

    ImageButton back_btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageButton nav_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_snaks);

        recyclerView = findViewById(R.id.snack_rv);
        itemsList.add(new Items(R.drawable.item2,R.drawable.snaks, "SHAWERMA", "MEAL", 5, 6));
        itemsList.add(new Items(R.drawable.item3,R.drawable.snaks, "HOTDOG", "MEAL", 4, 6));
        itemsList.add(new Items(R.drawable.iteam1,R.drawable.snaks, "CHRISPY", "MEAL", 6, 7));
        itemsList.add(new Items(R.drawable.item2,R.drawable.snaks, "FAHETA", "MEAL", 3, 4));

        adapter = new ItemsRecyclerViewAdapter(itemsList, this);
        recyclerView.setAdapter(adapter);

        back_btn = findViewById(R.id.snack_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
                Intent intent = new Intent(SnaksActivity.this,MainPageActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.addItemDecoration(new SpaceItemDecoration(200));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        //hooks navigation drawer
        drawerLayout = findViewById(R.id.snack_drawer_layout);
        navigationView = findViewById(R.id.snack_nav_view);
        nav_btn = findViewById(R.id.snack_navigation_btn);
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

    public static void placeOrder() {

        for(int i=0; i<adapter.itemList.size();i++){
            if(adapter.itemList.get(i).CartQuantity > 0){
                Items Items = new Items(adapter.itemList.get(i).getBackgroungImg(),
                        adapter.itemList.get(i).getItemNameSelected(),
                        adapter.itemList.get(i).getPrice(),
                        adapter.itemList.get(i).getImglogo() );
                Items.CartQuantity = adapter.itemList.get(i).CartQuantity;
                itemsListOrder.add(Items);
                ordersList.add(Items.getJsonObject().toString());
            }
        }

        jsonArray = new JSONArray(ordersList);
    }



    private void openOrderActivity(String orderItems) {
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
            Intent intent=new Intent(SnaksActivity.this,ProfileActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_burger) {
            Intent intent=new Intent(SnaksActivity.this,BurgerActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_snacks) {
            Intent intent=new Intent(SnaksActivity.this,SnaksActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_orders) {
            Intent intent=new Intent(SnaksActivity.this, OrderActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_location) {
            Intent intent=new Intent(SnaksActivity.this,LocationsActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.menu_logout) {
            Intent intent=new Intent(SnaksActivity.this,LoginActivity.class);
            startActivity(intent);
        }


        drawerLayout.closeDrawer(Gravity.RIGHT);
        return true;
    }
}