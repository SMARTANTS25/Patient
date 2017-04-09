package com.pifss.patient;

import android.content.Intent;
import android.content.SharedPreferences;
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
    Drawer homeDrawer;
    PrimaryDrawerItem myDoctorsItem;
    int myDoctorCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        toolbar.setTitle("Home");
        //
        //setSupportActionBar(toolbar);

        // Get Patient data

        SharedPreferences sharedpreferences = getSharedPreferences("patient", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        // put info (shared preferences
        String username = sharedpreferences.getString("username", "ERROR");
        String fullName = sharedpreferences.getString("firstName", "ERROR") + sharedpreferences.getString("lastName", "ERROR");

        // MaterialDrawer Creation

        int myDoctorsAmount = 0;

        myDoctorsItem = new PrimaryDrawerItem().withIdentifier(1).withIcon(R.mipmap.doctor_profile_icon_two).withName("My Doctors").withBadge(String.valueOf(myDoctorsAmount)).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        PrimaryDrawerItem findDoctorsItem = new PrimaryDrawerItem().withIdentifier(2).withIcon(R.mipmap.doctor_profile_icon).withName("Find Doctors");
        PrimaryDrawerItem hospitalsItem = new PrimaryDrawerItem().withIdentifier(3).withIcon(R.mipmap.hospital).withName("Hospitals");
        PrimaryDrawerItem reportItem = new PrimaryDrawerItem().withIdentifier(4).withIcon(R.mipmap.medical_report_icon).withName("My Reports");
        DividerDrawerItem itemDivider = new DividerDrawerItem();
        PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(5).withIcon(R.mipmap.settings_icon).withName("Settings");
        PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withIdentifier(6).withIcon(R.mipmap.logout_icon).withName("Logout");


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.navigation_drawer_icon)
                .addProfiles(
                        new ProfileDrawerItem().withName(fullName).withTextColor(Color.BLUE).withEmail("mikepenz@gmail.com")
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })

                .build();

        // Drawer creation + navigation (listener)

        homeDrawer = new DrawerBuilder()
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
        updateMyDoctorsBadge();


        ListView lv = (ListView) findViewById(R.id.HomeDoctorList);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor m = model.get(position);

                Intent intent = new Intent(Home.this, NewDoctorProfile.class);
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

        final ArrayList<Doctor> parsedModel = new ArrayList<>();

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

                Doctor m = parsedModel.get(position);

                Intent intent = new Intent(Home.this, NewDoctorProfile.class);
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

    private void updateMyDoctorsBadge () {
        String patientId = "2";
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/accepteddoctor/"+patientId;

        myDoctorCount = 0;

        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(Home.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        model = new Gson().fromJson(response, new TypeToken<ArrayList<Doctor>>(){}.getType());

                        myDoctorsItem.withName("My Doctors").withBadge(String.valueOf(model.size()) ).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
                        //notify the drawer about the updated element. it will take care about everything else
                        homeDrawer.updateItem(myDoctorsItem);

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
