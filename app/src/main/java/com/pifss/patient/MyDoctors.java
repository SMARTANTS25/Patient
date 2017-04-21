package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.pifss.patient.Adapters.DoctorAdapter;
import com.pifss.patient.utils.Doctor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import com.pifss.patient.utils.MySingleton;

public class MyDoctors extends AppCompatActivity {

    ArrayList<Doctor> model;
    private ListView lv;

    String Id;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctors);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.AllDoctorToolbar);

        toolbar.setTitle("My Doctors");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        // Doctor List

        updateModel();


         lv = (ListView) findViewById(R.id.MyDoctorList);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = model.get(position);
                //
                Intent intent = new Intent(MyDoctors.this, MyDoctorProfile.class);
                intent.putExtra("name", m.getFirstName()+" "+m.getMiddleName()+" "+m.getLastName());
                intent.putExtra("gender", m.getGender());
                intent.putExtra("specialty", m.getSpecialityId());
                intent.putExtra("nationality", m.getNationality());
                intent.putExtra("email", m.getEmail());
                intent.putExtra("cvURL", m.getCvUrl());
                intent.putExtra("imageURL", m.getImageUrl());
                intent.putExtra("drId",m.getDrId());
            //    Toast.makeText(MyDoctors.this, m.getFirstName()+"  "+m.getDrId(), Toast.LENGTH_SHORT).show();

                startActivity(intent);

            }
        });

        // Search Bar

        final android.widget.SearchView sv = (android.widget.SearchView) findViewById(R.id.MyDoctorSearchView);


        sv.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                initAdapterWithFilter(newText.toLowerCase());

                return false;
            }
        });


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Myfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void initAdapterWithFilter(String filter) {
        if (model == null || model.size() <= 0) {
            return;
        }

        final ArrayList<Doctor> parsedModel = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            Doctor curDoc = model.get(i);
            String fullName = curDoc.getFirstName() + " "+ curDoc.getMiddleName() + " " + curDoc.getLastName();
            if ( fullName.toLowerCase().contains(filter) ) {
                parsedModel.add(curDoc);
            }
        }


        ListView lv = (ListView) findViewById(R.id.MyDoctorList);

        DoctorAdapter adapter = new DoctorAdapter(parsedModel, this);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = parsedModel.get(position);

                Intent intent = new Intent(MyDoctors.this, MyDoctorProfile.class);
                intent.putExtra("name", m.getFirstName()+m.getMiddleName()+m.getLastName());
                intent.putExtra("gender", m.getGender());
                intent.putExtra("specialty", m.getSpecialityId());
                intent.putExtra("nationality", m.getNationality());
                intent.putExtra("email", m.getEmail());
                intent.putExtra("cvURL", m.getCvUrl());
                intent.putExtra("imageURL", m.getImageUrl());
                intent.putExtra("drId",m.getDrId());
        //        Toast.makeText(MyDoctors.this, ""+m.getFirstName()+"  "+m.getDrId(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(MyDoctors.this, m.getDrId()+"", Toast.LENGTH_SHORT).show();
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
      //  Toast.makeText(this, Pid+"", Toast.LENGTH_SHORT).show();
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/accepteddoctor/"+Pid;

        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(MyDoctors.this);
        final ProgressDialog progressDialog = new ProgressDialog(MyDoctors.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        SharedPreferences sharedpreferences = getSharedPreferences("MyDoctorData", MODE_PRIVATE);

                        System.out.println(response);

                        model = new Gson().fromJson(response.toString(), new TypeToken<ArrayList<Doctor>>(){}.getType());
                       // Toast.makeText(MyDoctors.this, model.size()+"", Toast.LENGTH_SHORT).show();
                        DoctorAdapter adapter = new DoctorAdapter(model, MyDoctors.this);

                        lv.setAdapter(adapter);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        // Handle error
                        Toast.makeText(MyDoctors.this, "Connection error sorry", Toast.LENGTH_SHORT).show();
                    }
                });
        if (!isNetworkAvailable()){
            Toast.makeText(MyDoctors.this, R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
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
