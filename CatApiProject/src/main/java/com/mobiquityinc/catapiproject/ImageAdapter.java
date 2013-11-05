package com.mobiquityinc.catapiproject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {


    private List<Image> imageList;
    private int scrollState;
    private Context context;
    public BitmapDownLoader download;


    public class ImageHolder{
        ImageView catIV;
    }

    public ImageAdapter(Context context, List<Image> imageList){
        this.context = context;
        this.imageList = imageList;
        //this.adImageFragment = imageFragment;
    }



    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    public void setScrollState(int x){
        scrollState = x;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ImageHolder imageHolder;


        if(convertView == null){

            imageHolder = new ImageHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.thumbnail_img_layout, parent, false);

            imageHolder.catIV = (ImageView) convertView.findViewById(R.id.thumbnail_image);
            imageHolder.catIV.setVisibility(View.INVISIBLE);

            convertView.setTag(imageHolder);


        }
        else {
            imageHolder = (ImageHolder) convertView.getTag();
            imageHolder.catIV.setVisibility(View.INVISIBLE);


        }

        download = new BitmapDownLoader(imageHolder.catIV);
        download.execute(imageList.get(position).getUrl());




        return convertView;
    }
}
