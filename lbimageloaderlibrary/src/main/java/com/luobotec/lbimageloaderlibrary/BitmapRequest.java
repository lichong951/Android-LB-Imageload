package com.luobotec.lbimageloaderlibrary;

import com.luobotec.lbimageloaderlibrary.policy.LoadPolicy;
import com.luobotec.lbimageloaderlibrary.policy.SerialPolicy;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 图片请求下载类
 * @date 16/6/24  08:59
 * @powered by 北京萝卜科技有限公司
 */
public class BitmapRequest extends Thread implements Comparable<BitmapRequest> {
    public int serialNum;

    LoadPolicy mLoadPolicy = new SerialPolicy();


    public void setLoadPolicy(LoadPolicy loadPolicy) {

        mLoadPolicy = loadPolicy;
    }


    @Override
    public int compareTo(BitmapRequest another) {

        return mLoadPolicy.compare(this, another);
    }


}
