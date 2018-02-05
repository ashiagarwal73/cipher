package com.agarwal.ashi.cipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText editText;
    Button encrypt;
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText=findViewById(R.id.editext);
        encrypt=findViewById(R.id.encrypt);
        textView=findViewById(R.id.textview);
        progressBar=findViewById(R.id.progress);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals(""))
                {
                    Toast.makeText(Main2Activity.this, "Enter key", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    MyAsync my=new MyAsync(Main2Activity.this,textView,progressBar);
                    my.execute("https://shreyansh1601.000webhostapp.com/new%201.php?name="+editText.getText().toString());
                }
            }
        });
    }
}
