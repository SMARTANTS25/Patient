package com.pifss.patient;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegistrationPage1 extends AppCompatActivity {

         EditText email;
         EditText password;
         EditText confirmPassword;
         Button RegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page1);

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
        final Button regButton = (Button) findViewById(R.id.Reg1Btn);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validReg()== false){
                    regButton.setEnabled(false);
                }  //validReg();

                else {
                    validReg();
                    Intent i = new Intent(RegistrationPage1.this, RegistrationPage2.class);
                    i.putExtra(email.getText().toString(), "email");
                    i.putExtra(password.getText().toString(), "password");
                    i.putExtra(confirmPassword.getText().toString(), "confirmPassword");
                    startActivity(i);
                }
            }
        });
    }
    public boolean validReg(){

        boolean invalid = false;

        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();
        String confirmPassValue = confirmPassword.getText().toString();

        if (emailValue.isEmpty()) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Enter your email ", Toast.LENGTH_SHORT).show();

        } else if (passwordValue.isEmpty()) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please enter the password", Toast.LENGTH_SHORT).show();
        } else if (confirmPassValue.isEmpty()) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please enter the confirm password", Toast.LENGTH_SHORT).show();
        } else if (!confirmPassValue.equals(passwordValue)) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Passwords Dont Match", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
