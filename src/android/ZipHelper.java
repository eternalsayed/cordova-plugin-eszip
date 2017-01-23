package ZipHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.util.zip.*;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class ZipHelper  
{
    public static JSONObject zip(String source, String destination) throws IOException, JSONException{
        Log.d("Zipper", "zip:In zip function");
        Log.d("Zipper", "zip:Source="+source);
        JSONObject result = new JSONObject();
        try {
            Log.d("Zipper", "zip: trying to zip");
            result = zipFolder(source, destination);
            return result;
        } catch(IOException e2) {
            Log.d("Zipper", "Error occurred:"+e2.getMessage());
            System.err.println(e2);
            
            result.put("success", false);
            result.put("code", "ZIP_FAILED");
            result.put("error", e2.getMessage());
            return result;
        }
    }
    
    public static JSONObject zipFolder(String srcFolder, String destZipFile) throws IOException, JSONException {
    ZipOutputStream zip = null;
    FileOutputStream fileWriter = null;

    JSONObject result = new JSONObject();
    try{
        fileWriter = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileWriter);

        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
        result.put("success", true);
        return result;
    }
    catch(IOException e)
    {
        result.put("success", false);
        result.put("code", "ZIP_FAILED");
        result.put("error", e.getMessage());
        return result;
    }
  }

  static private void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws IOException {

    File folder = new File(srcFile);
    if (folder.isDirectory()) {
      addFolderToZip(path, srcFile, zip);
    } else {
      byte[] buf = new byte[1024];
      int len;
      FileInputStream in = new FileInputStream(srcFile);
      zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
      while ((len = in.read(buf)) > 0) {
        zip.write(buf, 0, len);
      }
    }
  }

  static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws IOException {
    File folder = new File(srcFolder);

    for (String fileName : folder.list()) {
      if (path.equals("")) {
        addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
      }
      else
      {
        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
      }
    }
  }
}