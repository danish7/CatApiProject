package com.mobiquityinc.catapiproject;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashajgotra on 11/4/13.
 */
public class FragmentGetImages extends Fragment {

    private final static String IMAGE_COUNT  = "imageCount";
    private final static String DEBUG_TAG = "debug_tag";

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

        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            GetMoreCats getMoreCats = new GetMoreCats();
            getMoreCats.execute();
        } else {
            // display error
        }
    }

    private static class GetMoreCats extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            List<Image> imageList = new ArrayList<Image>();

            // do the network call
            int len = 500;
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.

            String myurl = "http://thecatapi.com/api/images/get?format=xml&results_per_page=20";

            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();



              //  if(response== )

                // parse the result
                // create an imageObject
                // add it to the list

                XmlPullParserFactory factory = null;
                try {
                    factory = XmlPullParserFactory.newInstance();
                } catch (XmlPullParserException e1) {
                    e1.printStackTrace();
                }
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput( new StringReader( "<foo>Hello World!</foo>" ) );
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        System.out.println("Start document");
                    } else if(eventType == XmlPullParser.START_TAG) {
                        System.out.println("Start tag "+xpp.getName());
                    } else if(eventType == XmlPullParser.END_TAG) {
                        System.out.println("End tag "+xpp.getName());
                    } else if(eventType == XmlPullParser.TEXT) {
                        System.out.println("Text "+xpp.getText());
                    }
                    eventType = xpp.next();
                }
                System.out.println("End document");
            }


                return contentAsString;

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // do the parsing



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //if(result)


        }

        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }
    }




}
