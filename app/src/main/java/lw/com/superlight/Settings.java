package lw.com.superlight;

import android.os.Bundle;
import android.widget.SeekBar;

/**
 * Created by lenovo on 2015/10/18.
 */
public class Settings extends PoliceLight implements SeekBar.OnSeekBarChangeListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSeekBarPoliceLight.setOnSeekBarChangeListener(this);
        mSeekBarWarningLight.setOnSeekBarChangeListener(this);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekbar_police_light:
                mCurrentPoliceLightInterval=progress+50;
                break;

            case R.id.seekbar_warning_light:
                mCurrentWarningLightInterval=progress+100;
                break;
        }
    }

    //快捷方式标志
//    private boolean shortcutInScreen() {
//        Cursor cursor = getContentResolver()
//                .query(Uri.parse("content://com.cyanogenmod.trebuchet.settings/favorites"),
//                        null,
//                        "intent like ?",
//                        new String[] { "%component=cn.eoe.superflashlight/.MainActivity%" },
//                        null);
//
//        if (cursor.getCount() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
//
//    public void onClick_Addshortcut(View view){
//        if (!shortcutInScreen()) {
//        Intent intentShortcut=new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
//        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,"超级手电筒");
//        Parcelable icon = Intent.ShortcutIconResource.fromContext(this,R.drawable.icon);
//
//        Intent flashlightIntent = new Intent();
//        flashlightIntent.setClassName("lw.com.superlight.flashlight","lw.com.superlight.MainActivity");
//        flashlightIntent.setAction(Intent.ACTION_MAIN);
//        flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intentShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
//        sendBroadcast(intentShortcut);
//        Toast.makeText(this, "已经把快捷方式放到桌面", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this, "快捷方式已经添加到桌面上，无法再添加快捷方式!", Toast.LENGTH_LONG)
//                    .show();
//        }
//    }
//
//    public void onClick_Removeshortcut(View view) {
//        if (shortcutInScreen()) {
//            Intent unintentShortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
//            unintentShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
//            Intent flashlightIntent = new Intent();
//            flashlightIntent.setClassName("lw.com.superlight.flashlight", "lw.com.superlight.MainActivity");
//            unintentShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
//            flashlightIntent.setAction(Intent.ACTION_MAIN);
//            flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//            sendBroadcast(unintentShortcut);
//        } else {
//            Toast.makeText(this, "没有快捷方式，无法删除", Toast.LENGTH_LONG).show();
//        }
//    }
}
