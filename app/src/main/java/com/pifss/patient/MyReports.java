package com.pifss.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.patient.Adapters.DoctorAdapter;
import com.pifss.patient.Adapters.ReportAdapter;
import com.pifss.patient.utils.Doctor;
import com.pifss.patient.utils.Reports;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyReports extends AppCompatActivity {

    ArrayList<Reports> model;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMyReports);
        toolbar.setTitle("My Reports");
        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        setSupportActionBar(toolbar);

        String Patientid = getSharedPreferences("PatientData", MODE_PRIVATE).getString("Patient", " ");


        updateModel ();

        lv = (ListView) findViewById(R.id.MyReportsLV);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reports reports = model.get(position);

                Intent intent = new Intent(MyReports.this,ReportDetails.class);
                intent.putExtra("heartBeat",reports.getHeartbeatRate());
                intent.putExtra("bloodPressure",reports.getBloodPressure());
                intent.putExtra("drId",reports.getDrId());
                intent.putExtra("comments",reports.getComments());
                intent.putExtra("fever",reports.getFever());
                intent.putExtra("coughing",reports.getCoughing());
                intent.putExtra("dizziness",reports.getDizziness());
                intent.putExtra("headache",reports.getHeadache());
                intent.putExtra("nauseous",reports.getNauseous());
                intent.putExtra("pain",reports.getPain());
                intent.putExtra("painLocation",reports.getPainlocation());
                intent.putExtra("SugarLever",reports.getSugarLevel());
                intent.putExtra("patientId",reports.getPatientId());
                intent.putExtra("drComment",reports.getDrcomment());
                intent.putExtra("date",reports.getTimestamp());
                Toast.makeText(MyReports.this, reports.getPainlocation()+" "+reports.getDizziness(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }


        });
        // Step 2 converting String to Json Object

//        try {
//            JSONObject obj = new JSONObject(Patientid);
//            String id = obj.getString("patientId");
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        
    }





    private void initAdapterWithFilter(String filter) {
        if (model == null || model.size() <= 0) {
            return;
        }

        final ArrayList<Doctor> parsedModel = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            Reports curDoc = model.get(i);
            //String fullName = curDoc.getFirstName() + " "+ curDoc.getMiddleName() + " " + curDoc.getLastName();
//            if ( fullName.toLowerCase().contains(filter) ) {
//                parsedModel.add(curDoc);
//            }
        }


        ListView lv = (ListView) findViewById(R.id.MyDoctorList);

        DoctorAdapter adapter = new DoctorAdapter(parsedModel, this);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = parsedModel.get(position);

                Intent intent = new Intent(MyReports.this, ReportDetails.class);
                intent.putExtra("name", m.getFirstName()+m.getMiddleName()+m.getLastName());
                intent.putExtra("gender", m.getGender());
                intent.putExtra("specialty", m.getSpecialityId());
                intent.putExtra("nationality", m.getNationality());
                intent.putExtra("email", m.getEmail());
                intent.putExtra("cvURL", m.getCvUrl());
                intent.putExtra("imageURL", m.getImageUrl());
                intent.putExtra("drId",m.getDrId());
                Toast.makeText(MyReports.this, ""+m.getFirstName()+"  "+m.getDrId(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(MyDoctors.this, m.getDrId()+"", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }





    private void updateModel () {

        String Pid="";
        String PatientDat = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1"," ");
        try {
            JSONObject o = new JSONObject(PatientDat);
            Pid = o.getString("patientId");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  Toast.makeText(this, Pid+"", Toast.LENGTH_SHORT).show();
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patientreport/"+Pid;

        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(MyReports.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences sharedpreferences = getSharedPreferences("MyReportData", MODE_PRIVATE);

                        System.out.println(response);

                        try {
                            JSONObject obj = new JSONObject(response);
                            sharedpreferences.edit()
                                    .putString("MyReport" , obj.toString())
                                    .commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        model = new Gson().fromJson(response.toString(), new TypeToken<ArrayList<Doctor>>(){}.getType());
                        // Toast.makeText(MyDoctors.this, model.size()+"", Toast.LENGTH_SHORT).show();
                        ReportAdapter adapter = new ReportAdapter(model, MyReports.this);

                        lv.setAdapter(adapter);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(MyReports.this, "Connection error sorry", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonReq);
    }
}