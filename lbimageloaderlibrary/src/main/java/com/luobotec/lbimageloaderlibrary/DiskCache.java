package com.luobotec.lbimageloaderlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.luobotec.lbimageloaderlibrary.utils.CloseUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: SD卡缓存
 * @date 16/6/18  09:38
 * @powered by 北京萝卜科技有限公司
 */
public class DiskCache implements BitmapCache {
    static String cacheDir= Environment.getExternalStorageDirectory()+"/luobotec/cache/";
    //从缓存中获取图片
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir+url);
    }

    //将图片缓存到内存中
    public void put(String url,Bitmap bmp ){
        FileOutputStream fileOutputStream=null;
        try {
            //TODO 对uel进行md5
            fileOutputStream=new FileOutputStream(cacheDir+url);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }
}
