package com.pifss.patient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationPage2 extends AppCompatActivity {

    EditText fname;
    EditText lname;
    TextView birthDate;
    EditText civilId;
    EditText phone;
    EditText emergencyNumber;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page2);

        final String email = getIntent().getStringExtra("email");
        final String password = getIntent().getStringExtra("password");
        final String gender = getIntent().getStringExtra("gender");


      //  Toast.makeText(this, email+ " "+password+" "+gender , Toast.LENGTH_SHORT).show();

        fname= (EditText) findViewById(R.id.Reg2_FirstNameTF);
        lname= (EditText) findViewById(R.id.Reg2_LastNameTF);
        birthDate= (TextView) findViewById(R.id.Reg2_BirthDateTF);
        civilId= (EditText) findViewById(R.id.Reg2_CivilIDTF);
        phone= (EditText) findViewById(R.id.Reg2_PhoneNumberTF);
        emergencyNumber= (EditText) findViewById(R.id.Reg2_EmergencyNumberTF);


//        final String fnameValue = fname.getText().toString();
//        final String lnameValue = lname.getText().toString();
//       // final String birthdateValue = birthDate.getText().toString();
//        final String civilIdValue = birthDate.getText().toString();
//        final String phoneValue = phone.getText().toString();
//        final String emergencyNumValue = emergencyNumber.getText().toString();
          regButton = (Button) findViewById(R.id.Reg2_NextButton);
        birthDate.setText(1995 + "-" + 2 + "-" + 23);

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog d = new DatePickerDialog(RegistrationPage2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDate.setText(year + "-" + month + "-" + dayOfMonth);

                    }
                }, 1995, 2, 23);

                d.show();

            }});

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                          if(validReg()== false){
                              Toast.makeText(RegistrationPage2.this, "Invalid Data Check Please!?", Toast.LENGTH_SHORT).show();
                          }else {
                              Intent i2 = new Intent(RegistrationPage2.this, RegistrationPage3.class);

                              i2.putExtra("firstName",fname.getText().toString());
                              i2.putExtra("lastName",lname.getText().toString());
                              i2.putExtra( "birthDate",birthDate.getText().toString());
                              i2.putExtra("civilID",civilId.getText().toString());
                              i2.putExtra( "phoneNumber",phone.getText().toString());
                              i2.putExtra( "emergencyNumber",emergencyNumber.getText().toString());
                              i2.putExtra("email1",email);
                              i2.putExtra("password1",password);
                              i2.putExtra("gender1",gender);
                              Toast.makeText(RegistrationPage2.this, "done with Reg2, Welcome to Reg3", Toast.LENGTH_SHORT).show();
                              startActivity(i2);
                          }
            }
        });
    }


    public boolean validReg(){

        boolean invalid = true;

        String fnameValue = fname.getText().toString();
        String lnameValue = lname.getText().toString();
        String birthdateValue = birthDate.getText().toString();
        String civilIdValue = civilId.getText().toString();
        String phoneValue = phone.getText().toString();
        String emergencyNumValue = emergencyNumber.getText().toString();

        if (fnameValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Enter your Firstname", Toast.LENGTH_SHORT).show();

        } else if (lnameValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your Lastname", Toast.LENGTH_SHORT).show();
//        } else if (birthdateValue.isEmpty()) {
//            invalid = false;
//            //Toast.makeText(getApplicationContext(), "Please enter your Birth Date", Toast.LENGTH_SHORT).show();
//        } else if (civilIdValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your civil id", Toast.LENGTH_SHORT).show();

        } else if (phoneValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
        } else if (emergencyNumValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your emergency phone number", Toast.LENGTH_SHORT).show();
        }else if (civilIdValue.length() < 12){
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter a valid civil ID", Toast.LENGTH_SHORT).show();

        }
        return invalid;
    }
}