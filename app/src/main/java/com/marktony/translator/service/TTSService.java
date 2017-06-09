package com.marktony.translator.service;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Administrator on 2017/6/9.
 */

public class TTSService implements TextToSpeech.OnInitListener {
    private TextToSpeech textToSpeech; // TTS对象
    private Context context;

    public TTSService(Context context) {
        this.context = context;
        textToSpeech = new TextToSpeech(context, this); // 参数Context,TextToSpeech.OnInitListener
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void speak(String text) {
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            textToSpeech.setPitch(1.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void stop() {
        textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
        textToSpeech.shutdown(); // 关闭，释放资源
    }
}
