package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pifss.patient.MySingleton;

public class RegistrationPage3 extends AppCompatActivity {

    EditText medications;
    EditText allergies;
    Button reg3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page3);

        medications= (EditText) findViewById(R.id.Reg_MedicationsET);
        allergies= (EditText) findViewById(R.id.Reg_AllergiesET);

        reg3Button = (Button) findViewById(R.id.Reg3_button);

        final String allergieValue = allergies.getText().toString();
        final String medicationValue = medications.getText().toString();


        String url="";
         RequestQueue queue= MySingleton.getInstance().getRequestQueue(this);
         StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {


                Toast.makeText(RegistrationPage3.this, response, Toast.LENGTH_LONG);
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

             }
         });

        //hello its me



        reg3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validReg()== false){
                    reg3Button.setEnabled(false);
                }  //validReg();

                else {
                    Intent i = new Intent(RegistrationPage3.this, ViewPatientProfile.class);

                    i.putExtra(allergieValue, "allergies");
                    i.putExtra(medicationValue, "medications");
                     startActivity(i);
                }

            }
        });


    }

    public boolean validReg(){

        boolean invalid = false;

        String allergieValue = allergies.getText().toString();
        String medicationValue = medications.getText().toString();


        if (allergieValue.isEmpty()) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please mention if you have allergy", Toast.LENGTH_SHORT).show();

        } else if (medicationValue.isEmpty()) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please mention if you are taking any medications", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
