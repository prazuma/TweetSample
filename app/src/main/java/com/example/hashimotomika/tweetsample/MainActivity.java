package com.example.hashimotomika.tweetsample;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.widget.Toast;

import java.util.Random;

import twitter4j.Twitter;
import twitter4j.TwitterException;


public class MainActivity extends ListActivity {
    Random rand = new Random();
    int n = rand.nextInt(1000);
    private TweetAdapter mAdapter;
    private Twitter mTwitter;
    private String mInputText = "ﾌﾘﾌﾘ~(ｏ^-^)[" + Integer.toString(n) + "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        if have no accessToken, go TwitterOAuthActivity
         */
        if(!TwitterUtils.hasAccessToken((this))){
            Intent intent = new Intent(this, TwitterOAuthActivity.class);
            startActivity(intent);
            finish();
        } else {
            mAdapter = new TweetAdapter(this);
            setListAdapter(mAdapter);
            mTwitter = TwitterUtils.getTwitterInstance(this);
            tweet();
        }
    }

    private void tweet(){
        AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    mTwitter.updateStatus(params[0]);
                    return true;
                } catch (TwitterException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    showToast("ツイートしたお");
                    finish();
                } else {
                    showToast("ツイートできなかったのだ");
                }
            }
        };
        task.execute(mInputText);
    }


    private void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
