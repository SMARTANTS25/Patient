package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
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

    ArrayList<Reports> model = new ArrayList<>();
    private ListView lv;
    private ReportAdapter adapter;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMyReports);

        toolbar.setTitle(R.string.Home_MyReports);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SearchView searchViewReport = (SearchView) findViewById(R.id.searchViewMyReports);


        updateModel();

        lv = (ListView) findViewById(R.id.MyReportsLV);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Reports reports = model.get(position);

                Intent intent = new Intent(MyReports.this, ReportDetails.class);
                intent.putExtra("heartBeat",reports.getHeartbeatRate());
                intent.putExtra("bloodPressure",reports.getBloodPressure());
                intent.putExtra("drId",reports.getDrId());
              //  Toast.makeText(MyReports.this, reports.getDrId()+"", Toast.LENGTH_SHORT).show();
                intent.putExtra("comments",reports.getComments());
                intent.putExtra("fever",reports.getFever());
                intent.putExtra("coughing",reports.getCoughing());
                intent.putExtra("dizziness",reports.getDizziness());
                intent.putExtra("headache",reports.getHeadache());
                intent.putExtra("nauseous",reports.getNauseous());
                intent.putExtra("pain",reports.getPain());
                //Toast.makeText(MyReports.this, reports.getPain()+"", Toast.LENGTH_SHORT).show();
                intent.putExtra("painLocation",reports.getPainlocation());
                intent.putExtra("SugarLever",reports.getSugarLevel());
                intent.putExtra("patientId",reports.getPatientId());
                intent.putExtra("drComment",reports.getDrcomment());
                intent.putExtra("date",reports.getTimestamp());
                System.out.print(reports.getPainlocation()+" "+reports.getDizziness());
            //    Toast.makeText(MyReports.this, reports.getPainlocation()+" "+reports.getDizziness(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }


        });


        searchViewReport.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                initAdapterWithFilter(newText);


                return false;
            }
        });



    }

    private void initAdapterWithFilter(String filter) {
        if (model == null || model.size() <= 0) {
            return;
        }

        final ArrayList<Reports> parsedModel = new ArrayList<>();

        for (int i = 0; i < adapter.model.size(); i++) {
            Reports currentReport = adapter.model.get(i);


            String fullName = currentReport.doctorName;
            if ( fullName.toLowerCase().contains(filter) ) {
                parsedModel.add(currentReport);
            }
        }

        ListView lv = (ListView) findViewById(R.id.MyReportsLV);

        ReportAdapter adapter = new ReportAdapter(parsedModel, this);


        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Reports reports = parsedModel.get(position);

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
      //    Toast.makeText(this, Pid+"", Toast.LENGTH_SHORT).show();
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patientreport/getPr/"+Pid;

        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(MyReports.this);
        final ProgressDialog progressDialog = new ProgressDialog(MyReports.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                progressDialog.hide();

                Gson gson = new Gson();
                TypeToken <ArrayList<Reports>> token= new TypeToken<ArrayList<Reports>>(){};

                model = gson.fromJson(response,token.getType());

                adapter = new ReportAdapter(model, MyReports.this);

                lv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();

                Toast.makeText(MyReports.this, error.getMessage(), Toast.LENGTH_LONG);
            }
        });
        progressDialog.setMessage("Connecting...");
        progressDialog.show();
        queue.add(request);
    }
}