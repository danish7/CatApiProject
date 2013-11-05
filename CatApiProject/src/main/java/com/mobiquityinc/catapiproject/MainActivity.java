package com.mobiquityinc.catapiproject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity implements AbsListView.OnScrollListener, FragmentGetImages.OnListReady {

    public static List<Image> allImages;
    public static GridView gridView;
    //int scrollState;
    public static ImageAdapter imgAdapter;
    private ImageView fullscreenKitty;
    public static Boolean[] reupdate;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        imgAdapter.setScrollState(scrollState);

        if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
            // This is not ideal, as whenever it stops scrolling, ALL images reload.
            imgAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    /**
     * Image
     */


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allImages = new ArrayList<Image>();


        //Call async fragment into allImages
        FragmentGetImages getImagesFragment = new FragmentGetImages();
        getFragmentManager().beginTransaction().add(getImagesFragment.newInstance(10), null).commit();

        fullscreenKitty = (ImageView) findViewById(R.id.full_kitty);

        //List here

        for(int i=0;i<100;i++)
        {

            allImages.add(new Image(
                "http://jasonlefkowitz.net/wp-content/uploads/2013/07/Cute-Cats-cats-33440930-1280-800.jpg",
                    String.valueOf(i),
                "http://i.huffpost.com/gen/964776/thumbs/s-CATS-KILL-BILLIONS-large.jpg"));
        }

        gridView = (GridView) findViewById(R.id.cat_gv_cats);

    }

    private void resetReupdate(){
        reupdate = new Boolean[allImages.size()];
        for(int i = 0; i < reupdate.length; i++)
            reupdate[i] = true;
    }

    public void printList(List<Image> list)
    {
        Log.e("TAVON", "print list ran | " + list.size());
        allImages = list;
        resetReupdate();

        imgAdapter = new ImageAdapter(this, allImages);
        gridView.setAdapter(imgAdapter);
        gridView.setOnScrollListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new FullscreenCatDialog(allImages.get(position)).show(getFragmentManager(), "meow");

            }
        });
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
