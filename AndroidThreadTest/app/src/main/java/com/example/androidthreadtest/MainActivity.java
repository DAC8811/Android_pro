package com.example.androidthreadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int UPDATE_TEXT = 1;

    private TextView text;

    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE_TEXT:
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button changeText = (Button)findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
        text = (TextView)findViewById(R.id.text);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        text.setText("Nice to meet you");//直接这样在子线程中操作UI是不行的

                        Message message = new Message();//用于在线程间传递信息
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//将传递到handleMessage()方法中
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}