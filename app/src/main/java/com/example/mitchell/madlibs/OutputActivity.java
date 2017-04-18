package com.example.mitchell.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        // get intent (story) from inputActivity
        Intent intent = getIntent();
        String storyText = intent.getStringExtra(InputActivity.intentArg);
        TextView textView = (TextView) findViewById(R.id.outputTextView);
        textView.setText(Html.fromHtml(storyText));

    }
    // to go back to the homescreen
    public void anotherStory(View view){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    // when backbutton is pressed go back to homescreen and not previous activity
    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent (OutputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
