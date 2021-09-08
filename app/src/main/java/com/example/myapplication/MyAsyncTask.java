package com.example.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask {
    Activity contextCha;
    @Override
    protected Object doInBackground(Object[] objects) {
        for(int i=0;i<=100;i++){
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return null;
    }

    public MyAsyncTask(Activity contextCha) {
        this.contextCha = contextCha;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextCha,"Progressing...",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Toast.makeText(contextCha,"Done!",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        ProgressBar paCha = contextCha.findViewById(R.id.progressBar);
        int giatri = (int) values[0];
        paCha.setProgress(giatri);

        TextView txtmsg = contextCha.findViewById(R.id.tvProgress);
        txtmsg.setText(giatri+"%");
    }
}
