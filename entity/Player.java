package com.example.yamslib.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int order;
    private int score;
    private List<Column> scoreboard;


    public Player (String name, int order) {

        this.name = name;
        this.order = order;
        this.score = 0;
        this.scoreboard = new ArrayList<>();

    }

    public String getPlayerName(){
        return this.name;
    }

    public void setScoreboard(List<Column> columnList){
        for (Column c: columnList){

        }

        this.scoreboard = columnList;
    }

    public List<Column> getScoreboard(){
        return this.scoreboard;
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

    public void saveThrow(int columnNumber, Row row, int point){
        this.scoreboard.get(columnNumber).addPointToRow(row, point);
    }

}
