package com.trunksys.backulele;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lyhcode on 2016/8/21.
 */
class BluetoothIO implements Runnable {

    static final UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private SoundPlayer player;

    private BluetoothAdapter bluetooth = null;

    private BluetoothDevice device = null;

    private BluetoothSocket socket = null;

    private InputStream in = null;
    private OutputStream out = null;

    private boolean isConnected = false;

    public BluetoothIO() {
        this.player = new SoundPlayer();

        if (bluetooth == null) {
            bluetooth = BluetoothAdapter.getDefaultAdapter();
        }

        if (bluetooth == null) {
            Log.e("bt", "phone has no bluetooth adpater");
            return;
        }

        // force enable bluetooth
        if (!bluetooth.isEnabled()) {
            bluetooth.enable();
        }

        Set<BluetoothDevice> paired = bluetooth.getBondedDevices();
        if (paired.size() > 0) {
            for (BluetoothDevice d : paired) {
                if (d.getName().equals("HC-05")) {
                    device = d;
                    break;
                }
            }
        }

        if (device == null) {
            Log.e("bt", "cannot find arduino device");
            return;
        }

        try {
            socket = device.createInsecureRfcommSocketToServiceRecord(BT_UUID);
            Log.d("bt", "connecting");
            socket.connect();
            in = socket.getInputStream();
            out = socket.getOutputStream();

            isConnected = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void run() {
        while (true) {
            try {
                processIO(in, out);
                //Thread.sleep(5);
            }
            catch (Exception e) {
                Log.e("bt loop", e.getMessage());
            }
        }
    }

    private void processIO(InputStream in, OutputStream out) throws Exception {
        if (in.available() > 0) {

            byte ch;

            StringBuilder bcmd = new StringBuilder();

            while ((ch = (byte) in.read()) != '#') {
                bcmd.append((char) ch);
            }

            String cmd = bcmd.toString().trim();

            Log.d("bt cmd", cmd + " l: " + cmd.length());

            switch (cmd.charAt(0)) {
                case 'S':
                    Thread.sleep(Integer.parseInt(cmd.substring("S".length())));
                    break;
                case 'C':
                    player.setActiveChord(cmd.substring("C".length()));
                    break;
                case 'P':

                    char[] lines = cmd.substring("P".length()).toCharArray();

                    Log.d("bt play", cmd + ", '" + cmd.substring("P".length()) + "'");

                    if (lines[0] == '1' && lines[1] == '1' && lines[2] == '1' && lines[3] == '1') {
                        player.play(4);
                    }
                    else {
                        if (lines[0] == '1') {
                            player.play(0);
                        }
                        if (lines[1] == '1') {
                            player.play(1);
                        }
                        if (lines[2] == '1') {
                            player.play(2);
                        }
                        if (lines[3] == '1') {
                            player.play(3);
                        }
                    }
                    break;
            }

            Log.d("bt", cmd);

            out.write(cmd.getBytes());
            out.write("#OK#\n".getBytes());
        }
    }
}
