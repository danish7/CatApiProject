package com.mobiquityinc.catapiproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by tgatling on 11/4/13.
 */
public class FullscreenCatDialog extends DialogFragment {

    Image kittyImage;

    public FullscreenCatDialog(Image kittyImage){
        this.kittyImage = kittyImage;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.cat_fullscreen_image, null);
        ImageView kittyIv = (ImageView) v.findViewById(R.id.cat_image_view);

        BitmapDownLoader download = new BitmapDownLoader(kittyIv);
        download.execute(kittyImage.getUrl());

        builder.setView(v)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
