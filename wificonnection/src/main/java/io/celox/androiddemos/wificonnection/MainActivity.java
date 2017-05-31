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

package io.celox.androiddemos.wificonnection;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // network connection
    private boolean mGuest = false;

    // task handling
    private TextView mTvInfo;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mIsTaskRunning = true;
            mHandler.postDelayed(mRunnable, 14);
            mTvInfo.setText(makeDate());
        }
    };

    private boolean mIsTaskRunning = false;


    private WifiManager wifiManager;
//    private WifiConnectionManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvInfo = (TextView) findViewById(R.id.tv_info);
        mTvInfo.setText(makeDate());


        wifiManager = (WifiManager) getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);

    }


    private void toggleTask() {
        if (mIsTaskRunning) {
            mIsTaskRunning = false;
            mHandler.removeCallbacks(mRunnable);

            return;
        }

        mRunnable.run();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_switch_network:
                connectToSpecificWifi(mGuest = !mGuest);
                break;
            case R.id.btn_toggle_task:
                toggleTask();
                break;
        }
    }

    private static String makeDate() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        Date date = new Date(System.currentTimeMillis());
        return dateFormat.format(date);
    }


    public void connectToSpecificWifi(final boolean guestWifi) {
        Log.i(TAG, "connectToSpecificWifi: guestWifi=" + guestWifi);


        List<WifiConfiguration> wifiConfigurations = wifiManager.getConfiguredNetworks();

        Log.i(TAG, "_ctsw: " + new SimpleDateFormat("HH:mm:ss:SSS", Locale.GERMANY).format(new Date()));

        for (WifiConfiguration wc : wifiConfigurations) {
            if (guestWifi && wc.SSID.equals("\"BucketList Gast\"")) {

                Log.i(TAG, "_ctsw: " + wc.toString());

                wifiManager.enableNetwork(wc.networkId, true);

            } else if (!guestWifi && wc.SSID.equals("\"BucketList TC\"")) {

                Log.i(TAG, "_ctsw: " + wc.toString());

                wifiManager.enableNetwork(wc.networkId, true);
            }
        }
    }

    @Override
    protected void onDestroy() {
//        manager.abort();

        super.onDestroy();
    }


}
