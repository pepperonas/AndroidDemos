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

package com.pepperonas.showcase.udpconnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEtAddress;
    private EditText mEtPort;
    private Button mBtnConnect;
    private TextView mTvState;
    private TextView mTvRx;

    UdpClientHandler mUdpClientHandler;
    UdpClientThread mUdpClientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtAddress = (EditText) findViewById(R.id.et_address);
        mEtPort = (EditText) findViewById(R.id.et_port);
        mBtnConnect = (Button) findViewById(R.id.btn_connect);
        mTvState = (TextView) findViewById(R.id.tv_state);
        mTvRx = (TextView) findViewById(R.id.tv_received);

        mEtAddress.setText("10.0.2.2");
        mBtnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUdpClientThread = new UdpClientThread(
                        mEtAddress.getText().toString(),
                        Integer.parseInt(mEtPort.getText().toString()),
                        mUdpClientHandler);
                mUdpClientThread.start();

                mBtnConnect.setEnabled(false);
            }
        });

        mUdpClientHandler = new UdpClientHandler(this);
    }

    private void updateState(String state) {
        mTvState.setText(state);
    }

    private void updateRxMsg(String rxmsg) {
        mTvRx.append(rxmsg + "\n");
    }

    private void clientEnd() {
        mUdpClientThread = null;
        mTvState.setText(R.string.done);
        mBtnConnect.setEnabled(true);
    }

    static class UdpClientHandler extends Handler {
        public static final int UPDATE_STATE = 0;
        public static final int UPDATE_MSG = 1;
        public static final int UPDATE_END = 2;
        public MainActivity mMainActivity;

        public UdpClientHandler(MainActivity mainActivity) {
            super();
            this.mMainActivity = mainActivity;
        }

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case UPDATE_STATE:
                    mMainActivity.updateState((String) msg.obj);
                    break;
                case UPDATE_MSG:
                    mMainActivity.updateRxMsg((String) msg.obj);
                    break;
                case UPDATE_END:
                    mMainActivity.clientEnd();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

}