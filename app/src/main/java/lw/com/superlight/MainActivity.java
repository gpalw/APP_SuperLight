package lw.com.superlight;

import android.graphics.Color;
import android.view.View;

public class MainActivity extends Settings {
public void onClick_ToFlashlight(View view){
    hideAllUi();
    mUIFlashliht.setVisibility(View.VISIBLE);
    mLastUIType=UIType.UI_TYPE_FLASHLIGHT;
    mCurrentUIType=UIType.UI_TYPE_FLASHLIGHT;
}
    public void onClick_ToWarningLight(View view){
        hideAllUi();
        mUIWarningLight.setVisibility(View.VISIBLE);
        mLastUIType=UIType.UI_TYPE_WARNINGLIGHT;
        mCurrentUIType=mLastUIType;
        screenBrightness(this,1);
        new WarningLightThread().start();
    }
    public void onClick_ToMorse(View view){
        hideAllUi();
        mUIMorseLight.setVisibility(View.VISIBLE);
        mLastUIType=UIType.UI_TYPE_MORSE;
        mCurrentUIType=mLastUIType;
    }

    public void onClick_ToPolice(View view){
        hideAllUi();
        screenBrightness(this, 1);
        mUIPolicelight.setVisibility(View.VISIBLE);
        mLastUIType=UIType.UI_TYPE_POLICE;
        mCurrentUIType=mLastUIType;
        mHideTextViewPoliceLight.hide();
        new PoliceThread().start();
    }
    public void onClick_ToSetting(View view){
        hideAllUi();
        mUISettings.setVisibility(View.VISIBLE);
        mLastUIType=UIType.UI_TYPE_SETTINGS;
        mCurrentUIType=mLastUIType;
    }
    public void onClick_ToColor(View view){
        hideAllUi();
        mUIColorlight.setVisibility(View.VISIBLE);
        screenBrightness(this,1);
        mHideTextViewColorLight.setTextColor(Color.rgb(255-Color.red(mCurrentColorLight)
        ,255-Color.green(mCurrentColorLight),255-Color.blue(mCurrentColorLight)));
        mLastUIType=UIType.UI_TYPE_COLOR;
        mCurrentUIType=mLastUIType;

    }
    public void onClick_ToBulb(View view){
        hideAllUi();
        mUIBulb.setVisibility(View.VISIBLE);
        mHideText_Bulb.hide();
        mHideText_Bulb.setTextColor(Color.BLACK);
        mLastUIType=UIType.UI_TYPE_BLUB;
        mCurrentUIType=mLastUIType;

    }

    public void onClick_Controller(View view){
        hideAllUi();
        screenBrightness(this, mDefaultScreenBringhtness);
    if (mCurrentUIType != UIType.UI_TYPE_MAIN){
        mUIMain.setVisibility(View.VISIBLE);
        mCurrentUIType=UIType.UI_TYPE_MAIN;
        mWarningLightFlicker=false;

        if (mBulbCrossFadeFlag) mDraw.reverseTransition(0);
            mBulbCrossFadeFlag=false;
            mPoliceState=false;
            mSharedPreferences.edit().putInt("warning_light_interval", mCurrentWarningLightInterval)
                .putInt("police_light_interval", mCurrentPoliceLightInterval).commit();



    }else {
        switch (mLastUIType){
            case UI_TYPE_FLASHLIGHT:
                mUIFlashliht.setVisibility(View.VISIBLE);
                mCurrentUIType =UIType.UI_TYPE_FLASHLIGHT;
                break;
            case UI_TYPE_WARNINGLIGHT:
                mUIWarningLight.setVisibility(View.VISIBLE);
                mCurrentUIType =UIType.UI_TYPE_WARNINGLIGHT;
                screenBrightness(this,1);
                new WarningLightThread().start();
                break;

            case UI_TYPE_MORSE:
                mUIMorseLight.setVisibility(View.VISIBLE);
                mCurrentUIType =UIType.UI_TYPE_MORSE;
                break;

            case UI_TYPE_BLUB:
                mUIBulb.setVisibility(View.VISIBLE);
                mCurrentUIType =UIType.UI_TYPE_BLUB;
                break;

            case UI_TYPE_POLICE:
                mUIPolicelight.setVisibility(View.VISIBLE);
                mCurrentUIType =UIType.UI_TYPE_POLICE;
                new PoliceThread().start();
                break;

            case UI_TYPE_SETTINGS:
                mUISettings.setVisibility(View.VISIBLE);
                mCurrentUIType =UIType.UI_TYPE_SETTINGS;
                break;
        }
    }
}


}
