package com.example.yamslib.entity;

import com.example.yamslib.GameLauncher;

import java.util.EnumMap;

public class Column {

    private Type type;
    private boolean isEmpty;
    private EnumMap<Row, Integer> column = new EnumMap<>(Row.class);


    public Column (Type type) {

        for(int i = 0; i < Row.values().length ; i++) {
            column.put(Row.values()[i], 0);
        }
        this.type = type;
        this.isEmpty = true;
    }

    public Column (int i){
        for(int j = 0; j < Row.values().length ; j++) {
            column.put(Row.values()[j], 0);
        }
        switch (i){
            case 1: this.type = Type.UPWARDS;
            case 2: this.type = Type.DOWNWARDS;
            case 3: this.type = Type.FREE;
            case 4: this.type = Type.ONESHOT;
        }
        this.isEmpty = true;
    }

    private boolean isEmpty(){
        return this.isEmpty;
    }

    public void addPointToRow(Row row, int point){
        this.column.put(row, point);
        this.isEmpty = false;
    }

    public void removePointFromRow(Row row){
        this.column.put(row, 0);
    }

    public void clearColumn(){
        for(int i = 0; i < Row.values().length ; i++) {
            column.put(Row.values()[i], 0);
        }
        this.isEmpty = true;
    }

    public void displayColumn(){
        System.out.println(this.column);
    }

    public Object getRow(int i){
        return column.keySet().toArray()[i];
    }

    public String getRow(Row row){
        return row.name();
    }

    public int getRowValue(int i){
        return column.get(getRow(i));
    }

    public int getRowValue(Row row){
        return column.get(row);
    }

    public int getColumnLength(){
        return this.column.size();
    }

    public boolean checkFillable(Row row) {

        int pos = row.ordinal();

        switch (this.type) {
            case FREE:
                if (getRowValue(row) == 0) {
                    return true;
                }

            case ONESHOT:
                if (getRowValue(row) == 0 && GameLauncher.throwNumber == 1) {
                    return true;
                }

            case UPWARDS:
                System.out.println(getRowValue(pos - 1));

                if (this.isEmpty() && row == Row.ONE) {
                    return true;
                } else if (getRowValue(row) == 0 && getRowValue(pos - 1) != 0) {
                    System.out.println("OUI");
                    return true;
                } else {
                    System.out.println("BEN NON");
                }

            case DOWNWARDS:
                System.out.println(getRowValue(pos + 1));

                if (this.isEmpty() && row == Row.YAMS) {
                    return true;
                } else if (getRowValue(row) == 0 && getRowValue(pos + 1) != 0) {
                    System.out.println("OUI");
                    return true;
                } else {
                    System.out.println("BEN NON");
                }

        }
        return false;
    }
}