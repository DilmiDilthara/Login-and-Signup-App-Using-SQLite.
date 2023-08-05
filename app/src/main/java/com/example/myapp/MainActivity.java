package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    databaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new databaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email = binding.signupEmail.getText().toString();
               String password = binding.signupPassword.getText().toString();
               String confirmPassword =binding.signupConfirm.getText().toString();
               
               if(email.equals("") || password.equals("") || confirmPassword.equals(""))
                   Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
               else {
                   if (password.equals(confirmPassword)) {
                       Boolean checkUserEmail = databaseHelper.checkEmail(email);

                       if (checkUserEmail == false) {
                           Boolean insert = databaseHelper.insertData(email, password);

                           if (insert == true) {
                               Toast.makeText(MainActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                               startActivity(intent);
                           } else {
                               Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(MainActivity.this, "User Already Exists please login..", Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(MainActivity.this, "Invalid Password..", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);

            }
        });

        }
    }
