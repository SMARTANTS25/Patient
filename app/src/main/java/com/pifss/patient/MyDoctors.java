package com.pifss.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pifss.patient.Adapters.DoctorAdapter;
import com.pifss.patient.utils.Doctor;

import java.util.ArrayList;

public class MyDoctors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.AllDoctorToolbar);
        toolbar.setTitle("My Doctors");
        setSupportActionBar(toolbar);

        // Doctor List

        final ArrayList<Doctor> model = new ArrayList<>();

        //
        Doctor d = new Doctor();

        d.setFirstName("Ahmad");
        d.setMiddleName("Ahmad");
        d.setLastName("Ahmad");
        d.setImageUrl("http://images.pocketgamer.co.uk/artwork/na-wrds/mario-2.png");
        d.setSpecialityId("eye doctor");
        d.setGender("f");

        model.add(d);


        ListView lv = (ListView) findViewById(R.id.AllDoctorList);

        DoctorAdapter adapter = new DoctorAdapter(model, this);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = model.get(position);

                Toast.makeText(MyDoctors.this, m.getFirstName(), Toast.LENGTH_SHORT).show();

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


                initAdapterWithFilter(newText.toLowerCase(), model);

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


        ListView lv = (ListView) findViewById(R.id.AllDoctorList);

        DoctorAdapter adapter = new DoctorAdapter(parsedModel, this);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = model.get(position);

                Toast.makeText(MyDoctors.this, m.getFirstName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
