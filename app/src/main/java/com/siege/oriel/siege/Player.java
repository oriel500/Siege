package com.siege.oriel.siege;

public class Player {

    private String name; //שם שחקן
    private Siege siege; //המבצר שברשותו

    public Player(String name, Siege s) {
        this.name = name;
        this.siege = s;
    }

    //gets and sets
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Siege getSiege() {
        return siege;
    }

    public void setSiege(Siege siege) {
        this.siege = siege;
    }

    /*To String*/
    @Override
    public String toString() {
        return this.name;
    }

}

