package com.trunksys.backulele;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button c6, c6_1, c6_2, c6_3, c6_4;
    private Button f, f_1, f_2, f_3, f_4;
    private Button am, am_1, am_2, am_3, am_4;
    private Button em, em_1, em_2, em_3, em_4;
    private Button c, c_1, c_2, c_3, c_4;
    private Button g, g_1, g_2, g_3, g_4;

    private SoundPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        player = new SoundPlayer(getBaseContext());

        new Thread(new BluetoothIO(player)).start();


        ((Button) findViewById(R.id.play_MA_DOWN_A)).setOnClickListener(generateListener("Major/Downstroke/A"));
        ((Button) findViewById(R.id.play_MA_UP_A)).setOnClickListener(generateListener("Major/Upstroke/A"));
        ((Button) findViewById(R.id.play_MN_DOWN_A)).setOnClickListener(generateListener("Minor/Downstroke/A"));
        ((Button) findViewById(R.id.play_MN_UP_A)).setOnClickListener(generateListener("Minor/Upstroke/A"));

        ((Button) findViewById(R.id.play_MA_DOWN_B)).setOnClickListener(generateListener("Major/Downstroke/B"));
        ((Button) findViewById(R.id.play_MA_UP_B)).setOnClickListener(generateListener("Major/Upstroke/B"));
        ((Button) findViewById(R.id.play_MN_DOWN_B)).setOnClickListener(generateListener("Minor/Downstroke/B"));
        ((Button) findViewById(R.id.play_MN_UP_B)).setOnClickListener(generateListener("Minor/Upstroke/B"));

        ((Button) findViewById(R.id.play_MA_DOWN_C)).setOnClickListener(generateListener("Major/Downstroke/C"));
        ((Button) findViewById(R.id.play_MA_UP_C)).setOnClickListener(generateListener("Major/Upstroke/C"));
        ((Button) findViewById(R.id.play_MN_DOWN_C)).setOnClickListener(generateListener("Minor/Downstroke/C"));
        ((Button) findViewById(R.id.play_MN_UP_C)).setOnClickListener(generateListener("Minor/Upstroke/C"));

        ((Button) findViewById(R.id.play_MA_DOWN_CS)).setOnClickListener(generateListener("Major/Downstroke/CS"));
        ((Button) findViewById(R.id.play_MA_UP_CS)).setOnClickListener(generateListener("Major/Upstroke/CS"));
        ((Button) findViewById(R.id.play_MN_DOWN_CS)).setOnClickListener(generateListener("Minor/Downstroke/CS"));
        ((Button) findViewById(R.id.play_MN_UP_CS)).setOnClickListener(generateListener("Minor/Upstroke/CS"));

        ((Button) findViewById(R.id.play_MA_DOWN_D)).setOnClickListener(generateListener("Major/Downstroke/D"));
        ((Button) findViewById(R.id.play_MA_UP_D)).setOnClickListener(generateListener("Major/Upstroke/D"));
        ((Button) findViewById(R.id.play_MN_DOWN_D)).setOnClickListener(generateListener("Minor/Downstroke/D"));
        ((Button) findViewById(R.id.play_MN_UP_D)).setOnClickListener(generateListener("Minor/Upstroke/D"));

        ((Button) findViewById(R.id.play_MA_DOWN_E)).setOnClickListener(generateListener("Major/Downstroke/E"));
        ((Button) findViewById(R.id.play_MA_UP_E)).setOnClickListener(generateListener("Major/Upstroke/E"));
        ((Button) findViewById(R.id.play_MN_DOWN_E)).setOnClickListener(generateListener("Minor/Downstroke/E"));
        ((Button) findViewById(R.id.play_MN_UP_E)).setOnClickListener(generateListener("Minor/Upstroke/E"));

        ((Button) findViewById(R.id.play_MA_DOWN_F)).setOnClickListener(generateListener("Major/Downstroke/F"));
        ((Button) findViewById(R.id.play_MA_UP_F)).setOnClickListener(generateListener("Major/Upstroke/F"));
        ((Button) findViewById(R.id.play_MN_DOWN_F)).setOnClickListener(generateListener("Minor/Downstroke/F"));
        ((Button) findViewById(R.id.play_MN_UP_F)).setOnClickListener(generateListener("Minor/Upstroke/F"));

        ((Button) findViewById(R.id.play_MA_DOWN_FS)).setOnClickListener(generateListener("Major/Downstroke/FS"));
        ((Button) findViewById(R.id.play_MA_UP_FS)).setOnClickListener(generateListener("Major/Upstroke/FS"));
        ((Button) findViewById(R.id.play_MN_DOWN_FS)).setOnClickListener(generateListener("Minor/Downstroke/FS"));
        ((Button) findViewById(R.id.play_MN_UP_FS)).setOnClickListener(generateListener("Minor/Upstroke/FS"));

        ((Button) findViewById(R.id.play_MA_DOWN_G)).setOnClickListener(generateListener("Major/Downstroke/G"));
        ((Button) findViewById(R.id.play_MA_UP_G)).setOnClickListener(generateListener("Major/Upstroke/G"));
        ((Button) findViewById(R.id.play_MN_DOWN_G)).setOnClickListener(generateListener("Minor/Downstroke/G"));
        ((Button) findViewById(R.id.play_MN_UP_G)).setOnClickListener(generateListener("Minor/Upstroke/G"));

        ((Button) findViewById(R.id.play_MA_DOWN_GS)).setOnClickListener(generateListener("Major/Downstroke/GS"));
        ((Button) findViewById(R.id.play_MA_UP_GS)).setOnClickListener(generateListener("Major/Upstroke/GS"));
        ((Button) findViewById(R.id.play_MN_DOWN_GS)).setOnClickListener(generateListener("Minor/Downstroke/GS"));
        ((Button) findViewById(R.id.play_MN_UP_GS)).setOnClickListener(generateListener("Minor/Upstroke/GS"));

        c6 = (Button) findViewById(R.id.play_C6);
        c6.setOnClickListener(generateListener(new String[] {"Legacy/C6_4", "Legacy/C6_3", "Legacy/C6_2", "Legacy/C6_1"}));
        c6_1 = (Button) findViewById(R.id.play_C6_1);
        c6_1.setOnClickListener(generateListener("Legacy/C6_1"));
        c6_2 = (Button) findViewById(R.id.play_C6_2);
        c6_2.setOnClickListener(generateListener("Legacy/C6_2"));
        c6_3 = (Button) findViewById(R.id.play_C6_3);
        c6_3.setOnClickListener(generateListener("Legacy/C6_3"));
        c6_4 = (Button) findViewById(R.id.play_C6_4);
        c6_4.setOnClickListener(generateListener("Legacy/C6_4"));

        f = (Button) findViewById(R.id.play_F);
        f.setOnClickListener(generateListener(new String[] {"Legacy/F_4", "Legacy/F_3", "Legacy/F_2", "Legacy/F_1"}));
        f_1 = (Button) findViewById(R.id.play_F_1);
        f_1.setOnClickListener(generateListener("Legacy/F_1"));
        f_2 = (Button) findViewById(R.id.play_F_2);
        f_2.setOnClickListener(generateListener("Legacy/F_2"));
        f_3 = (Button) findViewById(R.id.play_F_3);
        f_3.setOnClickListener(generateListener("Legacy/F_3"));
        f_4 = (Button) findViewById(R.id.play_F_4);
        f_4.setOnClickListener(generateListener("Legacy/F_4"));

        am = (Button) findViewById(R.id.play_Am);
        am.setOnClickListener(generateListener(new String[] {"Legacy/Am_4", "Legacy/Am_3", "Legacy/Am_2", "Legacy/Am_1"}));
        am_1 = (Button) findViewById(R.id.play_Am_1);
        am_1.setOnClickListener(generateListener("Legacy/Am_1"));
        am_2 = (Button) findViewById(R.id.play_Am_2);
        am_2.setOnClickListener(generateListener("Legacy/Am_2"));
        am_3 = (Button) findViewById(R.id.play_Am_3);
        am_3.setOnClickListener(generateListener("Legacy/Am_3"));
        am_4 = (Button) findViewById(R.id.play_Am_4);
        am_4.setOnClickListener(generateListener("Legacy/Am_4"));

        em = (Button) findViewById(R.id.play_Em);
        em.setOnClickListener(generateListener(new String[] {"Legacy/Em_4", "Legacy/Em_3", "Legacy/Em_2", "Legacy/Em_1"}));
        em_1 = (Button) findViewById(R.id.play_Em_1);
        em_1.setOnClickListener(generateListener("Legacy/Em_1"));
        em_2 = (Button) findViewById(R.id.play_Em_2);
        em_2.setOnClickListener(generateListener("Legacy/Em_2"));
        em_3 = (Button) findViewById(R.id.play_Em_3);
        em_3.setOnClickListener(generateListener("Legacy/Em_3"));
        em_4 = (Button) findViewById(R.id.play_Em_4);
        em_4.setOnClickListener(generateListener("Legacy/Em_4"));

        c = (Button) findViewById(R.id.play_C);
        c.setOnClickListener(generateListener(new String[] {"Legacy/C_4", "Legacy/C_3", "Legacy/C_2", "Legacy/C_1"}));
        c_1 = (Button) findViewById(R.id.play_C_1);
        c_1.setOnClickListener(generateListener("Legacy/C_1"));
        c_2 = (Button) findViewById(R.id.play_C_2);
        c_2.setOnClickListener(generateListener("Legacy/C_2"));
        c_3 = (Button) findViewById(R.id.play_C_3);
        c_3.setOnClickListener(generateListener("Legacy/C_3"));
        c_4 = (Button) findViewById(R.id.play_C_4);
        c_4.setOnClickListener(generateListener("Legacy/C_4"));

        g = (Button) findViewById(R.id.play_G);
        g.setOnClickListener(generateListener(new String[] {"Legacy/G_4", "Legacy/G_3", "Legacy/G_2", "Legacy/G_1"}));
        g_1 = (Button) findViewById(R.id.play_G_1);
        g_1.setOnClickListener(generateListener("Legacy/G_1"));
        g_2 = (Button) findViewById(R.id.play_G_2);
        g_2.setOnClickListener(generateListener("Legacy/G_2"));
        g_3 = (Button) findViewById(R.id.play_G_3);
        g_3.setOnClickListener(generateListener("Legacy/G_3"));
        g_4 = (Button) findViewById(R.id.play_G_4);
        g_4.setOnClickListener(generateListener("Legacy/G_4"));
    }

    private View.OnClickListener generateListener(final String chord) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.play(chord);
            }
        };
    }

    private View.OnClickListener generateListener(final String[] chords) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(String chord : chords) {
                    player.play(chord);
                }
            }
        };
    }
}

class SoundPlayer {
    //private MediaPlayer mp = new MediaPlayer();

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

    private static final String postfix = ".mp3";

    private Context context;

    public SoundPlayer(Context context) {
        this.context = context;
    }

    public void play(String chord) {
        String filePath = prefix;
        filePath += chord;
        filePath += postfix;

        MediaPlayer mp = MediaPlayer.create(context, Uri.parse(filePath));

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
}

class BluetoothIO implements Runnable {

    static final UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private SoundPlayer player;

    private BluetoothAdapter bluetooth = null;

    private OutputStream out = null;

    private InputStream in = null;

    BluetoothSocket socket = null;

    private long t1 = System.currentTimeMillis();
    private long t2 = System.currentTimeMillis();
    private long t3 = System.currentTimeMillis();
    private long t4 = System.currentTimeMillis();

    public BluetoothIO(SoundPlayer player) {

        this.player = player;

        bluetooth = BluetoothAdapter.getDefaultAdapter();

        // Force enable bluetooth
        if (bluetooth != null && !bluetooth.isEnabled()) {
            bluetooth.enable();
        }
    }

    @Override
    public void run() {

        if (bluetooth == null) {
            return;
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

        while (true) {
            try {

                if (socket == null) {
                    socket = device.createInsecureRfcommSocketToServiceRecord(BT_UUID);
                }

                if (!socket.isConnected()) {
                    socket.connect();
                    out = socket.getOutputStream();
                    in = socket.getInputStream();
                    out.write("CONNECTED.\n".getBytes());
                }

                processIO();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processIO() throws Exception {
        if (in.available() > 0) {
            long now = System.currentTimeMillis();
            boolean t1a = (now - t1) >= 200;
            boolean t2a = (now - t2) >= 200;
            boolean t3a = (now - t3) >= 200;
            boolean t4a = (now - t4) >= 200;

            byte ch;

            StringBuilder bcmd = new StringBuilder();

            while ((ch = (byte) in.read()) != '#') {
                bcmd.append((char) ch);
            }

            final String cmd = bcmd.toString();

            if (cmd.startsWith("PLAY_")) {
                String chord = cmd.substring("PLAY_".length());
                player.play(chord);
                //out.write(("PLAYING "+chord+"\n").getBytes());
            } else if (cmd.startsWith("EVENT_")) {
                char[] matrix = cmd.substring("EVENT_".length()).toCharArray();

                String chord;

                switch (matrix[0]) {
                    case '0':
                        chord = "Legacy/C6";
                        break;
                    case '1':
                        chord = "Legacy/F";
                        break;
                    case '2':
                        chord = "Legacy/Am";
                        break;
                    case '3':
                        chord = "Legacy/Em";
                        break;
                    case '4':
                        chord = "Legacy/C";
                        break;
                    case '5':
                        chord = "Legacy/G";
                        break;
                    default:
                        chord = "Legacy/C6";
                }

                if (matrix[4] == '1' && t4a) {
                    player.play(chord + "_4");
                    t4 = System.currentTimeMillis();
                }

                if (matrix[3] == '1' && t3a) {
                    player.play(chord + "_3");
                    t3 = System.currentTimeMillis();
                }

                if (matrix[2] == '1' && t2a) {
                    player.play(chord + "_2");
                    t2 = System.currentTimeMillis();
                }

                if (matrix[1] == '1' && t1a) {
                    player.play(chord + "_1");
                    t1 = System.currentTimeMillis();
                }
            }

            //out.write(cmd.getBytes());
            //out.write('\n');
            out.write("OK.\n".getBytes());

            Thread.sleep(5);
        }
    }
}

