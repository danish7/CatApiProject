package com.mobiquityinc.catapiproject;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by tgatling on 11/4/13.
 */
public class ImageFragment extends Fragment {

    ImageView catImage;
    Bitmap catBitmap;
    LinearLayout layout;


    @Override
    public void onAttach(Activity activity) {
            super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //catImage.setBackgroundColor(Color.RED);
        layout.setVisibility(View.VISIBLE);
        catImage.setImageBitmap(catBitmap);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cat_fullscreen_image, container, false);
        catImage = (ImageView) view.findViewById(R.id.cat_image_view);
        layout = (LinearLayout) view;
        Log.i("ImageFragment","YO:"+catImage);

        return view;
    }

    public void setCatBitmap(Bitmap bitmap){
        //catBitmap = bitmap;
        Log.i("ImageFragment",""+catImage);
        catBitmap = bitmap;
        //catImage.setImageResource(R.drawable.ic_launcher);
        //catImage.setBackgroundColor(Color.RED);
        //layout.setVisibility(View.VISIBLE);

    }

}
