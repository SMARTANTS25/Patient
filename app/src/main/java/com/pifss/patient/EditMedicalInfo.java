package com.pifss.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditMedicalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medical_info);

        Button saveChanges= (Button) findViewById(R.id.saveChangesMedicalButton);

        EditText bloodtype=(EditText) findViewById(R.id.bloodtypeEditText);
        EditText diabetes=(EditText) findViewById(R.id.diabetesEditText);
        EditText asthma=(EditText) findViewById(R.id.asthmaEditText);
        EditText allergies=(EditText) findViewById(R.id.allergiesEditText);
        EditText medications=(EditText) findViewById(R.id.medicationsEditText);




    }
}
