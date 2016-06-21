package com.luobotec.lbimageloaderlibrary;

import android.graphics.Bitmap;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 双缓存 获取图片先从内存缓存中获取，如果内存没有缓存该图片，再从SD卡中获取 缓存图片也在内存和sd卡中都缓存一份
 * @date 16/6/18  09:55
 * @powered by 北京萝卜科技有限公司
 */
public class DoubleCache implements BitmapCache {
    MemoryCache mMemoryCache=new MemoryCache();
    DiskCache mDiskCache=new DiskCache();

    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    public Bitmap get(String url){
        Bitmap bitmap=mMemoryCache.get(url);
        if(bitmap==null){
            bitmap=mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和SD卡中
    public void put(String url,Bitmap bmp){
        mMemoryCache.put(url,bmp);
        mDiskCache.put(url,bmp);
    }
}


