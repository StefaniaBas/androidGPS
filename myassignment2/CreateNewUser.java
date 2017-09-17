package gr.hua.android.myassignment2;


import android.os.AsyncTask;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

class CreateNewUser extends AsyncTask<String, String, String> {
    //Save user in database
    JSONParser jsonParser = new JSONParser();
    private static String url_create_product ="http://10.0.2.2/android_connect/create_user.php";

    protected String doInBackground(String... args) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", args[1]));
        params.add(new BasicNameValuePair("current_location", args[0]));
        JSONObject json = jsonParser.makeHttpRequest(url_create_product, "POST", params);

        return null;
    }

}

