package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
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
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        //本地广播无法静态注册
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //getInstance()方法常用于单例模式，即一个确保某一个类只有一个实例，且自行实例化并向整个系统提供
        //对于一些服务类，整个程序只能有一个实例化对象，因此使用getInstance()方法来获得唯一实例
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);//销毁时要记得注销
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button:
//                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
//                intent.setComponent(new ComponentName(getPackageName(),
//                        "com.example.broadcasttest.MyBroadcastReceiver"));
//                //Android 8以上的版本对于静态注册的接收器，发送端必须将广播设置为显示广播
//                //ComponentName第一个参数为自定义的广播的类名,第二个参数为自定义的接收的类名
//                sendBroadcast(intent);
//                Log.d(TAG, "onClick");

                Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
                break;
            default:
                break;
        }
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

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context,Intent intent){
            Toast.makeText(context,"received local broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}

