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
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class MainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageButton nav_btn;
    ActionBarDrawerToggle toggle;
    ImageButton burger_btn;
    ImageButton snaks_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        drawerLayout=findViewById(R.id.home_drawer_layout);
        navigationView=findViewById(R.id.home_nav_view);
        nav_btn=findViewById(R.id.menu_button);
        burger_btn=findViewById(R.id.burger_button);
        snaks_btn=findViewById(R.id.snaks_button);


         navigationView.bringToFront();
         navigationView.setNavigationItemSelectedListener(this);
         navigationView.setCheckedItem(R.id.menu_profile);
         toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
         drawerLayout.addDrawerListener(toggle);
         toggle.syncState();

         nav_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
             }
         });

         burger_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(MainPageActivity.this, BurgerActivity.class);
                 startActivity(intent);
             }
         });
        snaks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPageActivity.this,SnaksActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.RIGHT);
        }else{
        super.onBackPressed();}
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if(item.getItemId()==R.id.menu_profile){
            Intent intent=new Intent(MainPageActivity.this,ProfileActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_burger) {
            Intent intent=new Intent(MainPageActivity.this,BurgerActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_snacks) {
            Intent intent=new Intent(MainPageActivity.this,SnaksActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_orders) {
            Intent intent=new Intent(MainPageActivity.this, OrderActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.menu_location) {
            Intent intent=new Intent(MainPageActivity.this,LocationsActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.menu_logout) {
            Intent intent=new Intent(MainPageActivity.this,LoginActivity.class);
            startActivity(intent);
        }


        drawerLayout.closeDrawer(Gravity.RIGHT);
        return true;
    }
}