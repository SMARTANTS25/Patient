package com.pifss.patient;

import android.content.Intent;
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
//import com.pifss.patient.utils.MySingleton;

import java.util.ArrayList;

public class MyDoctors extends AppCompatActivity {

    ArrayList<Doctor> model;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.AllDoctorToolbar);
        toolbar.setTitle("My Doctors");
        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

                startActivity(intent);

            }
        });
    }

    private void updateModel () {
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/accepteddoctor/2";


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(MyDoctors.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        model = new Gson().fromJson(response, new TypeToken<ArrayList<Doctor>>(){}.getType());

                        DoctorAdapter adapter = new DoctorAdapter(model, MyDoctors.this);

                        lv.setAdapter(adapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        queue.add(jsonReq);
    }

}
