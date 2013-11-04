package com.mobiquityinc.catapiproject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity implements AbsListView.OnScrollListener {

    List<Image> allImages;
    GridView gridView;
    int scrollState;
    ImageAdapter imgAdapter;
    ImageFragment imageFragment;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //todo CALL ASYNC
        this.scrollState = scrollState;
        if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
            imgAdapter.notifyDataSetChanged();



    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    /**
     * Image
     */
    public class ImageAdapter extends BaseAdapter{


        List<Image> imageList;
        ImageFragment adImageFragment;

        public class ImageHolder{
            ImageView catIV;
        }

        public ImageAdapter(List<Image> imageList, ImageFragment imageFragment){
            this.imageList = imageList;
            this.adImageFragment = imageFragment;
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
        public View getView(final int position, View convertView, ViewGroup parent) {

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

            if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
            {
                imageHolder.catIV.setImageBitmap(allImages.get(position).getBitmapImage());
                imageHolder.catIV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.i("ImageFragment","NO:"+adImageFragment.catImage);
                        adImageFragment.setCatBitmap(allImages.get(position).getBitmapImage());
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, adImageFragment, null).addToBackStack(null).commit();

                    }
                });
            }


            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO  Call async fragment into allImages

        //List here
        allImages = new ArrayList<Image>();
        imageFragment = new ImageFragment();

        for(int i=0;i<100;i++)
        {

            allImages.add(new Image(
                "http://jasonlefkowitz.net/wp-content/uploads/2013/07/Cute-Cats-cats-33440930-1280-800.jpg",
                    String.valueOf(i),
                "http://i.huffpost.com/gen/964776/thumbs/s-CATS-KILL-BILLIONS-large.jpg"));
        }

        //ImageAdapter imageAdapter = new ImageAdapter(allImages);
        gridView = (GridView) findViewById(R.id.cat_gv_cats);
        imgAdapter = new ImageAdapter(allImages,imageFragment);
        gridView.setAdapter(imgAdapter);
        gridView.setOnScrollListener(this);




    }

    public static void printList()
    {
        Image image = listofCats.get(0);
        Log.v("DANISH", image.getUrl());
        BitmapDownLoader download = new BitmapDownLoader(imageView);
        download.execute(image.getUrl());
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
