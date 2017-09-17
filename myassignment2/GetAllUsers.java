package gr.hua.android.myassignment2;

import android.os.AsyncTask;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GetAllUsers extends AsyncTask<Void,Void,ArrayList> {
    JSONParser jParser = new JSONParser();
    private static String url_all_users ="http://10.0.2.2/android_connect/get_all_users.php";
    private static final String TAG_USERS = "users";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_CURRENT_LOCATION = "current_location";
    // users JSONArray
    JSONArray users = null;
    ArrayList<String> usersList=new ArrayList<>();

    //get all users from web-server
    @Override
    protected ArrayList doInBackground(Void... params) {
        // Building Parameters
        List<NameValuePair> params2 = new ArrayList<NameValuePair>();
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_all_users, "GET", params2);
        try {
            users = json.getJSONArray(TAG_USERS);
            try {
                // looping through All Users
                for (int i = 0; i < users.length(); i++) {
                    JSONObject c = users.getJSONObject(i);
                    // Storing each json item in variable
                    String name = c.getString(TAG_USERNAME);
                    String location = c.getString(TAG_CURRENT_LOCATION);
                    // adding String to ArrayList
                    usersList.add(name);
                    usersList.add(location);
                }
            }catch (NullPointerException e){
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usersList;
    }

}
