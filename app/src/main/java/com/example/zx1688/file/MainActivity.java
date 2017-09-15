package com.example.zx1688.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static android.R.attr.format;
import static java.nio.charset.StandardCharsets.UTF_8;


public class MainActivity extends AppCompatActivity {
    private final String FILE_NAME="fileDemo.txt";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得实例
        Button btnWrite=(Button)findViewById(R.id.buttonWrite);
        Button btnRead=(Button)findViewById(R.id.buttonRead);
        editText=(EditText)findViewById(R.id.editText) ;
        btnWrite.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view){
                OutputStream out=null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(FILE_NAME,MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String content=editText.getText().toString();
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));

                    }finally {
                        if (out!=null) {
                            out.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                InputStream in=null;
                try {
                    FileInputStream fileInputStream=openFileInput("fileDemo.txt");
                    in=new BufferedInputStream(fileInputStream);

                    int c;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try {
                        while ((c=in.read())!=-1){
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally {
                        if (in!=null)
                            try {
                                in.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
}


