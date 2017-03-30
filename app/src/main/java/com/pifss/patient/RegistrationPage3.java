package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegistrationPage3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page3);

        Button reg3= (Button) findViewById(R.id.Reg3_button);

        reg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(RegistrationPage3.this, Home.class);
                Toast.makeText(RegistrationPage3.this, "hello", Toast.LENGTH_LONG).show();
            }
        });

    }
}
