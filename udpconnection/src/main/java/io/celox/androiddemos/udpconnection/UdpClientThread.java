package io.celox.androiddemos.udpconnection;/*
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


import android.os.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static io.celox.androiddemos.udpconnection.MainActivity.UdpClientHandler.UPDATE_END;
import static io.celox.androiddemos.udpconnection.MainActivity.UdpClientHandler.UPDATE_MSG;
import static io.celox.androiddemos.udpconnection.MainActivity.UdpClientHandler.UPDATE_STATE;


/**
 * @author Martin Pfeffer <a href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="https://celox.io">https://celox.io</a>
 */

public class UdpClientThread extends Thread {

    private String mDestinationAddress;
    private int mDestinationPort;
    private boolean mIsRunning;
    private MainActivity.UdpClientHandler mUdpClientHandler;

    private DatagramSocket mDatagramSocket;
    private InetAddress mInetAddress;

    public UdpClientThread(String addr, int port, MainActivity.UdpClientHandler handler) {
        super();
        mDestinationAddress = addr;
        mDestinationPort = port;
        this.mUdpClientHandler = handler;
    }

    public void setRunning(boolean running) {
        this.mIsRunning = running;
    }

    private void sendState(String state) {
        mUdpClientHandler.sendMessage(Message.obtain(mUdpClientHandler, UPDATE_STATE, state));
    }

    @Override
    public void run() {
        sendState("connecting...");

        mIsRunning = true;

        try {
            mDatagramSocket = new DatagramSocket();
            mInetAddress = InetAddress.getByName(mDestinationAddress);

            // send request
            byte[] buf = new byte[256];

            DatagramPacket packet = new DatagramPacket(buf, buf.length, mInetAddress, mDestinationPort);
            mDatagramSocket.send(packet);

            sendState("connected");

            // get response
            packet = new DatagramPacket(buf, buf.length);
            mDatagramSocket.receive(packet);
            String line = new String(packet.getData(), 0, packet.getLength());
            mUdpClientHandler.sendMessage(Message.obtain(mUdpClientHandler, UPDATE_MSG, line));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mDatagramSocket != null) {
                mDatagramSocket.close();
                mUdpClientHandler.sendEmptyMessage(UPDATE_END);
            }
        }
    }

}