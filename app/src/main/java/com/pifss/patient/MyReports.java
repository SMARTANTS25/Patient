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
import com.pifss.patient.Adapters.ReportAdapter;
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

      //  String Patientid = getSharedPreferences("PatientData", MODE_PRIVATE).getString("Patient", " ");

//        final ArrayList<Reports> parsedModel = new ArrayList<>();
//
//        for (int i = 0; i < model.size(); i++) {
//            Reports curDoc = model.get(i);
////            String fullName = curDoc.getFirstName() + " "+ curDoc.getMiddleName() + " " + curDoc.getLastName();
////            if ( fullName.toLowerCase().contains(filter) ) {
//
//            parsedModel.add(curDoc);
////            }
//        }




        updateModel ();

        lv = (ListView) findViewById(R.id.MyReportsLV);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Reports reports = model.get(position);

                Intent intent = new Intent(MyReports.this, ReportDetails.class);
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
                System.out.print(reports.getPainlocation()+" "+reports.getDizziness());
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





//    private void initAdapterWithFilter(String filter) {
//        if (model == null || model.size() <= 0) {
//            return;
//        }
//
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Reports reports = model.get(position);
//
//                Intent intent = new Intent(MyReports.this,ReportDetails.class);
//                intent.putExtra("heartBeat",reports.getHeartbeatRate());
//                intent.putExtra("bloodPressure",reports.getBloodPressure());
//                intent.putExtra("drId",reports.getDrId());
//                intent.putExtra("comments",reports.getComments());
//                intent.putExtra("fever",reports.getFever());
//                intent.putExtra("coughing",reports.getCoughing());
//                intent.putExtra("dizziness",reports.getDizziness());
//                intent.putExtra("headache",reports.getHeadache());
//                intent.putExtra("nauseous",reports.getNauseous());
//                intent.putExtra("pain",reports.getPain());
//                intent.putExtra("painLocation",reports.getPainlocation());
//                intent.putExtra("SugarLever",reports.getSugarLevel());
//                intent.putExtra("patientId",reports.getPatientId());
//                intent.putExtra("drComment",reports.getDrcomment());
//                intent.putExtra("date",reports.getTimestamp());
//                Toast.makeText(MyReports.this, reports.getPainlocation()+" "+reports.getDizziness(), Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//
//            }
//        });
//    }





    private void updateModel () {

        String Pid="";
        String PatientDat = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1"," ");
        try {
            JSONObject o = new JSONObject(PatientDat);
            Pid = o.getString("patientId");

        } catch (JSONException e) {
            e.printStackTrace();
        }
          Toast.makeText(this, Pid+"", Toast.LENGTH_SHORT).show();
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patientreport/getPr/"+Pid;

        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(MyReports.this);




        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                SharedPreferences sharedpreferences = getSharedPreferences("MyReportData", MODE_PRIVATE);

                System.out.println(response);

                sharedpreferences.edit()
                        .putString("MyReport" , response)
                        .commit();
                Gson gson = new Gson();
                JSONObject json = null;
                TypeToken <ArrayList<Reports>> token= new TypeToken<ArrayList<Reports>>(){};
                try {
                    json = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                model = gson.fromJson(response,token.getType());

                ReportAdapter adapter = new ReportAdapter(model, MyReports.this);

                lv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyReports.this, error.getMessage(), Toast.LENGTH_LONG);
            }
        });
        System.out.println(model);
        //System.out.println(model.size());
        Toast.makeText(this, "reached here ", Toast.LENGTH_SHORT).show();

        queue.add(request);
    }
}