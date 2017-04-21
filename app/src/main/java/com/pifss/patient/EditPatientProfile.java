package com.pifss.patient;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class EditPatientProfile extends AppCompatActivity {

    String gender = "Female";
    Patient updatedPatient = new Patient();
    Patient currentPatientObject;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_profile);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Edit Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button saveChanges= (Button) findViewById(R.id.buttonSave);

        final EditText fname=(EditText) findViewById(R.id.editTextFirstName);
        final EditText lname=(EditText) findViewById(R.id.editTextLastName);
        final EditText mname=(EditText) findViewById(R.id.editTextMiddleName);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.GroupGenderRadio);

        final TextView dateOFBirth =(TextView) findViewById(R.id.textViewDateOfBirth);
        final EditText civilID=(EditText) findViewById(R.id.editTextCivilID);


        final EditText phone =(EditText) findViewById(R.id.editTextPhoneNumber);
        final EditText emergency =(EditText) findViewById(R.id.editTextEmergencyNumber);

        final EditText nationality =(EditText) findViewById(R.id.editTextNationality);
        final EditText password  =(EditText) findViewById(R.id.editTextPassword);
        final EditText newPassword  =(EditText) findViewById(R.id.editTextNewPassword);


        SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
        String patientProfile = pref1.getString("Patient1","notfound");
        currentPatientObject = new Gson().fromJson(patientProfile,Patient.class);


        fname.setText(currentPatientObject.getFirstName());
        mname.setText(currentPatientObject.getMiddleName());
        lname.setText(currentPatientObject.getLastName());

        if ( (currentPatientObject.getGender().charAt(0)+"").equalsIgnoreCase("f") ){
            RadioButton rbutton = (RadioButton) findViewById( R.id.radioButtonFemale);
            rbutton.setChecked(true);
            gender = "Female";
        }else{
            RadioButton rbutton = (RadioButton) findViewById( R.id.radioButtonMale);
            rbutton.setChecked(true);
            gender = "Male";
        }

        dateOFBirth.setText(currentPatientObject.getDateOfBirth());
        civilID.setText(currentPatientObject.getCivilId());
        phone.setText(currentPatientObject.getPhoneNumber());
        emergency.setText(currentPatientObject.getEmergencyNumber());
        nationality.setText(currentPatientObject.getNationality());

        dateOFBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d = new DatePickerDialog(EditPatientProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOFBirth.setText(year + "-" + month + "-" + dayOfMonth);
                        Toast.makeText(EditPatientProfile.this, "date set", Toast.LENGTH_SHORT).show();
                    }
                }, 1995, 2, 23);

                d.show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radioButtonFemale){
                    gender  = "Female";
                }else{
                    gender  = "Male";
                }
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fname.getText().toString().length() == 0 || mname.getText().toString().length() == 0 ||
                        lname.getText().toString().length() == 0 || civilID.getText().toString().length() == 0 ||
                        phone.getText().toString().length() == 0 || emergency.getText().toString().length() == 0 ||
                        nationality.getText().toString().length() == 0){

                    Toast.makeText(EditPatientProfile.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(password.getText().toString().equals(currentPatientObject.getPassword()))){

                    Toast.makeText(EditPatientProfile.this, "Password in incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }
                String newPasswordTemp = currentPatientObject.getPassword();
                if (newPassword.getText().toString().length() < 8 && newPassword.getText().toString().length() >0){

                    Toast.makeText(EditPatientProfile.this, "Please enter a valid new password", Toast.LENGTH_SHORT).show();
                    return;
                }else if (newPassword.getText().toString().length() >= 8){
                    newPasswordTemp = newPassword.getText().toString();
                }



                // send Update Profile Request
                String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/"+currentPatientObject.getPatientId();
                RequestQueue queue = MySingleton.getInstance().getRequestQueue(EditPatientProfile.this);

                try {


                    updatedPatient = new Gson().fromJson(currentPatientObject.toJSONString(),Patient.class);

                    updatedPatient.setFirstName(fname.getText().toString());
                    updatedPatient.setMiddleName(mname.getText().toString());
                    updatedPatient.setLastName(lname.getText().toString());

                    updatedPatient.setGender(gender);
                    updatedPatient.setDateOfBirth(dateOFBirth.getText().toString());
                    updatedPatient.setCivilId(civilID.getText().toString());
                    updatedPatient.setPhoneNumber(phone.getText().toString());
                    updatedPatient.setEmergencyNumber(emergency.getText().toString());
                    updatedPatient.setNationality(nationality.getText().toString());

                    updatedPatient.setPassword(newPasswordTemp);

                    JSONObject jsonProfile = new JSONObject(updatedPatient.toJSONString());
                    System.out.println("request: "+jsonProfile.toString());
                    final ProgressDialog progressDialog = new ProgressDialog(EditPatientProfile.this);

                    JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, url, jsonProfile, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.hide();
                            System.out.println("response: "+response.toString());
                            try {
                                if (response.getString("errorMsgEn").equalsIgnoreCase("Done")){

                                    Toast.makeText(EditPatientProfile.this, "Profile updated", Toast.LENGTH_SHORT).show();

                                    SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
                                    SharedPreferences.Editor Ed1 = pref1.edit();
                                    //profile
                                    Ed1.putString("Patient1",updatedPatient.toJSONString());
                                    Ed1.commit();
                                    currentPatientObject = updatedPatient;
                                }else{
                                    Toast.makeText(EditPatientProfile.this, "Error Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(EditPatientProfile.this, "Error connection failed.", Toast.LENGTH_SHORT).show();

                        }
                    });

                    if (!isNetworkAvailable()){
                        Toast.makeText(EditPatientProfile.this, R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
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
}
