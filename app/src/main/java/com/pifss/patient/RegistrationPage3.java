package com.pifss.patient;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

public class RegistrationPage3 extends AppCompatActivity {

    String diabetes="";
    String BloodType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page3);

        Button reg3= (Button) findViewById(R.id.Reg3_button);

        String RegEmail = getIntent().getStringExtra("email");
        String Regpassword = getIntent().getStringExtra("password");
        final String fname = getIntent().getStringExtra("firstName");
        final String lname = getIntent().getStringExtra("lastName");
        String bDate = getIntent().getStringExtra("birthDate");
        String civilID = getIntent().getStringExtra("civilID");
        String phoneNum = getIntent().getStringExtra("phoneNumber");
        String emergencyNum = getIntent().getStringExtra("emergencyNumber");

        ImageView bloodBPlus = (ImageView) findViewById(R.id.bplusImageButton);
        ImageView bloodBMinus = (ImageView) findViewById(R.id.bminImageButton);
        ImageView bloodAPlus = (ImageView) findViewById(R.id.aplusImgButton);
        ImageView bloodAMinus = (ImageView) findViewById(R.id.aminImgButton);
        ImageView bloodABPlus = (ImageView) findViewById(R.id.abplusImgButton);
        ImageView bloodABMinus = (ImageView) findViewById(R.id.abminImgButton);
        ImageView bloodOPlus = (ImageView) findViewById(R.id.oplusImageButton);
        ImageView bloodOMinus = (ImageView) findViewById(R.id.ominImageButton);


        EditText allergies = (EditText) findViewById(R.id.Reg_AllergiesET);
        EditText medications = (EditText) findViewById(R.id.Reg_MedicationsET);

        Switch SAsthma = (Switch) findViewById(R.id.switchAsthma);
        Switch SAllergies = (Switch) findViewById(R.id.switchAllergies);

        RadioButton RDiabetesNo = (RadioButton) findViewById(R.id.NoDiabetesRB);
        final RadioButton RDiabetesTOne = (RadioButton) findViewById(R.id.TypeOneDiabetesRB);
        RadioButton RDiabetesTTwo = (RadioButton) findViewById(R.id.TypeTwoDiabetesRB);

        BloodType = "";

        bloodBPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "B+";
            }
        });
        bloodBMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "B-";
            }
        });
        bloodAPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "A+";
            }
        });
        bloodAMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "A-";
            }
        });
        bloodABPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "AB+";
            }
        });
        bloodABMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "AB-";
            }
        });
        bloodOPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "O+";
            }
        });
        bloodOMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodType = "O-";
            }
        });

        SAsthma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(RegistrationPage3.this, ""+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        SAllergies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(RegistrationPage3.this, ""+isChecked, Toast.LENGTH_SHORT).show();
            }
        });


                String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/";
        final RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);
        JsonObjectRequest req = null;
        final JSONObject obj = new JSONObject();
        try {
            obj.put("email", getIntent().getStringExtra("email"));
            obj.put("password", getIntent().getStringExtra("password"));
            obj.put("fname", getIntent().getStringExtra("firstName"));
            obj.put("lname", getIntent().getStringExtra("lastName"));
            obj.put("birthDate" , getIntent().getStringExtra("birthDate"));
            obj.put("civilId" , getIntent().getStringExtra("civilID"));
            obj.put("phone",getIntent().getStringExtra("phoneNumber"));
            obj.put("emergencyNumber", getIntent().getStringExtra("emergencyNumber"));
            obj.put("bloodType",BloodType.toString());

             req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Toast.makeText(RegistrationPage3.this, response.toString(), Toast.LENGTH_SHORT).show();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(RegistrationPage3.this, error.toString(), Toast.LENGTH_SHORT).show();

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(req);



        reg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences pref = getSharedPreferences("PatientData",MODE_PRIVATE);

                pref.edit()
                        .putString("Patient",obj.toString())
                        .commit();

                Intent i = new Intent(RegistrationPage3.this, Home.class);
                Toast.makeText(RegistrationPage3.this, "hello & you are done " + getIntent().getStringExtra("firstName") + " "+ getIntent().getStringExtra("lastName"), Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });

    }

    public void onRadioButtonClicked(View view ) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.NoDiabetesRB:
                if (checked)
                    // Pirates are the best
                    diabetes = "No";
                break;
            case R.id.TypeOneDiabetesRB:
                if (checked)
                    diabetes = "Type One";
                    // Ninjas rule
                break;
            case R.id.TypeTwoDiabetesRB:
                if(checked)
                    diabetes = "Type Two";
                    break;
        }

    }


}
