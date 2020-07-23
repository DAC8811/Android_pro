package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.LongDef;

public class MyService extends Service {

    private static final String TAG = "MyService";

    private DownloadBinder mBinder = new DownloadBinder();//内置一个binder

    class DownloadBinder extends Binder{
        public void startDownload(){//模拟开始下载的功能
            Log.d(TAG, "startDownload executed");
        }

        public int getProgress(){//模拟查看进度的功能
            Log.d(TAG, "getProgress executed");
            return 0;
        }
    }
    
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;//返回内置的binder
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){//开始后马上执行的命令可以写在这个方法里
        Log.d(TAG, "onStartCommand executed");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy executed");
    }
}
