package com.siege.oriel.siege;

import java.util.ArrayList;
import android.app.Activity;

public class GameManager {

    private Player[] players; //מערך של שחקנים
    int k; //אינדקס של מי התור
    public static Deck deckGame; //חפיסת קלפים
    private Activity activity;

    /*פעולה בונה*/
    public GameManager(Activity activity) {
        activity = activity;
        deckGame = new Deck();
        deckGame.shuffle();

        this.players = new Player[2];
        players[0] = new Player("Player 1", new Siege(new Card(12,"s"), new Card(13,"s"), deckGame));
        players[1] = new Player("Player 2", new Siege(new Card(12,"h"), new Card(13,"h"), deckGame));
        this.k = 0;
    }

    /*gets and sets*/
    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public Player getPlayerTurn() {
        return players[k];
    }

    public Player getPlayerNotTurn() {
        if(k == 0) return players[1];
        return players[0];
    }

    public Player getPlayer(int k) {
        return players[k];
    }

    /*==פעולות==*/
    //משנה שמות שחקנים
    public void setPlayersName(String p1, String p2) {
        players[0].setName(p1);
        players[1].setName(p2);
    }

    //מחליף את האינדקס
    public void nextTurn() {
        if(this.k == 0) this.k = 1;
        else this.k = 0;
    }

    public Card pickUpDeck() {
        return deckGame.removeCard();
    }

    //פעולה שמקבלת שורה וקלף ומחזירה את המיקום בשורה ששווה לקלף המתקבל
    public int getEqualPlace(ArrayList<Tower> line, Card card) {
        for (int i = 0; i < line.size(); i++) {
            if(line.get(i).getCard().equalsNum(card.getNum())) return i;
        }
        return 0;
    }

    //עושה תור במשחק
    //i - מיקום הקלף במערך
    //הפעולה מחזירה אמת אם התור יתבצע שקר התור לא יתבצע
    public boolean makeATurn(int i/*מיקום קלף במבצר*/, int action/*פעולה*/, Card cardUse/*הקלף שיצא בקופה*/) {
        ArrayList<Tower> line;
        ArrayList<Tower> enemyLine;
        Card attackCard = null;

        if(!cardUse.equals(new Card(0,""))) {
            if(i < 3) {/*line 1*/
                line = getPlayerTurn().getSiege().getLine1();
                enemyLine = getPlayerNotTurn().getSiege().getLine1();
                switch (action) {
                    case 1://נשרף
                        deckGame.addCard(cardUse);
                        return true;
                    case 2://לבצר
                        if(!line.get(i).isFull() && line.get(i).getCard().equalsNum(cardUse.getNum())) {
                            line.get(i).addCard(cardUse);
                            return true;
                        }
                        else return false;
                    case 3://לתקוף
                        if(cardUse.getNum() - enemyLine.get(i).getCard().getNum() == 1) {
                            if(enemyLine.get(i).getSize() == 1)	enemyLine.get(i).getCard().setNum(0);
                            else attackCard = enemyLine.get(i).removeCard();
                            deckGame.addCard(attackCard);
                            return true;
                        }else return false;
                }//סוגר switch
            }/*סוגר line 1*/

            if(i >= 3 && i < 7) {/*line 2*/
                line = getPlayerTurn().getSiege().getLine2();
                enemyLine = getPlayerNotTurn().getSiege().getLine2();
                switch (action) {
                    case 1://נשרף
                        deckGame.addCard(cardUse);
                        return true;
                    case 2://לבצר
                        if(!line.get(i-3).isFull() && line.get(i-3).getCard().equalsNum(cardUse.getNum())) {
                            line.get(i-3).addCard(cardUse);
                            return true;
                        }
                        else return false;
                    case 3://לתקוף
                        if(cardUse.getNum() - enemyLine.get(i-3).getCard().getNum() == 1) {
                            if(enemyLine.get(i-3).getSize() == 1)	enemyLine.get(i-3).getCard().setNum(0);
                            else attackCard = enemyLine.get(i-3).removeCard();
                            deckGame.addCard(attackCard);
                            return true;
                        }else return false;
                }//סוגר switch
            }/*סוגר line 2*/

            if(i >= 7 && i < 9) {/*line 3*/
                line = getPlayerTurn().getSiege().getQk();
                enemyLine = getPlayerNotTurn().getSiege().getQk();
                switch (action) {
                    case 1://נשרף
                        deckGame.addCard(cardUse);
                        return true;
                    case 2://לבצר
                        if(!line.get(i-7).isFull() && line.get(i-7).getCard().equalsNum(cardUse.getNum())) {
                            line.get(i-7).addCard(cardUse);
                            return true;
                        }
                        else return false;
                    case 3://לתקוף
                        if(cardUse.getNum() - enemyLine.get(i-7).getCard().getNum() == 1) {
                            if(enemyLine.get(i-7).getSize() == 1)	enemyLine.get(i-7).getCard().setNum(0);
                            else attackCard = enemyLine.get(i).removeCard();
                            deckGame.addCard(attackCard);
                            return true;
                        }else return false;
                }//סוגר switch
            }/*סוגר line 3*/
            cardUse = null;
            //אם הקלף שמשתמשים בוא הוא מהמבצר שלך
        }else {

        }
        return false;
    }

    //פעולה שמחזירה אמת אם המשחק הסתיים שקר אחרת
    public boolean isEnd() {
        return getPlayerTurn().getSiege().isEmpty() || getPlayerNotTurn().getSiege().isEmpty();
    }

}

