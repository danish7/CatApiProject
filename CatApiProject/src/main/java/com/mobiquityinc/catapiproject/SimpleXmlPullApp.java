package com.mobiquityinc.catapiproject;

import android.os.Debug;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashajgotra on 11/4/13.
 */
public class SimpleXmlPullApp
{

    public static List<Image> main (String listString) throws XmlPullParserException, IOException
    {
        String url = null;
        String id = null;
        String source = null;
        List<Image> imageList = new ArrayList<Image>();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput( new StringReader(listString) );
        int eventType = xpp.getEventType();

        boolean isUrl=false,isId=false,isSource=false;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagname = xpp.getName();

            if(eventType == XmlPullParser.START_DOCUMENT) {

            } else if(eventType == XmlPullParser.START_TAG) {

                if(tagname.equalsIgnoreCase("url")){
                    isUrl=true;
                }

                if(tagname.equalsIgnoreCase("id")){
                    isId=true;
                }

                if(tagname.equalsIgnoreCase("source_url")){
                    isSource=true;
                }

            } else if(eventType == XmlPullParser.END_TAG) {
                isUrl=false;isId=false;isSource=false;

                if(tagname.equalsIgnoreCase("image")){
                    Image image = new Image(url,id,source);
                    imageList.add(image);
                    Log.i("debug","created: "+image.imageId);
                }

            } else if(eventType == XmlPullParser.TEXT) {
                System.out.println("Text "+xpp.getText());

                if(isUrl){
                    url = xpp.getText();
                }

                if(isId){
                    id = xpp.getText();
                }

                if(isSource){
                    source = xpp.getText();
                }
            }
            eventType = xpp.next();
        }
        return imageList;
    }
}
