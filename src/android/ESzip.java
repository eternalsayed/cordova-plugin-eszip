package org.apache.cordova.plugin.eszip;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.util.zip.*;
import java.io.*;

import ZipHelper.*;

public class ESzip extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("zipFolder")) {
            String source = args.getString(0);
            String destination = args.getString(1);
            Log.d("Zipper", "Source="+source);
            try{
                zipFolder(source, destination, callbackContext);
                return true;
            }
            catch(IOException e)
            {
                Log.d("Zipper", "Error in execute function!"+e.getMessage());
                return false;
            }
        }
        return false;
    }

    private void zipFolder(String source, String destination, CallbackContext callbackContext) throws JSONException, IOException {
        JSONObject returnObject = new JSONObject();
        returnObject.put("success", false);
        if (source != null && source.length() > 0) {
            ZipHelper zipHelper = new ZipHelper();
            try{
                returnObject = zipHelper.zip(source, destination);
                
                if(returnObject.has("success") && returnObject.getBoolean("success")==true)
                    callbackContext.success(returnObject);
                else
                    callbackContext.error(returnObject);
            }
            catch(IOException e)
            {
                returnObject.put("message", e.getMessage());
                callbackContext.error(returnObject);
            }
            catch(JSONException e)
            {
                returnObject.put("message", e.getMessage());
                callbackContext.error(returnObject);
            }
        } else {
            returnObject.put("message", "Expected one non-empty string argument.");
            callbackContext.error(returnObject);
        }
    }
}
