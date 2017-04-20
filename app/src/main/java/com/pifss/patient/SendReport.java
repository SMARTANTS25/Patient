package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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

public class SendReport extends AppCompatActivity {




    int Pain;
    String Headache;
    String Dizziness;
    String docName;
    String imgurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        toolbar.setTitle(R.string.SendReport_Send);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.aplus);

        ImageView DcoImage = (ImageView) findViewById(R.id.SendReport_DoctorImage);
        final TextView DocName = (TextView) findViewById(R.id.SendReport_DoctorNameTV);

        RadioButton PainOne = (RadioButton) findViewById(R.id.ReportPainRadioOne);
        RadioButton PainTwo = (RadioButton) findViewById(R.id.ReportPainRadioTwo);
        RadioButton PainThree = (RadioButton) findViewById(R.id.ReportPainRadioThree);
        RadioButton PainFour = (RadioButton) findViewById(R.id.ReportPainRadioFour);
        RadioButton PainFive = (RadioButton) findViewById(R.id.ReportPainRadioFive);

        final EditText PainLocation = (EditText) findViewById(R.id.SendReport_PainLocation);

        RadioButton HeadacheYes = (RadioButton) findViewById(R.id.ReportHeadacheYes);
        RadioButton HeadacheNo = (RadioButton) findViewById(R.id.ReportHeadacheNo);

        RadioButton DizzinessYes = (RadioButton) findViewById(R.id.ReportDizzinessYes);
        RadioButton DizzinessNo = (RadioButton) findViewById(R.id.ReportDizzinessNo);

        Button b = (Button) findViewById(R.id.SendReport_Button);

//        String DocId ="";
//        String MyDoctorData = getSharedPreferences("MyDoctorData",MODE_PRIVATE).getString("MyDoc"," error");
//        try {
//            JSONObject obj = new JSONObject(MyDoctorData);
//             DocId = obj.getString("drId");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        final int DocId = getIntent().getIntExtra("DocId",0);
        Toast.makeText(this, DocId+"  weeeeeeeeee", Toast.LENGTH_SHORT).show();
       // int Docidds  = DocId;
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/doctor/"+DocId;



        final RequestQueue queue = MySingleton.getInstance().getRequestQueue(SendReport.this);
        final ProgressDialog progressDialog = new ProgressDialog(SendReport.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
progressDialog.hide();
                        System.out.println(response);
                        try {
                            JSONObject obj = new JSONObject(response);
                             docName = obj.getString("firstName") +" "+ obj.getString("lastName");
                             imgurl = obj.getString("imageUrl");

                            DocName.setText(docName);
                            Toast.makeText(SendReport.this, docName+"", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        progressDialog.hide();
                    }
                });

        if (!isNetworkAvailable()){
            Toast.makeText(SendReport.this, ""+R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
        }



        progressDialog.setMessage("Connecting...");
        progressDialog.show();
        queue.add(jsonReq);

        if (imgurl != null && imgurl.length() >= 1 ) {
            Picasso.with(this).load(imgurl).into(DcoImage);

        }




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Pain Level
        PainOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pain = 1;
            }
        });
        PainTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pain = 2;
            }
        });
        PainThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pain = 3;
            }
        });
        PainFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pain = 4;
            }
        });
        PainFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pain = 5;
            }
        });

        // ---------------------------------------
        HeadacheYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Headache = "Yes";
            }
        });
        HeadacheNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Headache = "No";
            }
        });

        DizzinessYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dizziness = "Yes";
            }
        });
        DizzinessNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dizziness ="No";
            }
        });



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SendReport.this , SendReportPage2.class);
                i.putExtra("pain",Pain);
                i.putExtra("painLocation",PainLocation.getText().toString());
                i.putExtra("headache",Headache);
                i.putExtra("dizziness",Dizziness);
                i.putExtra("drid",DocId);
                startActivity(i);


                    }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
//
    public boolean sendReport(String comments) {
        return false;
    }

}
