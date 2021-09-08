package com.example.myapplication;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.Random;

public class Music extends AppCompatActivity {
    static final String MY_MODE = "MY_MODE";
    ImageView ibtnPlay, ibtnStop, ibtnPre, ibtnNext, profile, ibtnShufle, ibtnRepeat;
    static Boolean flag = true, checkPlaying = false, shuffle = false, repeat = false, flagShufle = true, flagRepeat = true;
    DatabaseReference listRef, listDetail, listSong, listArtist;
    ProgressBar progressBar;
    String[] part, partName = {"How You Like That", "Ngắm Hoa Lệ Rơi", "Suy Nghĩ Trong Anh", "Until You", "Why Not Me"},
            partArtist = {"BlackPink", "Châu Khải Phong", "Nam Cường - Khắc Việt", "Shayne Ward", "Enrique Iglesias"};
    int index = 0, dura = 0, posi = 0, prog = 0, secStart = 0, minusStart = 0;
    int[] image = {R.drawable.blackpink, R.drawable.chaukhaiphong, R.drawable.khacviet, R.drawable.shayne, R.drawable.enrique};
    String history = "", timeEnd = "", timeStart = "", secS = "";
    PosiReceiver posiReceiver;
    IndexReceiver indexReceiver;
    Random random;
    SeekBar seekBar;
    TextView songName, songArtist, endSong, startSong;
    Animation animation;
    SharedPreferences mypre;
    SharedPreferences.Editor myedit;
    RotateAnimation rotate;

    MyThread myThread, myThread0;
    ModeThread modeThread;
    final static String MY_PRO = "MY_PRO";
    Intent intent;

    @Override
    protected void onStart() {
        indexReceiver = new IndexReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MediaPlayerService.MY_ACTION);
        registerReceiver(indexReceiver, intentFilter);

        posiReceiver = new PosiReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(MediaPlayerService.MY_PO);
        registerReceiver(posiReceiver, intentFilter2);


        super.onStart();
    }

    @Override
    protected void onStop() {
        //unregisterReceiver(posiReceiver);

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mypre = getSharedPreferences("mysave", MODE_PRIVATE);
        myedit = mypre.edit();
        myedit.putInt("dura", dura);
        myedit.putString("history", history);
        myedit.putString("timeEnd", timeEnd);
        myedit.putString("timeStart", timeStart);
        myedit.putBoolean("repeat", repeat);
        myedit.putBoolean("shuffle", shuffle);
        myedit.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ibtnPlay = findViewById(R.id.ibtnPlay);
        //ibtnStop = findViewById(R.id.ibtnStop);
        ibtnPre = findViewById(R.id.ibtnPre);
        ibtnNext = findViewById(R.id.ibtnNext);
        ibtnRepeat = findViewById(R.id.ibtnRepeat);
        ibtnShufle = findViewById(R.id.ibtnShufle);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        seekBar = findViewById(R.id.seekBar3);
        songName = findViewById(R.id.songName);
        songArtist = findViewById(R.id.songArtist);
        startSong = findViewById(R.id.startSong);
        endSong = findViewById(R.id.endSong);
        profile = findViewById(R.id.profile);


        seekBar.setProgress(0);
        intent = new Intent(Music.this, MediaPlayerService.class);
        animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        songName.setAnimation(animation);

        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(25000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setFillAfter(true);
        rotate.setRepeatCount(Animation.INFINITE);
        profile.startAnimation(rotate);

        mypre = getSharedPreferences("mysave", MODE_PRIVATE);
        history = mypre.getString("history", "");
        dura = mypre.getInt("dura", 0);
        index = mypre.getInt("index", 0);
        timeEnd = mypre.getString("timeEnd", "");
        timeStart = mypre.getString("timeStart", "");
        repeat = mypre.getBoolean("repeat", false);
        shuffle = mypre.getBoolean("shuffle", false);

        if (repeat == true)
            ibtnRepeat.setImageResource(R.drawable.ic_repeat_e);
        else
            ibtnRepeat.setImageResource(R.drawable.ic_repeat);
        if (shuffle == true)
            ibtnShufle.setImageResource(R.drawable.ic_shuffle_e);
        else
            ibtnShufle.setImageResource(R.drawable.ic_shuffle);
        if (MediaPlayerService.isPause == true) {
            seekBar.setMax(dura);
            endSong.setText(timeEnd);
            startSong.setText(timeStart);
            seekBar.setProgress(Integer.parseInt(history));
        }

        checkPlaying = false;
        if (isMyServiceRunning(MediaPlayerService.class) && MediaPlayerService.isPlaying == true) {
            seekBar.setMax(dura);
            seekBar.setProgress(Integer.parseInt(history));
            endSong.setText(timeEnd);
            startSong.setText(timeStart);
            ibtnPlay.setImageResource(R.drawable.ic_pause);
            checkPlaying = true;
            flag = false;
        } else {
            ibtnPlay.setImageResource(R.drawable.ic_play);
            checkPlaying = false;
            flag = true;
        }

        /*listRef = FirebaseDatabase.getInstance("https://song-a1da7-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
        listSong = listRef.child("link");
        listDetail = listRef.child("detail");
        listArtist = listRef.child("artist");

        listDetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    int len = snapshot.getValue().toString().length();
                    partName = snapshot.getValue().toString().substring(1, len - 1).split(",");
                }

                for (int i = 0; i < partName.length; i++) {
                    partName[i] = partName[i].trim().substring(3);
                    //System.out.println(partSong[i]);
                }
                songName.setText(partName[index]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        listArtist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    int len = snapshot.getValue().toString().length();
                    partArtist = snapshot.getValue().toString().substring(1, len - 1).split(",");
                }

                for (int i = 0; i < partArtist.length; i++) {
                    partArtist[i] = partArtist[i].trim().substring(3);
                    //System.out.println(partArtist[i]);
                }
                songArtist.setText(partArtist[index]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        listSong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    int len = snapshot.getValue().toString().length();
                    part = snapshot.getValue().toString().substring(1, len - 1).split(",");
                }

                for (int i = 0; i < part.length; i++) {
                    part[i] = part[i].trim().substring(3);
                    //System.out.println(part[i]);
                }

                intent.putExtra("arr", part);
                intent.putExtra("index", index);
                intent.putExtra("len", part.length - 1);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
*/
        profile.setImageResource(image[index]);
        songArtist.setText(partArtist[index]);
        songName.setText(partName[index]);
        intent.putExtra("index", index);
        intent.putExtra("len", partArtist.length - 1);
        intent.putExtra("shuffle", shuffle);
        intent.putExtra("repeat", repeat);
        progressBar.setVisibility(View.INVISIBLE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    prog = progress;
                    if (myThread0 != null && myThread0.isAlive())
                        myThread0.stop();
                    myThread0 = new MyThread();
                    myThread0.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true) {
                    intent.putExtra("index", index);
                    ibtnPlay.setImageResource(R.drawable.ic_pause);
                    checkPlaying = true;
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser) {
                                prog = progress;
                                if (myThread != null && myThread.isAlive())
                                    myThread.stop();
                                myThread = new MyThread();
                                myThread.start();
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    });
                    startService(intent);
                    flag = false;
                } else {
                    ibtnPlay.setImageResource(R.drawable.ic_play);
                    checkPlaying = false;
                    startService(intent);
                    flag = true;
                }
            }
        });


        /*ibtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("@@" + index);
                seekBar.setProgress(0);
                stopService(intent);
                intent.putExtra("index", index);
                ibtnPlay.setImageResource(R.drawable.play);
                checkPlaying = false;
                flag = true;
            }
        });*/

        ibtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                if (shuffle == true) {
                    random = new Random();
                    index = random.nextInt(intent.getIntExtra("len", 0));
                } else {
                    index++;
                    if (index > intent.getIntExtra("len", 0))
                        index = 0;
                }
                intent.putExtra("index", index);
                startService(intent);
                ibtnPlay.setImageResource(R.drawable.ic_pause);
            }
        });

        ibtnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                if (shuffle == true) {
                    random = new Random();
                    index = random.nextInt(intent.getIntExtra("len", 0));
                } else {
                    index--;
                    if (index < 0)
                        index = intent.getIntExtra("len", 0);
                }
                intent.putExtra("index", index);
                startService(intent);
            }
        });

        ibtnShufle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagShufle == true) {
                    shuffle = true;
                    flagShufle = false;
                    ibtnShufle.setImageResource(R.drawable.ic_shuffle_e);
                } else {
                    shuffle = false;
                    flagShufle = true;
                    ibtnShufle.setImageResource(R.drawable.ic_shuffle);
                }
                intent.putExtra("shuffle", shuffle);
                if (modeThread != null && modeThread.isAlive())
                    modeThread.stop();
                modeThread = new ModeThread();
                modeThread.start();
                myedit = mypre.edit();
                myedit.putBoolean("shuffle", shuffle);
                myedit.commit();
            }
        });

        ibtnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagRepeat == true) {
                    repeat = true;
                    flagRepeat = false;
                    ibtnRepeat.setImageResource(R.drawable.ic_repeat_e);
                } else {
                    repeat = false;
                    flagRepeat = true;
                    ibtnRepeat.setImageResource(R.drawable.ic_repeat);
                }
                intent.putExtra("repeat", repeat);
                if (modeThread != null && modeThread.isAlive())
                    modeThread.stop();
                modeThread = new ModeThread();
                modeThread.start();
                myedit = mypre.edit();
                myedit.putBoolean("repeat", repeat);
                myedit.commit();
            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private class PosiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
           
            posi = arg1.getIntExtra("POSI", 0);
            dura = arg1.getIntExtra("DURA", 0);

            if (posi <= dura) {
                secStart = posi % 60000 / 1000;
                secS = secStart + "";
                if (posi % 60000 / 1000 < 10)
                    secS = "0" + secS;
                minusStart = posi / 60000;
                timeStart = minusStart + ":" + secS;
            } else
                timeStart = minusStart + ":" + secS;

            startSong.setText(timeStart);
            seekBar.setMax(dura);
            seekBar.setProgress(posi);
            if (MediaPlayerService.stop == true)
                seekBar.setProgress(0);
            history = String.valueOf(posi);
        }
    }

    private class IndexReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
           
            mypre = getSharedPreferences("mysave", MODE_PRIVATE);
            index = arg1.getIntExtra("INDEX", mypre.getInt("index", 0));
            dura = arg1.getIntExtra("DURA", mypre.getInt("dura", 0));
            
            timeEnd = dura / 60000 + ":" + dura % 60000 / 1000;
            if (dura % 60000 / 1000 < 10)
                timeEnd = dura / 60000 + ":0" + dura % 60000 / 1000;

            myedit = mypre.edit();
            myedit.putInt("index", index);
            myedit.putString("timeEnd", timeEnd);
            myedit.commit();
            
            endSong.setText(timeEnd);
            songName.setText(partName[index]);
            songArtist.setText(partArtist[index]);
            profile.setImageResource(image[index]);
        }
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(0);
                Intent intent = new Intent();
                intent.setAction(MY_PRO);
                intent.putExtra("PROGRESS", prog);
                sendBroadcast(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class ModeThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(0);
                Intent intent = new Intent();
                intent.setAction(MY_MODE);
                intent.putExtra("SHUFFLE", shuffle);
                intent.putExtra("REPEAT", repeat);
                sendBroadcast(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
