package com.pifss.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class EditMedicalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medical_info);

        Button saveChanges= (Button) findViewById(R.id.saveChangesMedicalButton);

        final EditText bloodtypeText=(EditText) findViewById(R.id.bloodtypeEditText);
        final EditText diabetesText=(EditText) findViewById(R.id.diabetesEditText);
        final EditText asthmaText=(EditText) findViewById(R.id.asthmaEditText);
        final EditText allergiesText=(EditText) findViewById(R.id.allergiesEditText);
        final EditText medicationsText=(EditText) findViewById(R.id.medicationsEditText);

        String url="";
        RequestQueue queue= MySingleton.getInstance().getRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



               // Toast.makeText(EditMedicalInfo.this, response, Toast.LENGTH_LONG);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });




        




    }
}
