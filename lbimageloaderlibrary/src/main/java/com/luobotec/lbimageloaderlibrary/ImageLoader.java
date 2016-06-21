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
    BitmapCache mImageCache = new MemoryCache();
    //图片加载中显示的图片id
    int mLoadingImageId;
    //加载失败时显示的图片ID
    int mLoadingFailedImageId;
    //图片加载策略
    LoadPolicy mLoaderPolicy;
    private  ImageLoaderConfig mConfig;

    //线程池，线程数量cpu的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private volatile static ImageLoader mInstance;

    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    public void init(ImageLoaderConfig config) {
        mConfig=config;
        checkConfiguration();

    }

    private void checkConfiguration() {
            if (mConfig == null) {
                throw new IllegalStateException("ERROR_NOT_INIT");
            }
    }

    public void setImageCache(BitmapCache cache) {
        mImageCache = cache;
    }

    public void setLoadingImage(int resId) {
        mLoadingImageId = resId;
    }

    public void setLoadingFailedImage(int resId) {
        mLoadingFailedImageId = resId;
    }

    public void setLoadingPolicy(LoadPolicy policy) {
        mLoaderPolicy = policy;
    }

    public void setThreadCount(int count) {
        mExecutorService.shutdown();
        mExecutorService = null;
        mExecutorService = Executors.newFixedThreadPool(count);
    }

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadRequest(url, imageView);
    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setImageResource(mLoadingImageId);

        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    imageView.setImageResource(mLoadingFailedImageId);
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
