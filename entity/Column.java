package com.example.yamslib.entity;

import com.example.yamslib.GameLauncher;
import java.util.EnumMap;

public class Column {

    private Type type;
    private boolean isEmpty;
    private EnumMap<Row, Integer> column = new EnumMap<>(Row.class);


    public Column (Type type) {

        /*TODO
        Change 0 into -1 for permitting scoring 0 for a row.
         */
        for(int i = 0; i < Row.values().length ; i++) {
            column.put(Row.values()[i], 0);
        }
        this.type = type;
        this.isEmpty = true;
    }

    public Column (int i){

        /*TODO
        Change 0 into -1 for permitting scoring 0 for a row.
         */
        for(int j = 0; j < Row.values().length ; j++) {
            column.put(Row.values()[j], 0);
        }
        switch (i){
            case 1: this.type = Type.UPWARDS;
            break;
            case 2: this.type = Type.DOWNWARDS;
            break;
            case 3: this.type = Type.FREE;
            break;
            case 4: this.type = Type.ONESHOT;
            break;
        }
        this.isEmpty = true;
    }

    private boolean isEmpty(){
        return this.isEmpty;
    }

    public Type getType() {
        return type;
    }

    public void addPointToRow(Row row, int point){
        this.column.put(row, point);
        this.isEmpty = false;
    }

    public void removePointFromRow(Row row){
        this.column.put(row, 0);
    }

    public void clearColumn(){

        /*TODO
        Change 0 into -1 for permitting scoring 0 for a row.
         */
        for(int i = 0; i < Row.values().length ; i++) {
            column.put(Row.values()[i], 0);
        }
        this.isEmpty = true;
    }

    public void displayColumn(){

        /*TODO
        Do something kinda readable
         */
        switch (getType()){
            case DOWNWARDS:
                System.out.println(getType().toString() +"\t" + this.column);
                break;
            case UPWARDS:
            case ONESHOT:
            case FREE:
                System.out.println(getType().toString() +"\t\t" + this.column);
                break;

        }
    }

    public String getRowName(Row row){
        return row.name();
    }

    public static Row retrieveRow(String s){
        for (Row r: Row.values()){
            if (s.equals(r.toString())){
                return r;
            }
        }
        return null;
    }

    public int getRowValue(int i){
        return (int) column.values().toArray()[i];
    }

    public int getRowValue(Row row){
        return column.get(row);
    }

    public boolean isColumnFull(){
        boolean columnFull = true;
        for (int r = 0 ; r < getColumnLength() ; r++ ) {
            if (this.getRowValue(r) == 0) {
                columnFull = false;
                break;
            }
        }
        return columnFull;
    }

    public int getColumnLength(){
        return this.column.size();
    }

    public boolean checkFillable(Row row) {

        int pos = row.ordinal();
        System.out.println(pos);
        System.out.println(this.type);

        switch (this.getType()) {
            case FREE:
                if (getRowValue(row) == 0) {
                    return true;
                }else{
                    System.out.println("This row is already filled.");
                    return false;
                }

            case ONESHOT:
                if (getRowValue(row) == 0 && GameLauncher.throwNumber == 1) {
                    return true;
                } else{
                    System.out.println("You can fill this column only after the first throw.");
                    return false;
                }

            case UPWARDS:
                if (this.isEmpty() && row == Row.ONE) {
                    return true;
                } else if (getRowValue(row) == 0 && getRowValue(pos - 1) != 0) {
                    System.out.println("OUI");
                    return true;
                } else {
                    System.out.println("You can't fill this row.");
                    return false;
                }

            case DOWNWARDS:
                if (this.isEmpty() && row == Row.YAMS) {
                    return true;
                } else if (getRowValue(row) == 0 && getRowValue(pos + 1) != 0) {
                    return true;
                } else {
                    System.out.println("You can't fill this row.");
                    return false;
                }
        }
        return false;
    }
}