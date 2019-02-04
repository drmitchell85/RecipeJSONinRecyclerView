package com.danmitch.android.recipejsoninrecyclerview;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FetchJsonAsyncTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "FetchJsonAsyncTask";

    // vars
    private AsyncResponse delegate = null;
    private String mJsonUrl = "https://api.myjson.com/bins/ossbg";
    private String mJsonData;
    private static String mNullPrefix = "null";
    private static ArrayList<User> mUsersList = new ArrayList<>();

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: started");

        try {
            URL url = new URL(mJsonUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // our connection
            InputStream inputStream = httpURLConnection.getInputStream(); // gets a readable stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); // reads data from steam

            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                mJsonData += line;
            }

            extractFeatureFromJson(mJsonData);


        } catch (MalformedURLException e) {
            Log.d(TAG, "doInBackground: MalformedURLException");
            e.printStackTrace();

        } catch (IOException e) {
            Log.d(TAG, "doInBackground: IOException");
            e.printStackTrace();
        }


        return null;
    }

    private static ArrayList<User> extractFeatureFromJson(String jsonData) {
        // If JSON string is empty, return early
        if (TextUtils.isEmpty(jsonData)) {
            return null;
        }

        if (jsonData.startsWith(mNullPrefix)) {
            jsonData = jsonData.substring(mNullPrefix.length(), jsonData.length());
        }

        // Parse JSON response string
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                String firstName = jsonObject.getString("firstname");
                String lastName = jsonObject.getString("lastname");
                String phoneNumber = jsonObject.getString("phonenumber");
                String country = jsonObject.getString("country");

                User user = new User(firstName, lastName, phoneNumber, country);
                mUsersList.add(user);
            }

        } catch (JSONException e) {
            Log.d(TAG, "extractFeatureFromJson: JSONException");
            e.printStackTrace();
        }

        return mUsersList;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.mTextViewTest.setText(mJsonData);
        //delegate.processFinish(mUsersList);
    }
}
