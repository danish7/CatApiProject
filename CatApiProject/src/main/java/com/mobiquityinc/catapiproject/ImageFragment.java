package com.mobiquityinc.catapiproject;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by tgatling on 11/4/13.
 */
public class ImageFragment extends Fragment {

    ImageView catImage;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        catImage.setBackgroundColor(Color.RED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cat_fullscreen_image, container);
        catImage = (ImageView) view.findViewById(R.id.cat_imageview);


        return view;
    }
}
