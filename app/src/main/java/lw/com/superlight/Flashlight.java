package lw.com.superlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by lenovo on 2015/10/17.
 */
public class Flashlight extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageView_Flashlight.setTag(false);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        LayoutParams layoutParams = (LayoutParams) mImageView_FlashlightController.getLayoutParams();
        layoutParams.height=point.y * 3/4;
        layoutParams.width=point.x / 3;
        mImageView_FlashlightController.setLayoutParams(layoutParams);
    }
    public void onClick_Flashlight(View view){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(Flashlight.this, "不好意思，没有闪光灯噢！", Toast.LENGTH_SHORT).show();
            openFlashlight();
            return;
        }
        if (((Boolean)mImageView_Flashlight.getTag())==false){
            openFlashlight();
        }else {
            closeFlashlight();
        }

    }
    /**
     * 打开闪光灯
     * */
    protected void openFlashlight(){
        //获取动画对象
        TransitionDrawable drwable= (TransitionDrawable) mImageView_Flashlight.getDrawable();
        drwable.startTransition(50);
        mImageView_Flashlight.setTag(true);
        try {
        mCamera =Camera.open();
        int textureId=0;
            mCamera.setPreviewTexture(new SurfaceTexture(textureId));
            mCamera.startPreview();
            mParameter= mCamera.getParameters();
            mParameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭闪光灯
     * */
    protected void closeFlashlight(){
        TransitionDrawable drwable= (TransitionDrawable) mImageView_Flashlight.getDrawable();
        if (((Boolean)mImageView_Flashlight.getTag()))
        {
            drwable.reverseTransition(50);
            mImageView_Flashlight.setTag(false);
            if (mCamera!=null){
                    mParameter=mCamera.getParameters();
                    mParameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(mParameter);
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera=null;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeFlashlight();
        screenBrightness(this,mDefaultScreenBringhtness);
    }
}
