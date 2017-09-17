package gr.hua.android.myassignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartGPSHandler extends BroadcastReceiver {
    //call GPSHandler
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent();
        i.setClass(context,GPSHandler.class);
        context.startService(i);
    }
}
