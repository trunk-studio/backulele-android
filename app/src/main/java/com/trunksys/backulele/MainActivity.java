package com.trunksys.backulele;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.net.Uri;
import android.view.MotionEvent;
import android.widget.Button;
import android.view.View;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button c6, c6_1, c6_2, c6_3, c6_4;
    private Button f, f_1, f_2, f_3, f_4;
    private Button am, am_1, am_2, am_3, am_4;
    private Button em, em_1, em_2, em_3, em_4;
    private Button c, c_1, c_2, c_3, c_4;
    private Button g, g_1, g_2, g_3, g_4;

    //private MediaPlayer mp = new MediaPlayer();

    private BluetoothAdapter bluetooth;

    static final UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

/*
    private static final String prefix = "/storage/sdcard0/ukulele/RR3-RR3-major-downstroke-rr3-";

    private static final String[] chords = {
            "1A7J", "1O8J", "2V25", "3D5O", "4CYA", "4T53", "4VP9", "621Q", "6FAG", "6LEG",
            "6TPH", "7UYM", "8AAF", "8CMM", "8JLM", "AKZI", "B50H", "BMYK", "CFVE", "CTQQ",
            "D59O", "D6GM", "DSIW", "EJOB", "EQ6K", "EZTP", "F5Y8", "HWZX", "IAQK", "J5RH",
            "JRCT", "KCLL", "KHOJ", "LKXQ", "MDUK", "NXUQ", "ORSA", "PCM3", "QDT6", "QPE1",
            "QUU6", "QV8A", "VWBS", "WN03", "XMZG", "Y4LA", "ZQ7W", "ZTSZ", "ZUN5", "ZUV2"
    };

    private static final String postfix = ".wav";
*/

    private static final String prefix = "/storage/sdcard0/ukulele/";

    private static final String[] chords = {
            "C6_1", "C6_2", "C6_3", "C6_4",
            "F_1", "F_2", "F_3", "F_4",
            "Am_1", "Am_2", "Am_3", "Am_4",
            "Em_1", "Em_2", "Em_3", "Em_4",
            "C_1", "C_2", "C_3", "C_4",
            "G_1", "G_2", "G_3", "G_4"
    };

    private static final String postfix = ".mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetooth = BluetoothAdapter.getDefaultAdapter();

        if (!bluetooth.isEnabled()) {
            bluetooth.enable();
        }

        Set<BluetoothDevice> paired = bluetooth.getBondedDevices();

        BluetoothDevice device = null;

        if (paired.size() > 0) {
            for (BluetoothDevice d : paired) {
                if (d.getName().equals("HC-05")) {
                    device = d;
                    break;
                }
            }
        }

        try {
            BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(BT_UUID);

            socket.connect();

            final OutputStream out = socket.getOutputStream();
            final InputStream in = socket.getInputStream();

            out.write("CONNECTED.\n".getBytes());

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true){
                        try {

                            byte ch;
                            int i = 0;

                            StringBuilder bcmd = new StringBuilder();

                            while((ch=(byte)in.read()) != '#'){
                                bcmd.append((char)ch);
                            }

                            final String cmd = bcmd.toString();

                            if (cmd.startsWith("PLAY_")) {
                                String chord = cmd.substring("PLAY_".length());
                                playSound(chord);
                                //out.write(("PLAYING "+chord+"\n").getBytes());
                            }
                            else if (cmd.startsWith("EVENT_")) {
                                char[] matrix = cmd.substring("EVENT_".length()).toCharArray();

                                if (matrix[4]=='1') {
                                    playSound("C6_4");
                                }

                                if (matrix[3]=='1') {
                                    playSound("C6_3");
                                }

                                if (matrix[2]=='1') {
                                    playSound("C6_2");
                                }

                                if (matrix[1]=='1') {
                                    playSound("C6_1");
                                }
                            }

                            //out.write(cmd.getBytes());
                            //out.write('\n');
                            out.write("OK.\n".getBytes());

                            Thread.sleep(100);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        c6 = (Button) findViewById(R.id.play_C6);
        c6.setOnClickListener(generateListener(new String[] {"C6_4", "C6_3", "C6_2", "C6_1"}));
        c6_1 = (Button) findViewById(R.id.play_C6_1);
        c6_1.setOnClickListener(generateListener("C6_1"));
        c6_2 = (Button) findViewById(R.id.play_C6_2);
        c6_2.setOnClickListener(generateListener("C6_2"));
        c6_3 = (Button) findViewById(R.id.play_C6_3);
        c6_3.setOnClickListener(generateListener("C6_3"));
        c6_4 = (Button) findViewById(R.id.play_C6_4);
        c6_4.setOnClickListener(generateListener("C6_4"));

        f = (Button) findViewById(R.id.play_F);
        f.setOnClickListener(generateListener(new String[] {"F_4", "F_3", "F_2", "F_1"}));
        f_1 = (Button) findViewById(R.id.play_F_1);
        f_1.setOnClickListener(generateListener("F_1"));
        f_2 = (Button) findViewById(R.id.play_F_2);
        f_2.setOnClickListener(generateListener("F_2"));
        f_3 = (Button) findViewById(R.id.play_F_3);
        f_3.setOnClickListener(generateListener("F_3"));
        f_4 = (Button) findViewById(R.id.play_F_4);
        f_4.setOnClickListener(generateListener("F_4"));

        am = (Button) findViewById(R.id.play_Am);
        am.setOnClickListener(generateListener(new String[] {"Am_4", "Am_3", "Am_2", "Am_1"}));
        am_1 = (Button) findViewById(R.id.play_Am_1);
        am_1.setOnClickListener(generateListener("Am_1"));
        am_2 = (Button) findViewById(R.id.play_Am_2);
        am_2.setOnClickListener(generateListener("Am_2"));
        am_3 = (Button) findViewById(R.id.play_Am_3);
        am_3.setOnClickListener(generateListener("Am_3"));
        am_4 = (Button) findViewById(R.id.play_Am_4);
        am_4.setOnClickListener(generateListener("Am_4"));

        em = (Button) findViewById(R.id.play_Em);
        em.setOnClickListener(generateListener(new String[] {"Em_4", "Em_3", "Em_2", "Em_1"}));
        em_1 = (Button) findViewById(R.id.play_Em_1);
        em_1.setOnClickListener(generateListener("Em_1"));
        em_2 = (Button) findViewById(R.id.play_Em_2);
        em_2.setOnClickListener(generateListener("Em_2"));
        em_3 = (Button) findViewById(R.id.play_Em_3);
        em_3.setOnClickListener(generateListener("Em_3"));
        em_4 = (Button) findViewById(R.id.play_Em_4);
        em_4.setOnClickListener(generateListener("Em_4"));

        c = (Button) findViewById(R.id.play_C);
        c.setOnClickListener(generateListener(new String[] {"C_4", "C_3", "C_2", "C_1"}));
        c_1 = (Button) findViewById(R.id.play_C_1);
        c_1.setOnClickListener(generateListener("C_1"));
        c_2 = (Button) findViewById(R.id.play_C_2);
        c_2.setOnClickListener(generateListener("C_2"));
        c_3 = (Button) findViewById(R.id.play_C_3);
        c_3.setOnClickListener(generateListener("C_3"));
        c_4 = (Button) findViewById(R.id.play_C_4);
        c_4.setOnClickListener(generateListener("C_4"));

        g = (Button) findViewById(R.id.play_G);
        g.setOnClickListener(generateListener(new String[] {"G_4", "G_3", "G_2", "G_1"}));
        g_1 = (Button) findViewById(R.id.play_G_1);
        g_1.setOnClickListener(generateListener("G_1"));
        g_2 = (Button) findViewById(R.id.play_G_2);
        g_2.setOnClickListener(generateListener("G_2"));
        g_3 = (Button) findViewById(R.id.play_G_3);
        g_3.setOnClickListener(generateListener("G_3"));
        g_4 = (Button) findViewById(R.id.play_G_4);
        g_4.setOnClickListener(generateListener("G_4"));
    }

    private void playSound(String chord) {
        String filePath = prefix;
        filePath += chord;
        filePath += postfix;

        MediaPlayer mp = MediaPlayer.create(getBaseContext(), Uri.parse(filePath));

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

        try {
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener generateListener(final String chord) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filePath = prefix;

                filePath += chord;

                filePath += postfix;

                MediaPlayer mp = MediaPlayer.create(getBaseContext(), Uri.parse(filePath));

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });

                try {
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };
    }

    private View.OnClickListener generateListener(final String[] chords) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(String chord : chords) {

                    String filePath = prefix;

                    filePath += chord;

                    filePath += postfix;

                    MediaPlayer mp = MediaPlayer.create(getBaseContext(), Uri.parse(filePath));

                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });

                    try {
                        mp.start();
                        Thread.sleep(20);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };
    }
}

