package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.view.Menu;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = (Button)findViewById(R.id.button_1);//通过findViewById方法获取布局中的元素
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FirstActivity.this,"You clicked Button 1",
//                        Toast.LENGTH_LONG).show();
//                //这里实际上是调用静态方法makeText创建了一个Toast对象并调用了它的show()方法进行显示
//                //其中第一个参数为context，即上下文，也就是一个工作环境
//                //一个context可以是一个activity，或者一个service等等
//                //第三个参数是Toast停留显示的时间长短

//                finish();//退出

//                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
//                startActivity(intent);
//                //显式intent，第一个参数为上下文，第二个参数为目标活动

//                Intent intent = new Intent("com.example.activitytest.ACTION_START");
//                intent.addCategory("com.example.activitytest.MY_CATEGORY");
//                //android.intent.category.DEFAULT是默认的category，inent会自动加载
//                //但自定义的category则必须手动加载
//                startActivity(intent);
//                //隐式intent,在manifest文件中设置intent-filter,使intent寻找对应的action和category

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                //Intent.ACTION_VIEW是Android系统内置的动作，其常量值为android.intent.action.VIEW
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                //Uri.pares()方法将一个网址字符串解析成一个Uri对象，并传入setData方法中
//                startActivity(intent);

//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                //同样是内置动作
//                intent.setData(Uri.parse("tel:10086"));
//                //确定了tel协议，即拨打电话
//                startActivity(intent);

                //给下一个活动传递数据
//                String data = "Hello SecondActivity";
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                intent.putExtra("extra_data",data);//第一个参数是键值，第二个是要传的数据
//                startActivity(intent);

                //从下一个活动获取数据
                Intent inetent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivityForResult(inetent,1);//requestCode用于指定请求码
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        //使用getMenuInflater方法得到MenuInflater对象，再用其创建菜单
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            //获得了item的id后对不同选项进行不同的操作
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,"You clicked Add",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,"You clicked Remove",
                        Toast.LENGTH_SHORT).show();
                break;
            default :}
        return true;
    }
    //活动返回的数据均会回调到onActivityResult()这个方法中
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){//根据请求码判断数据来源
            case 1:
                if(resultCode==RESULT_OK){//判断处理是否成功
                    String returnedData = data.getStringExtra("data_return");
                    Log.d(TAG, returnedData);
                }
                break;
            default:
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

}