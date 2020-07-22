package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = (Button)findViewById(R.id.save_data);
        saveData.setOnClickListener(this);
        Button restoreData = (Button)findViewById(R.id.restore_data);
        restoreData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
       switch (v.getId()){
           case R.id.save_data:
               SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
               editor.putString("name","tom");//第一个参数为键值，第二个参数为要存储的数据
               editor.putInt("age",28);
               editor.putBoolean("married",false);
               editor.apply();//用apply()方法进行提交
               break;
           case R.id.restore_data:
               SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
               String name = pref.getString("name","");//读取各项数据，如果没有找到则会直接返回第二个参数
               int age = pref.getInt("age",0);
               boolean married = pref.getBoolean("married",true);
               Log.d(TAG, "name is "+name);
               Log.d(TAG, "age is "+age);
               Log.d(TAG, "married is "+married);
               break;
           default:
               break;
       }
    }
}