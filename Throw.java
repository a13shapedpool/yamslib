package com.example.yamslib;


import com.example.yamslib.entity.Dice;
import com.example.yamslib.entity.Row;

import java.util.List;

public class Throw {

    public void requestThrow(List<Dice> diceList) {

        for (Dice d : diceList) {
            if (d.getRelaunch()) {
                d.rollDice();
            }
        }

        GameLauncher.throwNumber += 1;

        System.out.format("Throw number %d ||| Dices : %d %d %d %d %d",
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

        int[] values = getDicesValues(diceList);
        pair(values);


    }

    public void analyseThrowCount(int[] valuesArray){

        int countOne = 0;
        int countTwo = 0;
        int countThree = 0;
        int countFour = 0;
        int countFive = 0;
        int countSix = 0;

        for (int i: valuesArray){
            switch(i){
                case 1: countOne += 1;
                case 2: countTwo += 1;
                case 3: countThree += 1;
                case 4: countFour += 1;
                case 5: countFive += 1;
                case 6: countSix += 1;
            }
        }
    }



    public int pair(int[] valuesArray){

        boolean pairFound = false;
        int pairValue;

        for (int i = 0 ; i < valuesArray.length ; i++){
            for (int j = i + 1 ; j < valuesArray.length ; j++){
                if (valuesArray[i] == (valuesArray[j])){
                    pairFound = true;
                    pairValue = valuesArray[i];
                    System.out.format("\nYay une paire de %d", pairValue);
                    return pairValue;
                }
            }
        }
        return 0;
    }
}
