package com.siege.oriel.siege;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StatActivity extends Activity implements TextView.OnClickListener{

    private ImageButton btnBack; //כפתור חזרה למסך הראשי

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        MainActivity.musicMain.stop();

        /*אתחול*/
        btnBack = (ImageButton) findViewById(R.id.btnBack_stat);
        btnBack.setOnClickListener(this);

        /*להקטין כפתור חזרה*/
        btnBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //מוזיקה
                    makeMusic(R.raw.sound_button,false);

                    float x = (float) 0.90;
                    float y = (float) 0.90;

                    btnBack.setScaleX(x);
                    btnBack.setScaleY(y);
                    btnBack.setBackgroundResource(R.drawable.btnback);
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    float x = 1;
                    float y = 1;

                    btnBack.setScaleX(x);
                    btnBack.setScaleY(y);
                    btnBack.setBackgroundResource(R.drawable.btnback);

                }
                return false;
            }
        });
    }

    /*פעולות*/
    public void makeMusic(int id, boolean isLoop){
        MainActivity.musicMain = MediaPlayer.create(this ,id);
        if(isLoop) MainActivity.musicMain.setLooping(true);
        MainActivity.musicMain.start();
    }

    /*ON CLICK*/
    @Override
    public void onClick(View v) {
        ImageButton btn = (ImageButton) v;

        switch (btn.getId()) {//switch
            /*כפתור חזרה למסך ראשי*/
            case R.id.btnBack_stat:
                //אלארט בוקס
                new AlertDialog.Builder(this).setTitle("Return Main Screen").
                        setMessage("Do you sure you want return to main screen?").
                        setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })/*.setIcon(R.drawable.btnback)*/.show();
                //סיום אלרט בוקס
                break;
        }
    }


}
