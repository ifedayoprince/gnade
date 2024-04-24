package com.mycompany.application2;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

public class accessibilityServiceUtils {
    
    static String nodeText;
    public static boolean isMessageTextVisible(AccessibilityNodeInfo nodeInfo, String idName, String packageName) {
        AccessibilityNodeInfo rootNode = nodeInfo;
        if (rootNode == null) {
            return false;
        }

        AccessibilityNodeInfo messageText = findViewByID(rootNode, packageName + ":id/" + idName);
        if (messageText != null && messageText.getText() != null) {
            nodeText = messageText.getText().toString();
            return true;
        }

        return false;
    }

    
    
    

    // Helper function to find view by ID
    private static AccessibilityNodeInfo findViewByID(AccessibilityNodeInfo rootNode, String id) {
        if (rootNode == null) {
            return null;
        }

        List<AccessibilityNodeInfo> nodeInfos = rootNode.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfos != null && !nodeInfos.isEmpty()) {
            return nodeInfos.get(0);
        }

        return null;
    }
    
    public static String retrieveStatusText(){
        return nodeText;
    }
}
