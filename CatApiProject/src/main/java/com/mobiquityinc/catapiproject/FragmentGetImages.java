package com.mobiquityinc.catapiproject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

/**
 * Created by ashajgotra on 11/4/13.
 */
public class FragmentGetImages extends Fragment {

    private final static String IMAGE_COUNT  = "imageCount";

    public static FragmentGetImages newInstance(int imageCount){

        FragmentGetImages f = new FragmentGetImages();
        Bundle bdl = new Bundle();
        bdl.putInt(IMAGE_COUNT,imageCount);
        f.setArguments(bdl);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    private static class GetMoreCats extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // do the network call
            



            // do the parsing



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //if(result)


        }
    }


}
