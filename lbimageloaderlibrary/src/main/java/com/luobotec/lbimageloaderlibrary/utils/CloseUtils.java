package com.luobotec.lbimageloaderlibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 关闭工具类
 * @date 16/6/20  08:49
 * @powered by 北京萝卜科技有限公司
 */
public final class CloseUtils {
    private CloseUtils(){

    }

    public static void closeQuietly(Closeable closeable){
        if(null != closeable) {
            try {
                closeable.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
