package com.example.ido.thesoundofdeath;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import 	android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.GestureDetector;

import java.lang.Runnable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import java.util.Locale;
import android.os.Handler;


import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;

import com.example.ido.thesoundofdeath.Exceptions.InvalidPositionException;

public class MainActivity extends AppCompatActivity implements OnTouchListener {
    TextToSpeech Tts;
    NotificationControl notificationControl = new NotificationControl(Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels);
    Button b1;
    EditText ed1;
    MediaPlayer dirSpeaker;
    OnTouchListener listener;
    OnTouchListener touchListener;
   // private  GestureListener gestureDetector = new GestureListener();

    // String DEBUG_TAG = "The_Sound_Of_Death";



    public MainActivity() { }

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    // Use  d to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        notificationControl.acticateNotification(x, y, Tts);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.editText);
        b1 = (Button) findViewById(R.id.button);

        // Text to speech - noy - was changed
        //  +

        //notificate direction by random valus:

        // Press on a button - noy

        final Context context = this;
        dirSpeaker = MediaPlayer.create(context, R.raw.press_me);
        final Button press_me = (Button) this.findViewById(R.id.Press_me);
        press_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (dirSpeaker.isPlaying()) {
                        dirSpeaker.stop();
                        dirSpeaker.release();
                        dirSpeaker = MediaPlayer.create(context, R.raw.press_me);
                    }
                    dirSpeaker.start();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }

                if (dirSpeaker == null)
                    Log.d("on click", "mp is null");
                else
                    dirSpeaker.start();
            }
        });


        Tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    Tts.setLanguage(Locale.UK);
                    /*
                    String toSpeak = notificMsgDir;
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    Tts.speak((CharSequence) (toSpeak), TextToSpeech.QUEUE_FLUSH, null, null);
                    */

                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ed1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                Tts.speak((CharSequence) (toSpeak), TextToSpeech.QUEUE_FLUSH, null, null);

            }
        });

/*
        //notificate direction by random valus:
        notificationControl = new NotificationControl();
        for(int i = 0; i < 10; i ++) {
            float randomPos = notificationControl.getRandomPos();
            try {
                notificationControl.updateDir(randomPos);
            } catch (InvalidPositionException invalidPosException) {
                System.out.println("the pos:" + invalidPosException.getInvalidPos() + "is INVALID");
            } finally {
            }

            final String notificMsgDir = notificationControl.getCurrDirMsg();

            Tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        Tts.setLanguage(Locale.UK);
                        String toSpeak = notificMsgDir;
                        Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                        Tts.speak((CharSequence) (toSpeak), TextToSpeech.QUEUE_FLUSH, null, null);


                    }
                }
            });

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toSpeak = ed1.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    Tts.speak((CharSequence) (toSpeak), TextToSpeech.QUEUE_FLUSH, null, null);

                }
            });
        }
*/


       // touchListener.setOn
    }


    public void onPause() {
        if (Tts != null) {
            Tts.stop();
            Tts.shutdown();
        }
        super.onPause();
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
