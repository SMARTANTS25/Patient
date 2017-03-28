package com.pifss.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pifss.patient.Adapters.HospitalAdapter;

import java.util.ArrayList;

public class SearchHospital extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hospital);



        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbarHospitalSearch);

        toolbar.setTitle("Hospitals");

        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);

        SearchView svHospital = (SearchView) findViewById(R.id.searchViewHospital);

        final ArrayList<String> model = new ArrayList<>();


      //  Hospital hospital = new Hospital("aa@aa", "kuwait kuwait","this is the hospital we are looking for","21212", "alfrwania","55656565","www.waleed.wees","24/7","bone","22.4","43.1","1");



//        model.add(new Hospital("walsa","this is the hospital we are looking for","ss@weed.com"));


        model.add("walsa");

        model.add("this is the hospital we are looking for");
        model.add("ss@weed.com");
       // model.add(new Hospital("aa@aa", "kuwait kuwait","this is the hospital we are looking for","21212", "alfrwania","55656565","www.waleed.wees","24/7","bone","22.4","43.1","1"));



        ListView lv= (ListView) findViewById(R.id.hospitalListView);


        svHospital.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<String> model1 = new ArrayList<String>();
                model1.add(newText);
                HospitalAdapter hospitalA = new HospitalAdapter(model1 , SearchHospital.this);



                return false;
            }
        });
        HospitalAdapter hospitalAdapter = new HospitalAdapter(model ,this);

        lv.setAdapter(hospitalAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(SearchHospital.this, HospitalProfile.class);

                startActivity(i);

            }
        });
    }


}
