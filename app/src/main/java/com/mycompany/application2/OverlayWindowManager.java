package com.mycompany.application2;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import com.mycompany.application2.R;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

public class OverlayWindowManager {
    private WindowManager windowManager;
    private View overlayView;
    private LinearLayout record_pause;
    private ImageView cancel;
    private boolean isRecording = false;
    private Drawable drawable;
    private Context context;
    private ImageView indicator;

    public OverlayWindowManager(Context context) {
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            overlayView = layoutInflater.inflate(R.layout.overlay_layout, null);
            record_pause = overlayView.findViewById(R.id.linear6);
            cancel = overlayView.findViewById(R.id.cancel);
            indicator = overlayView.findViewById(R.id.indicator);

            WindowManager.LayoutParams params = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
            } else {
                params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
            }

            params.gravity = Gravity.RIGHT| Gravity.BOTTOM;
            
            setClickListener();

            windowManager.addView(overlayView, params);
        }
    }

    public void removeOverlay() {
        if (windowManager != null && overlayView != null) {
            cleanUp();
            windowManager.removeView(overlayView);
        }
    }
    
    private void setClickListener(){
        record_pause.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(isRecording){
                        pause();
                    }else{
                        record();
                    }
                }
            });
            
        cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    cancel();
                }
            });
    }
    
    private void cleanUp(){
        record_pause.setOnClickListener(null);
        cancel.setOnClickListener(null);
        drawable = null;
        record_pause.setBackground(null);
        indicator.setImageResource(0);
        overlayView = null;
        windowManager = null;
    }
    
    private void record(){
        drawable = context.getResources().getDrawable(R.drawable.layout_linear3_design);
        indicator.setImageResource(R.drawable.ic_stop_black);
        record_pause.setBackground(drawable);
        isRecording = true;
        CatchStatusSaver.isReady = true;
    }
    
    private void pause(){
        drawable = context.getResources().getDrawable(R.drawable.layout_linear6_design);
        indicator.setImageResource(R.drawable.ic_brightness_1_black);
        record_pause.setBackground(drawable);
        isRecording = false;
        CatchStatusSaver.isReady = false;
    }
    
    private void cancel(){
        
    }
}

