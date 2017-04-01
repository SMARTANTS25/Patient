package com.pifss.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


        final String fnameValue = fname.getText().toString();
        final String lnameValue = lname.getText().toString();
        final String birthdateValue = birthDate.getText().toString();
        final String civilIdValue = birthDate.getText().toString();
        final String phoneValue = phone.getText().toString();
        final String emergencyNumValue = emergencyNumber.getText().toString();
          regButton = (Button) findViewById(R.id.BtnToMedicalReg);

//        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/";
//        RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);
//
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("", RegEmail);
//            obj.put("", Regpassword);
//            obj.put("fname", fnameValue);
//            obj.put("lname", lnameValue);
//            obj.put("birthDate" , birthdateValue);
//            obj.put("civilId" , civilIdValue);
//            obj.put("phone",phoneValue);
//            obj.put("emergencyNumber", emergencyNumValue);
//            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//
//                    Toast.makeText(RegistrationPage2.this, response.toString(), Toast.LENGTH_SHORT).show();
//
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    Toast.makeText(RegistrationPage2.this, error.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            case R.id.canc:
//                Intent i = new Intent(getBaseContext(), LoginActivity.class);
//                startActivity(i);
//                //finish();
//                break;
                          if(validReg()== false){
                              regButton.setEnabled(false);
                              Toast.makeText(RegistrationPage2.this, "Invalid Data Check Please!?", Toast.LENGTH_SHORT).show();
                          }

                           else {
                              Intent i = new Intent(RegistrationPage2.this, RegistrationPage3.class);

                              i.putExtra(fnameValue, "firstName");
                              i.putExtra(lnameValue, "lastName");
                              i.putExtra(birthdateValue, "birthDate");
                              i.putExtra(civilIdValue, "civilID");
                              i.putExtra(phoneValue, "phoneNumber");
                              i.putExtra(emergencyNumValue, "emergencyNumber");

                              Toast.makeText(RegistrationPage2.this, "done with Reg2, Welcome to Reg3", Toast.LENGTH_SHORT).show();
                              startActivity(i);
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
        } else if (birthdateValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your Birth Date", Toast.LENGTH_SHORT).show();
        } else if (civilIdValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your civil id", Toast.LENGTH_SHORT).show();

        } else if (phoneValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
        } else if (emergencyNumValue.isEmpty()) {
            invalid = false;
            Toast.makeText(getApplicationContext(), "Please enter your emergency phone number", Toast.LENGTH_SHORT).show();
        }
        return invalid;
    }
}