package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = (Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.send_notice:
                String id = "channle_1";
                Intent intent = new Intent(this,NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
                //延迟的意图，可通过几种静态方法获取实例，这里使用getActivity()方法
                NotificationManager manager =
                        (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                //获取一个服务
                NotificationChannel notificationChannel = new NotificationChannel(id,
                        "note_test",NotificationManager.IMPORTANCE_LOW);
                //从SDK26开始，每个通知要设置一个通道，第一个参数为唯一的通道id，第二参数为描述信息，第三个参数为重要性
                manager.createNotificationChannel(notificationChannel);//加载通道
                Notification notification = new NotificationCompat.Builder(this,id)//这个id也是通道id
                        .setContentTitle("This is content title")//设置标题内容
                        .setContentText("This is content text")//设置正文内容
                        .setWhen(System.currentTimeMillis())//指定通知创建的时间，单位毫秒
                        .setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        //设置大图标
                        .setContentIntent(pi)
                        //.setAutoCancel(true)//点开后自动关闭通知的方法其一
                        .build();
                manager.notify(1,notification);//显示，第一个参数为通知id，要保证每个通知id均不同
                break;
            default:
                break;
        }
    }
}