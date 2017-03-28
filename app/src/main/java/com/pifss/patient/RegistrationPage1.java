package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegistrationPage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page1);

        final EditText email = (EditText) findViewById(R.id.Reg_EmailTF);
        final EditText password = (EditText) findViewById(R.id.Reg_PasswordTF);
        EditText confirmPassword = (EditText) findViewById(R.id.Reg_ConfirmPassTF);
        RadioButton male = (RadioButton) findViewById(R.id.MaleRegMaleRadioButton);
        RadioButton female = (RadioButton) findViewById(R.id.RegFemaleRadioBu);

        Button regButton = (Button) findViewById(R.id.Reg1Btn);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(RegistrationPage1.this, RegistrationPage2.class);
                i.putExtra(email.getText().toString(),"email");
                i.putExtra(password.getText().toString(),"password");
//                i.putExtra(.getText().toString(),"password");
                startActivity(i);


            }
        });
    }
}
