package fr.univ_artois.rtbethune;;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button add =  (Button) findViewById(R.id.add);

        final TextInputEditText rss =  (TextInputEditText) findViewById(R.id.rsstxt);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading news", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();


                String url = rss.getText().toString();


                setContentView(R.layout.activity_main);

                new SettingsActivity.DownloadFileFromURL().execute(url);
            }
        });
    }
    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, List<RssItem>> {

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

            RssListAdapter adapter = new RssListAdapter(getApplicationContext(), news);
            list.setAdapter(adapter);

        }
    }

}


