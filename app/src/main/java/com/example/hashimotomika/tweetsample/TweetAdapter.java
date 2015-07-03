package com.example.hashimotomika.tweetsample;

import android.content.Context;
import android.widget.ArrayAdapter;

class TweetAdapter extends ArrayAdapter<String>{
    public TweetAdapter(Context context){
        super(context, android.R.layout.simple_list_item_1);
    }
}
