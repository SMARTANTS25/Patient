package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPage2 extends AppCompatActivity {

    EditText fname;
    EditText lname;
      EditText birthDate;
      EditText civilId;
     EditText phone;
     EditText emergencyNumber;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page2);

           fname= (EditText) findViewById(R.id.Reg_FirstNameTF);
           lname= (EditText) findViewById(R.id.Reg_LastNameTF);
          birthDate= (EditText) findViewById(R.id.Reg_BirthDateTF);
          civilId= (EditText) findViewById(R.id.Reg_CivilIDTF);
         phone= (EditText) findViewById(R.id.Reg_PhoneNumberTF);
        emergencyNumber= (EditText) findViewById(R.id.Reg_EmergencyNumberTF);

        final String fnameValue= fname.getText().toString();
        final String lnameValue= lname.getText().toString();
        final String birthdateValue= birthDate.getText().toString();
        final String civilIdValue= birthDate.getText().toString();
        final String phoneValue= phone.getText().toString();
        final String emergencyNoValue= emergencyNumber.getText().toString();

          regButton = (Button) findViewById(R.id.BtnToMedicalReg);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegistrationPage2.this, RegistrationPage3.class);

                i.putExtra(fnameValue,"firstName");
                i.putExtra(lnameValue,"lastName");
                i.putExtra(birthdateValue,"birthDate");
                i.putExtra(civilIdValue,"civilID");
                i.putExtra(phoneValue,"phoneNumber");
                i.putExtra(emergencyNoValue,"emergencyNumber");

                 startActivity(i);
            }
        });


    }

    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.canc:
//                Intent i = new Intent(getBaseContext(), LoginActivity.class);
//                startActivity(i);
//                //finish();
//                break;

            case R.id.Reg1Btn:


                String fnameValue = fname.getText().toString();
                String lnameValue = lname.getText().toString();
                String birthdateValue = birthDate.getText().toString();
                String civilIdValue = birthDate.getText().toString();
                String phoneValue = phone.getText().toString();
                String emergencyNoValue = emergencyNumber.getText().toString();


                boolean invalid = false;

                if (fnameValue.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your Firstname", Toast.LENGTH_SHORT).show();
                } else if (lnameValue.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Lastname", Toast.LENGTH_SHORT).show();
                } else if (birthdateValue.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your Birth Date", Toast.LENGTH_SHORT).show();
                } else if (civilIdValue.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your civil id", Toast.LENGTH_SHORT).show();

                } else if (phoneValue.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                } else if (emergencyNoValue.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Please enter your emergency phone number", Toast.LENGTH_SHORT).show();
                }
//                else if (invalid == false) {
//                    addEntry(fname, lname, Gen, uname, pass, email);
//                    Intent i_register = new Intent(Registration.this, LoginActivity.class);
//                    startActivity(i_register);
//                    //finish();
//                }
                regButton.setEnabled(false);
                break;
        }
    }
}


