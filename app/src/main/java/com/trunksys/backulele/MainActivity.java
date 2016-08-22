package com.trunksys.backulele;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector;

    /*
     * Sample https://yoopu.me/view/o%3A0PznlMP8
     */

//    private Button c6, c6_1, c6_2, c6_3, c6_4;
//    private Button f, f_1, f_2, f_3, f_4;
//    private Button am, am_1, am_2, am_3, am_4;
//    private Button em, em_1, em_2, em_3, em_4;
//    private Button c, c_1, c_2, c_3, c_4;
//    private Button g, g_1, g_2, g_3, g_4;

    private SoundPlayer player;

    private int screenWidth;
    private int screenHeight;
    private int screenAreaWidth;

    private HashMap<String, Button> chords2button;

    {
        chords2button = new HashMap<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;
        screenHeight = displaymetrics.heightPixels;
        screenAreaWidth = screenWidth / 4;


        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());


        player = new SoundPlayer();

        BluetoothIO btio = new BluetoothIO();

        if (btio.isConnected()) {
            new Thread(btio).start();
        }

        chords2button.put("C", (Button) findViewById(R.id.play_C));
        ((Button) findViewById(R.id.play_C)).setOnClickListener(chordClickListener("C"));

        chords2button.put("G", (Button) findViewById(R.id.play_G));
        ((Button) findViewById(R.id.play_G)).setOnClickListener(chordClickListener("G"));

        chords2button.put("Am", (Button) findViewById(R.id.play_Am));
        ((Button) findViewById(R.id.play_Am)).setOnClickListener(chordClickListener("Am"));

        chords2button.put("Em", (Button) findViewById(R.id.play_Em));
        ((Button) findViewById(R.id.play_Em)).setOnClickListener(chordClickListener("Em"));

        chords2button.put("F", (Button) findViewById(R.id.play_F));
        ((Button) findViewById(R.id.play_F)).setOnClickListener(chordClickListener("F"));


        ((Button) findViewById(R.id.play_L1)).setOnClickListener(lineClickListener(0));
        ((Button) findViewById(R.id.play_L2)).setOnClickListener(lineClickListener(1));
        ((Button) findViewById(R.id.play_L3)).setOnClickListener(lineClickListener(2));
        ((Button) findViewById(R.id.play_L4)).setOnClickListener(lineClickListener(3));
        ((Button) findViewById(R.id.play_LA)).setOnClickListener(lineClickListener(4));

//        ((Button) findViewById(R.id.play_MA_DOWN_A)).setOnClickListener(generateListener("Major/Downstroke/A"));
//        ((Button) findViewById(R.id.play_MA_UP_A)).setOnClickListener(generateListener("Major/Upstroke/A"));
//        ((Button) findViewById(R.id.play_MN_DOWN_A)).setOnClickListener(generateListener("Minor/Downstroke/A"));
//        ((Button) findViewById(R.id.play_MN_UP_A)).setOnClickListener(generateListener("Minor/Upstroke/A"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_B)).setOnClickListener(generateListener("Major/Downstroke/B"));
//        ((Button) findViewById(R.id.play_MA_UP_B)).setOnClickListener(generateListener("Major/Upstroke/B"));
//        ((Button) findViewById(R.id.play_MN_DOWN_B)).setOnClickListener(generateListener("Minor/Downstroke/B"));
//        ((Button) findViewById(R.id.play_MN_UP_B)).setOnClickListener(generateListener("Minor/Upstroke/B"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_C)).setOnClickListener(generateListener("Major/Downstroke/C"));
//        ((Button) findViewById(R.id.play_MA_UP_C)).setOnClickListener(generateListener("Major/Upstroke/C"));
//        ((Button) findViewById(R.id.play_MN_DOWN_C)).setOnClickListener(generateListener("Minor/Downstroke/C"));
//        ((Button) findViewById(R.id.play_MN_UP_C)).setOnClickListener(generateListener("Minor/Upstroke/C"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_CS)).setOnClickListener(generateListener("Major/Downstroke/CS"));
//        ((Button) findViewById(R.id.play_MA_UP_CS)).setOnClickListener(generateListener("Major/Upstroke/CS"));
//        ((Button) findViewById(R.id.play_MN_DOWN_CS)).setOnClickListener(generateListener("Minor/Downstroke/CS"));
//        ((Button) findViewById(R.id.play_MN_UP_CS)).setOnClickListener(generateListener("Minor/Upstroke/CS"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_D)).setOnClickListener(generateListener("Major/Downstroke/D"));
//        ((Button) findViewById(R.id.play_MA_UP_D)).setOnClickListener(generateListener("Major/Upstroke/D"));
//        ((Button) findViewById(R.id.play_MN_DOWN_D)).setOnClickListener(generateListener("Minor/Downstroke/D"));
//        ((Button) findViewById(R.id.play_MN_UP_D)).setOnClickListener(generateListener("Minor/Upstroke/D"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_E)).setOnClickListener(generateListener("Major/Downstroke/E"));
//        ((Button) findViewById(R.id.play_MA_UP_E)).setOnClickListener(generateListener("Major/Upstroke/E"));
//        ((Button) findViewById(R.id.play_MN_DOWN_E)).setOnClickListener(generateListener("Minor/Downstroke/E"));
//        ((Button) findViewById(R.id.play_MN_UP_E)).setOnClickListener(generateListener("Minor/Upstroke/E"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_F)).setOnClickListener(generateListener("Major/Downstroke/F"));
//        ((Button) findViewById(R.id.play_MA_UP_F)).setOnClickListener(generateListener("Major/Upstroke/F"));
//        ((Button) findViewById(R.id.play_MN_DOWN_F)).setOnClickListener(generateListener("Minor/Downstroke/F"));
//        ((Button) findViewById(R.id.play_MN_UP_F)).setOnClickListener(generateListener("Minor/Upstroke/F"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_FS)).setOnClickListener(generateListener("Major/Downstroke/FS"));
//        ((Button) findViewById(R.id.play_MA_UP_FS)).setOnClickListener(generateListener("Major/Upstroke/FS"));
//        ((Button) findViewById(R.id.play_MN_DOWN_FS)).setOnClickListener(generateListener("Minor/Downstroke/FS"));
//        ((Button) findViewById(R.id.play_MN_UP_FS)).setOnClickListener(generateListener("Minor/Upstroke/FS"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_G)).setOnClickListener(generateListener("Major/Downstroke/G"));
//        ((Button) findViewById(R.id.play_MA_UP_G)).setOnClickListener(generateListener("Major/Upstroke/G"));
//        ((Button) findViewById(R.id.play_MN_DOWN_G)).setOnClickListener(generateListener("Minor/Downstroke/G"));
//        ((Button) findViewById(R.id.play_MN_UP_G)).setOnClickListener(generateListener("Minor/Upstroke/G"));
//
//        ((Button) findViewById(R.id.play_MA_DOWN_GS)).setOnClickListener(generateListener("Major/Downstroke/GS"));
//        ((Button) findViewById(R.id.play_MA_UP_GS)).setOnClickListener(generateListener("Major/Upstroke/GS"));
//        ((Button) findViewById(R.id.play_MN_DOWN_GS)).setOnClickListener(generateListener("Minor/Downstroke/GS"));
//        ((Button) findViewById(R.id.play_MN_UP_GS)).setOnClickListener(generateListener("Minor/Upstroke/GS"));

//        c6 = (Button) findViewById(R.id.play_C6);
//        c6.setOnClickListener(generateListener(new String[] {"Legacy/C6_4", "Legacy/C6_3", "Legacy/C6_2", "Legacy/C6_1"}));
//        c6_1 = (Button) findViewById(R.id.play_C6_1);
//        c6_1.setOnClickListener(generateListener("Legacy/C6_1"));
//        c6_2 = (Button) findViewById(R.id.play_C6_2);
//        c6_2.setOnClickListener(generateListener("Legacy/C6_2"));
//        c6_3 = (Button) findViewById(R.id.play_C6_3);
//        c6_3.setOnClickListener(generateListener("Legacy/C6_3"));
//        c6_4 = (Button) findViewById(R.id.play_C6_4);
//        c6_4.setOnClickListener(generateListener("Legacy/C6_4"));
//
//        f = (Button) findViewById(R.id.play_F);
//        f.setOnClickListener(generateListener(new String[] {"Legacy/F_4", "Legacy/F_3", "Legacy/F_2", "Legacy/F_1"}));
//        f_1 = (Button) findViewById(R.id.play_F_1);
//        f_1.setOnClickListener(generateListener("Legacy/F_1"));
//        f_2 = (Button) findViewById(R.id.play_F_2);
//        f_2.setOnClickListener(generateListener("Legacy/F_2"));
//        f_3 = (Button) findViewById(R.id.play_F_3);
//        f_3.setOnClickListener(generateListener("Legacy/F_3"));
//        f_4 = (Button) findViewById(R.id.play_F_4);
//        f_4.setOnClickListener(generateListener("Legacy/F_4"));
//
//        am = (Button) findViewById(R.id.play_Am);
//        am.setOnClickListener(generateListener(new String[] {"Legacy/Am_4", "Legacy/Am_3", "Legacy/Am_2", "Legacy/Am_1"}));
//        am_1 = (Button) findViewById(R.id.play_Am_1);
//        am_1.setOnClickListener(generateListener("Legacy/Am_1"));
//        am_2 = (Button) findViewById(R.id.play_Am_2);
//        am_2.setOnClickListener(generateListener("Legacy/Am_2"));
//        am_3 = (Button) findViewById(R.id.play_Am_3);
//        am_3.setOnClickListener(generateListener("Legacy/Am_3"));
//        am_4 = (Button) findViewById(R.id.play_Am_4);
//        am_4.setOnClickListener(generateListener("Legacy/Am_4"));
//
//        em = (Button) findViewById(R.id.play_Em);
//        em.setOnClickListener(generateListener(new String[] {"Legacy/Em_4", "Legacy/Em_3", "Legacy/Em_2", "Legacy/Em_1"}));
//        em_1 = (Button) findViewById(R.id.play_Em_1);
//        em_1.setOnClickListener(generateListener("Legacy/Em_1"));
//        em_2 = (Button) findViewById(R.id.play_Em_2);
//        em_2.setOnClickListener(generateListener("Legacy/Em_2"));
//        em_3 = (Button) findViewById(R.id.play_Em_3);
//        em_3.setOnClickListener(generateListener("Legacy/Em_3"));
//        em_4 = (Button) findViewById(R.id.play_Em_4);
//        em_4.setOnClickListener(generateListener("Legacy/Em_4"));
//
//        c = (Button) findViewById(R.id.play_C);
//        c.setOnClickListener(generateListener(new String[] {"Legacy/C_4", "Legacy/C_3", "Legacy/C_2", "Legacy/C_1"}));
//        c_1 = (Button) findViewById(R.id.play_C_1);
//        c_1.setOnClickListener(generateListener("Legacy/C_1"));
//        c_2 = (Button) findViewById(R.id.play_C_2);
//        c_2.setOnClickListener(generateListener("Legacy/C_2"));
//        c_3 = (Button) findViewById(R.id.play_C_3);
//        c_3.setOnClickListener(generateListener("Legacy/C_3"));
//        c_4 = (Button) findViewById(R.id.play_C_4);
//        c_4.setOnClickListener(generateListener("Legacy/C_4"));
//
//        g = (Button) findViewById(R.id.play_G);
//        g.setOnClickListener(generateListener(new String[] {"Legacy/G_4", "Legacy/G_3", "Legacy/G_2", "Legacy/G_1"}));
//        g_1 = (Button) findViewById(R.id.play_G_1);
//        g_1.setOnClickListener(generateListener("Legacy/G_1"));
//        g_2 = (Button) findViewById(R.id.play_G_2);
//        g_2.setOnClickListener(generateListener("Legacy/G_2"));
//        g_3 = (Button) findViewById(R.id.play_G_3);
//        g_3.setOnClickListener(generateListener("Legacy/G_3"));
//        g_4 = (Button) findViewById(R.id.play_G_4);
//        g_4.setOnClickListener(generateListener("Legacy/G_4"));
    }

    private void resetAllButtonStatus() {
        for (Button btn : chords2button.values()) {
            btn.setTextColor(Color.BLACK);
            btn.setAlpha(0.5f);
        }
    }

    private void makeChordActive(String chord) {
        resetAllButtonStatus();
        Button btn = chords2button.get(chord);
        btn.setAlpha(1f);
        btn.setTextColor(Color.RED);
        player.setActiveChord(chord);
    }

    private View.OnClickListener chordClickListener(final String chord) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeChordActive(chord);
                //player.play(chords2file.get(chord));
            }
        };
    }

    private View.OnClickListener lineClickListener(final int line) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.play(line);
            }
        };
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

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {

            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            int s = (int)Math.floor(event1.getX() / screenAreaWidth);
            int e = (int)Math.floor(event2.getX() / screenAreaWidth);

            if (s == 0 && e == 3) {
                player.play(4);
            }
            else {
                for (int i = s; i <= e; i++) {
                    if (i < 4) {
                        player.play(i);
                    }
                }
            }

            return true;
        }
    }
}

