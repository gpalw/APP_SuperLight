package lw.com.superlight;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/10/17.
 */
public class Morse extends WarningLight {

    private final int DOT_TIME =200;//单位：毫秒  点时间
    private final int LINE_TIME=DOT_TIME*3;//线时间
    private final int DOT_LINE_TIME=DOT_TIME;//点线之间的时间

    private final int CHAR_CHAR_TIME=DOT_TIME*3;//字符之间的时间
    private final int WORD_WORD_TIME=DOT_TIME*7;//单词之间的时间

    private String mMorseCode;//存储的莫尔斯电码
    private Map<Character,String> mMorseCodeMap=new HashMap<Character,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMorseCodeMap.put('a',".-");
        mMorseCodeMap.put('b', "-...");
        mMorseCodeMap.put('c', "-.-.");
        mMorseCodeMap.put('d', "-..");
        mMorseCodeMap.put('e', ".");
        mMorseCodeMap.put('f', "..-.");
        mMorseCodeMap.put('g', "--.");
        mMorseCodeMap.put('h', "....");
        mMorseCodeMap.put('i', "..");
        mMorseCodeMap.put('j', ".---");
        mMorseCodeMap.put('k', "-.-");
        mMorseCodeMap.put('l', ".-..");
        mMorseCodeMap.put('m', "--");
        mMorseCodeMap.put('n', "-.");
        mMorseCodeMap.put('o', "---");
        mMorseCodeMap.put('p', ".--.");
        mMorseCodeMap.put('q', "--.-");
        mMorseCodeMap.put('r', ".-.");
        mMorseCodeMap.put('s', "...");
        mMorseCodeMap.put('t', "-");
        mMorseCodeMap.put('u', "..-");
        mMorseCodeMap.put('v', "...-");
        mMorseCodeMap.put('w', ".--");
        mMorseCodeMap.put('x', "-..-");
        mMorseCodeMap.put('y', "-.--");
        mMorseCodeMap.put('z', "--..");

        mMorseCodeMap.put('0', "-----");
        mMorseCodeMap.put('1', ".----");
        mMorseCodeMap.put('2', "..---");
        mMorseCodeMap.put('3', "...--");
        mMorseCodeMap.put('4', "....-");
        mMorseCodeMap.put('5', ".....");
        mMorseCodeMap.put('6', "-....");
        mMorseCodeMap.put('7', "--...");
        mMorseCodeMap.put('8', "---..");
        mMorseCodeMap.put('9', "----.");
    }

    public void onClick_Morse_Button(View view ){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(this, "不好意思，没有闪光灯噢！", Toast.LENGTH_SHORT).show();
            openFlashlight();
            return;
        }

        if (verifyMorseCode()){
            sendSentense(mMorseCode);
        }
    }


    //设置延时
    protected void sleep(long t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //发送点
    private void sendDot(){
        openFlashlight();
        sleep(DOT_TIME);
        closeFlashlight();
    }

    //发送线
    private void sendLine(){
        openFlashlight();
        sleep(LINE_TIME);
        closeFlashlight();
    }

    //发送字符
    private void sendChar(char c){
        String morsCode=mMorseCodeMap.get(c);
        if (morsCode!=null)
        {
            char lastChar=' ';
            for (int i=0;i<morsCode.length();i++){
                char dotLine =morsCode.charAt(i);
                if (dotLine=='.')
                {
                    sendDot();
                }
                if (dotLine=='-')
                {
                    sendLine();
                }
                if (i>0&&i<morsCode.length()-1){
                    if (lastChar=='.'&&dotLine=='-'){
                        sleep(DOT_LINE_TIME);
                    }
                }
                lastChar=dotLine;
            }

        }
    }

    //发送单词
    private void sendWord(String s){
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            sendChar(c);
            if (i<s.length()-1){
                sleep(CHAR_CHAR_TIME);
            }
        }
    }

    //发送句子
    private void sendSentense(String s){
        String[] words=s.split(" +");//正则表达式
        for (int i=0;i<words.length;i++){
            sendWord(words[i]);
            if (i<words.length-1){
                sleep(WORD_WORD_TIME);
            }
        }
        Toast.makeText(Morse.this, "莫尔斯电码已经发送完毕", Toast.LENGTH_SHORT).show();
    }

    private boolean verifyMorseCode(){
        mMorseCode=mEditTextMorseCode.getText().toString().toLowerCase();
        if ("".equals(mMorseCode)){
            Toast.makeText(Morse.this, "请输入莫尔斯电码字符串", Toast.LENGTH_SHORT).show();
            return false;
        }

        for (int i=0;i<mMorseCode.length();i++){
            char c=mMorseCode.charAt(i);
            if (!(c>='a'&&c<='z')&&!(c>='0'&&c<='9')&&c!=' '){
                Toast.makeText(Morse.this, "输入格式不对，只能输入字母、数字和空格", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }
}
