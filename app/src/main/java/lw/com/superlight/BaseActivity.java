package lw.com.superlight;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by lenovo on 2015/10/17.
 */
public class BaseActivity extends Activity {
   //定义枚举型
    protected enum UIType
   {
       UI_TYPE_MAIN,UI_TYPE_FLASHLIGHT,UI_TYPE_WARNINGLIGHT,UI_TYPE_MORSE,
       UI_TYPE_BLUB,UI_TYPE_COLOR,UI_TYPE_POLICE,UI_TYPE_SETTINGS
   }

    //警灯和警示灯的闪烁频率
    protected int mCurrentWarningLightInterval=500;
    protected int mCurrentPoliceLightInterval=300;


    protected ImageView mImageView_Flashlight;
    protected ImageView mImageView_FlashlightController;
    //警告灯
    protected ImageView mImageViewWarningLight1;
    protected ImageView mImageViewWarningLight2;

    //莫尔斯电码控件
    protected EditText mEditTextMorseCode;

    //设置电灯泡图像
    protected ImageView mImageViewBulb;
    //控制照相机
    protected Camera mCamera;
    protected Camera.Parameters mParameter;

    //控制框视图
    protected FrameLayout mUIFlashliht;
    protected LinearLayout mUIMain;
    protected LinearLayout mUIMorseLight;
    protected LinearLayout mUIWarningLight;
    protected FrameLayout mUIBulb;
    protected FrameLayout mUIColorlight;
    protected FrameLayout mUIPolicelight;
    protected LinearLayout mUISettings;

    //设置页面滑动杆
    protected SeekBar mSeekBarWarningLight;
    protected SeekBar mSeekBarPoliceLight;



    //快捷方式按钮
    protected Button mAddshortcut;
    protected Button mRemoveshortcut;
    //设置当前UI类型
    protected UIType mCurrentUIType =UIType.UI_TYPE_FLASHLIGHT;

    protected UIType mLastUIType = UIType.UI_TYPE_FLASHLIGHT;

    //当前亮度
    protected int mDefaultScreenBringhtness;

    //退出计数
    protected int mFinshCount=0;
    //保存设置
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //页面初始化
        mUIFlashliht= (FrameLayout) findViewById(R.id.framelayout_flashlight);
        mUIMain= (LinearLayout) findViewById(R.id.linearlayout_main);
        mUIWarningLight=(LinearLayout) findViewById(R.id.linearlayout_warning_light);
        mUIMorseLight= (LinearLayout) findViewById(R.id.linearlayout_morse);
        mUIBulb= (FrameLayout) findViewById(R.id.framelayout_bulb);
        mUIColorlight= (FrameLayout) findViewById(R.id.framelayout_colorlight);
        mUIPolicelight=(FrameLayout) findViewById(R.id.framelayout_policelight);
        mUISettings= (LinearLayout) findViewById(R.id.linearlayout_settings);
        //控件初始化
        mImageView_Flashlight= (ImageView) findViewById(R.id.ig_flashlight);
        mImageView_FlashlightController= (ImageView) findViewById(R.id.ig_flashlight_controller);
        mImageViewWarningLight1= (ImageView) findViewById(R.id.ig_warning_linght1);
        mImageViewWarningLight2= (ImageView) findViewById(R.id.ig_warning_linght2);
        mEditTextMorseCode= (EditText) findViewById(R.id.edtext_morse_code);
        mImageViewBulb= (ImageView) findViewById(R.id.ig_bulb);
        mSeekBarWarningLight= (SeekBar) findViewById(R.id.seekbar_warning_light);
        mSeekBarPoliceLight= (SeekBar) findViewById(R.id.seekbar_police_light);
//        mAddshortcut= (Button) findViewById(R.id.button_add_shortcut);
//        mRemoveshortcut= (Button) findViewById(R.id.button_remove_shortcut);

        //保存设置
        mSharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        //获取默认亮度
        mDefaultScreenBringhtness=getNowScreenBrightness(this);

        //滚动条初始值
        mSeekBarPoliceLight.setProgress(mCurrentPoliceLightInterval-50);
        mSeekBarWarningLight.setProgress(mCurrentWarningLightInterval-100);

        mCurrentWarningLightInterval=mSharedPreferences.getInt("waring_light_interval",mCurrentWarningLightInterval);
        mCurrentPoliceLightInterval=mSharedPreferences.getInt("police_light_interval",mCurrentPoliceLightInterval);
    }

/***
 *隐藏视图
 */
    protected void hideAllUi(){
        mUIMain.setVisibility(View.GONE);
        mUIFlashliht.setVisibility(View.GONE);
        mUIWarningLight.setVisibility(View.GONE);
        mUIMorseLight.setVisibility(View.GONE);
        mUIBulb.setVisibility(View.GONE);
        mUIColorlight.setVisibility(View.GONE);
        mUIPolicelight.setVisibility(View.GONE);
        mUISettings.setVisibility(View.GONE);
    }

    protected void screenBrightness(Activity activity, int value){
        WindowManager.LayoutParams layoutParams=getWindow().getAttributes();
        layoutParams.screenBrightness=value;
        activity.getWindow().setAttributes(layoutParams);
    }

    protected int getNowScreenBrightness(Activity activity){
        int value = 0;
        ContentResolver cr = activity.getContentResolver();

        try {
            value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFinshCount=0;
        return super.dispatchTouchEvent(ev);
    }

    //重写结束方法
    @Override
    public void finish() {
        mFinshCount++;
        if (mFinshCount==1) {Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();}
        else if (mFinshCount==2) {finish(); super.finish();}
    }
}
