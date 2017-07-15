package com.example.ido.thesoundofdeath;

import android.speech.tts.TextToSpeech;

import com.example.ido.thesoundofdeath.Exceptions.InvalidPositionException;

import java.util.Random;

/**
 * Created by noy on 05/07/2017.
 */

public class NotificationControl {
    float pos; // the position in degree
    int x;
    int y;
    int pixelX;
    int pixelY;
    direction currDir;
    TextToSpeech objectMsg;

    //directions
    enum direction {NORTH_EAST, NORTH_WEST, NORTH,SOUTH, SOUTH_EAST, SOUTH_WEST, WEST,EAST}
    static String NORTH_EAST = "Object in north east!";
    static String NORTH_WEST= "Object in north west!";
    static String NORTH = "Object in north!";
    static String SOUTH = "object in south!";
    static String SOUTH_EAST = "object in south east!";
    static String SOUTH_WEST = "object in south west!";
    static String WEST = "object in west!";
    static String EAST = "object in EAST!";


    public NotificationControl(int pixelX, int pixelY){
        this.pixelX = pixelX; this.pixelY = pixelY;
    }

    //degree style
     public void  updateDir(float posDegree)throws InvalidPositionException {
        if((posDegree < 0 ) || (posDegree > 360))
            throw new InvalidPositionException(posDegree);
        else{
            if((posDegree <= 10) || (posDegree >= 350)) //north
                currDir = direction.NORTH;
            else if(posDegree < 80)
                currDir = direction.NORTH_EAST;
            else if( posDegree <= 100)
                currDir = direction.EAST;
            else if(posDegree <= 170)
                currDir = direction.SOUTH_EAST;
            else if(posDegree <= 190)
                currDir = direction.SOUTH;
            else if(posDegree <= 270)
                currDir = direction.SOUTH_WEST;
            else if(posDegree <= 290)
                currDir = direction.WEST;
            else
                currDir = direction.NORTH_WEST;
        }
    }

    // x,y style
    public void  updateDir(float x, float y){
        if(x/pixelX < 0.5)
            currDir = direction.WEST;
        else
            currDir = direction.EAST;
    }



        public String getCurrDirMsg(){
        switch (currDir) {
            case NORTH_EAST:
                return NORTH_EAST;
            case NORTH_WEST:
                return NORTH_WEST;
            case NORTH:
                return NORTH;
            case WEST:
                return WEST;
            case SOUTH_EAST:
                return SOUTH_EAST;
            case SOUTH_WEST:
                return SOUTH_WEST;
            case EAST:
                return EAST;
            case SOUTH:
                return SOUTH;
            default:
                break;
        }
        return"";
    }






    //get random degree pos --- just for TEST

    public float getRandomPos(){
        Random rnd = new Random();
        return rnd.nextFloat() * 360;
    }





}
