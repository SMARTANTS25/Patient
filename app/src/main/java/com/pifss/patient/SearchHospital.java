package com.pifss.patient;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.patient.Adapters.DoctorAdapter;
import com.pifss.patient.Adapters.HospitalAdapter;
import com.pifss.patient.utils.Doctor;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchHospital extends AppCompatActivity {

    ArrayList<com.pifss.patient.Hospital> model;
    ListView lv;
    Location currentLocation = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hospital);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHospitalSearch);
        toolbar.setTitle("Hospitals");

        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        SearchView svHospital = (SearchView) findViewById(R.id.searchViewHospital);

        lv = (ListView) findViewById(R.id.hospitalListView);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                com.pifss.patient.Hospital h = model.get(position);

                Toast.makeText(SearchHospital.this, h.getHospitalName(), Toast.LENGTH_SHORT).show();

            }
        });

        updateModel();


        //  Hospital hospital = new Hospital("aa@aa", "kuwait kuwait","this is the hospital we are looking for","21212", "alfrwania","55656565","www.waleed.wees","24/7","bone","22.4","43.1","1");


        svHospital.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                initAdapterWithFilter(newText);


                return false;
            }
        });


        lv = (ListView) findViewById(R.id.hospitalListView);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(SearchHospital.this, HospitalProfile.class);


                //    i.putExtra();
                startActivity(i);

            }
        });



    }

    private void initAdapterWithFilter(String filter) {
        if (model == null || model.size() <= 0) {
            return;
        }

        ArrayList<com.pifss.patient.Hospital> parsedModel = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            com.pifss.patient.Hospital curHospital = model.get(i);
            String fullName = curHospital.getHospitalName();
            if ( fullName.toLowerCase().contains(filter) ) {
                parsedModel.add(curHospital);
            }
        }


        ListView lv = (ListView) findViewById(R.id.hospitalListView);

        HospitalAdapter adapter = new HospitalAdapter(parsedModel, this);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                com.pifss.patient.Hospital m = model.get(position);

                // move to doc profile

            }
        });
    }


    private void updateModel () {
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/hospital/";


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(SearchHospital.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        model = new Gson().fromJson(response, new TypeToken<ArrayList<com.pifss.patient.Hospital>>(){}.getType());

                        ListView lv = (ListView) findViewById(R.id.hospitalListView);

                        HospitalAdapter adapter = new HospitalAdapter(model, SearchHospital.this);

                        lv.setAdapter(adapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(SearchHospital.this, error.getMessage(), Toast.LENGTH_LONG);
                    }
                });

        queue.add(jsonReq);
    }



}
