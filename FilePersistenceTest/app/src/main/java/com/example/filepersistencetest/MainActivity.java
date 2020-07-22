package com.example.filepersistencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_text);
        String inputText = load();
        if(!TextUtils.isEmpty(inputText)){//该方法能直接判断字符串是否为空或null
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            //设置光标放在字符串末尾
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText = editText.getText().toString();
        save(inputText);
    }

    public void save(String inputText){
        FileOutputStream out = null;
        //java内置的文件输出流
        BufferedWriter writer = null;
        //java内置的字符缓冲输出流
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            //第一个参数为文件名（不能指定路径，因为统一存储在/data/data/<packgename>/files/文件夹下）
            //第二个参数为文件写入的模式
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //OutoutStreamWriter类，java内置，字符流到字节流的桥梁
            writer.write(inputText);
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public String load(){
        //加载部分中各个类的作用与存储部分对应
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine())!=null){
                content.append(line);
            }//一行行读取字符串
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}