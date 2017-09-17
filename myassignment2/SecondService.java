package gr.hua.android.myassignment2;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SecondService extends Service {
    ArrayList<String> myList= new ArrayList<>();

    //call GetAllUsers and save them in our system database
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            myList = new GetAllUsers().execute().get();
            ContentValues values = new ContentValues();
            for(int i=0; i<myList.size()-1; i=i+2) {
                values.put("_USERNAME", myList.get(i));
                values.put("_CURRENT_LOCATION", myList.get(i + 1));
                Uri res = getContentResolver().insert(Uri.parse("content://gr.hua.android.mycontentprovider/_USERS"), values);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
