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

package io.celox.androiddemos.async;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyThread((TextView) findViewById(R.id.textView)).start();

    }


    class MyThread extends Thread implements Runnable {

        TextView textView;

        MyThread(TextView textView) {
            this.textView = textView;
//            run();
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnMainUiThread(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        textView.setText("" + System.currentTimeMillis());
                        return null;
                    }
                });

            }
        }

        void runOnMainUiThread(final Callable callable) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    try {
                        callable.call();
                    } catch (Exception e) {
                        Log.e("MyThread", "runOnMainUiThread: ", e);
                    }
                }
            });
        }
    }
}
