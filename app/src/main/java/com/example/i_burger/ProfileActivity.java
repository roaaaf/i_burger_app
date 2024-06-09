package com.example.i_burger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageButton menubtn,backbtn;
    EditText fullname,phonenumber,email,location,password,repassword;
    Button savebtn;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        menubtn=findViewById(R.id.navigation_btn);
        backbtn=findViewById(R.id.back_icon);
        savebtn=findViewById(R.id.save_btn);
        fullname=findViewById(R.id.full_name);
        phonenumber=findViewById(R.id.phone_number);
        email=findViewById(R.id.email);
        location=findViewById(R.id.location);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.re_password);

        drawerLayout=findViewById(R.id.nav_drawer);
        navigationView=findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_view);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                  drawerLayout.closeDrawer(Gravity.RIGHT);
              }else{
                 drawerLayout.openDrawer(Gravity.RIGHT);
              }
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.RIGHT);
        }else {
            super.onBackPressed();
        }
    }
   public void onClickBackBtn(View view){

       Intent intent=new Intent(ProfileActivity.this,MainPageActivity.class);
       startActivity(intent);
   }

    public void onClickSaveBtn(View view) {
        if (!validateEmail() | !validatePassword() | !validateFullName() | !validatephone()) {
            return;
        }
        Intent i = new Intent(ProfileActivity.this,MainPageActivity.class);
        startActivity(i);
    }
    private boolean validateFullName() {
        String nameInput = fullname.getText().toString().trim();
        if (nameInput.isEmpty()) {
            fullname.setError("field can't be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Email field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        String confirmPasswordInput = repassword.getText().toString().trim();
        final Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[0-9])" + //at least 1 digit
                        "(?=.*[a-z])" + //at least 1 lower letter
                        "(?=.*[A-Z])" + //at least 1 upper letter
                        "(?=\\S+$)" +  //no white spaces
                        ".{6,}" + //at least 6 char
                        "$");

        if (passwordInput.isEmpty()) {
            password.setError("field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else if (!passwordInput.equals(confirmPasswordInput)) {
            repassword.setError("Password not match");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validatephone() {
        String phoneInput = phonenumber.getText().toString().trim();
        final Pattern PHONE_PATTERN =
                Pattern.compile("^" +
                        "([0-9])" +
                        "{10}" +
                        "$");

        if (phoneInput.isEmpty()) {
            phonenumber.setError("field can't be empty");
            return false;
        } else if (!PHONE_PATTERN.matcher(phoneInput).matches()) {
            phonenumber.setError("Please Enter 10 digit numbers");
            return false;
        } else {
            phonenumber.setError(null);
            return true;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_profile){

        }else if(item.getItemId()==R.id.menu_burger){
            Intent i = new Intent(ProfileActivity.this,BurgerActivity.class);
            startActivity(i);
        }else if(item.getItemId()==R.id.menu_snacks){
            Intent i = new Intent(ProfileActivity.this,SnaksActivity.class);
            startActivity(i);
        }else if(item.getItemId()==R.id.menu_orders){
            Intent i = new Intent(ProfileActivity.this, OrderActivity.class);
            startActivity(i);
        }else if(item.getItemId()==R.id.menu_location){
            Intent i = new Intent(ProfileActivity.this,LocationsActivity.class);
            startActivity(i);
        }

        return false;
    }
}