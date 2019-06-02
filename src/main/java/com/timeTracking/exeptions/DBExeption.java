package com.timeTracking.exeptions;

public class DBExeption extends RuntimeException {

    public DBExeption(){

    }

    public DBExeption(String message){
        super(message);
    }
}
