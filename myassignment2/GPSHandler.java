package gr.hua.android.myassignment2;

import android.app.Service;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class GPSHandler extends Service{
    //counters to control if gps is enabled or disabled
    int GPSEnabled=0,GPSDisabled=0;

    //if gps is disabled start SecondService,
    // else if gps is enabled start ScheduledReceiver
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!(lm.isProviderEnabled(LocationManager.GPS_PROVIDER))) {
            GPSEnabled=0;
            if(GPSDisabled==0) {
                GPSDisabled++;
                Intent in = new Intent();
                in.setClassName("gr.hua.android.myassignment2", "gr.hua.android.myassignment2.SecondService");
                startService(in);
            }
            Intent i = new Intent(getApplicationContext(),FirstService.class);
            stopService(i);
            //Toast.makeText(GPSHandler.this, "Please activate the GPS!!", Toast.LENGTH_SHORT).show();
        } else {
            GPSDisabled=0;
            if(GPSEnabled==0) {
                GPSEnabled++;
               // Toast.makeText(GPSHandler.this, "GPS is enabled!!", Toast.LENGTH_SHORT).show();
                Intent in = new Intent();
                in.setClassName("gr.hua.android.myassignment2", "gr.hua.android.myassignment2.ScheduledReceiver");
                sendBroadcast(in);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
