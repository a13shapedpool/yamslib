package com.example.yamslib;

import com.example.yamslib.entity.Dice;
import com.example.yamslib.entity.Row;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Throw {

    public void requestThrow(List<Dice> diceList) {

        for (Dice d : diceList) {
            if (d.getRelaunch()) {
                d.rollDice();
            }
        }

        GameLauncher.throwNumber += 1;

        System.out.format("Throw number %d ||| Dices : %d %d %d %d %d\n",
                GameLauncher.throwNumber,
                diceList.get(0).getValue(),
                diceList.get(1).getValue(),
                diceList.get(2).getValue(),
                diceList.get(3).getValue(),
                diceList.get(4).getValue());

        analyseThrow(diceList);

    }

    public int[] getDicesValues(List<Dice> diceList) {

        int[] valuesArray = new int[5];
        valuesArray[0] = diceList.get(0).getValue();
        valuesArray[1] = diceList.get(1).getValue();
        valuesArray[2] = diceList.get(2).getValue();
        valuesArray[3] = diceList.get(3).getValue();
        valuesArray[4] = diceList.get(4).getValue();
        return valuesArray;
    }

    public void analyseThrow(List<Dice> diceList) {

        int[] throwRes;
        int[] values = getDicesValues(diceList);
        int sum = getDicesSum(values);

        throwRes = analyseThrowCount(values);
        checkPossibilities(throwRes);


    }

    private int getDicesSum(int[] values) {

        int sum = 0;
        for (int i = 0 ; i < values.length ; i++){
            sum += values[i];
        }
        return sum;

    }

    public int[] analyseThrowCount(int[] valuesArray){

        int countOne = 0;
        int countTwo = 0;
        int countThree = 0;
        int countFour = 0;
        int countFive = 0;
        int countSix = 0;
        int throwRes[] = new int[6];

        for (int i = 0 ; i < valuesArray.length ; i++){
            switch(valuesArray[i]){
                case 1: countOne += 1; break;
                case 2: countTwo += 1; break;
                case 3: countThree += 1; break;
                case 4: countFour += 1; break;
                case 5: countFive += 1; break;
                case 6: countSix += 1; break;
            }
        }
        throwRes[0] = countOne;
        throwRes[1] = countTwo;
        throwRes[2] = countThree;
        throwRes[3] = countFour;
        throwRes[4] = countFive;
        throwRes[5] = countSix;
        return throwRes;
    }

    public void checkPossibilities(int[] throwRes){

        EnumMap<Row, List<Integer>> possibilities = new EnumMap<>(Row.class);
        List<Integer> pairList = new ArrayList<>();
        List<Integer> threeOAKList = new ArrayList<>();
        List<Integer> fourOAKList = new ArrayList<>();
        List<Integer> yamsList = new ArrayList<>();
        List<Integer> flushList = new ArrayList<>();
        List<Integer> oneList = new ArrayList<>();
        List<Integer> twoList = new ArrayList<>();
        List<Integer> threeList = new ArrayList<>();
        List<Integer> fourList = new ArrayList<>();
        List<Integer> fiveList = new ArrayList<>();
        List<Integer> sixList = new ArrayList<>();
        int throwSum = 0;

        // 1, 2, 3, 4, 5, 6
        oneList.add(throwRes[0]);
        possibilities.put(Row.ONE, oneList);
        twoList.add(throwRes[1]);
        possibilities.put(Row.TWO, twoList);
        threeList.add(throwRes[2]);
        possibilities.put(Row.THREE, threeList);
        fourList.add(throwRes[3]);
        possibilities.put(Row.FOUR, fourList);
        fiveList.add(throwRes[4]);
        possibilities.put(Row.FIVE, fiveList);
        sixList.add(throwRes[5]);
        possibilities.put(Row.SIX, sixList);


        for (int i = 0 ; i < throwRes.length ; i++){
            throwSum += throwRes[i]*(i+1);

            if (throwRes[i] >= 2) {
                pairList.add(i+1);
                possibilities.put(Row.PAIR, pairList);
            }

            if (throwRes[i] >= 3) {
                threeOAKList.add(i+1);
                possibilities.put(Row.THREEOAK, threeOAKList);
            }

            if (throwRes[i] >= 4) {
                fourOAKList.add(i+1);
                possibilities.put(Row.FOUROAK, fourOAKList);
            }

            if (throwRes[i] >= 5) {
                yamsList.add(i+1);
                possibilities.put(Row.YAMS, yamsList);
            }
        }

        if (pairList.size() == 2){
            possibilities.put(Row.TWOPAIR, pairList);
        }

        if (pairList.size() == 2 && threeOAKList.size() == 1){
            possibilities.put(Row.FULL, pairList);
        }

        if (pairList.size() == 0 && throwSum == 15){
            flushList.add(1);
            possibilities.put(Row.LITTLEFLUSH, flushList);
        }

        if (pairList.size() == 0 && throwSum == 20){
            flushList.add(1);
            possibilities.put(Row.GREATFLUSH, flushList);
        }

        System.out.println(possibilities);
    }



}
