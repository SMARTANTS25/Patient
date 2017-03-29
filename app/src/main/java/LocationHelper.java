import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by HelgenHills on 3/29/17.
 */

public class LocationHelper {

    Activity activity;


    private Location currentLocation ;

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
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},0);
            }


            return ;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
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


    public float distanceBetweenUserAndHospital(double latHospital, double longHospital) {

        Location loc1 = new Location("");
        loc1.setLatitude(latHospital);
        loc1.setLongitude(longHospital);

        return currentLocation.distanceTo(loc1);
    }

    public Location getCurrentLocation() { return currentLocation; }


}
