package lw.com.superlight;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import lw.com.superlight.widget.HideTextView;

/**
 * Created by lenovo on 2015/10/18.
 */
public class PoliceLight extends ColorLight{
    protected boolean mPoliceState;//警灯闪烁
    protected HideTextView mHideTextViewPoliceLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHideTextViewPoliceLight= (HideTextView) findViewById(R.id.tv_hide_policelight);


    }

    class PoliceThread extends Thread{
        @Override
        public void run() {
            mPoliceState=true;
            while (mPoliceState){

                try {
                    mHandler.sendEmptyMessage(Color.BLUE);
                    sleep(mCurrentPoliceLightInterval);
                    mHandler.sendEmptyMessage(Color.BLACK);
                    sleep(mCurrentPoliceLightInterval);
                    mHandler.sendEmptyMessage(Color.RED);
                    sleep(mCurrentPoliceLightInterval);
                    mHandler.sendEmptyMessage(Color.BLACK);
                    sleep(mCurrentPoliceLightInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int color=msg.what;
            mUIPolicelight.setBackgroundColor(color);
        }
    };
}
