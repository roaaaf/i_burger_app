package com.example.i_burger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class ConfirmActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton back_btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageButton nav_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_confirm);

        back_btn = findViewById(R.id.confirm_back_icon);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnaksActivity.ordersList.clear();
                SnaksActivity.itemsListOrder.clear();
                Intent intent = new Intent(ConfirmActivity.this,MainPageActivity.class);
                startActivity(intent);
            }
        });

        //hooks navigation drawer
        drawerLayout = findViewById(R.id.confirm_drawer_layout);
        navigationView = findViewById(R.id.confirm_nav_view);
        nav_btn = findViewById(R.id.confirm_navigation_btn);
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
            Intent intent=new Intent(ConfirmActivity.this,ProfileActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_burger) {
            Intent intent=new Intent(ConfirmActivity.this,BurgerActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_snacks) {
            Intent intent=new Intent(ConfirmActivity.this,SnaksActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_orders) {
            Intent intent=new Intent(ConfirmActivity.this, OrderActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_location) {
            Intent intent=new Intent(ConfirmActivity.this,LocationsActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.menu_logout) {
            Intent intent=new Intent(ConfirmActivity.this,LoginActivity.class);
            startActivity(intent);
        }


        drawerLayout.closeDrawer(Gravity.RIGHT);
        return true;
    }
}