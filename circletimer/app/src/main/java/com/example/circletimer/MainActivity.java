package com.example.circletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.os.SystemClock.sleep;

public class MainActivity extends Activity {

    private int COUNT_DOWN_INTERVAL = 1000;
    private int study_time=0;
    private int rest_time=0;
    private int count = 50; //초기값 50분
    private TextView countTxt ;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart=(Button)findViewById(R.id.start);
        Button btnStop=(Button)findViewById(R.id.stop);
        Button btnOkay=(Button)findViewById(R.id.okay);
        Button btnOkay2=(Button)findViewById(R.id.okay2);
        EditText editText=(EditText)findViewById(R.id.editText);
        EditText editText2=(EditText)findViewById(R.id.editText2);

        countTxt = (TextView)findViewById(R.id.text);
        countTxt.setText(count+":00");

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=editText.getText().toString(); //분단위로 입력받기
                study_time=Integer.parseInt(str);
                count=study_time;
                if(count<10)
                    countTxt.setText("0"+count+":00"); //초기화면 재설정
                else
                    countTxt.setText(count+":00");
            }
        });
        btnOkay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=editText2.getText().toString(); //분단위로 입력받기
                rest_time=Integer.parseInt(str);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studyTimer();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
            }
        });
    }


    public void studyTimer(){

        if(study_time!=0) //직접 입력 했으면
            count=study_time;//집중모드
        else { //아니면 디폴트값
            count = 50;
            study_time=50;
        }

        countDownTimer = new CountDownTimer(study_time*60*1000, COUNT_DOWN_INTERVAL) {
            int min, sec=59;

            public void onTick(long millisUntilFinished) {
                min=count;
                min--;
                if(min<10) {
                    if(sec<10) {
                        countTxt.setText("0" + String.valueOf(min) + ":0" + String.valueOf(sec));
                    }
                    else{
                        countTxt.setText("0" + String.valueOf(min) + ":" + String.valueOf(sec));
                    }
                }
                else{
                    countTxt.setText(String.valueOf(min) + ":" + String.valueOf(sec));
                }
                sec--;
                if(sec==-1) {
                    count--;
                    sec=59;
                }
            }

            public void onFinish() {
                //countTxt.setText(String.valueOf("study finish ."));
                Uri uri =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
                ringtone.play();
                sleep(3*1000);
                ringtone.stop();
                restTimer();
            }
        }.start();
    }

    public void restTimer(){//쉬는시간
        if(rest_time!=0)
            count=rest_time;
        else {
            count = 10;
            rest_time=10;
        }
        countDownTimer = new CountDownTimer(rest_time*60*1000, COUNT_DOWN_INTERVAL) {
            int min, sec=59;
            public void onTick(long millisUntilFinished) {
                min=count;
                min--;
                if(min<10) {
                    if(sec<10) {
                        countTxt.setText("0" + String.valueOf(min) + ":0" + String.valueOf(sec));
                    }
                    else{
                        countTxt.setText("0" + String.valueOf(min) + ":" + String.valueOf(sec));
                    }
                }
                else{
                    countTxt.setText(String.valueOf(min) + ":" + String.valueOf(sec));
                }
                sec--;
                if(sec==-1) {
                    count--;
                    sec=59;
                }
            }

            public void onFinish() {
                //countTxt.setText(String.valueOf("rest finish ."));
                Uri uri =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
                ringtone.play();
                sleep(3*1000);
                ringtone.stop();
                studyTimer();
            }
        }.start();
    }


}
