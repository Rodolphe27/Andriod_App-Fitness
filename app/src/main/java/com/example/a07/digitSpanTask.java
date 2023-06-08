package com.example.a07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class digitSpanTask extends AppCompatActivity {


    private TextView digitTextView;
    private Button yesButton;
    private Button noButton;
    private List<Integer> digitSequence;
    private int sequenceIndex;
    private List<Boolean> userResponses;






    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digit_span_task);


        digitTextView = findViewById(R.id.digitTextView);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);

        digitSequence = generateDigitSequence(5);
        sequenceIndex = 0;
        userResponses = new ArrayList<>();



        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordUserResponse(true);
                nextDigit();

            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordUserResponse(false);
                nextDigit();
            }
        });
    }
    private void updateDigitText() {
        digitTextView.setText(String.valueOf(digitSequence.get(sequenceIndex)));
    }
    private void recordUserResponse (boolean response){
        userResponses.add(response);
    }
    private void nextDigit () {
        sequenceIndex++;

        if (sequenceIndex < digitSequence.size()) {
            updateDigitText();
        } else {}

    }

       private List<Integer>generateDigitSequence ( int length){
        List<Integer> sequence = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sequence.add(digit);
        }
        return sequence;

       }




}