package com.luobotec.lbimageloaderlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 图片加载类
 * @date 16/6/18  08:50
 * @powered by 北京萝卜科技有限公司
 */
public class ImageLoader {
    //内存缓存
    ImageCache mImageCache=new MemoryCache();
    //线程池，线程数量cpu的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void setImageCache(ImageCache cache){
        mImageCache=cache;
    }

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap=mImageCache.get(url);
        if(bitmap!=null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadRequest(url,imageView);
    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }



    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }
}
