package com.pifss.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pifss.patient.Adapters.DoctorAdapter;
import com.pifss.patient.utils.Doctor;

import java.util.ArrayList;

public class AllDoctors extends AppCompatActivity {



    
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

        final ArrayList<Doctor> model = new ArrayList<>();

        Doctor d = new Doctor();

        d.setFirstName("Ahmad");
        d.setMiddleName("Ahmad");
        d.setLastName("Ahmad");
        d.setImageUrl("http://images.pocketgamer.co.uk/artwork/na-wrds/mario-2.png");
        d.setSpecialityId("eye doctor");
        d.setGender("f");

        Doctor d2 = new Doctor();

        d2.setFirstName("mahmod");
        d2.setMiddleName("Ahmad");
        d2.setLastName("Ahmad");
        d2.setImageUrl("http://images.pocketgamer.co.uk/artwork/na-wrds/mario-2.png");
        d2.setSpecialityId("eye doctor");
        d2.setGender("m");

        model.add(d);
        model.add(d2);


        ListView lv = (ListView) findViewById(R.id.MyDoctorList);

        DoctorAdapter adapter = new DoctorAdapter(model, this);


        lv.setAdapter(adapter);


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


                initAdapterWithFilter(newText.toLowerCase(), model);

                return false;
            }
        });

    }

    private void initAdapterWithFilter(String filter, ArrayList<Doctor> ar) {
        // Doctor List

        final ArrayList<Doctor> model = ar;

        ArrayList<Doctor> parsedModel = new ArrayList<>();

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

                Doctor m = model.get(position);

                Toast.makeText(AllDoctors.this, m.getFirstName(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
