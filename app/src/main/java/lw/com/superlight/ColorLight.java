package lw.com.superlight;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import lw.com.superlight.widget.HideTextView;

/**
 * Created by lenovo on 2015/10/18.
 */
public class ColorLight extends Bulb implements ColorPickerDialog.OnColorChangedListener{
    protected int mCurrentColorLight = Color.RED;
    protected HideTextView mHideTextViewColorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHideTextViewColorLight= (HideTextView) findViewById(R.id.tv_hide_color);

    }

    public void onClick_ShowColorPicker(View view){
       // new ColorPickerDialog(ColorLight.this, ColorLight.this, Color.RED).show();
    }

    @Override
    public void colorChanged(int color) {
        mUIColorlight.setBackgroundColor(color);
        mCurrentColorLight=color;
    }
}
