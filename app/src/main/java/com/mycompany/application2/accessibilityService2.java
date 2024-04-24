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

public class accessibilityService2 extends AccessibilityService {

    private String statusMsg, name, date;
    private AccessibilityNodeInfo nodeInfo;
    private final String packageName = "com.whatsapp.w4b";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        proccessResult(event);
    }
    
    private void proccessResult(AccessibilityEvent event){
        if(event.getClassName() != null && event.getClassName().toString().equals("com.whatsapp.status.playback.StatusPlaybackActivity")) {
            nodeInfo = getRootInActiveWindow();
                if (nodeInfo != null) {
                    if (accessibilityServiceUtils.isMessageTextVisible(nodeInfo, "message_text", packageName)) {
                        //Retrieve screen status text
                        getScreenText(nodeInfo);
                    } else {
                        Log.d(TAG, "No whatsapp buisness status text here");
                    }
                }
             }
        }
    

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Whatsapp Business Accessibility Service OnInterrupt");
        nodeInfo.recycle();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Whatsapp Business Accessibility Service OnUnbind");
        nodeInfo.recycle();
        
        return super.onUnbind(intent);
    }
    
    

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        
        Log.d(TAG, "Whatsapp Business Accessibility Service Started");
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
    
    
    public static final String TAG = "accessibilityService2";
    
    
    
}
