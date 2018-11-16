package com.luobotec.lbimageloaderlibrary.utils;


import java.io.File;

/**
 * @author lichong_951@163.com
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7  14:55
 * @powered by lichong
 */

public class DiskCacheUtils {
    private DiskCacheUtils() {

    }

    public static File findInCache(String imageUri, DiskCache diskCache) {
        File image = diskCache.get(imageUri);
        return image != null && image.exists() ? image : null;
    }

    public static boolean removeFromCache(String imageUri, DiskCache diskCache) {
        File image = diskCache.get(imageUri);
        return image != null && image.exists() && image.delete();
    }
}
