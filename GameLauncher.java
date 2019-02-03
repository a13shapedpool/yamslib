package com.example.yamslib;

import com.example.yamslib.entity.Column;
import com.example.yamslib.entity.Dice;
import com.example.yamslib.entity.Player;
import com.example.yamslib.entity.Row;
import com.example.yamslib.entity.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class GameLauncher {

    public static int throwNumber = 0;
    public static List<Dice> diceList = new ArrayList<Dice>(5);


    public static void main(String[] args) throws IOException {

        generateGame();

    }


    public static void generateGame() throws IOException {

        System.out.println("Welcome to Yam's. \n -------- \n");

        generatePlayers();
        generateColumns();
        generateDices();

        System.out.println("\n ------- GAME GENERATED ------- \n");

        Throw throw1 = new Throw();
        throw1.requestThrow(diceList);

    }

    public static void generatePlayers() throws IOException{
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        // Number of players
        System.out.println("How many players ?");
        int playersNumber = Integer.valueOf(reader.readLine());
        System.out.format("%d players mode launched \n \n", playersNumber);

        List<Player> playerList = new ArrayList<>(playersNumber);

        // Player names
        System.out.println("Please enter each player's name.");

        for (int i = 0; i < playersNumber ; i++) {
            System.out.format("Player %d name :\n", i);
            String name = reader.readLine();
            playerList.add(new Player(name, i));
        }

        System.out.println("List of players :");
        for (int i = 0; i < playersNumber ; i++) {
            System.out.format("Player %d : " + playerList.get(i).getPlayerName() +"\n \n", i);
        }

    }

    public static void generateColumns() throws IOException {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        // Number of columns
        System.out.println("How many columns?");
        int columnsNumber = Integer.valueOf(reader.readLine());
        System.out.format("%d columns generated for each player \n \n", columnsNumber);

        List<Column> columnList = new ArrayList<>(columnsNumber);

        // Column types
        for (int i = 0 ; i < columnsNumber ; i++){
            System.out.format("What type of column should Col %d be ? \n", i);
            System.out.println("1 : UPWARDS ; 2 : DOWNWARDS ; 3 : FREE ; 4 : ONESHOT");
            int columnType = Integer.valueOf(reader.readLine());
            columnList.add(new Column(columnType));
        }
    }


        public static void generateDices() {
            Dice dice1 = new Dice(1);
            Dice dice2 = new Dice(2);
            Dice dice3 = new Dice(3);
            Dice dice4 = new Dice(4);
            Dice dice5 = new Dice(5);

            diceList.add(dice1);
            diceList.add(dice2);
            diceList.add(dice3);
            diceList.add(dice4);
            diceList.add(dice5);
        }

    }