package com.pifss.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SendReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Send Report");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.aplus);


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


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // ---------------------------------------

        Button b = (Button) findViewById(R.id.SendReport_Button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SendReport.this, "Report Sent", Toast.LENGTH_SHORT).show();
            }
        });

    }
//
    public boolean sendReport(String comments) {
        return false;
    }

}
