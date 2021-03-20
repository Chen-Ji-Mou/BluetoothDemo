package com.example.bluetoothdemo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ECGUtil {

    private Timer timer;
    private TimerTask timerTask;
    /**
     * 模拟源源不断的数据源
     */
    public void showWaveData(final ECGChart ecgChart, final int data){
//        timer = new Timer();
//        timerTask = new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        };
//        timer.schedule(timerTask,500);
        ecgChart.showLine((float) data);
    }

    /**
     * 停止绘制波形
     */
    public void stop(){
        if(timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        if(null != timerTask) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}
