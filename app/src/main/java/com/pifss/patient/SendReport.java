package com.pifss.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class SendReport extends AppCompatActivity {




    int Pain;
    String Headache;
    String Dizziness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Send Report");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.aplus);

        ImageView DcoImage = (ImageView) findViewById(R.id.SendReport_DoctorImage);
        TextView DocName = (TextView) findViewById(R.id.SendReport_DoctorNameTV);

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

                Intent i = new Intent(SendReport.this,SendReportPage2.class);
                i.putExtra("pain",Pain);
                i.putExtra("painLocation",PainLocation.getText().toString());
                i.putExtra("headache",Headache);
                i.putExtra("dizziness",Dizziness);




                    }
        });

    }
//
    public boolean sendReport(String comments) {
        return false;
    }

}
