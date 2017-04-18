package com.pifss.patient.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HelgenHills on 3/29/17.
 */


public class LocationHelper {

    private static int BLOOD_CAMPAIGN_NOTIFICATION_ID = 003;

    Activity activity;



    private Location currentLocation ;

    double lang;
    double alt;
    public LocationHelper(Activity activity) {
        this.activity = activity;
        initLocation();
    }

    public void initLocation() {

        // ----------------  GPS START

        //#1 get Location Manager

        final LocationManager locationManager = (LocationManager) activity.getSystemService(activity.LOCATION_SERVICE);


        //#2 Listen for all location changes

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},0);
            }


            return ;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location)
            {

                System.out.print(location);


                Toast.makeText(activity, location.getAltitude()+"AAA"+location.getLatitude(), Toast.LENGTH_SHORT).show();
                currentLocation = location;
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


        //throw new UnsupportedOperationException("Not yet implemented");
        // ----------------  GPS END

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




    public float distanceBetweenUserAndHospital(double latHospital, double longHospital) {

        if (currentLocation == null) {
            return -1;
        }


        Location loc1 = new Location("hospital");
        loc1.setLatitude(latHospital);
        loc1.setLongitude(longHospital);

        return currentLocation.distanceTo(loc1);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void sendNotificationBloodCampaigns() {
        /*
        int notificationId = BLOOD_CAMPAIGN_NOTIFICATION_ID;
        // Build intent for notification content
        Intent viewIntent = new Intent(this, ViewEventActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_event)
                        .setContentTitle(eventTitle)
                        .setContentText(eventLocation)
                        .setContentIntent(viewPendingIntent);
        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Issue the notification with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
        */

    }

    public ArrayList<Location> getNearbyBloodCampaigns() {

        return null;
    }

    //


}
