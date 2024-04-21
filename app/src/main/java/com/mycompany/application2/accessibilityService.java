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
    private boolean enabled = false;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        proccessResult(event);
    }
    
    private void proccessResult(AccessibilityEvent event){
        if(event.getClassName() != null && event.getClassName().toString().equals("com.whatsapp.status.playback.StatusPlaybackActivity")) {
            enabled = true;
            }else{
                enabled = false;
            }
         if(enabled == true){
                AccessibilityNodeInfo rootNodeInfo = event.getSource();
                if (rootNodeInfo != null) {
                    Log.d(TAG, "1");
                    if (accessibilityServiceUtils.isMessageTextVisible(rootNodeInfo, "message_text")) {
                        //Retrieve screen status text
                        //    Toast.makeText(MainActivity.context, "Yes", Toast.LENGTH_SHORT).show();
                        getScreenText(rootNodeInfo);
                    } else {
                        // Toast.makeText(MainActivity.context, "No", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "FALSE");
                    }
                    rootNodeInfo.recycle();
                }else{
                    Log.d(TAG, "NULL");
                }
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
        
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.packageNames = new String[]{"com.whatsapp"}; // Register the package name "WhatsApp"
        info.eventTypes =
            AccessibilityEvent.TYPES_ALL_MASK ; // Register the desired event types
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC; // Set feedback type

        setServiceInfo(info);
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
