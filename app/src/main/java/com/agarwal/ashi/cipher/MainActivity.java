package com.agarwal.ashi.cipher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Scanner;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MainActivity extends AppCompatActivity {
    public static String string,original;
    EditText editText;
    Button encrypt;
    TextView textView;
    TextView forgot;ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editext);
        encrypt=findViewById(R.id.encrypt);
        textView=findViewById(R.id.textview);
        progressBar=findViewById(R.id.progress);
        forgot=findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = editText.getText().toString();
                original=string;
                progressBar.setVisibility(View.VISIBLE);
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                byte[] d = new byte[0];
                try {
                    d = md.digest(string.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                convertByteArrayToHexString(d);
            }
        });

    }
    public  void convertByteArrayToHexString(byte[] digest)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < digest.length; i++)
        {
            stringBuffer.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        string = stringBuffer.toString();
        textView.setText(string);
        Sending_data sending_data=new Sending_data(MainActivity.this,"false",progressBar);
        sending_data.execute("https://shreyansh1601.000webhostapp.com/Register_details.php?original="+original+"&string="+string);
    }
}
