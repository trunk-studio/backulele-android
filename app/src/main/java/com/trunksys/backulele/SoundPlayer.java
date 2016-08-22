package com.trunksys.backulele;

import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by lyhcode on 2016/8/21.
 */
class SoundPlayer {

    private HashMap<String, String> chords2file;
    private HashMap<String, String[]> chords2single;

    private String activeChord = "C6";

    {
        chords2file = new HashMap<>();

        chords2file.put("C", "Major/Downstroke/C");
        chords2file.put("G", "Major/Downstroke/G");
        chords2file.put("Am", "Minor/Downstroke/A");
        chords2file.put("Em", "Minor/Downstroke/E");
        chords2file.put("F", "Major/Downstroke/F");

        chords2single = new HashMap<>();

        chords2single.put("C6", new String[] {"Single/G4", "Single/C4", "Single/E4", "Single/A4"});
        chords2single.put("C", new String[]  {"Single/G4", "Single/C4", "Single/E4", "Single/C4"});
        chords2single.put("Dm", new String[] {"Single/A4", "Single/D4", "Single/F4", "Single/A4"});
        chords2single.put("Em", new String[] {"Single/G4", "Single/E4", "Single/G4", "Single/B4"});
        chords2single.put("F", new String[]  {"Single/A4", "Single/C4", "Single/F4", "Single/A4"});
        chords2single.put("G", new String[]  {"Single/G4", "Single/D4", "Single/G4", "Single/B4"});
        chords2single.put("Am", new String[] {"Single/A4", "Single/C4", "Single/E4", "Single/A4"});
        chords2single.put("G7", new String[] {"Single/G4", "Single/D4", "Single/F4", "Single/B4"});
    }

    private static final String prefix = "/storage/sdcard0/ukulele/";

    private static final String postfix = ".mp3";

    private SoundPool soundPool;

    private HashMap<String, Integer> soundMap;

    public SoundPlayer() {

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                //complete
            }
        });

        soundMap = new HashMap<>();

        for (String file : chords2file.values()) {
            if (!soundMap.containsKey(file)) {
                soundMap.put(file, soundPool.load(getStorageFilePath(file), 1));
            }
        }

        for (String[] singles : chords2single.values()) {
            for (String single : singles) {
                if (!soundMap.containsKey(single)) {
                    soundMap.put(single, soundPool.load(getStorageFilePath(single), 1));
                }
            }
        }
    }

    private String getStorageFilePath(String chord) {
        String filePath = prefix;
        filePath += chord;
        filePath += postfix;
        return filePath;
    }

    public void play(final String file) {

        Log.d("sound player", file);

        if (soundMap.get(file) != null) {
            //Log.d("player", "t1 = " + System.currentTimeMillis());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundMap.get(file), 1.0f, 1.0f, 1, 0, 1.0f);
                }
            }).start();
            //Log.d("player", "t2 = " + System.currentTimeMillis());
        }
    }

    public void play(int line) {

        Log.d("sound player", "L: "+ line);

        if (line == 4) {
            String file = chords2file.get(activeChord);
            if (file != null) {
                play(file);
            }
            else {
                // simulate chord with composite singles
                String[] singles = chords2single.get(activeChord);
                for (String single : singles) {
                    play(single);
                }
            }
        }
        else {
            String[] singles = chords2single.get(activeChord);
            if (singles != null) {
                play(singles[line]);
            }
        }
    }

    public String getActiveChord() {
        return activeChord;
    }

    public void setActiveChord(String chord) {
        this.activeChord = chord;
    }
}
