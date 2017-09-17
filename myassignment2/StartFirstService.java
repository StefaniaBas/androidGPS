package gr.hua.android.myassignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartFirstService extends BroadcastReceiver {
    //call FirstService
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent();
        i.setClass(context, FirstService.class);
        context.startService(i);
    }
}