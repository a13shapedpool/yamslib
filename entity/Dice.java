package com.example.yamslib.entity;
import java.util.Random;

public class Dice {

    private int number;
    private int value;
    private boolean relaunch;

    public Dice (int number) {
        this.number = number;
        this.value = 1;
        this.relaunch = true;
    }

    public void resetDice(){
        this.relaunch = true;
    }

    public int getValue(){
        return this.value;
    }

    public int getNumber(){
        return this.number;
    }

    public boolean getRelaunch(){
        return this.relaunch; }

    public void saveDice() {
        this.relaunch = false;
    }

    public void rollDice(){
        Random r = new Random();
        this.value = r.nextInt(6) + 1;
    }
}
