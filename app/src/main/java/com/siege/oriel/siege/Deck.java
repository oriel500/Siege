package com.siege.oriel.siege;
import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> deck; //תור של קלפים - חפיסת קלפים
    //חפיסה בלי מלך ומלכה עלה ולב

    /*פעולה בונה*/
    public Deck() {
        this.deck = new LinkedList<Card>();
        fillDeck();
    }

    /*gets and sets*/
    public int getSize(){
        return this.deck.size();
    }

    /*פעולות*/
    //פעולה שממלת את התור בכלפים לפי סדר
    private void fillDeck() {
        for (int i = 1; i <= 4; i++)
            for (int j = 1; j <= 11; j++) {

                switch (i){
                    case 1: this.deck.add(new Card(j , "c"));
                        break;
                    case 2: this.deck.add(new Card(j , "d"));
                        break;
                    case 3: this.deck.add(new Card(j, "s"));
                        break;
                    case 4: this.deck.add(new Card(j , "h"));
                        break;
                }
            }
        this.deck.add(new Card(12,"c"));
        this.deck.add(new Card(13,"c"));
        this.deck.add(new Card(12,"d"));
        this.deck.add(new Card(13,"d"));
    }

    //פעולה ששמה קלף בחפיסה
    public void addCard(Card card) {
        this.deck.add(card);
    }

    //פעולה שמוציאה מהחפיסה
    public Card removeCard() {
        Card c = this.deck.remove();
        return c;
    }

    //פעולה שמערבבת את הכלפים
    public void shuffle() {
        Card[] array = new Card[48];
        Random rnd = new Random();

        //מילוי מערך temp
        for (int i = 0; i < array.length; i++) {
            array[i] = this.deck.remove();
        }

        //ערבוב מערך
        for (int i=0; i<array.length; i++) {
            int randomPosition = rnd.nextInt(array.length);
            Card temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        //לשים את הקלפים בתור
        for (int i = 0; i < array.length; i++) {
            deck.add(array[i]);
        }

    }

    /*To String*/
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String str = "[";
        for (int i = 0; i < 48; i++) {

            Card temp = this.deck.remove();
            str += temp.toString();
            this.deck.add(temp);
        }
        return str;
    }

}

