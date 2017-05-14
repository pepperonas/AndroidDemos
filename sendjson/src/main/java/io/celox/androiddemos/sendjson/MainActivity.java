/*
 * Copyright (c) 2017 Martin Pfeffer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.celox.androiddemos.sendjson;

import android.app.Activity;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    public static final String IP = "192.168.178.86";

    private enum PhpFunction {
        WRITE_JSON, CREATE_DB, INSERT_JSON, PRINT_PHP_INFO
    }


    private String getAction(PhpFunction which) {
        switch (which) {
            case WRITE_JSON:
                return "writeJsonInFile";
            case CREATE_DB:
                return "createDatabase";
            case INSERT_JSON:
                return "insertJson";
            case PRINT_PHP_INFO:
                return "printPhpInfo";
            default:
                return "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvIsConnected = (TextView) findViewById(R.id.tv_connected);
        tvIsConnected.setText("Not connected...");

        if (isConnected()) {
            tvIsConnected.setBackgroundColor(Color.GREEN);
            tvIsConnected.setText("Connected...");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_write_json:
                new HttpAsyncTask().execute(
                        "http://" + IP + "/sendjson/server.php?f=" + getAction(PhpFunction.WRITE_JSON));
                break;

            case R.id.btn_create_database:
                new HttpAsyncTask().execute(
                        "http://" + IP + "/sendjson/server.php?f=" + getAction(PhpFunction.CREATE_DB));
                break;

            case R.id.btn_insert_json:
                new HttpAsyncTask().execute(
                        "http://" + IP + "/sendjson/server.php?f=" + getAction(PhpFunction.INSERT_JSON));
                break;

            case R.id.btn_print_php_info:
                new HttpAsyncTask().execute(
                        "http://" + IP + "/sendjson/server.php?f=" + getAction(PhpFunction.PRINT_PHP_INFO));
                break;
        }
    }


    public String postJson(String _url) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(_url);

        // original data, contains some overhead
        LinkedHashMap<String, String> mapContent = getDummies();

        // map
        LinkedHashMap<String, String> mapMapping = getDummies();

        int ctr = 0;
        for (Map.Entry<String, String> entry : mapContent.entrySet()) {
            String keyFull = entry.getKey();
            String keyShortened = entry.getKey().substring(0, 1) + String.valueOf(++ctr);
            mapMapping.put(keyFull, keyShortened);
        }

        // map object to send - aka 'mapping'
        JSONObject jsonObjectMapping = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : mapMapping.entrySet()) {
                jsonObjectMapping.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray jsonArrayDataContainer = new JSONArray();
        int size = 10;
        for (int numbered = 0; numbered < size; numbered++) {
            // shortened key and value - aka 'data'
            JSONObject jsonObjectContent = new JSONObject();
            try {
                for (Map.Entry<String, String> entry : mapContent.entrySet()) {
                    String shortKey = mapMapping.get(entry.getKey());
                    String data = entry.getValue() + numbered;
                    jsonObjectContent.put(shortKey, data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArrayDataContainer.put(jsonObjectContent);
        }

        // map object to send - aka 'header'
        JSONObject headerInfo = new JSONObject();
        try {
            headerInfo.put("size", size);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // container holds 'header', 'mapping' and 'data'
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(headerInfo);
        jsonArray.put(jsonObjectMapping);
        jsonArray.put(jsonArrayDataContainer);


        // show the whole message
        Log.i(TAG, "postJson: " + jsonArray.toString());

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("json", jsonArray.toString()));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            Log.i(TAG, "postJson: " + response.getStatusLine());
        } catch (IOException e) {
            e.printStackTrace();
            return "FAILED";
        }

        return "PASSED";
    }

    @NonNull
    private LinkedHashMap<String, String> getDummies() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("some_variable_name_with_many_characters_1", "Alphaaa");
        map.put("some_variable_name_with_many_characters_2", "Betaaaa");
        map.put("some_variable_name_with_many_characters_3", "");
        return map;
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            return postJson(urls[0]);
        }


        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }


    private boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        inputStream.close();
        return result;
    }


}
