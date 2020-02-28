package fr.univ_artois.rtbethune;;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RSSloader {

    public static List<String> parse(String url) {
        List<String> news = new ArrayList<>();
        XmlPullParser parser = Xml.newPullParser();
        try {
            InputStream stream = new URL(url).openConnection().getInputStream();
            parser.setInput(stream, null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String name = parser.getName();
                    if (name.equalsIgnoreCase("title")) {
                        news.add(parser.nextText().trim());

                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return news;
    }
}

