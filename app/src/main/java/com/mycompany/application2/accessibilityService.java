package com.mycompany.application2;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mycompany.application2.accessibilityServiceUtils;
import android.widget.Toast;
import com.mycompany.application2.ui.activity.MainActivity;
import android.content.Intent;

public class accessibilityService extends AccessibilityService {

    private String statusMsg, name, date;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "OnEvent");
        proccessResult(event);
    }
    
    private void proccessResult(AccessibilityEvent event){
        if(event.getClassName() != null && event.getClassName().toString().equals("com.whatsapp.status.playback.StatusPlaybackActivity")) {
                if (event.getSource() != null) {
                    Log.d(TAG, "1");
                    if (accessibilityServiceUtils.isMessageTextVisible(event.getSource(), "message_text")) {
                        //Retrieve screen status text
                        Log.d(TAG, "YES");
                        getScreenText(event.getSource());
                    } else {
                        Log.d(TAG, "No status text here");
                    }
                }
             }else{
                 Log.d(TAG, "NULL");
             }
        }
    

    @Override
    public void onInterrupt() {
        Log.d(TAG, "OnInterrupt");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "OnUnbind");
        
        return super.onUnbind(intent);
    }
    
    

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        
        Log.d(TAG, "started");
    }
    
    private void getScreenText(AccessibilityNodeInfo rootNodeInfo){
        if(accessibilityServiceUtils.isMessageTextVisible(rootNodeInfo, "name")){
            name = accessibilityServiceUtils.retrieveStatusText();
            Log.d(TAG, name);
        }
        
        if(accessibilityServiceUtils.isMessageTextVisible(rootNodeInfo, "date")){
            date = accessibilityServiceUtils.retrieveStatusText();
            Log.d(TAG, date);
        }
        
        if(accessibilityServiceUtils.isMessageTextVisible(rootNodeInfo, "message_text")){
            statusMsg = accessibilityServiceUtils.retrieveStatusText();
            Log.d(TAG, statusMsg);
        }
       
    }
    
    
    public static final String TAG = "accessibilityService";
    
    
    
}
