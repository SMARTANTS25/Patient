package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportDetails extends AppCompatActivity {

    String Dname="";

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        final ProgressDialog progressDialog = new ProgressDialog(ReportDetails.this);

        toolbar.setTitle("Report Details");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final TextView Docname = (TextView) findViewById(R.id.ReportDetails_DocNameTV);
        TextView BloodPre = (TextView) findViewById(R.id.ReportDetails_BloodTV);
        TextView Cooment = (TextView) findViewById(R.id.ReportDetails_CommentsTV);
        TextView Nauseous = (TextView) findViewById(R.id.ReportDetails_NauseousTV);
        TextView Headache = (TextView) findViewById(R.id.ReportDetails_HeadacheTV);
        TextView HeartBeat = (TextView) findViewById(R.id.ReportDetails_HeartBeatTV);
        TextView Coughing = (TextView) findViewById(R.id.ReportDetails_CoughingTV);
        TextView Fever = (TextView) findViewById(R.id.ReportDetails_FeverTV);
        TextView Pain = (TextView) findViewById(R.id.ReportDetails_PainTV);
        TextView PainLocation = (TextView) findViewById(R.id.ReportDetails_PainLocationTV);
        TextView SugarLevel = (TextView) findViewById(R.id.ReportDetails_SugarLevelTV);
        //TextView Dizziness = findViewById(R.id.ReportDetails_)
        ImageView drImg = (ImageView) findViewById(R.id.ReportDetails_DrImg);

        String heartBeat = getIntent().getStringExtra("heartBeat");
        String bloodPressure = getIntent().getStringExtra("bloodPressure");
        String commets = getIntent().getStringExtra("comments");
        String fever = getIntent().getStringExtra("fever");
        String coughing = getIntent().getStringExtra("coughing");
        //String dizziness = getIntent().getStringExtra("dizziness");
        String headache = getIntent().getStringExtra("headache");
      //  String pain = getIntent().getStringExtra("pain");
       boolean pain = getIntent().getBooleanExtra("pain",true);
        // Toast.makeText(this, pain+"", Toast.LENGTH_SHORT).show();
        String painLocation = getIntent().getStringExtra("painLocation");
        String sugarLevel = getIntent().getStringExtra("SugarLever");
        String nouseous = getIntent().getStringExtra("nauseous");
        String DrImg = getIntent().getStringExtra("drImg");
      //  String Did = getIntent().getStringExtra("drId");
        int dd  = getIntent().getIntExtra("drId",0);
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/doctor/"+dd;


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(ReportDetails.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj;
                progressDialog.hide();
            try{
                obj = new JSONObject(response);

                    Dname = obj.getString("firstName")+" "+obj.getString("lastName");


                Docname.setText(Dname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReportDetails.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonReq);
     //   Toast.makeText(this, Dname+" doctor ID:"+dd, Toast.LENGTH_SHORT).show();
       if (nouseous.equals("no"))
        Nauseous.setText(R.string.nauseous_No);
        else
            Nauseous.setText(R.string.nauseous_Yes);



        PainLocation.setText(painLocation);

        if (coughing.equals("no"))
            Coughing.setText(R.string.nauseous_No);
        else
            Coughing.setText(R.string.nauseous_Yes);

        if (sugarLevel.equals("high"))
        SugarLevel.setText(R.string.SendReport_High);
        else if (sugarLevel.equals("moderate"))
            SugarLevel.setText(R.string.SendReport_Moderate);
        else SugarLevel.setText(R.string.SendReport_Low);

        if (headache.equals("no"))
        Headache.setText(R.string.nauseous_No);
        else Headache.setText(R.string.nauseous_Yes);

        if (heartBeat.equals("no"))
        HeartBeat.setText(R.string.nauseous_No);
        else HeartBeat.setText(R.string.nauseous_Yes);

        if (fever.equals("no"))
        Fever.setText(R.string.nauseous_No);
        else Fever.setText(R.string.nauseous_Yes);


        Cooment.setText(commets);

        if (bloodPressure.equals("high"))
        BloodPre.setText(R.string.SendReport_High);
        else if (bloodPressure.equals("moderate"))
            BloodPre.setText(R.string.SendReport_Moderate);
        else BloodPre.setText(R.string.SendReport_Low);


        if (pain)
        {
            Pain.setText(R.string.MyReport_HavePain);
        }
        else
        {
            Pain.setText(R.string.MyReportNoPain);
        }

        if (DrImg != null && DrImg.length() >= 4 &&  DrImg.isEmpty() == false ) {
            Picasso.with(this).load(DrImg).into(drImg);
        }

        if (!isNetworkAvailable()){
            Toast.makeText(ReportDetails.this, R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Connecting...");
        progressDialog.show();
        queue.add(jsonReq);





    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
