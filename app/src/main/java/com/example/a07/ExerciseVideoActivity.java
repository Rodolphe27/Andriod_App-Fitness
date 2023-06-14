package com.example.a07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Random;

public class ExerciseVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;
    private int[] videoResources = {R.raw.sample_exercise1, R.raw.sample_exercise_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_video);

        videoView = findViewById(R.id.videoView);

        // Add media controls (play/pause/seekbar)
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);   //set the position of the control bar

        playRandomVideo();  //play random video from video resource
        findViewById(R.id.btn_quit_video).setOnClickListener(this);
    }

    private void playRandomVideo(){
        int randomIndex = new Random().nextInt(videoResources.length);      //random number between 0 and length-1
        int videoResource = videoResources[randomIndex];
        String videoPath = "android.resource://" + getPackageName() + "/" + videoResource;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_quit_video:
                Intent intent = new Intent();
                intent.setClass(ExerciseVideoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}