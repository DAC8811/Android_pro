package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.send_request:
                //sendRequestWithHTTPURLConnection();
                sendRequestWithOkHttp();
                break;
            default:
                break;
        }
    }

    private void sendRequestWithHTTPURLConnection(){
        new Thread(new Runnable() {//开启一个子线程用于发送HTTP请求
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");//设置方法（POST和GET)
                    connection.setConnectTimeout(8000);//设置连接超时的时间(毫秒)
                    connection.setReadTimeout(8000);//设置读取超时的时间(毫秒)
                    InputStream in = connection.getInputStream();//获取服务器返回的输入流
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();//StringBuffer的简易替换类
                    String line;
                    while((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    if(reader!=null){
                        try{
                            reader.close();
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {//ui操作不允许在子线程中完成，所以使用runOnUiThread方法切换到主线程
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                   OkHttpClient client = new OkHttpClient();
                   Request request = new Request.Builder()
                           .url("https://www.baidu.com")//设置网址
                           .build();
                   Response response = client.newCall(request).execute();//执行后获得响应
                   String responseData = response.body().string();//得到具体内容
                   showResponse(responseData);
                }catch (IOException ex){
                   ex.printStackTrace();
                }
            }
        }).start();
    }
}