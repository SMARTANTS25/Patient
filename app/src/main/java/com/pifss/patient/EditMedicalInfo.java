package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class EditMedicalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medical_info);

        Button saveChanges= (Button) findViewById(R.id.saveChangesMedicalButton);
        Button goToGeneralInfo = (Button) findViewById(R.id.editGeneralInfoButton);

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(EditMedicalInfo.this, .class);
               // startActivity(i);
            }
        });
        goToGeneralInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i = new Intent(EditMedicalInfo.this, EditPatientProfile.class);
                startActivity(i);
            }
        });

        final EditText bloodtypeText=(EditText) findViewById(R.id.bloodtypeEditText);
        final EditText diabetesText=(EditText) findViewById(R.id.diabetesEditText);
        final EditText asthmaText=(EditText) findViewById(R.id.asthmaEditText);
        final EditText allergiesText=(EditText) findViewById(R.id.allergiesEditText);
        final EditText medicationsText=(EditText) findViewById(R.id.medicationsEditText);

        final TextView nameText=(TextView) findViewById(R.id.PNameTF);


        //change URL
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/";
        RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);

        JSONObject obj = new JSONObject();
        try {
            obj.put("bloodtype", bloodtypeText.getText().toString());
            obj.put("diabetes", diabetesText.getText().toString());

            obj.put("asthma", asthmaText.getText().toString());
            obj.put("allergies", allergiesText.getText().toString());

            obj.put("medications", medicationsText.getText().toString());
            //check
           // Patient p=new Gson().fromJson(response,Patient.class);
            // nameText.setText(p.getFirstName());



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








    }
}
