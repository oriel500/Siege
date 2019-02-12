package com.siege.oriel.siege;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends Activity implements View.OnClickListener {

    private Button btn; //כפתור
    EditText edPlayer1; //שחקן 1
    EditText edPlayer2; //שחקן 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        /*אתחול*/
        btn = (Button) findViewById(R.id.btn_toWar);
        btn.setOnClickListener(this);
        edPlayer1 = (EditText) findViewById(R.id.player1_name);//שחקן מספר 1
        edPlayer2 = (EditText) findViewById(R.id.player2_name);//שחקן מספר 2

        //להקטין כפתור
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //מוזיקה
                    makeMusic(R.raw.sound_button, false);

                    float x = (float) 0.90;
                    float y = (float) 0.90;

                    btn.setScaleX(x);
                    btn.setScaleY(y);
                }

                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    float x = 1;
                    float y = 1;

                    btn.setScaleX(x);
                    btn.setScaleY(y);

                }
                return false;
            }
        });
    }

    /*פעולות*/
    //פעולה שמקבלת שיר והאם הוא בלופ ומשמיעה אותו
    public void makeMusic(int id, boolean isLoop) {
        MainActivity.musicMain = MediaPlayer.create(this, id);
        if (isLoop) MainActivity.musicMain.setLooping(true);
        MainActivity.musicMain.start();
    }

    //ON CLICK
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_toWar: //תיבת דו שיח
                if(edPlayer1.getText().toString().equals("") || edPlayer2.getText().toString().equals(""))
                { //אם לא רשום שם
                    new AlertDialog.Builder(this).setTitle("Error!")
                            .setMessage("Please Enter a Player Name")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            ./*setIcon(R.drawable.error).*/show();
                }else
                { //אם הוכנסו שני שמות
                    Intent i;
                    /*else*/ i = new Intent(this,GameActivity.class);
                    //מכניסים את השמות למשחק
                    i.putExtra("data1",edPlayer1.getText().toString());
                    i.putExtra("data2",edPlayer2.getText().toString());
                    startActivity(i);
                    finish();
                }
                break;
        }
    }
}
