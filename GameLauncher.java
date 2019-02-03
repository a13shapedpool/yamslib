package com.example.yamslib;

import com.example.yamslib.entity.Column;
import com.example.yamslib.entity.Dice;
import com.example.yamslib.entity.Player;
import com.example.yamslib.entity.Row;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;


public class GameLauncher {

    public static int throwNumber;
    public static List<Dice> diceList = new ArrayList<>(5);
    public static List<Player> playerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        generateGame();
        round(playerList.get(0));

    }


    private static void generateGame() throws IOException {

        System.out.println("Welcome to Yam's. \n -------- \n");

        generatePlayers(playerList);
        generateColumns();
        generateDices();

        System.out.println("\n    ------ \nGAME GENERATED\n    ------\n\n");

    }

    private static void generatePlayers(List<Player> playerList) throws IOException {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        // Number of players
        System.out.println("How many players ?");
        int playersNumber = Integer.valueOf(reader.readLine());
        System.out.format("%d players mode launched \n \n", playersNumber);

        // Player names
        System.out.println("Please enter each player's name.");

        for (int i = 0; i < playersNumber; i++) {
            System.out.format("Player %d name :\n", i);
            String name = reader.readLine();
            playerList.add(new Player(name, i));
        }

        System.out.println("List of players :");
        for (int i = 0; i < playersNumber; i++) {
            System.out.format("Player %d : " + playerList.get(i).getPlayerName() + "\n \n", i);
        }

    }

    private static void generateColumns() throws IOException {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        // Number of columns
        System.out.println("How many columns?");
        int columnsNumber = Integer.valueOf(reader.readLine());
        System.out.format("%d columns generated for each player \n \n", columnsNumber);

        List<Integer> columnListType = new ArrayList<>(columnsNumber);

        // Column types
        for (int i = 0; i < columnsNumber; i++) {
            System.out.format("What type of column should Col %d be ? \n", i);
            System.out.println("1 : UPWARDS ; 2 : DOWNWARDS ; 3 : FREE ; 4 : ONESHOT");
            int columnType = Integer.valueOf(reader.readLine());
            columnListType.add(columnType);
        }

        // Creating the columns for each player
        for (int i = 0; i < playerList.size(); i++) {
            List<Column> columnList = new ArrayList<>(columnsNumber);
            for (int j = 0; j < columnsNumber; j++) {
                columnList.add(new Column(j));
            }
            playerList.get(i).setScoreboard(columnList);
        }
    }

    private static List<Dice> generateDices() {
        throwNumber = 1;
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

        return diceList;
    }

    private static void round(Player player) throws IOException{

        int[] values;
        int[] throwRes;
        EnumMap<Row, Integer> possibilities;


        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        diceList = generateDices();
        while (throwNumber <= 3) {
            Throw diceThrow = new Throw();
            diceThrow.requestThrow(diceList);
            values = diceThrow.analyseThrow(diceList);
            throwRes = diceThrow.analyseThrowCount(values);
            possibilities = diceThrow.checkPossibilities(throwRes);

            if (throwNumber < 3){
                System.out.println("Do you want to score a row ? (y/n) : ");
                String ans = reader.readLine();

                switch (ans) {
                    case "n":
                        System.out.println("Do you want to save some dices ? (Numbers without spacing)");
                        String reroll = reader.readLine();

                        if (!reroll.equals("0")) {
                            for (int i = 0; i < reroll.length(); i++) {
                                int nb = reroll.charAt(i);
                                //TODO Check the -49 thingy
                                diceList.get(nb - 49).saveDice();
                            }
                        }


                        System.out.format("\nRerolling other dices...\n");
                        throwNumber += 1;

                    case "y":
                        System.out.println("Which column would you like to save the throw in ?");
                        int columnSave = Integer.valueOf(reader.readLine());
                        System.out.println("Which row would you like to save the throw in ?");
                        int rowSave = Integer.valueOf(reader.readLine());

                        player.saveThrow(columnSave, possibilities.);
                        break;
                }

            }else{
                System.out.println("No more throws available");
                break;
            }
        }

    }
}