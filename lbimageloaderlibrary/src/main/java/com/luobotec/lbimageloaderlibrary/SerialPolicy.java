package com.luobotec.lbimageloaderlibrary;

import com.luobotec.lbimageloaderlibrary.policy.LoadPolicy;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: TODO
 * @date 16/6/21  09:13
 * @powered by 北京萝卜科技有限公司
 */
public class SerialPolicy implements LoadPolicy {
    @Override
    public int compare(BitmapRequest request1, BitmapRequest request2) {
        return 0;
    }
}

