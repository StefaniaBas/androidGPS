package gr.hua.android.myassignment2;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class FirstService extends Service {
    //current longitude and latitude
    final double[] longitude = {0};
    final double[] latitude = {0};
    //previous longitude and latitude
    final double[] lastLongitude = {0};
    final double[] lastLatitude = {0};

    //get current_location and call CreateNewUser
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        LocationManager mLocationMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        if((lastLatitude[0]!=latitude[0] || lastLongitude[0]!=longitude[0])) {
            lastLatitude[0]=latitude[0];
            lastLongitude[0] =longitude[0];
            new CreateNewUser().execute(latitude[0] + "-" + longitude[0],id);
            Toast.makeText(getApplicationContext(), "This is my location: "+latitude[0]+ " "+longitude[0], Toast.LENGTH_SHORT).show();
        }
        mLocationMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            public void onLocationChanged(Location location) {
                longitude[0] =location.getLatitude();
                latitude[0] =location.getLongitude();
            }
            public void onProviderDisabled(String provider) {
            // required for interface, not used
            }
            public void onProviderEnabled(String provider) {
            // required for interface, not used
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
            // required for interface, not used
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

