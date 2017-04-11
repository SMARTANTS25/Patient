package com.pifss.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

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

        String PainLocation = getIntent().getStringExtra("painLocation");
        String Headache = getIntent().getStringExtra("headache");
        String Dizziness = getIntent().getStringExtra("dizziness");
        int pain = getIntent().getIntExtra("pain",0);





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

        EditText Comments = (EditText) findViewById(R.id.SendReport_CommentsEText);

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

                JsonObjectRequest req = null;
                final JSONObject obj = new JSONObject();

            }
        });




    }
}
