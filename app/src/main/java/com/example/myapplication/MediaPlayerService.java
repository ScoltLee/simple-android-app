package com.example.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.Random;

public class MediaPlayerService extends Service {
    MediaPlayer myMediaPlayer = new MediaPlayer();
    public static Boolean isPlaying = false;
    public static Boolean stop = false;
    public static Boolean isPause = false, shuffle = false, repeat = false;
    String[] userID;
    int index = 0;
    int len = 0, dura = 0;
    int list[] = {R.raw.howyoulikethat, R.raw.ngamhoaleroi, R.raw.suynghitronganh, R.raw.untilyou, R.raw.whynotme};
    final static String MY_ACTION = "MY_ACTION";
    final static String MY_PO = "MY_PO";
    MyThread myThread;
    MyThreadPo myThreadPo;
    int progress;
    MyReceiver mReceiver;
    ModeReceiver modeReceiver;
    Random random;

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            progress = arg1.getIntExtra("PROGRESS", 0);
            myMediaPlayer.seekTo(progress);
        }
    }

    public class ModeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            shuffle = arg1.getBooleanExtra("SHUFFLE", false);
            repeat = arg1.getBooleanExtra("REPEAT", false);
        }
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Music.MY_PRO);
        mReceiver = new MyReceiver();
        registerReceiver(mReceiver, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(Music.MY_MODE);
        modeReceiver = new ModeReceiver();
        registerReceiver(modeReceiver, intentFilter1);

        stop = false;
        isPause = false;
        isPlaying = false;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        unregisterReceiver(modeReceiver);
        myMediaPlayer.stop();
        stop = true;
        isPause = false;
        isPlaying = false;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if (myMediaPlayer.isPlaying()) {
            myMediaPlayer.pause();
            isPause = true;
            isPlaying = false;
        } else {
            myMediaPlayer.start();
            isPlaying = true;
            isPause = false;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //userID = intent.getStringArrayExtra("arr");
        index = intent.getIntExtra("index", 0);
        len = intent.getIntExtra("len", 0);
        shuffle = intent.getBooleanExtra("shuffle",false);
        repeat = intent.getBooleanExtra("repeat",false);
        if (myMediaPlayer.isPlaying()) {
            if (Music.checkPlaying == false) {
                myMediaPlayer.pause();
                isPlaying = false;
                isPause = true;
            }
            myThreadPo = new MyThreadPo();
            myThreadPo.start();
        } else {
            if (isPause == true) {
                myMediaPlayer.start();
                isPlaying = true;
                isPause = false;
                if (myThreadPo != null && myThreadPo.isAlive())
                    myThreadPo.stop();
                myThreadPo = new MyThreadPo();
                myThreadPo.start();
            } else {
                playSong();
            }
        }
        return START_STICKY;
    }

    void nextSong() {
        if (repeat == true) {
        } else if (shuffle == true) {
            random = new Random();
            index = random.nextInt(len);
        } else {
            index++;
            if (index > len)
                index = 0;
        }
        playSong();
    }

    void playSong() {
        // try {
        myMediaPlayer.reset();
        myMediaPlayer.release();
        //myMediaPlayer = new MediaPlayer();
        //myMediaPlayer.setDataSource(userID[index]);
        myMediaPlayer = MediaPlayer.create(this, list[index]);
        myMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //myMediaPlayer.prepare();
        dura = myMediaPlayer.getDuration() - 1000;
        myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextSong();
            }
        });

        isPlaying = true;
        isPause = false;
        myMediaPlayer.start();

        if (myThread != null && myThread.isAlive())
            myThread.stop();
        myThread = new MyThread();
        myThread.start();

        if (myThreadPo != null && myThreadPo.isAlive())
            myThreadPo.stop();
        myThreadPo = new MyThreadPo();
        myThreadPo.start();
        /*}catch (Exception e){
            e.printStackTrace();
        }*/
    }

    public class MyThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(0);
                Intent intent = new Intent();
                intent.setAction(MY_ACTION);
                intent.putExtra("INDEX", index);
                intent.putExtra("DURA", dura);
                sendBroadcast(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyThreadPo extends Thread {

        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setAction(MY_PO);
            while (myMediaPlayer.isPlaying()) {
                try {
                    Thread.sleep(0);
                    intent.putExtra("POSI", myMediaPlayer.getCurrentPosition());
                    intent.putExtra("DURA", dura);
                    sendBroadcast(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
