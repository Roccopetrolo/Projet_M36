package fr.univ_artois.rtbethune;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.ListResourceBundle;

public class MainActivity extends AppCompatActivity {
public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading news", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                url = "https://rt-bethune.univ-artois.fr/feed.xml";

                new DownloadFileFromURL().execute(url);
                //android.os.NetworkOnMainThreadException;
                //List<RssItem> news = RssFeedProvider.parse(url);
                //RssListAdapter adapter = new RssListAdapter(getApplicationContext(), news);

                //ListView list = (ListView) findViewById(R.id.list_news);
                //list.setAdapter(adapter);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
          //  super.onCreate(savedInstanceState);
          //  setContentView(R.layout.activity_settings);
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, List<RssItem> > {

        /**
         * Downloading file in background thread
         * */
        @Override
        protected List<RssItem> doInBackground(String ... url) {
            List<RssItem> news = RssFeedProvider.parse(url[0]);
            return news;
        }

        @Override
        protected void onPostExecute(List<RssItem> news) {
            super.onPostExecute(news);
            ListView list = (ListView) findViewById(R.id.list_news);

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, android.R.id.text1) ;
            //for (RssItem message : news) {
            //    adapter.add(message.title);
            //}
            //or
            //ArrayAdapter<RssItem> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1 , news);
            // or
            RssListAdapter adapter = new RssListAdapter(getApplicationContext(), news);
            list.setAdapter(adapter);

        }
    }

}
