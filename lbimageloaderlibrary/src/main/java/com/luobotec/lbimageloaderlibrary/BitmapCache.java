package com.luobotec.lbimageloaderlibrary;

import android.graphics.Bitmap;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 图片缓存接口
 * @date 16/6/18  10:19
 * @powered by 北京萝卜科技有限公司
 */
public interface BitmapCache {
    public Bitmap get(String url);
    public void put(String url,Bitmap bmp);
}
