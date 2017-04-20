package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.patient.Adapters.HospitalAdapter;
import com.pifss.patient.utils.Hospital;

import java.util.ArrayList;

public class SearchHospital extends AppCompatActivity {

    ArrayList<Hospital> model;
    ListView lv;
    Location currentLocation = new Location("");

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hospital);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHospitalSearch);

        toolbar.setTitle("Hospitals");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SearchView svHospital = (SearchView) findViewById(R.id.searchViewHospital);


        updateModel();

        lv = (ListView) findViewById(R.id.hospitalListView);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.pifss.patient.utils.Hospital h = model.get(position);
                //
                Intent intent = new Intent(SearchHospital.this, HospitalProfile.class);

                intent.putExtra("name", h.getHospitalName());
                intent.putExtra("phoneNumber", h.getPhone());
                intent.putExtra("type", h.getType());
                intent.putExtra("workingHours", h.getWorkingHours());
                intent.putExtra("email", h.getEmail());
                intent.putExtra("extraInfo", h.getExtraInfo());
                intent.putExtra("address", h.getAddress());
                intent.putExtra("specialty", h.getSpecialityId());
                intent.putExtra("logoURL", h.getLogoUrl());
                intent.putExtra("specialityId",h.getSpecialityId());
                startActivity(intent);

            }
        });


        //R.id.textViewHospitalAddress


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



    }

    private void initAdapterWithFilter(String filter) {
        if (model == null || model.size() <= 0) {
            return;
        }

        final ArrayList<Hospital> parsedModel = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            com.pifss.patient.utils.Hospital curHospital = model.get(i);
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

                com.pifss.patient.utils.Hospital h = parsedModel.get(position);
                //
                Intent intent = new Intent(SearchHospital.this, HospitalProfile.class);

                intent.putExtra("name", h.getHospitalName());
                intent.putExtra("phoneNumber", h.getPhone());
                intent.putExtra("type", h.getType());
                intent.putExtra("workingHours", h.getWorkingHours());
                intent.putExtra("email", h.getEmail());
                intent.putExtra("extraInfo", h.getExtraInfo());
                intent.putExtra("address", h.getAddress());
                intent.putExtra("specialtyId", h.getSpecialityId());
                intent.putExtra("logoURL", h.getLogoUrl());

                startActivity(intent);

            }
        });
    }


    private void updateModel () {
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/hospital/";


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(SearchHospital.this);
        final ProgressDialog progressDialog = new ProgressDialog(SearchHospital.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.hide();

                        model = new Gson().fromJson(response, new TypeToken<ArrayList<com.pifss.patient.utils.Hospital>>(){}.getType());

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
                        progressDialog.hide();

                    }
                });
        if (!isNetworkAvailable()){
            Toast.makeText(SearchHospital.this, ""+R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
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