package com.luobotec.lbimageloaderlibrary;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 内存图片缓存
 * @date 16/6/18  09:11
 * @powered by 北京萝卜科技有限公司
 */
public class MemoryCache implements BitmapCache {
    //图片缓存
    LruCache<String,Bitmap> mMemoryCache;



    public MemoryCache(){
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() + bitmap.getHeight() / 1024;
            }
        };
    }

    public void put(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
    }

    public Bitmap get(String url){
        return mMemoryCache.get(url);
    }

    public String[] keys() {
        return new String[0];
    }

    public void remove(String keysToRemove) {

    }
}
