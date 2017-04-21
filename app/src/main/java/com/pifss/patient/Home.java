package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ArrayList<Doctor> model;
    Drawer homeDrawer;
    PrimaryDrawerItem myDoctorsItem;
    int myDoctorCount = 0;
    AccountHeader headerResult;
    JSONObject obj;
    int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String shared = getSharedPreferences("PatientData1", MODE_PRIVATE).getString("Patient1"," ");
        Patient profile = new Gson().fromJson(shared,Patient.class);
        String patientName = profile.getFirstName() + " " + profile.getLastName();
        String patientEmail = profile.getEmail();
        JSONObject obj1;
        try {
            obj1 = new JSONObject(shared);
            this.patientId = obj1.getInt("patientId"); //profile.getPatientId();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        toolbar.setTitle(R.string.MyDoctorHome);


        // MaterialDrawer Creation



        myDoctorsItem = new PrimaryDrawerItem().withIdentifier(1).withIcon(R.mipmap.doctor_profile_icon_two).withName(R.string.Home_MyDoctors).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        PrimaryDrawerItem findDoctorsItem = new PrimaryDrawerItem().withIdentifier(2).withIcon(R.mipmap.doctor_profile_icon).withName(R.string.Home_FindDoctors);
        PrimaryDrawerItem hospitalsItem = new PrimaryDrawerItem().withIdentifier(3).withIcon(R.mipmap.hospital).withName(R.string.Home_Hospitals);
        PrimaryDrawerItem reportItem = new PrimaryDrawerItem().withIdentifier(4).withIcon(R.mipmap.medical_report_icon).withName(R.string.Home_MyReports);
        DividerDrawerItem itemDivider = new DividerDrawerItem();
        PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(5).withIcon(R.mipmap.settings_icon).withName(R.string.Home_Sittings);
        PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withIdentifier(6).withIcon(R.mipmap.logout_icon).withName(R.string.Home_Logout);


             headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.navigation_drawer_icon)
                .addProfiles(
                        new ProfileDrawerItem().withName(patientName).withTextColor(Color.BLUE).withEmail(patientEmail)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        startActivity(new Intent(Home.this,ViewPatientProfile.class));
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
                               // intent = new Intent(Home.this, MyDoctors.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(Home.this, AllDoctors.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(Home.this, SearchHospital.class);
                            } else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(Home.this, MyReports.class);
                            } else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(Home.this, Settings.class);
                            }  else if (drawerItem.getIdentifier() == 6) {
                                intent = new Intent(Home.this, Login.class);
                                //String shared = getSharedPreferences("patientData", MODE_PRIVATE).getString("Patient"," ");
                                SharedPreferences pref = getSharedPreferences("PatientData1",MODE_PRIVATE);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            pref.edit()
                                    .clear()
                                    .apply();
                                finish();
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
                //
                Intent intent = new Intent(Home.this, MyDoctorProfile.class);
                intent.putExtra("name", m.getFirstName()+" "+m.getMiddleName()+" "+m.getLastName());
                intent.putExtra("gender", m.getGender());
                intent.putExtra("specialty", m.getSpecialityId());
                intent.putExtra("nationality", m.getNationality());
                intent.putExtra("email", m.getEmail());
                intent.putExtra("cvURL", m.getCvUrl());
                intent.putExtra("imageURL", m.getImageUrl());
                intent.putExtra("drId",m.getDrId());
          //      Toast.makeText(Home.this, m.getFirstName()+"  "+m.getDrId(), Toast.LENGTH_SHORT).show();

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

                Intent intent = new Intent(Home.this, MyDoctorProfile.class);
                intent.putExtra("name", m.getFirstName()+m.getMiddleName()+m.getLastName());
                intent.putExtra("gender", m.getGender());
                intent.putExtra("specialty", m.getSpecialityId());
                intent.putExtra("nationality", m.getNationality());
                intent.putExtra("email", m.getEmail());
                intent.putExtra("cvURL", m.getCvUrl());
                intent.putExtra("imageURL", m.getImageUrl());
                intent.putExtra("drId",m.getDrId());
               // Toast.makeText(Home.this, ""+m.getFirstName()+"  "+m.getDrId(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(MyDoctors.this, m.getDrId()+"", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }


    private void updateModel () {
        try {
        String Pid="";
        String PatientDat = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1"," ");

            JSONObject o = new JSONObject(PatientDat);
            Pid = o.getString("patientId");

        //  Toast.makeText(this, Pid+"", Toast.LENGTH_SHORT).show();
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/accepteddoctor/"+Pid;


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(Home.this);
            final ProgressDialog progressDialog = new ProgressDialog(Home.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
progressDialog.hide();


                   //     Toast.makeText(Home.this, response, Toast.LENGTH_LONG);
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
                        progressDialog.hide();
                        Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_LONG);
                    }
                });

            if (!isNetworkAvailable()){
                Toast.makeText(Home.this, R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
            }

            progressDialog.setMessage("Connecting...");
            progressDialog.show();
        queue.add(jsonReq);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
