package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistrationPage3 extends AppCompatActivity {


    String BloodType;

    boolean diabetes = false;
    boolean asthmas = false;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page3);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Register");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Button reg3= (Button) findViewById(R.id.Reg3_button);

        final String email = getIntent().getStringExtra("email1");
        final String password = getIntent().getStringExtra("password1");
        final String gender = getIntent().getStringExtra("gender1");

        final String fname = getIntent().getStringExtra("firstName");
        final String lname = getIntent().getStringExtra("lastName");
        final String bDate = getIntent().getStringExtra("birthDate");
        final String civilID = getIntent().getStringExtra("civilID");
        final String phoneNum = getIntent().getStringExtra("phoneNumber");
        final String emergencyNum = getIntent().getStringExtra("emergencyNumber");

        ImageButton bloodBPlus = (ImageButton) findViewById(R.id.Reg3_bplusImageButton);
        ImageButton bloodBMinus = (ImageButton) findViewById(R.id.Reg3_bminImageButton);
        ImageButton bloodAPlus = (ImageButton) findViewById(R.id.Reg3_aplusImgButton);
        ImageButton bloodAMinus = (ImageButton) findViewById(R.id.Reg3_aminImgButton);
        ImageButton bloodABPlus = (ImageButton) findViewById(R.id.Reg3_abplusImgButton);
        ImageButton bloodABMinus = (ImageButton) findViewById(R.id.Reg3_abminImgButton);
        ImageButton bloodOPlus = (ImageButton) findViewById(R.id.Reg3_oplusImageButton);
        ImageButton bloodOMinus = (ImageButton) findViewById(R.id.Reg3_ominImageButton);
        myImageButtons.add(bloodAMinus);
        myImageButtons.add(bloodAPlus);
        myImageButtons.add(bloodABMinus);
        myImageButtons.add(bloodABPlus);
        myImageButtons.add(bloodBMinus);
        myImageButtons.add(bloodBPlus);
        myImageButtons.add(bloodOMinus);
        myImageButtons.add(bloodOPlus);

        final EditText allergies = (EditText) findViewById(R.id.Reg3_AllergiesET);
        final EditText medications = (EditText) findViewById(R.id.Reg3_MedicationsET);

        Switch SAsthma = (Switch) findViewById(R.id.switchAsthma);
        Switch SAllergies = (Switch) findViewById(R.id.switchAllergies);

        final RadioButton RDiabetesNo = (RadioButton) findViewById(R.id.Reg3_NoDiabetesRB);
        final RadioButton RDiabetesTOne = (RadioButton) findViewById(R.id.Reg3_TypeOneDiabetesRB);

        diabetes = false;
        RDiabetesNo.setChecked(true);

        RDiabetesNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    diabetes = false;
                    RDiabetesTOne.setChecked(false);
                }

            }
        });
        RDiabetesTOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    diabetes = true;
                    RDiabetesNo.setChecked(false);
                }
            }
        });


        BloodType = "A-";
selectedBloodTypeImageButton(0);
        bloodBPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "B+";
                selectedBloodTypeImageButton(5);
            }
        });
        bloodBMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "B-";
                selectedBloodTypeImageButton(4);
            }
        });
        bloodAPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "A+";
                selectedBloodTypeImageButton(1);
            }
        });
        bloodAMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "A-";
                selectedBloodTypeImageButton(0);
            }
        });
        bloodABPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "AB+";
                selectedBloodTypeImageButton(3);
            }
        });
        bloodABMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "AB-";
                selectedBloodTypeImageButton(2);
            }
        });
        bloodOPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "O+";
                selectedBloodTypeImageButton(7);
            }
        });
        bloodOMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "O-";
                selectedBloodTypeImageButton(6);
            }
        });

        SAsthma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    asthmas = true;

            }
        });
        SAllergies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });







        final String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient";
        final RequestQueue queue = MySingleton.getInstance().getRequestQueue(RegistrationPage3.this);

        reg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(RegistrationPage3.this);

                JsonObjectRequest req = null;
                final JSONObject obj = new JSONObject();
                try {

                    obj.put("email", email);
                    obj.put("password", password);
                    obj.put("gender",gender);
                    obj.put("deleted",0);
                    obj.put("firstName", fname);
                    obj.put("lastName", lname);
                    obj.put("dateOfBirth" , bDate);
                    obj.put("civilId" , civilID);
                    obj.put("phoneNumber", phoneNum);
                    obj.put("emergencyNumber", emergencyNum);
                    obj.put("bloodType",BloodType);
                    obj.put("diabetes",diabetes);
                    obj.put("asthma",asthmas);
                    obj.put("status",true);

                    obj.put("nationality","");
                    obj.put("middleName","");
                    obj.put("imageUrl", "");
                    String alergies = allergies.getText().toString();
                    if (alergies.length() == 0){
                        alergies = "NO Alergies";
                    }
                    obj.put("allergies",alergies);
                    obj.put("medications",medications.getText().toString());
                    System.out.println(obj.toString());
                    req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.hide();

                            if (response.has("errorCode")){

                                int errorCode = 406;
                                try {
                                    errorCode = response.getInt("errorCode");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (errorCode == 200){
                                    Toast.makeText(RegistrationPage3.this, "Account created", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(RegistrationPage3.this , Login.class);

                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    finish();

                                    startActivity(i);
                                }else{
                                    Toast.makeText(RegistrationPage3.this, "Failed, use another email address.", Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(RegistrationPage3.this, "Connection Failed", Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressDialog.hide();

                            Toast.makeText(RegistrationPage3.this, "Error, connection failed", Toast.LENGTH_SHORT).show();

                        }
                    });
                    if (!isNetworkAvailable()){
                        Toast.makeText(RegistrationPage3.this, R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.setMessage("Connecting...");
                    progressDialog.show();
                    queue.add(req);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onRadioButtonClicked(View view ) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();

       //  Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Reg3_NoDiabetesRB:
                if (checked)
                    // Pirates are the best
                    diabetes = false;
                break;
            case R.id.Reg3_TypeOneDiabetesRB:
                if (checked)
                    diabetes = true;
                // Ninjas rule
                break;


        }
    }


    ArrayList<ImageButton> myImageButtons = new ArrayList<>();
    int selectedBloodTypeIndex = 0;
    public void selectedBloodTypeImageButton(int position) {
        selectedBloodTypeIndex = position;
        for (int i = 0; i < myImageButtons.size(); i++) {

            ImageButton button = myImageButtons.get(i);

            if (i == position) {
                button.setAlpha(1.0f);
            } else {
                button.setAlpha(0.4f);
            }

        }
    }
}
