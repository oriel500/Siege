package com.siege.oriel.siege;

public class Card {

    private int num; //המספר על הקלף
    private String shape; //צורה

    /*פעולות בונות*/
    public Card(int num, String shape) {
        this.num = num;
        this.shape = shape;
    }

    public Card(Card k) {
        this.num = k.getNum();
        this.shape = k.getShape();
    }

    /*gets and sets*/
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    /*פעולות*/
    public boolean equalsCard(Card c) {
        return c.getNum() == this.getNum() && c.getShape().equals(this.getShape());
    }

    public boolean equalsNum(int num) {
        return this.getNum() == num;
    }

    /*To String*/
    @Override
    public String toString() {
        String str = "";
        if(this.num != 0)  //אם הקלף הוא לא 0
            str += "(" + this.num + " " + this.shape.toString() + ")";

        return str;
    }

}

