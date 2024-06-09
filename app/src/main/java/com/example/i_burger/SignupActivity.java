package com.example.i_burger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    Button buttonsignup;
    EditText fullname;
    EditText phonenumber;
    EditText email2;
    EditText location;
    EditText password2;
    EditText repassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname=findViewById(R.id.editTextText);
        phonenumber=findViewById(R.id.editTextPhone);
        email2=findViewById(R.id.editTextTextEmailAddress2);
        buttonsignup=findViewById(R.id.button2);
        location=findViewById(R.id.editTextText2);
        password2=findViewById(R.id.editTextTextPassword2);
        repassword=findViewById(R.id.editTextTextPassword3);


        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,MainPageActivity.class);
                startActivity(intent);
            }
        });


    }
}