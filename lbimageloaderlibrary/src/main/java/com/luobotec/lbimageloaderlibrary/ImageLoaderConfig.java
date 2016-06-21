package com.luobotec.lbimageloaderlibrary;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 配置清单
 * @date 16/6/21  08:52
 * @powered by 北京萝卜科技有限公司
 */
public class ImageLoaderConfig {
    //
    BitmapCache bitmapCache = new MemoryCache();

    DisplayConfig displayConfig = new DisplayConfig();

    LoadPolicy loadPolicy = new SerialPolicy();

    int threadCount = Runtime.getRuntime().availableProcessors() + 1;

    private ImageLoaderConfig() {

    }

    public static class Builder {
        BitmapCache bitmapCache = new MemoryCache();

        DisplayConfig displayConfig = new DisplayConfig();

        LoadPolicy loadPolicy = new SerialPolicy();


        int threadCount = Runtime.getRuntime().availableProcessors() + 1;

        public Builder setThreadCount(int count) {
            threadCount = Math.max(1, count);
            return this;
        }

        public Builder setCache(BitmapCache cache) {
            bitmapCache = cache;
            return this;
        }

        public Builder setLoadingPlaceHolder(int resId) {
            displayConfig.loadingResId = resId;
            return this;

        }


        public Builder setNotFoundPlaceholder(int resId) {
            displayConfig.failedResId = resId;
            return this;

        }

        public Builder setLoadPolicy(LoadPolicy policy) {
            if (policy != null) {
                loadPolicy = policy;
            }
            return this;

        }

        void applyConfig(ImageLoaderConfig config){
            config.bitmapCache=this.bitmapCache;
            config.displayConfig=this.displayConfig;
            config.loadPolicy=this.loadPolicy;
            config.threadCount=this.threadCount;
        }

        public ImageLoaderConfig create(){
            ImageLoaderConfig config=new ImageLoaderConfig();
            applyConfig(config);
            return config;
        }

    }

}
