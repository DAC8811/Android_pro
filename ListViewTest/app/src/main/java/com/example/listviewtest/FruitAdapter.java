package com.example.listviewtest;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //当ListView中某一行出现在屏幕中时，将会自动调用getView方法
        //其中第一个参数是当前要返回的视图的位置（即第几行），第二个参数是前一个视图的缓存，
        // 第三个参数是当前视图的父视图
        Fruit fruit = getItem(position);//根据position提供的行数获取该行对应的对象
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView)view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//使用setTag方法来存储viewHolder对象
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//提取viewHolder
        }
        //为子项（也就是当前这一行）加载我们传入的布局

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
        //view也就是一个视图（包括布局和控件，而不包涵具体的图片和文字资源）
        //本质上就是我们自定义了一个视图并传给系统
    }
}

class ViewHolder{
    ImageView fruitImage;
    TextView fruitName;
}
