package com.pifss.patient;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistrationPage3 extends AppCompatActivity {


    String BloodType;

    boolean diabetes = false;
    boolean asthmas = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page3);

        Button reg3= (Button) findViewById(R.id.Reg3_button);

        final String email = getIntent().getStringExtra("email1");
        final String password = getIntent().getStringExtra("password1");
        final String gender = getIntent().getStringExtra("gender1");

        final String fname = getIntent().getStringExtra("firstName");
        final String lname = getIntent().getStringExtra("lastName");
        final String bDate = getIntent().getStringExtra("birthDate");
        final String civilID = getIntent().getStringExtra("civilID");
        final String phoneNum = getIntent().getStringExtra("phoneNumber");
        final String emergencyNum = getIntent().getStringExtra("emergencyNumber");

        ImageButton bloodBPlus = (ImageButton) findViewById(R.id.Reg3_bplusImageButton);
        ImageButton bloodBMinus = (ImageButton) findViewById(R.id.Reg3_bminImageButton);
        ImageButton bloodAPlus = (ImageButton) findViewById(R.id.Reg3_aplusImgButton);
        ImageButton bloodAMinus = (ImageButton) findViewById(R.id.Reg3_aminImgButton);
        ImageButton bloodABPlus = (ImageButton) findViewById(R.id.Reg3_abplusImgButton);
        ImageButton bloodABMinus = (ImageButton) findViewById(R.id.Reg3_abminImgButton);
        ImageButton bloodOPlus = (ImageButton) findViewById(R.id.Reg3_oplusImageButton);
        ImageButton bloodOMinus = (ImageButton) findViewById(R.id.Reg3_ominImageButton);
        myImageButtons.add(bloodAMinus);
        myImageButtons.add(bloodAPlus);
        myImageButtons.add(bloodABMinus);
        myImageButtons.add(bloodABPlus);
        myImageButtons.add(bloodBMinus);
        myImageButtons.add(bloodBPlus);
        myImageButtons.add(bloodOMinus);
        myImageButtons.add(bloodOPlus);

        final EditText allergies = (EditText) findViewById(R.id.Reg3_AllergiesET);
        final EditText medications = (EditText) findViewById(R.id.Reg3_MedicationsET);

        Switch SAsthma = (Switch) findViewById(R.id.switchAsthma);
        Switch SAllergies = (Switch) findViewById(R.id.switchAllergies);

        final RadioButton RDiabetesNo = (RadioButton) findViewById(R.id.Reg3_NoDiabetesRB);
        final RadioButton RDiabetesTOne = (RadioButton) findViewById(R.id.Reg3_TypeOneDiabetesRB);

        diabetes = false;
        RDiabetesNo.setChecked(true);

        RDiabetesNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    diabetes = false;
                    RDiabetesTOne.setChecked(false);
                }

            }
        });
        RDiabetesTOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    diabetes = true;
                    RDiabetesNo.setChecked(false);
                }
            }
        });


        BloodType = "A-";
selectedBloodTypeImageButton(0);
        bloodBPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "B+";
                selectedBloodTypeImageButton(5);
            }
        });
        bloodBMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "B-";
                selectedBloodTypeImageButton(4);
            }
        });
        bloodAPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "A+";
                selectedBloodTypeImageButton(1);
            }
        });
        bloodAMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "A-";
                selectedBloodTypeImageButton(0);
            }
        });
        bloodABPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "AB+";
                selectedBloodTypeImageButton(3);
            }
        });
        bloodABMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "AB-";
                selectedBloodTypeImageButton(2);
            }
        });
        bloodOPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "O+";
                selectedBloodTypeImageButton(7);
            }
        });
        bloodOMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "O-";
                selectedBloodTypeImageButton(6);
            }
        });

        SAsthma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    asthmas = true;

            }
        });
        SAllergies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });







        final String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient";
        final RequestQueue queue = MySingleton.getInstance().getRequestQueue(RegistrationPage3.this);

        reg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest req = null;
                final JSONObject obj = new JSONObject();
                try {
//
//                    "allergies": "1",
//                            "asthma": true,
//                            "bloodType": "b",
//                            "civilId": "123456789012",
//                            "dateOfBirth": "2017-01-18",
//                            "deleted": 0,
//                            "diabetes": true,
//                            "email": "ali@ali.com",
//                            "emergencyNumber": "232323",
//                            "firstName": "ali",
//                            "gender": "m",
//                            "imageUrl": "http://www.fancyicons.com/download/?id=2652&t=png&s=256",
//                            "lastName": "alo",
//                            "medications": "1",
//                            "middleName": "m",
//                            "nationality": "kw",
//                            "password": "11",
//                            "patientId": 3,
//                            "phoneNumber": "323232",
//                            "status": true
                    obj.put("email", email);
                    obj.put("password", password);
                    obj.put("gender",gender);
                    obj.put("deleted",0);
                    obj.put("firstName", fname);
                    obj.put("lastName", lname);
                    obj.put("dateOfBirth" , bDate);
                    obj.put("civilId" , civilID);
                    obj.put("phoneNumber", phoneNum);
                    obj.put("emergencyNumber", emergencyNum);
                    obj.put("bloodType",BloodType);
                    obj.put("diabetes",diabetes);
                    obj.put("asthma",asthmas);
                    obj.put("status",true);

                    obj.put("nationality"," ");
                    obj.put("middleName"," ");
                    obj.put("imageUrl", " ");
                    String alergies = allergies.getText().toString();
                    if (alergies.length() == 0){
                        alergies = "NO Alergies";
                    }
                    obj.put("allergies",alergies);
                    obj.put("medications",medications.getText().toString());
                    System.out.println(obj.toString());
                    req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(RegistrationPage3.this, response.toString(), Toast.LENGTH_SHORT).show();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(RegistrationPage3.this, error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                    queue.add(req);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                SharedPreferences pref = getSharedPreferences("PatientData1",MODE_PRIVATE);

                pref.edit()
                        .putString("Patient1",obj.toString())
                        .commit();

                Intent i = new Intent(RegistrationPage3.this , Login.class);

                Toast.makeText(RegistrationPage3.this, "hello & you are done " + fname + " "+ lname, Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });

    }

    public void onRadioButtonClicked(View view ) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();

       //  Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Reg3_NoDiabetesRB:
                if (checked)
                    // Pirates are the best
                    diabetes = false;
                break;
            case R.id.Reg3_TypeOneDiabetesRB:
                if (checked)
                    diabetes = true;
                // Ninjas rule
                break;


        }
    }


    ArrayList<ImageButton> myImageButtons = new ArrayList<>();
    int selectedBloodTypeIndex = 0;
    public void selectedBloodTypeImageButton(int position) {
        selectedBloodTypeIndex = position;
        for (int i = 0; i < myImageButtons.size(); i++) {

            ImageButton button = myImageButtons.get(i);

            if (i == position) {
                button.setAlpha(1.0f);
            } else {
                button.setAlpha(0.4f);
            }

        }
    }
}
