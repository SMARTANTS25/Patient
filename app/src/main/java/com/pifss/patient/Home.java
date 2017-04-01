package com.pifss.patient;

import android.content.Intent;
import android.graphics.Color;
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
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.pifss.patient.Adapters.DoctorAdapter;
import com.pifss.patient.utils.Doctor;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ArrayList<Doctor> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        toolbar.setTitle("Home");
        //
        //setSupportActionBar(toolbar);

        // MaterialDrawer Creation

        int myDoctorsAmount = 1;

        PrimaryDrawerItem myDoctorsItem = new PrimaryDrawerItem().withIdentifier(1).withIcon(R.mipmap.ic_launcher_round).withName("My Doctors").withBadge(String.valueOf(myDoctorsAmount)).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        PrimaryDrawerItem findDoctorsItem = new PrimaryDrawerItem().withIdentifier(2).withIcon(R.mipmap.ic_launcher_round).withName("Find Doctors");
        PrimaryDrawerItem hospitalsItem = new PrimaryDrawerItem().withIdentifier(3).withIcon(R.mipmap.ic_launcher_round).withName("Hospitals");
        PrimaryDrawerItem reportItem = new PrimaryDrawerItem().withIdentifier(4).withIcon(R.mipmap.ic_launcher_round).withName("My Reports");
        DividerDrawerItem itemDivider = new DividerDrawerItem();
        PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(5).withIcon(R.mipmap.ic_launcher_round).withName("Settings");
        PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withIdentifier(6).withIcon(R.mipmap.ic_launcher_round).withName("Logout");


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.navigation_drawer_icon)
                .addProfiles(
                        new ProfileDrawerItem().withName("PatientName").withTextColor(Color.BLUE).withEmail("mikepenz@gmail.com")
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })

                .build();

        // Drawer creation + navigation (listener)

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(myDoctorsItem, findDoctorsItem, hospitalsItem, reportItem, itemDivider, settingsItem, logoutItem)
                .withAccountHeader(headerResult)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(Home.this, MyDoctors.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(Home.this, AllDoctors.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(Home.this, SearchHospital.class);
                            } else if (drawerItem.getIdentifier() == 4) {
                              //  intent = new Intent(Home.this, SendReport.class);
                            } else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(Home.this, Settings.class);
                            }


                            if (intent != null) {
                                startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .build();


        /*modify an item of the drawer
        myDoctorsItem.withName("My Doctors").withBadge("4").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        //notify the drawer about the updated element. it will take care about everything else
        result.updateItem(myDoctorsItem);*/

        // Doctor List

        updateModel();


        ListView lv = (ListView) findViewById(R.id.HomeDoctorList);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = model.get(position);

                Toast.makeText(Home.this, m.getFirstName(), Toast.LENGTH_SHORT).show();

            }
        });

        // Search Bar

        final android.widget.SearchView sv = (android.widget.SearchView) findViewById(R.id.homeSearchView);


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


        ListView lv = (ListView) findViewById(R.id.HomeDoctorList);

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


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(Home.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Home.this, response, Toast.LENGTH_LONG);
                        model = new Gson().fromJson(response, new TypeToken<ArrayList<Doctor>>(){}.getType());

                        ListView lv = (ListView) findViewById(R.id.HomeDoctorList);

                        DoctorAdapter adapter = new DoctorAdapter(model, Home.this);

                        lv.setAdapter(adapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_LONG);
                    }
                });

        queue.add(jsonReq);
    }

}
