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
    private final String packageName = "com.whatsapp";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        proccessResult(event);
    }
    
    private void proccessResult(AccessibilityEvent event){
        if(event.getClassName() != null && event.getClassName().toString().equals("com.whatsapp.status.playback.StatusPlaybackActivity")) {
                if (getRootInActiveWindow() != null) {
                    if (accessibilityServiceUtils.isMessageTextVisible(getRootInActiveWindow(), "message_text" , packageName)) {
                        //Retrieve screen status text
                        getScreenText(getRootInActiveWindow());
                    } else {
                        Log.d(TAG, "No status text here");
                    }
                }
             }
        }
    

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Whatsapp Accessibility Service OnInterrupt");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Whatsapp Accessibility Service OnUnbind");
        
        return super.onUnbind(intent);
    }
    
    

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        
        Log.d(TAG, "Whatsapp Accessibility Service Started");
    }
    
    private void getScreenText(AccessibilityNodeInfo rootNodeInfo){
        if(accessibilityServiceUtils.isMessageTextVisible(rootNodeInfo, "name", packageName)){
            name = accessibilityServiceUtils.retrieveStatusText();
            Log.d(TAG, name);
        }
        
        if(accessibilityServiceUtils.isMessageTextVisible(rootNodeInfo, "date", packageName)){
            date = accessibilityServiceUtils.retrieveStatusText();
            Log.d(TAG, date);
        }
        
        if(accessibilityServiceUtils.isMessageTextVisible(rootNodeInfo, "message_text", packageName)){
            statusMsg = accessibilityServiceUtils.retrieveStatusText();
            Log.d(TAG, statusMsg);
        }
       
    }
    
    
    public static final String TAG = "accessibilityService";
    
    
    
}
