package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AsyncTask2 extends AppCompatActivity {
    ListView lv;
    TextView tvDate;
    ArrayList<TyGia> arr;
    HoiDoaiArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task2);
        lv = findViewById(R.id.lvHD);
        tvDate = findViewById(R.id.tvDate);
        getdate();
        arr = new ArrayList<TyGia>();
        adapter = new HoiDoaiArrayAdapter(AsyncTask2.this, R.layout.itemhoidoai,arr);
        lv.setAdapter(adapter);
        TyGiaTask task = new TyGiaTask();
        task.execute();

    }

    private void getdate() {
        Date current = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvDate.setText("Hôm Nay: "+simpleDateFormat.format(current));
    }

    private class TyGiaTask extends AsyncTask<Void, Void, ArrayList<TyGia>> {
        @Override
        protected ArrayList<TyGia> doInBackground(Void... voids) {
            ArrayList<TyGia> ds = new ArrayList<TyGia>();
            try{
                URL url = new URL("https://dongabank.com.vn/exchange/export");
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                connection.setRequestProperty("Accept", "*/*");

                InputStream is= null;
                int responseStatusCode = connection.getResponseCode();
                if( responseStatusCode != HttpURLConnection.HTTP_OK ) {
                    is = connection.getErrorStream();
                } else {
                    is = connection.getInputStream();
                }

                InputStreamReader isr=new InputStreamReader(is,"UTF-8");
                BufferedReader br=new BufferedReader(isr);

                String line=br.readLine();
                StringBuilder builder=new StringBuilder();


                while (line!=null) {
                    builder.append(line); line=br.readLine();
                }
                String json=builder.toString();
                json=json.replace("(", "");
                json=json.replace(")","");
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray= jsonObject.getJSONArray("items");

                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject item=jsonArray.getJSONObject(i);
                    TyGia tiGia=new TyGia();
                    tiGia.setType(item.getString("type"));
                    if(item.has("muatienmat")) { tiGia.setMuatm(item.getString("muatienmat"));
                    }
                    if(item.has("muack")) { tiGia.setMuack(item.getString("muack"));
                    }
                    if(item.has("bantienmat")) { tiGia.setBantm(item.getString("bantienmat"));
                    }
                    if(item.has("banck")) { tiGia.setBanck(item.getString("banck"));
                    }
                    ds.add(tiGia);
                }
                Log.d("JSON_DONGA",json);

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return ds;
        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            adapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<TyGia> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            adapter.clear();
            adapter.addAll(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }
    }
}