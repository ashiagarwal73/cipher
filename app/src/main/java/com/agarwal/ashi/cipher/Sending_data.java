package com.agarwal.ashi.cipher;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

class Sending_data extends AsyncTask<String,TextView,String> {
    Context context;
    //TextView mText;
    String inserted;
    ProgressBar progressBar;
    Sending_data(Context context,/*TextView Text,*/String inserted, ProgressBar progressBar)
    {
        this.context=context;
        //mText=Text;
        this.inserted=inserted;
        this.progressBar=progressBar;
    }
    @Override
    protected String doInBackground(String...  strings) {
        String final_url=strings[0];
        try {
            URL preurl = new URL(final_url);
            HttpURLConnection con = (HttpURLConnection)preurl.openConnection();
            con.setRequestMethod("GET");
            int response = con.getResponseCode();
            if (response==200)
            {
                inserted="true";
                System.out.println("Data Loaded Successfully");
            }
            inserted="true";
        }catch (Exception ex)
        {
            Log.i("Exceptiion", ex.toString());
            //mText.setText("Unsuccesful Submission");
            //mText.setTextColor(0xFFEA0F0F);
           // Toast.makeText(context, "Unsuccesful Submission Due to internet problem", Toast.LENGTH_SHORT).show();
            inserted="false";
        }
        return inserted;
    }
    @Override
    protected void onPostExecute(String s) {
        //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        if(s.equals("true"))
        {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(context, "Data Submitted Successfully!", Toast.LENGTH_LONG).show();
            //mText.setText("Succesful Submission!");
        }
    }
}
