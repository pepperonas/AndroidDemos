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

package io.celox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Martin Pfeffer
 *         <a href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="https://celox.io">https://celox.io</a>
 */

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting server...");

        new UdpServerThread().start();
    }

    private static class UdpServerThread extends Thread {

        final int mServerPort = 4445;

        DatagramSocket mDatagramSocket = null;

        public UdpServerThread() throws IOException {
            this("UdpServerThread");
        }

        public UdpServerThread(String name) throws IOException {
            super(name);
            mDatagramSocket = new DatagramSocket(mServerPort);
            System.out.println("JavaUdpServer run on: " + mServerPort);
        }

        @Override
        public void run() {

            while (true) {
                try {
                    byte[] buf = new byte[256];

                    // receive request
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    mDatagramSocket.receive(packet);

                    String anInteger = String.valueOf(getAnInteger());
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    String strDate = formatter.format(date);

                    String msg = strDate + " (RAND=" + anInteger + ")";
                    buf = msg.getBytes();

                    // send the response to the client at "address" and "port"
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    System.out.println("Request from: " + address + ":" + port);
                    packet = new DatagramPacket(buf, buf.length, address, port);
                    mDatagramSocket.send(packet);
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private static int getAnInteger() {
        return ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }

}