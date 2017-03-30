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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pifss.patient.Adapters.HospitalAdapter;
import com.pifss.patient.utils.Hospital;
import com.pifss.patient.utils.MySingleton;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchHospital extends AppCompatActivity {

    Location currentLocation = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hospital);


        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbarHospitalSearch);

        toolbar.setTitle("Hospitals");

        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);


        SearchView svHospital = (SearchView) findViewById(R.id.searchViewHospital);



  //      final ArrayList<Hospital> model = getHospitals();





      //  Hospital hospital = new Hospital("aa@aa", "kuwait kuwait","this is the hospital we are looking for","21212", "alfrwania","55656565","www.waleed.wees","24/7","bone","22.4","43.1","1");


        svHospital.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                ArrayList<String> model1 = new ArrayList<String>();
//                model1.add(newText);
              //  HospitalAdapter hospitalA = new HospitalAdapter(model1 , SearchHospital.this);



                return false;
            }
        });



        final ListView lv= (ListView) findViewById(R.id.hospitalListView);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(SearchHospital.this, HospitalProfile.class);


            //    i.putExtra();
                startActivity(i);

            }
        });
    }

    private ArrayList<Hospital> getHospitals () {
        final ArrayList<Hospital> hospitals = new ArrayList<>();

        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/hospital/";


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(SearchHospital.this);


        final JsonObjectRequest jsonReq=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               // Toast.makeText(SearchHospital.this, "Al khaiyat is in the house and likes weed", Toast.LENGTH_SHORT).show();


                ArrayList<Hospital> hospitalsData =new Gson().fromJson(response.toString(),new TypeToken<ArrayList<Hospital>>(){}.getType());
                Toast.makeText(SearchHospital.this, response.toString(), Toast.LENGTH_SHORT).show();

                for (int i=0; i < hospitalsData.size(); i++) {
                    hospitals.add(hospitalsData.get(i));
                    Toast.makeText(SearchHospital.this, hospitals.get(i).getHospitalName(), Toast.LENGTH_SHORT).show();
                }


                HospitalAdapter hospitalAdapter = new HospitalAdapter(hospitalsData , SearchHospital.this);
                lv.setAdapter(hospitalAdapter);


            }
            
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(SearchHospital.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonReq);


        return hospitals;
    }

    public float distanceBetweenUserAndHospital(double latA, double longA) {
        Location loc1 = new Location("");
        loc1.setLatitude(latA);
        loc1.setLongitude(longA);

        return currentLocation.distanceTo(loc1);
    }


}
