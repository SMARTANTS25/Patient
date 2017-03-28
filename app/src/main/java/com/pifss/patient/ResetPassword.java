package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        EditText email = (EditText) findViewById(R.id.ResetPassword_emailTF);
        Button submit = (Button) findViewById(R.id.Reset_Button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ResetPassword.this, "Check your email", Toast.LENGTH_LONG).show();

            }
        });
    }
}
