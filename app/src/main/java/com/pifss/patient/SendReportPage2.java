package com.pifss.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SendReportPage2 extends AppCompatActivity {

    String Nausaous;
    String HeartBeat;
    String Coughing;
    String BloodPressure;
    String SugarLevel;
    String Fever;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report_page2);

        final String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patientreport/";
        final RequestQueue queue = MySingleton.getInstance().getRequestQueue(SendReportPage2.this);

        final String PainLocation = getIntent().getStringExtra("painLocation");
        final String Headache = getIntent().getStringExtra("headache");
        final String Dizziness = getIntent().getStringExtra("dizziness");
        final int pain = getIntent().getIntExtra("pain",0);
        final int drId = getIntent().getIntExtra("drid",0);




        RadioButton NauseousYes = (RadioButton) findViewById(R.id.ReportNauseousYes);
        RadioButton NausaousNo = (RadioButton) findViewById(R.id.ReportNauseousNo);

        RadioButton HeartRateHigh = (RadioButton) findViewById(R.id.ReportHeartBeatsRateHigh);
        RadioButton HeartRateModerate = (RadioButton) findViewById(R.id.ReportHeartBeatsRateModerate);
        RadioButton HeartRateLow = (RadioButton) findViewById(R.id.ReportHeartBeatsRateLow);

        RadioButton CoughingYes = (RadioButton) findViewById(R.id.ReportCoughingYes);
        RadioButton CoughingNo = (RadioButton) findViewById(R.id.ReportCoughingNo);

        RadioButton BloodPressureHigh = (RadioButton) findViewById(R.id.ReportBloodPressureHigh);
        RadioButton BloodPressureModerate = (RadioButton) findViewById(R.id.ReportBloodPressureModerate);
        RadioButton BloodPressureLow = (RadioButton) findViewById(R.id.ReportBloodPressureLow);

        RadioButton SugarLevelHigh = (RadioButton) findViewById(R.id.ReportSugarLevelHigh);
        RadioButton SugarLevelModerate = (RadioButton) findViewById(R.id.ReportSugarLevelModerate);
        RadioButton SugarLevelLow = (RadioButton) findViewById(R.id.ReportSugarLevelLow);

        RadioButton FeverYes = (RadioButton) findViewById(R.id.ReportFeverYes);
        RadioButton FeverNo = (RadioButton) findViewById(R.id.ReportFeverNo);

        final EditText Comments = (EditText) findViewById(R.id.SendReport_CommentsEText);

        Button Submit = (Button) findViewById(R.id.SendReport_SubmitButton);

        NauseousYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nausaous = "Yes";
            }
        });
        NausaousNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nausaous = "No";
            }
        });

        HeartRateHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeartBeat = "High";
            }
        });

        HeartRateModerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeartBeat = "Moderate";
            }
        });

        HeartRateLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeartBeat = "Low";
            }
        });
        CoughingYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coughing ="Yes";
            }
        });
        CoughingNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coughing ="No";
            }
        });
        BloodPressureHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodPressure = "High";
            }
        });
        BloodPressureModerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodPressure = "Moderate";
            }
        });
        BloodPressureLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodPressure = "Low";
            }
        });

        SugarLevelHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SugarLevel = "High";
            }
        });
        SugarLevelModerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SugarLevel = "Moderate";
            }
        });
        SugarLevelLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SugarLevel = "Low";
            }
        });
        FeverYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fever = "Yes";
            }
        });
        FeverNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fever = "No";
            }
        });





        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patientreport";

                    /*
        * {
    "bloodPressure": "high",
    "comments": "High sugar",
    "coughing": "no",
    "dizziness": "yes",
    "drId": 7,
    "drcomment": "Eat less suge hello android",
    "fever": "no",
    "headache": "Yes",
    "heartbeatRate": "high",
    "nauseous": "no",
    "pain": true,
    "painlocation": "Head",
    "patientId": 2,
    "reportId": 2,
    "sugarLevel": "high",
    "timestamp": "2017-02-02T00:00:00Z"
  }
  */
                String pId="";
                String Patientdata  = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1"," ");

                try {
                    JSONObject o = new JSONObject(Patientdata);
                    pId = o.getString("patientId");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest req = null;
                final JSONObject obj = new JSONObject();
                try {
//                    Toast.makeText(SendReportPage2.this, pain+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, PainLocation+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, Dizziness+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, Fever+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, SugarLevel+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, Coughing+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, Headache+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, Comments.getText().toString()+"", Toast.LENGTH_SHORT).show();

//                    Toast.makeText(SendReportPage2.this, drId+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SendReportPage2.this, pId+"", Toast.LENGTH_SHORT).show();


                    obj.put("painlocation",PainLocation);
                    obj.put("dizziness",Dizziness);
                    obj.put("fever",Fever);
                    obj.put("sugarLevel",SugarLevel);
                    obj.put("coughing",Coughing);
                    obj.put("headache",Headache);
                    obj.put("comments",Comments.getText().toString());
//                    Date s = new Date();
//                    android.text.format.DateFormat form = new android.text.format.DateFormat();
//                    form.format("yyyy-MM-dd", s);
//                    obj.put("timestamp",s.toString()+"T00:00:00Z");
                    obj.put("heartbeatRate",HeartBeat);
                    obj.put("drId",drId);
                    obj.put("pain",true);
                    obj.put("patientId",Integer.parseInt(pId));
                    obj.put("nauseous",Nausaous);
                    obj.put("drcomment"," ");
                    obj.put("bloodPressure",BloodPressure);
                    System.out.print(obj);
                final RequestQueue queue = MySingleton.getInstance().getRequestQueue(SendReportPage2.this);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(SendReportPage2.this,Home.class);
                startActivity(i);
            }
        });




    }
}
