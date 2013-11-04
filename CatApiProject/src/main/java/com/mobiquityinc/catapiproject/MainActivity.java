package com.mobiquityinc.catapiproject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {

    List<Image> allImages;


    /**
     * Image
     */
    public class ImageAdapter extends BaseAdapter{


        List<Image> imageList;

        public class ImageHolder{
            ImageView catIV;
        }

        public ImageAdapter(List<Image> imageList){
            this.imageList = imageList;
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

            return Long.parseLong(imageList.get(position).getImageId());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageHolder imageHolder;

            if(convertView == null){

                imageHolder = new ImageHolder();

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.thumbnail_img_layout, parent, false);

                imageHolder.catIV = (ImageView) convertView.findViewById(R.id.thumbnail_image);

                convertView.setTag(imageHolder);


            }
            else {
                imageHolder = (ImageHolder) convertView.getTag();
            }

            imageHolder.catIV.setImageURI(Uri.fromFile(new File(allImages.get(position).getUrl())));


            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO  Call async fragment into allImages

        //List here
        allImages = new ArrayList<Image>();

        for(int i=0;i<10;i++)
        {

            allImages.add(new Image(
                "http://i.huffpost.com/gen/964776/thumbs/s-CATS-KILL-BILLIONS-large.jpg?10",
                    String.valueOf(i),
                "http://i.huffpost.com/gen/964776/thumbs/s-CATS-KILL-BILLIONS-large.jpg?10"));
        }

        //ImageAdapter imageAdapter = new ImageAdapter(allImages);
        GridView gridView = (GridView) findViewById(R.id.cat_gv_cats);
        gridView.setAdapter(new ImageAdapter(allImages));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
