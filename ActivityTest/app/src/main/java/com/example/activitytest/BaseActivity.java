package com.example.activitytest;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    //这个技巧挺有启发性的，新建一个BaseActivity类继承AppCompatActivity
    //在onCreate()方法中多加一个输出，同时使所有活动均继承BaseActivity而非直接继承AppCompatActivity
    //这样既能在所有类中修改，又不会影响原来的功能，妙啊
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        Log.d(TAG, getClass().getSimpleName());
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
