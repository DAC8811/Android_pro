package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {
    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = getIntent();//通过getIntent()方法获取用于启动SecondActivity的intent
//                String data = intent.getStringExtra("extra_data");//传入相应键值获取信息
//                Log.d(TAG, data);

//                //返回数据给上一个活动
//                Intent intent = new Intent();//新建一个intent仅用于传递数据
//                intent.putExtra("data_return","Hello FirstActivity");
//                setResult(RESULT_OK,intent);//专门用于给上一个活动返回数据
//                finish();

                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    public static void actionStart(Context context,String data1,String data2){
        //通过这样封装函数能够让SecondActivity的启动所需参数一目了然
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);//静态函数不能直接调用非静态方法
    }
}