package com.pifss.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        RegButton = (Button) findViewById(R.id.Reg1Btn);

        RadioButton male = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton female = (RadioButton) findViewById(R.id.radioButton);


        final String[] gender = new String[1];
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        gender[0] = "M";
                Toast.makeText(RegistrationPage1.this, gender[0]+"", Toast.LENGTH_SHORT).show();
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender[0] ="F";
                Toast.makeText(RegistrationPage1.this, gender[0]+"", Toast.LENGTH_SHORT).show();
            }
        });

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validReg() == false){
                    Toast.makeText(RegistrationPage1.this, "Not Valid Email Or Password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(RegistrationPage1.this, RegistrationPage2.class);
                    i.putExtra( "email",email.getText().toString());
                    i.putExtra( "password",password.getText().toString());
                    i.putExtra("gender", gender[0]);

                    Toast.makeText(RegistrationPage1.this, "Next Pressed", Toast.LENGTH_SHORT).show();
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
        else if (passwordValue.isEmpty()){
            invalid = false;
            Toast.makeText(getApplicationContext(), "please enter the password", Toast.LENGTH_SHORT).show();
        }
        else if (confirmPassValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter the confirm password", Toast.LENGTH_SHORT).show();
        }
        else if ( password.length() < 6 || password.length() > 10 || confirmPassword.length() < 6 || confirmPassword.length()>10 ) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "your passwords should be between 6 - 10 characters", Toast.LENGTH_SHORT).show();
        }
        else if (!confirmPassValue.equals(passwordValue)) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Passwords Dont Match", Toast.LENGTH_SHORT).show();
        }
        return invalid;
    }

}
