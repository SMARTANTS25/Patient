package com.pifss.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button login=(Button) findViewById(R.id.loginButton);

         EditText email=(EditText) findViewById(R.id.loginEmailText);
        EditText password=(EditText) findViewById(R.id.passwordLoginText);


         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                 
             }
         });







    }
}


