package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private String[] data = {"Apple","Banana","Watermelon","Pear","Grape","Pineapple","Strawberry",
//            "Cherry","Mango","Apple","Banana","Watermelon","Pear","Grape","Pineapple","Strawberry",
//            "Cherry","Mango",};
    private ArrayList<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.support_simple_spinner_dropdown_item,data);
//        //创建一个适配器,第二个参数为子项布局,第三个为具体数据
//        ListView listView = (ListView)findViewById(R.id.list_view);
//        listView.setAdapter(adapter);//绑定适配器

        initFruits();
        FruitAdapter fruitAdapter = new FruitAdapter(this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(fruitAdapter);
    }

    private void initFruits(){
        for(int i=0;i<2;i++){
            Fruit apple = new Fruit("Apple",R.drawable.apple_pic);
            Fruit banana = new Fruit("Banana",R.drawable.banana_pic);
            Fruit watermelon = new Fruit("Watermelon",R.drawable.watermelon_pic);
            Fruit pear = new Fruit("pear",R.drawable.pear_pic);
            Fruit grape = new Fruit("grape",R.drawable.grape_pic);
            Fruit pineapple = new Fruit("pineapple",R.drawable.pineapple_pic);
            Fruit strawberry = new Fruit("strawberry",R.drawable.strawberry_pic);
            Fruit cherry = new Fruit("cherry",R.drawable.cherry_pic);
            Fruit mango = new Fruit("mango",R.drawable.mango_pic);
            fruitList.add(apple);
            fruitList.add(banana);
            fruitList.add(watermelon);
            fruitList.add(pear);
            fruitList.add(grape);
            fruitList.add(pineapple);
            fruitList.add(strawberry);
            fruitList.add(cherry);
            fruitList.add(mango);
        }
    }
}