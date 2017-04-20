package com.pifss.patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class  RegistrationPage1 extends AppCompatActivity {

         EditText email;
         EditText password;
         EditText confirmPassword;
         Button RegButton;
    String gender = "M";

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page1);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Register");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        final EditText emailf = (EditText) findViewById(R.id.Reg_EmailTF);
//        final EditText passwordf = (EditText) findViewById(R.id.Reg_PasswordTF);
//        final EditText confirmPasswordf = (EditText) findViewById(R.id.Reg_ConfirmPassTF);
//        RadioButton male = (RadioButton) findViewById(R.id.MaleRegMaleRadioButton);
//        RadioButton female = (RadioButton) findViewById(R.id.RegFemaleRadioBu);

         email= (EditText) findViewById(R.id.Reg_EmailTF);
         password = (EditText) findViewById(R.id.Reg_PasswordTF);
         confirmPassword = (EditText) findViewById(R.id.Reg_ConfirmPassTF);




        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();
        String confirmPassValue = confirmPassword.getText().toString();
        RegButton = (Button) findViewById(R.id.Reg1Btn);

        RadioButton male = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton female = (RadioButton) findViewById(R.id.radioButton);

        male.setChecked(true);


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        gender = "M";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender ="F";
            }
        });

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validReg() == false){
                    //Toast.makeText(RegistrationPage1.this, "Not Valid Email Or Password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(RegistrationPage1.this, RegistrationPage2.class);
                    i.putExtra( "email",email.getText().toString());
                    i.putExtra( "password",password.getText().toString());
                    i.putExtra("gender", gender);
                    startActivity(i);
                }
            }
        });
    }
    public boolean validReg(){

        boolean invalid = true;

        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();
        String confirmPassValue = confirmPassword.getText().toString();

        if (emailValue.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
        }
        else if (passwordValue.length() < 6){
            invalid = false;
            Toast.makeText(getApplicationContext(), "Password must be greater than 6", Toast.LENGTH_SHORT).show();
        }
        else if (!confirmPassValue.equals(passwordValue)) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
        }
        return invalid;
    }

}
