package com.pifss.patient.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pifss.patient.Home;
import com.pifss.patient.R;
import com.pifss.patient.RegistrationPage1;
import com.pifss.patient.ResetPassword;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.loginButton);
        final EditText email = (EditText) findViewById(R.id.loginEmailText);
        final EditText password = (EditText) findViewById(R.id.passwordLoginText);
        TextView signup = (TextView) findViewById(R.id.signupTextView);
        TextView forgetPassword = (TextView) findViewById(R.id.forgetpasswordTV);

         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){

                     //Toast.makeText(Login.this,"You Have been Loggned in Successfuly", Toast.LENGTH_LONG);

                     Intent i = new Intent(Login.this, Home.class);
                        startActivity(i);
                                                 }

                        else{

                      Toast.makeText(Login.this,"Invalid email or password", Toast.LENGTH_LONG);
                                          }
             }
         });


                    signup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(Login.this, RegistrationPage1.class);
                            startActivity(i);
                        }
                    });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this, ResetPassword.class);
                startActivity(i);
            }
        });

    }
}
