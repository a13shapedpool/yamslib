package com.example.yamslib;

import com.example.yamslib.entity.Column;
import com.example.yamslib.entity.Dice;
import com.example.yamslib.entity.Row;
import com.example.yamslib.entity.Type;

import java.util.ArrayList;
import java.util.List;

public class Throw {

    private static final Integer POINT_PAIR = 10;
    private static final Integer POINT_DOUBLEPAIR = 20;
    private static final Integer POINT_THREEOAK = 25;
    private static final Integer POINT_FULL = 30;
    private static final Integer POINT_LITTLEFLUSH = 35;
    private static final Integer POINT_GREATFLUSH = 40;
    private static final Integer POINT_FOUROAK = 45;
    private static final Integer POINT_YAMS = 50;

    public void requestThrow(List<Dice> diceList) {

        for (Dice d : diceList) {
            if (d.getRelaunch()) {
                d.rollDice();
            }
        }

        System.out.format("Throw number %d ||| Dices : %d %d %d %d %d\n",
                GameLauncher.throwNumber,
                diceList.get(0).getValue(),
                diceList.get(1).getValue(),
                diceList.get(2).getValue(),
                diceList.get(3).getValue(),
                diceList.get(4).getValue());

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

    public int[] analyseThrow(List<Dice> diceList) {
        return getDicesValues(diceList);
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

    public Column checkPossibilities(int[] throwRes){

        Column possibilities = new Column(Type.FREE);
        int throwSum = 0;

        // 1, 2, 3, 4, 5, 6
        possibilities.addPointToRow(Row.ONE, throwRes[0]);
        possibilities.addPointToRow(Row.TWO, throwRes[1]*2);
        possibilities.addPointToRow(Row.THREE, throwRes[2]*3);
        possibilities.addPointToRow(Row.FOUR, throwRes[3]*4);
        possibilities.addPointToRow(Row.FIVE, throwRes[4]*5);
        possibilities.addPointToRow(Row.SIX, throwRes[5]*6);
        List<Integer> pairList = new ArrayList<>();

        for (int i = 0; i < throwRes.length ; i++){
            throwSum += throwRes[i]*(i+1);

            if (throwRes[i] >= 2) {
                pairList.add(i);
                possibilities.addPointToRow(Row.PAIR, POINT_PAIR);
            }

            if (throwRes[i] >= 3) {
                possibilities.addPointToRow(Row.THREEOAK, POINT_THREEOAK);
            }

            if (throwRes[i] >= 4) {
                possibilities.addPointToRow(Row.FOUROAK, POINT_FOUROAK);
            }

            if (throwRes[i] >= 5) {
                possibilities.addPointToRow(Row.YAMS, POINT_YAMS);
                possibilities.addPointToRow(Row.FULL, POINT_FULL);
            }
        }

        if (pairList.size() == 2 || possibilities.getRowValue(Row.FOUROAK) == POINT_FOUROAK){
            possibilities.addPointToRow(Row.TWOPAIR, POINT_DOUBLEPAIR);
        }

        if (possibilities.getRowValue(Row.THREEOAK) == POINT_THREEOAK
                && pairList.size() == 2){
            possibilities.addPointToRow(Row.FULL, POINT_FULL);
        }

        if (pairList.size() == 0 && throwSum == 15){
            possibilities.addPointToRow(Row.LITTLEFLUSH, POINT_LITTLEFLUSH);
        }

        if (pairList.size() == 0 && throwSum == 20){
            possibilities.addPointToRow(Row.GREATFLUSH, POINT_GREATFLUSH);
        }

        possibilities.addPointToRow(Row.MINI, throwSum);
        possibilities.addPointToRow(Row.MAXI, throwSum);

        possibilities.displayColumn();
        return possibilities;
    }

}
