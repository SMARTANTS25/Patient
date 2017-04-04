package com.pifss.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewPatientProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_profile);

        ImageView imageProfile = (ImageView) findViewById(R.id.profileImgView);

        Button editButton=(Button) findViewById(R.id.editPersonalInfoButton);
        Button myDrButton=(Button) findViewById(R.id.mydrGeneralInfoButton);
        Button medicalProfileButton = (Button) findViewById(R.id.medicalInfoViewButton);


        final TextView nameText=(TextView) findViewById(R.id.profilePatientNameText);
        TextView genderText=(TextView) findViewById(R.id.genderViewText);
        final TextView birthdateText=(TextView) findViewById(R.id.birthdateViewText);
        final TextView emailText=(TextView) findViewById(R.id.emailViewText);
        final TextView phoneText=(TextView) findViewById(R.id.phoneViewText);
        final TextView emergencyNoText=(TextView) findViewById(R.id.emergencyNoView);


//        String url="";
//        RequestQueue queue= MySingleton.getInstance().getRequestQueue(this);
//
//        JSONObject obj = new JSONObject();
//        try {
//
//            obj.put("fName", nameText);
//            obj.put("gender", genderText);
//            obj.put("birthDate", birthdateText);
//            obj.put("email", emailText);
//            obj.put("phoneNumber", phoneText);
//            obj.put("emergencyNumber", emergencyNoText);
//            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(final JSONObject response) {
//
//                    Patient p=new Gson().fromJson(response.toString(),Patient.class);
//                    nameText.setText(p.getFirstName());
//                    birthdateText.setText(p.getDateOfBirth());
//                    emailText.setText(p.getEmail());
//                    phoneText.setText(p.getPhoneNumber());
//                    emergencyNoText.setText(p.getEmergencyNumber());
//                    Toast.makeText(ViewPatientProfile.this, "View profile", Toast.LENGTH_SHORT).show();
//
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    Toast.makeText(ViewPatientProfile.this, "error", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        String movieSTR=getSharedPreferences("PatientData",MODE_PRIVATE).getString("Patient"," ");

        // Step 2 converting String to Json Object

        try {
            JSONObject obj=new JSONObject(movieSTR);

            String fname=obj.getString("fname");

            Toast.makeText(ViewPatientProfile.this, "the patient name: "+fname, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }





        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewPatientProfile.this, EditPatientProfile.class);
                startActivity(i);
            }
        });

        medicalProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewPatientProfile.this, ViewMedicalInfo.class);
                startActivity(i);
            }
        });
    }
}
