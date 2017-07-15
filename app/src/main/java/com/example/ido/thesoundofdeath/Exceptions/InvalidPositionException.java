package com.example.ido.thesoundofdeath.Exceptions;

/**
 * Created by noy on 05/07/2017.
 */

public class InvalidPositionException extends Exception {
    float invalidPos;
    String msg = invalidPos + "is INVALID positions!";

    public InvalidPositionException(float invalidPos){
        super();
        this.invalidPos = invalidPos;
    }

    public float getInvalidPos() {
        return invalidPos;
    }
}
