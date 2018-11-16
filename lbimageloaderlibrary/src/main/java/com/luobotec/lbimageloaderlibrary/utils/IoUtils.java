package com.luobotec.lbimageloaderlibrary.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lichong_951@163.com
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/19  20:46
 * @powered by lichong
 */

public final class IoUtils {
    public static final int DEFAULT_BUFFER_SIZE=32*1024;

    public static final int DEFAULT_IMAGE_TOTAL_SIZE=500*1024;

    public static final int CONTINUE_LOADING_PERCENTAGE=75;

    private IoUtils(){

    }

    public static boolean copyStream(InputStream is, OutputStream os,CopyListener listener)throws IOException{
        return copyStream(is,os,listener,DEFAULT_BUFFER_SIZE);

    }

    private static boolean copyStream(InputStream is, OutputStream os, CopyListener listener, int bufferSize) throws IOException{
        int current =0;
        int total=is.available();
        if(total<=0) {
            total=DEFAULT_IMAGE_TOTAL_SIZE;
        }
        final byte[] bytes=new byte[bufferSize];
        int count;
        if(shouldStopLoading(listener,current,total)) return false;
        while ((count=is.read(bytes,0,bufferSize))!=-1){
            os.write(bytes,0,count);
            current+=count;
            if(shouldStopLoading(listener,current,total)) {
                return false;
            }
        }
        os.flush();
        return true;
    }

    private static boolean shouldStopLoading(CopyListener listener, int current, int total) {
        if(listener!=null) {
            boolean shouldContinue=listener.onBytesCopied(current,total);
            if(!shouldContinue) {
                if(100*current/total<CONTINUE_LOADING_PERCENTAGE) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void readAndCloseStream(InputStream is){
        final byte[] bytes=new byte[DEFAULT_BUFFER_SIZE];
        try {
            while (is.read(bytes,0,DEFAULT_BUFFER_SIZE)!=-1);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeSilently(is);
        }
    }

    private static void closeSilently(Closeable closeable) {
        if(closeable!=null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static interface CopyListener{
        boolean onBytesCopied(int current,int total );
    }
}
