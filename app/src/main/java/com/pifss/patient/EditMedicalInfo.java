package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EditMedicalInfo extends AppCompatActivity {
    Patient updatedPatient = new Patient();
    Patient currentPatientObject;
String bloodType = "AB-";
    Boolean diabetes = false;
    Boolean asthma = false;

    ArrayList<RadioButton> bloodRadioButton = new ArrayList<>();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medical_info);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Edit medical Info");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Button saveChanges= (Button) findViewById(R.id.saveChangesMedicalButton);

        SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
        String patientProfile = pref1.getString("Patient1","notfound");
        currentPatientObject = new Gson().fromJson(patientProfile,Patient.class);




        final EditText allergiesText=(EditText) findViewById(R.id.editTextAllergies);
        final EditText medicationsText=(EditText) findViewById(R.id.editTextMedications);

        RadioButton ap = (RadioButton) findViewById(R.id.radioButtonAP);
        RadioButton am = (RadioButton) findViewById(R.id.radioButtonAM);
        RadioButton bp = (RadioButton) findViewById(R.id.radioButtonBP);
        RadioButton bm = (RadioButton) findViewById(R.id.radioButtonBM);
        RadioButton op = (RadioButton) findViewById(R.id.radioButtonOP);
        RadioButton om = (RadioButton) findViewById(R.id.radioButtonOM);
        RadioButton abp = (RadioButton) findViewById(R.id.radioButtonABP);
        RadioButton abm = (RadioButton) findViewById(R.id.radioButtonABM);
        bloodRadioButton.add(ap);
        bloodRadioButton.add(am);
        bloodRadioButton.add(bp);
        bloodRadioButton.add(bm);
        bloodRadioButton.add(op);
        bloodRadioButton.add(om);
        bloodRadioButton.add(abp);
        bloodRadioButton.add(abm);

        if (currentPatientObject.getBloodType().equals("A+")){
            ap.setChecked(true);
            bloodType = "A+";
        }else if (currentPatientObject.getBloodType().equals("A-")){
            am.setChecked(true);
            bloodType = "A-";
        }else if (currentPatientObject.getBloodType().equals("B+")){
            bp.setChecked(true);
            bloodType = "B+";
        }else if (currentPatientObject.getBloodType().equals("B-")){
            bm.setChecked(true);
            bloodType = "B-";
        }else if (currentPatientObject.getBloodType().equals("O+")){
            op.setChecked(true);
            bloodType = "O+";
        }else if (currentPatientObject.getBloodType().equals("O-")){
            om.setChecked(true);
            bloodType = "O-";
        }else if (currentPatientObject.getBloodType().equals("AB+")){
            abp.setChecked(true);
            bloodType = "AB+";
        }else{
            abm.setChecked(true);
            bloodType = "AB-";
        }

        ap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                bloodType = "A+";
                setCheckedBloodButton(0);

                }
            }
        });

        am.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                bloodType = "A-";
                setCheckedBloodButton(1);
                }
            }
        });

        bp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                bloodType = "B+";
                setCheckedBloodButton(2);
                }
            }
        });

        bm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                bloodType = "B-";
                setCheckedBloodButton(3);
                }
            }
        });

        op.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                bloodType = "O+";
                setCheckedBloodButton(4);
                }
            }
        });


        om.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                bloodType = "O-";
                setCheckedBloodButton(5);
                }
            }
        });

        abp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                bloodType = "AB+";
                setCheckedBloodButton(6);
                }
            }
        });

        abm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                bloodType = "AB-";
                setCheckedBloodButton(7);
                }
            }
        });


        RadioButton diabetesNo = (RadioButton) findViewById(R.id.radioButtonDiabetesNO);
        RadioButton diabetesYes = (RadioButton) findViewById(R.id.radioButtonDiabetesYes);
        RadioGroup radioGroupdiabetes = (RadioGroup) findViewById(R.id.GroupRadioDiabetes);

        if (currentPatientObject.getDiabetes()){
            diabetesYes.setChecked(true);
            diabetes = true;
        }else{
            diabetesNo.setChecked(true);
            diabetes = false;
        }
        radioGroupdiabetes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radioButtonDiabetesNO){
                    diabetes = false;
                }else{
                    diabetes = true;
                }
            }
        });


        RadioButton asthmaNo = (RadioButton) findViewById(R.id.radioButtonAsthmaNO);
        RadioButton asthmaYes = (RadioButton) findViewById(R.id.radioButtonAsthmaYes);
        RadioGroup radioGroupasthma = (RadioGroup) findViewById(R.id.GroupRadioAsthma);

        if (currentPatientObject.getAsthma()){
            asthmaYes.setChecked(true);
            asthma = true;
        }else{
            asthmaNo.setChecked(true);
            asthma = false;
        }
        radioGroupasthma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radioButtonAsthmaNO){
                    asthma = false;
                }else{
                    asthma = true;
                }
            }
        });


        allergiesText.setText(currentPatientObject.getAllergies());
        medicationsText.setText(currentPatientObject.getMedications());

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (allergiesText.getText().toString().length() == 0 || medicationsText.getText().toString().length() == 0){

                    Toast.makeText(EditMedicalInfo.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // send Update Profile Request
                String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/"+currentPatientObject.getPatientId();
                RequestQueue queue = MySingleton.getInstance().getRequestQueue(EditMedicalInfo.this);

                try {

                    updatedPatient = new Gson().fromJson(currentPatientObject.toJSONString(),Patient.class);

                    updatedPatient.setBloodType(bloodType);
                    updatedPatient.setAsthma(asthma);
                    updatedPatient.setDiabetes(diabetes);

                    updatedPatient.setAllergies(allergiesText.getText().toString());
                    updatedPatient.setMedications(medicationsText.getText().toString());


                    JSONObject jsonProfile = new JSONObject(updatedPatient.toJSONString());
                    System.out.println("request: "+jsonProfile.toString());
                    final ProgressDialog progressDialog = new ProgressDialog(EditMedicalInfo.this);

                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, url, jsonProfile, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.hide();
                            System.out.println("response: "+response.toString());
                            try {
                                if (response.getString("errorMsgEn").equalsIgnoreCase("Done")){

                                    Toast.makeText(EditMedicalInfo.this, "Profile updated", Toast.LENGTH_SHORT).show();

                                    SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
                                    SharedPreferences.Editor Ed1 = pref1.edit();
                                    //profile
                                    Ed1.putString("Patient1",updatedPatient.toJSONString());
                                    Ed1.commit();
                                    currentPatientObject = updatedPatient;
                                }else{
                                    Toast.makeText(EditMedicalInfo.this, "Error Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(EditMedicalInfo.this, "Error connection failed.", Toast.LENGTH_SHORT).show();

                        }
                    });

                    if (!isNetworkAvailable()){
                        Toast.makeText(EditMedicalInfo.this, R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.setMessage("Connecting...");
                    progressDialog.show();
                    queue.add(req);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void setCheckedBloodButton(int index){
        for (int i =0;i<bloodRadioButton.size();i++){
            RadioButton radioButton = bloodRadioButton.get(i);
            if (i == index){
                radioButton.setChecked(true);
            }else {
                radioButton.setChecked(false);
            }

        }
    }
}
