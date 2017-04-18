package com.pifss.patient.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.pifss.patient.R;
import com.pifss.patient.utils.LocationHelper;

import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by SMARTANS on 3/27/2017.
 */

public class HospitalAdapter extends BaseAdapter {

    double deviceLant;
    double deviceAlt;
    private final LayoutInflater inflater;
    ArrayList<com.pifss.patient.utils.Hospital> model;
    Activity context;
    public HospitalAdapter(ArrayList<com.pifss.patient.utils.Hospital> model, Activity context) {


        this.model = model;
        this.context = context;


        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = inflater.inflate(R.layout.listhospitalview,null);


        //ImageView imgHospitsl= (ImageView) view.findViewById(R.id.imageViewHospital);
        TextView tvHospitalTitle = (TextView) view.findViewById(R.id.ListHospitalView_HospitalTitleTV);
        TextView tvHospitalDistance = (TextView) view.findViewById(R.id.ListHospitalView_DistanceTV);
        TextView tvHospitalType = (TextView) view.findViewById(R.id.ListHospitalView_HospitalTypeTV);

        com.pifss.patient.utils.Hospital pos = model.get(position);


        //imgHospitsl.setImageResource(pos.getLogoUrl());

        LocationHelper helper = new LocationHelper(context);

        float latitude = Float.valueOf(pos.getHospitalAlt());
        float longitude = Float.valueOf(pos.getHospitalLang());

        float distance = helper.distanceBetweenUserAndHospital(latitude, longitude);


        final LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);



        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},0);
            }



        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                deviceAlt = location.getAltitude();
                deviceLant = location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

       double alt = Double.parseDouble(pos.getHospitalAlt());
        double lang = Double.parseDouble(pos.getHospitalLang());
       double dis =  distance(deviceLant,deviceAlt,alt,lang , "km");
        Toast.makeText(context, dis+"", Toast.LENGTH_SHORT).show();

        tvHospitalTitle.setText(pos.getHospitalName());
//        if (distance >= 0) {
            tvHospitalDistance.setText(dis+" km");
//        } else {
//            tvHospitalDistance.setText("N/A");
 //       }
        tvHospitalType.setText(pos.getType());


        return view;
    }


/*::  Official Web site: http://www.geodatasource.com                        :*/
/*::                                                                         :*/
/*::           GeoDataSource.com (C) All Rights Reserved 2015                :*/

    static double distance(double lat1, double lon1, double lat2, double lon2, String unit)

    {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "K")
        {
            dist = dist * 1.609344;
        }
        else if (unit == "N")
        {
            dist = dist * 0.8684;
        }

        return (dist);
    }
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians			 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees			 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
