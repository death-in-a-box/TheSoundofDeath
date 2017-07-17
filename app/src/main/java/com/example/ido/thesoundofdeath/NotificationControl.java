package com.example.ido.thesoundofdeath;

import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.example.ido.thesoundofdeath.Exceptions.InvalidPositionException;

import java.util.Random;

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
    static String NORTH_EAST = "Obstacle in front of you on the righ!";
    static String NORTH = "Obstacle in front of you!";
    static String NORTH_WEST= "Obstacle in front of you on the left!";
    static String WEST = "Obstacle on the left!";
    static String SOUTH_WEST = "object in south west!";
    static String SOUTH = "object in south!";
    static String SOUTH_EAST = "object in south east!";
    static String EAST = "Obstacle on the right!";


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
        int _1_5 = pixelX / 5;
        int _2_5 = pixelX * 2 / 5;
        int _3_5 = pixelX * 3 / 5;
        int _4_5 = pixelX * 4 / 5;

        if(x >= 0 && x < _1_5)
            currDir = direction.WEST;
        else if (x >= _1_5 && x < _2_5)
            currDir = direction.NORTH_WEST;
        else if (x >= _1_5 && x < _3_5)
            currDir = direction.NORTH;
        else if (x >= _1_5 && x < _4_5)
            currDir = direction.NORTH_EAST;
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

    public void acticateNotification(int x, int y, TextToSpeech tts){
        this.objectMsg = tts;
        this.updateDir(x,y);
        final String notificMsgDir = this.getCurrDirMsg();
        objectMsg.speak((CharSequence) (notificMsgDir), TextToSpeech.QUEUE_FLUSH, null, null);
    }






    //get random degree pos --- just for TEST

    public float getRandomPos(){
        Random rnd = new Random();
        return rnd.nextFloat() * 360;
    }





}
