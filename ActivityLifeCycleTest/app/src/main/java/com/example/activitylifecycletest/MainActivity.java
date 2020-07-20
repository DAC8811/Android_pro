package com.example.activitylifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){//调用上一次存储的值
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        }

        Button startNormalActivity = (Button)findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button)findViewById(R.id.start_dialog_activity);
        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NormalActivity.class);
                startActivity(intent);
            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){//活动创建后处于停止状态时，该方法使其进入暂停状态
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume(){//处于暂停状态时，使其进入前台，占领屏幕
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause(){//处于运行状态时，使其暂停
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop(){//处于暂停状态时，使其停止
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy(){//处于停止状态时，销毁并回收内存空间
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        //该方法保证在系统回收该活动时（该类没有被毁）能够将页面上的数据暂时存储，等到下一次返回时再重新调用
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key",tempData);
    }
}