package com.example.mitchell.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InputActivity extends AppCompatActivity {

//  declaring variables
    Story story;
    public static final String intentArg = "storyText";
    int wordsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // calling the functions
        generateStory();
        wordsCounter();
        hints();
    }

    private void generateStory(){
        try{
            // get a random filename and make an instance of the class Story
            story = new Story(getAssets().open(randomStory()));

        } catch (IOException e) {
            //log error
        }
    }

    public void onSubmit(View view) {
        // look at the edittext and get the word the user typed in
        EditText userInput = (EditText) findViewById(R.id.editText);
        String input = userInput.getText().toString();
        // checks if the user typed a word
        if (!input.equals("")) {
            // fill in the word
            story.fillInPlaceholder(input);
            // clear edittext
            userInput.setText("");
            Toast.makeText(this, "Keep going!", Toast.LENGTH_SHORT).show();
            // if the amount of words is reached go to next activity
            if (story.getPlaceholderRemainingCount() == 0) {
                Intent intent = new Intent(this, OutputActivity.class);
                intent.putExtra(intentArg, story.toString());
                this.startActivity(intent);
            }
            wordsCounter();
            hints();
        }
        else{
            Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
        }
    }

    // keeps track of the amount of words the user need to fill in
    private void wordsCounter(){
        wordsLeft = story.getPlaceholderRemainingCount();
        String words = wordsLeft + " word(s) left";
        TextView amountWords = (TextView) findViewById(R.id.amountWords);
        amountWords.setText(words);
    }

    // tells the user what kind of word they need to type
    private void hints(){
        EditText userInput = (EditText) findViewById(R.id.editText);
        String hint = story.getNextPlaceholder();
        userInput.setHint("Please type a/an " + hint);
    }

    // makes a arraylist with the stories and gives a random story from that list
    private String randomStory(){
        ArrayList<String> fileNames = new ArrayList<>();
        fileNames.add("stories/madlib0_simple.txt");
        fileNames.add("stories/madlib1_tarzan.txt");
        fileNames.add("stories/madlib2_university.txt");
        fileNames.add("stories/madlib3_clothes.txt");
        fileNames.add("stories/madlib4_dance.txt");

        Random rand = new Random();
        int  n = rand.nextInt(fileNames.size()-1);
        return fileNames.get(n);
    }
}
