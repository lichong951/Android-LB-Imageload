package com.luobotec.lbimageloaderlibrary.policy;

import com.luobotec.lbimageloaderlibrary.BitmapRequest;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 图片加载策略
 * @date 16/6/21  08:39
 * @powered by 北京萝卜科技有限公司
 */
public interface LoadPolicy {
    /**
    *
     *
    *
    *@author lichong@luobotec.com
    *created at 16/9/23
    */
    public int compare(BitmapRequest request1, BitmapRequest request2);
}
