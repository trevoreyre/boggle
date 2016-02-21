package com.team1.cs410.boggle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TwoPlayerGameBasic extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    public TextView text;
    private Activity activity;
    private Game game;
    private final long startTime = 180 * 1000;
    private final long interval = 1*1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_game_basic);
        activity = this;
        game = new Game(this, this);
        LinearLayout gameBoardWrapper = (LinearLayout)findViewById(R.id.game_board_wrapper);
        gameBoardWrapper.addView(game.getBoard());
        text = (TextView)this.findViewById(R.id.timer);
        countDownTimer = new TwoPlayerGameBasic.MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime/1000));
        if(!timerHasStarted){
            countDownTimer.start();
            timerHasStarted = true;
        }
        else
        {
            countDownTimer.cancel();
            timerHasStarted = false;
        }
    }
    //@Override
    public void onClick(View v)
    {
        if(!timerHasStarted){
            countDownTimer.start();
            timerHasStarted = true;
        }
        else
        {
            countDownTimer.cancel();
            timerHasStarted = false;
        }
    }
    public class MyCountDownTimer extends CountDownTimer{
        public MyCountDownTimer(long startTime, long interval){
            super(startTime, interval);
        }

        public void onFinish(){
            text.setText("Times up!");
        }
        public void onTick(long millsUntilFinished){
            if(millsUntilFinished/1000 == 30) {
                text.setTextColor(Color.RED);
            }
            text.setText("" + String.format("%02d",((millsUntilFinished/1000)/60))+":"+String.format("%02d",((millsUntilFinished/1000)%60)));
        }

            /*long remainedSecs = millsUntilFinished/1000;
            text.setText("" + millsUntilFinished/1000);*/

    }

}