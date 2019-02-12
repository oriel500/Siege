package com.siege.oriel.siege;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements TextView.OnClickListener {

    /*תכונות*/
    private ImageButton[] cardsP1; //מבצר של שחקן 1
    private ImageButton[] cardsP2; //מבצר של שחקן 2
    private ImageButton btnBack; //כפתור חזרה למסך הראשי
    GameManager game;

    /*==On Create==*/
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {/*פתיח ON CREATE*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MainActivity.musicMain.stop();
        makeMusic(R.raw.sound_start,false);

        /*אתחול*/
        btnBack = (ImageButton) findViewById(R.id.btnBack_game);
        btnBack.setOnClickListener(this);
        game = new GameManager(this);

        /*לקשר לייאוט(xml,id) לקלפים*/
        cardsP1 = new ImageButton[9];
        cardsP2 = new ImageButton[9];
        int id;
        for (int i = 0; i < 9; i++){//9 קלפים מבצר 1
            id = getResources().getIdentifier("card" + i + "_p1", "id", getPackageName());
            cardsP1[i] =(ImageButton) findViewById(id);
            cardsP1[i].setOnClickListener(this);
        }
        for (int i = 0; i < 9; i++){//9 קלפים מבצר 2
            id = getResources().getIdentifier("card" + i + "_p2", "id", getPackageName());
            cardsP2[i] =(ImageButton) findViewById(id);
            cardsP2[i].setOnClickListener(this);
        }

        /*לשנות את הקלפים לטקסטורה שלהם*/
        int cardNum = 0;
        String cardShape = "";
        int countCard = 1;
        int resCount;
        int res;
        for(int i = 0; i < cardsP1.length; i++) { //מבצר 1
            if(i < 3){
                cardNum = game.getPlayer(0).getSiege().getLine1().get(i).getCard().getNum();
                cardShape = game.getPlayer(0).getSiege().getLine1().get(i).getCard().getShape();
                countCard = game.getPlayer(0).getSiege().getLine1().get(i).getSize();
            }else if(i >= 3 && i < 7){
                cardNum = game.getPlayer(0).getSiege().getLine2().get(i-3).getCard().getNum();
                cardShape = game.getPlayer(0).getSiege().getLine2().get(i-3).getCard().getShape();
                countCard = game.getPlayer(0).getSiege().getLine2().get(i-3).getSize();
            }else if(i >= 7 && i < 9){
                cardNum = game.getPlayer(0).getSiege().getQk().get(i-7).getCard().getNum();
                cardShape = game.getPlayer(0).getSiege().getQk().get(i-7).getCard().getShape();
                countCard = game.getPlayer(0).getSiege().getQk().get(i-7).getSize();
            }

                //להפוך קלף
                res = cardsP1[i].getResources().getIdentifier("card" + cardShape + cardNum, "drawable", getPackageName());
                cardsP1[i].setBackgroundResource(res);
                if(countCard > 1){
                    resCount = cardsP1[i].getResources().getIdentifier("count" + countCard, "drawable", getPackageName());
                    cardsP1[i].setImageResource(resCount);
                }

        }
        for(int j = 0; j < cardsP2.length; j++) { //מבצר 2
            if(j < 3){
                cardNum = game.getPlayer(1).getSiege().getLine1().get(j).getCard().getNum();
                cardShape = game.getPlayer(1).getSiege().getLine1().get(j).getCard().getShape();
                countCard = game.getPlayer(1).getSiege().getLine1().get(j).getSize();
            }else if(j >= 3 && j < 7){
                cardNum = game.getPlayer(1).getSiege().getLine2().get(j-3).getCard().getNum();
                cardShape = game.getPlayer(1).getSiege().getLine2().get(j-3).getCard().getShape();
                countCard = game.getPlayer(1).getSiege().getLine2().get(j-3).getSize();
            }else if(j >= 7 && j < 9){
                cardNum = game.getPlayer(1).getSiege().getQk().get(j-7).getCard().getNum();
                cardShape = game.getPlayer(1).getSiege().getQk().get(j-7).getCard().getShape();
                countCard = game.getPlayer(1).getSiege().getQk().get(j-7).getSize();
            }

            //להפוך קלף
            res = cardsP2[j].getResources().getIdentifier("card" + cardShape + cardNum, "drawable", getPackageName());
            cardsP2[j].setBackgroundResource(res);
            if(countCard > 1){
                resCount = cardsP2[j].getResources().getIdentifier("count" + countCard, "drawable", getPackageName());
                cardsP2[j].setImageResource(resCount);
            }
        }

        /*שינוי שמות*/
        Intent in = getIntent();
        if (in != null) {
            Bundle xBundle = in.getExtras();
            String strData1 = xBundle.getString("data1");
            String strData2 = xBundle.getString("data2");
            game.setPlayersName(strData1, strData2);
        }

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
    }/*סגיר ON CREATE*/

    /*==פעולות==*/
    public void makeMusic(int id, boolean isLoop){
        MainActivity.musicMain = MediaPlayer.create(this ,id);
        if(isLoop) MainActivity.musicMain.setLooping(true);
        MainActivity.musicMain.start();
    }

    /*==on click==*/
    @Override
    public void onClick(View v) {
        Toast.makeText(this,"click",Toast.LENGTH_SHORT);
        ImageButton btn = (ImageButton) v;
        final int RESDEF = R.drawable.back; //כתובת של קלף הפוך
        boolean isEnd = game.isEnd(); //אמת אם המשחק נגמר שקר אחרת
        boolean isDone = false; //אם הפעולה במשחק בוצעה

        int res; //כתובת קלף הפוך
        int cardNum  = 0; //מספר של קלף
        String cardShape = ""; //צורה של קלף
        int coundCard = 0; //כמה יש במגדל

        switch (btn.getId()){//switch
            /*כפתור חזרה למסך ראשי*/
            case R.id.btnBack_game:
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

        /*ברירת מחדל*/
        default:

            //תור שחקן 1
            if(game.getK() == 0){

                for(int i = 0; i < cardsP1.length; i++)
                    if(cardsP1[i].equals(v)) {
                        isDone = game.makeATurn(i,2,new Card(5,"s"));
                        if(!isDone) Toast.makeText(this,"The action is not working",Toast.LENGTH_SHORT);
                    }
            //תור שחקן 2
            }else{

                for(int i = 0; i < cardsP2.length; i++)
                    if(cardsP2[i].equals(v)) {

                    }
            }

            break;
        }//switch

    }

}
