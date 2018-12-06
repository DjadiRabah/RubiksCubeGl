package fr.max_91490.rubikscubemenu;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerManager{

    private MediaPlayer mediaPlayer;
    boolean fxstate = true;
    boolean backgroundstate = true;

    public MediaPlayerManager(){

    }

    public MediaPlayerManager(Context context, int id){

         mediaPlayer = MediaPlayer.create(context, id);

    }

    public boolean isFxOn(){ return this.fxstate; }

    public boolean isBackgroundOn() {

        return this.backgroundstate;

    }

    public void setFxState(boolean state) {

        this.fxstate = state;

    }

    public void setBackgroundState(boolean state) {

        this.fxstate = state;

    }

    public MediaPlayer getMediaPlayer(){

        return this.mediaPlayer;

    }


}
