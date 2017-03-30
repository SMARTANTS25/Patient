package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.pifss.patient.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;


public class RegistrationPage3 extends AppCompatActivity {

    EditText medications;
    EditText allergies;
    RadioButton noDiabetes;
    RadioButton typeOneDiabetes;
    RadioButton typeTwoDiabetes;
    ImageButton aplus;
    ImageButton amin;
    ImageButton bplus;
    ImageButton bmin;
    ImageButton abplus;
    ImageButton abmin;
    ImageButton oplus;
    ImageButton omin;
    Switch asthmaSwitch;
    Switch allergiesSwitch;


    Button reg3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page3);

        allergiesSwitch = (Switch) findViewById(R.id.switchAllergies);
        asthmaSwitch = (Switch) findViewById(R.id.switchAsthma);

        //set the switch to ON
        asthmaSwitch.setChecked(true);
        //attach a listener to check for changes in state
        asthmaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //
                }else{
                }
            }
        });


        allergiesSwitch.setChecked(true);

        allergiesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }else{

                }
            }
        });
        noDiabetes = (RadioButton) findViewById(R.id.NoDiabetesRB);
        typeOneDiabetes = (RadioButton) findViewById(R.id.TypeOneDiabetesRB);
        typeTwoDiabetes = (RadioButton) findViewById(R.id.TypeTwoDiabetesRB);


        medications = (EditText) findViewById(R.id.Reg_MedicationsET);
        allergies = (EditText) findViewById(R.id.Reg_AllergiesET);

        reg3Button = (Button) findViewById(R.id.Reg3_button);

        //gender = (???) findViewById(R.id.);

      // HOW TO SET THE DATA TO THE GENDER VALUE!
        // final String genderValue = .getText().toString();
        final String allergieValue = allergies.getText().toString();
        final String medicationValue = medications.getText().toString();


        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/Register";
        RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);

        JSONObject obj = new JSONObject();
        try {
            obj.put("medications", medications.getText().toString());
            obj.put("allergies", allergies.getText().toString());
           // obj.put("gender",genderValue.getText().toString());

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        reg3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validReg()== false){
                    reg3Button.setEnabled(false);
                }
                else {
                    Intent i = new Intent(RegistrationPage3.this, ViewPatientProfile.class);
                    i.putExtra(allergieValue, "allergies");
                    i.putExtra(medicationValue, "medications");
                  //  i.putExtra(genderValue , "gender");
                     startActivity(i);
                }

            }
        });


    }
    // image button code
    public void addListenerOnButton(){
       // validateBT();

        aplus = (ImageButton) findViewById(R.id.aplusImgButton);
        amin = (ImageButton) findViewById(R.id.aminImgButton);
        abplus = (ImageButton) findViewById(R.id.abplusImgButton);
        abmin = (ImageButton) findViewById(R.id.abminImgButton);
        bplus = (ImageButton) findViewById(R.id.bplusImageButton);
        bmin = (ImageButton) findViewById(R.id.bminImageButton);
        omin = (ImageButton) findViewById(R.id.ominImageButton);
        oplus = (ImageButton) findViewById(R.id.oplusImageButton);


        aplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        amin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        abplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        abmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); oplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); omin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    //radio button
    public void onRadioButtonClicked(View view) {
             boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.NoDiabetesRB:
                if (checked)
                    Toast.makeText(RegistrationPage3.this,
                            "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
                    //do database
                    break;
            case R.id.TypeOneDiabetesRB:
                if (checked)
                    // dd
                    break;
            case R.id.TypeTwoDiabetesRB:
                if(checked)
                    // do
                    break;

        }
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
       //add if statment for diabetes + imageButton + switch

        return false;
    }
//add it to on click
//    public boolean validateDiabetes(){
//        boolean DiabetesRB = false;
//        if(noDiabetes.isSelected()&& typeTwoDiabetes.isSelected() && typeTwoDiabetes.isSelected()){
//            DiabetesRB = true;
//            Toast.makeText(getApplicationContext(), "cant pick more than one", Toast.LENGTH_SHORT).show();
//        }
//        else if(noDiabetes.isSelected()) {
//
//
//        }
//        else if(typeOneDiabetes.isSelected()){
//
//        }else if(typeTwoDiabetes.isSelected()){
//
//        }
//
//
//        return DiabetesRB;
//
//    }
}
