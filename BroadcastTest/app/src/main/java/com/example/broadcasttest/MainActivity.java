package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
        //动态方法注册广播接收器
        //优点：布置灵活
        //缺点：无法在开启程序前接收广播
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);//销毁时要记得注销
    }
    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            //onReceive()方法中无法使用多线程，所以不应该有太多任务
            //Toast.makeText(context,"network changes",Toast.LENGTH_SHORT).show();
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            //ConnectivityManager是专门用于管理网络的系统服务类
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //NetworkInfo类显示状况
            if(networkInfo != null && networkInfo.isAvailable())
                Toast.makeText(MainActivity.this,"network is available",
                        Toast.LENGTH_SHORT).show();
            else{
                Toast.makeText(MainActivity.this,"network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
}

