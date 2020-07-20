package com.example.uiwigettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //可通过直接继承接口的方式重写button中的按键动作
    private static final String TAG = "MainActivity";
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);//这种写法同样要注册监视器
        editText = (EditText)findViewById(R.id.edit_text);
        imageView = (ImageView)findViewById(R.id.image_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View v){//只需要重写一次该函数即可，以id判断按下的按键具体是哪一个
        switch(v.getId()){
            case R.id.button:
//                String inputText = editText.getText().toString();//从editText处获取字符串
//                Toast.makeText(this,inputText,Toast.LENGTH_SHORT).show();

//                imageView.setImageResource(R.drawable.img_2);//切换图片资源

//                if(progressBar.getVisibility()==View.GONE)
//                    progressBar.setVisibility(View.VISIBLE);//将进度条设置为可见
//                else
//                    progressBar.setVisibility(View.GONE);//将进度条设置为不可见

//                //警告提示框
//                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//                dialog.setTitle("This is Dialog");
//                dialog.setMessage("Something important");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//                dialog.show();

                //进度条提示框
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }

    }
}