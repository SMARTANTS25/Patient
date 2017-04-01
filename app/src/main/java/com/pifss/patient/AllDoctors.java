package com.pifss.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class AllDoctors extends AppCompatActivity {

    ArrayList<Doctor> model;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_doctors);

        Toolbar toolbar = (Toolbar) findViewById(R.id.AllDoctorToolbar);
        toolbar.setTitle("All Doctors");
        //setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.aplus);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Doctor List

        updateModel();


        ListView lv = (ListView) findViewById(R.id.AllDoctorList);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = model.get(position);

                Toast.makeText(AllDoctors.this, m.getFirstName(), Toast.LENGTH_SHORT).show();

            }
        });

        // Search Bar

        final android.widget.SearchView sv = (android.widget.SearchView) findViewById(R.id.AllDoctorSearchView);


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

    }

    private void initAdapterWithFilter(String filter) {
        if (model == null || model.size() <= 0) {
            return;
        }

        ArrayList<Doctor> parsedModel = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            Doctor curDoc = model.get(i);
            String fullName = curDoc.getFirstName() + " "+ curDoc.getMiddleName() + " " + curDoc.getLastName();
            if ( fullName.toLowerCase().contains(filter) ) {
                parsedModel.add(curDoc);
            }
        }


        ListView lv = (ListView) findViewById(R.id.AllDoctorList);

        DoctorAdapter adapter = new DoctorAdapter(parsedModel, this);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = model.get(position);

                // move to doc profile

            }
        });
    }


    private void updateModel () {
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/doctor";


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(AllDoctors.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AllDoctors.this, response, Toast.LENGTH_LONG);
                        model = new Gson().fromJson(response, new TypeToken<ArrayList<Doctor>>(){}.getType());

                        ListView lv = (ListView) findViewById(R.id.AllDoctorList);

                        DoctorAdapter adapter = new DoctorAdapter(model, AllDoctors.this);

                        lv.setAdapter(adapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(AllDoctors.this, error.getMessage(), Toast.LENGTH_LONG);
                    }
                });

        queue.add(jsonReq);
    }
}
