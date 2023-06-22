package com.example.a07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.Random;

public class ExerciseVideoActivity extends AppCompatActivity implements View.OnClickListener{
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
                // show a dialog to get the grading of this video







                Intent intent = new Intent();
                intent.setClass(ExerciseVideoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }

//    private void showDialogSeekBarAndComment() {
//        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
//        View mView = getLayoutInflater().inflate(R.layout.dialog_seekbar_and_comment, null);
//        // mySeekBar
//        SeekBar mySeekBar = mView.findViewById(R.id.my_exercise_review_seekbar);
//        mySeekBar.setOnSeekBarChangeListener(ExerciseVideoActivity.this);
//        mBuilder.setTitle(R.string.after_sport_seekbar);
//        //put the seekbar into the dialog
//        mBuilder.setView(mView);
//
//        mBuilder.setPositiveButton(R.string.btn_after_sport_dialog_positive, (dialog, which) -> {
//            // save to datebase;
//            saveSportDataToDB();
//
//            //show result dialog
//            showDialogRecordResult();
//            // todo? start questionaire?
//
//        });
//
//        mBuilder.setNegativeButton(R.string.btn_after_sport_dialog_negative, (dialog, which) -> {
//            // back ot main page
//            Intent intent = new Intent(Tracking.this, MainActivity.class);
//            startActivity(intent);
//        });
//
//
//        //show the AlertDialog using show() method
//        AlertDialog alertDialog2 = mBuilder.create();
//        alertDialog2.show();
//    }
//
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }
//

}