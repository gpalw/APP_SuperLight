package lw.com.superlight;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;

import lw.com.superlight.widget.HideTextView;

/**
 * Created by lenovo on 2015/10/18.
 */
public class Bulb extends Morse{

    protected boolean mBulbCrossFadeFlag;
    protected TransitionDrawable mDraw;
    protected HideTextView mHideText_Bulb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDraw= (TransitionDrawable) mImageViewBulb.getDrawable();
        mHideText_Bulb= (HideTextView) findViewById(R.id.tv_hide_bulb);
    }

    public void onClick_Bulb(View view){
        if (!mBulbCrossFadeFlag)
        {
            mDraw.startTransition(500);
            mBulbCrossFadeFlag=true;
            screenBrightness(this,1);
        }
        else {
            mDraw.reverseTransition(500);
            mBulbCrossFadeFlag=false;
            screenBrightness(this,mDefaultScreenBringhtness);
        }
    }
}
