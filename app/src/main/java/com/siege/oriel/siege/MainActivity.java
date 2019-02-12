package com.siege.oriel.siege;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements TextView.OnClickListener {

    private LinearLayout l; //לייר של כפתורים ותמונה למעטה
    private ImageView imageUp; //התמונה למעלה
    private Button btnPlay; //כפתור שחק
    private Button btnStat; //כפתור סטטיסטיקות
    private Button btnExit; //כפתור יציאה
    private Animation uptodown,downtoup; //אנימציות
    public static MediaPlayer musicMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {/*ON CREATE*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*אתחול*/
        btnPlay = (Button)findViewById(R.id.btnPlay_main);
        btnPlay.setOnClickListener(this);
        btnStat = (Button)findViewById(R.id.btnStat_main);
        btnStat.setOnClickListener(this);
        btnExit = (Button)findViewById(R.id.btnExit_main);
        btnExit.setOnClickListener(this);
        l = (LinearLayout) findViewById(R.id.layout_down_id);
        imageUp = (ImageView) findViewById(R.id.imgUp_main);
        //אנימציה
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        imageUp.setAnimation(uptodown);
        l.setAnimation(downtoup);
        //מוזיקה
        makeMusic(R.raw.background_music1, true);

        //להקטין את הכפתורים
        //כפתור משחק
        btnPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //מוזיקה
                    makeMusic(R.raw.sound_button, false);

                    float x = (float) 0.90;
                    float y = (float) 0.90;

                    btnPlay.setScaleX(x);
                    btnPlay.setScaleY(y);
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    float x = 1;
                    float y = 1;

                    btnPlay.setScaleX(x);
                    btnPlay.setScaleY(y);

                }
                return false;
            }
        });
        //כפתור יציאה
        btnExit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //מוזיקה
                    makeMusic(R.raw.sound_button, false);

                    float x = (float) 0.90;
                    float y = (float) 0.90;

                    btnExit.setScaleX(x);
                    btnExit.setScaleY(y);
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    float x = 1;
                    float y = 1;

                    btnExit.setScaleX(x);
                    btnExit.setScaleY(y);

                }
                return false;
            }
        });
        //כפתור סטטיסטיקות
        btnStat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //מוזיקה
                    makeMusic(R.raw.sound_button, false);

                    float x = (float) 0.90;
                    float y = (float) 0.90;

                    btnStat.setScaleX(x);
                    btnStat.setScaleY(y);
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    float x = 1;
                    float y = 1;

                    btnStat.setScaleX(x);
                    btnStat.setScaleY(y);

                }
                return false;
            }
        });
    }/*ON CREATE*/

    /*פעולות*/
    //פעולה שמקבלת שיר והאם הוא בלופ ומשמיעה אותו
    public void makeMusic(int id, boolean isLoop){
        this.musicMain = MediaPlayer.create(this ,id);
        if(isLoop) this.musicMain.setLooping(true);
        this.musicMain.start();
    }

    @Override
    public void onClick(View v) {

        Button btn = (Button) v;
        switch (btn.getId()){//כפתורים
            case R.id.btnPlay_main:/*לשחק*/
                Intent i1;
                i1 = new Intent(this,AddUserActivity.class);
                startActivity(i1);
            break;
            case R.id.btnStat_main:/*סטטיסטיקות*/
                Intent i2;
                i2 = new Intent(this,StatActivity.class);
                startActivity(i2);
            break;
            case R.id.btnExit_main:/*יציאה*/
                //אלארט בוקס
                new AlertDialog.Builder(this).setTitle("Exit").
                        setMessage("Do you sure you want exit?").
                        setNeutralButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        musicMain.stop();
                        finish();
                    }
                })/*.setIcon(R.drawable.btnback)*/.show();
                //סיום אלרט בוקס
            break;
        }

    }
}
