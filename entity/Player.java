package com.example.yamslib.entity;

public class Player {

    String name;
    int order;
    int score;
    int[] cases;


    public Player (String name, int order) {

        this.name = name;
        this.order = order;
        this.score = 0;
        this.cases = new int[0];

    }

    public String getPlayerName(){
        return this.name;
    }

    public int getOrder(){
        return this.order;
    }

    public int getScore(){
        return this.score;
    }

    public void giveName(String s){
        this.name = s;
    }

    public void updateScore(int point){
        this.score += point;
    }

}
