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

        ImageView imageProfile = (ImageView) findViewById(R.id.ViewPatientProfile_PatientImage);

        Button editButton=(Button) findViewById(R.id.ViewPatientProfile_editPersonalInfoButton);
        Button myDrButton=(Button) findViewById(R.id.ViewMedicalInfo_mydoctorsButton);
        Button medicalProfileButton = (Button) findViewById(R.id.ViewPatientProfile_medicalInfoViewButton);


        final TextView nameText=(TextView) findViewById(R.id.ViewPatientProfile_PatientNameTV);
        TextView genderText=(TextView) findViewById(R.id.ViewPatientProfile_GenderVT);
        final TextView birthdateText=(TextView) findViewById(R.id.ViewPatientProfile_BirthDateVT);
        final TextView emailText=(TextView) findViewById(R.id.ViewPatientProfile_emailVT);
        final TextView phoneText=(TextView) findViewById(R.id.ViewPatientProfile_phoneVT);
        final TextView emergencyNoText=(TextView) findViewById(R.id.ViewPatientProfile_emergencyNumberTV);




        String PatientProfile  = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1"," ");

        // Step 2 converting String to Json Object

        try {
            JSONObject obj=new JSONObject(PatientProfile);

            String fname = obj.getString("firstName");
            String lname = obj.getString("lastName");
            nameText.setText(fname +" "+ lname);

            System.out.print(fname +" "+ lname);

            String gender = obj.getString("gender");

      //      Toast.makeText(this, obj.getString("patientId")+"", Toast.LENGTH_SHORT).show();
            System.out.print(obj.getString("patientId"));
            if (gender.equals("M"))
                genderText.setText("male");
            else genderText.setText("female");

            String email = obj.getString("email");
            emailText.setText(email);
            String phone = obj.getString("phoneNumber");
            String emergency = obj.getString("emergencyNumber");
            phoneText.setText(phone);
            emergencyNoText.setText(emergency);
            String birthDate = obj.getString("dateOfBirth");
            birthdateText.setText(birthDate);

            Toast.makeText(ViewPatientProfile.this, "the patient name: " + fname, Toast.LENGTH_SHORT).show();

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
