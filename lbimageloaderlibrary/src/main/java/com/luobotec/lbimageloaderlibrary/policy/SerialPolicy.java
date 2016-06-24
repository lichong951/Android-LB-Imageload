package com.luobotec.lbimageloaderlibrary.policy;

import com.luobotec.lbimageloaderlibrary.BitmapRequest;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 按照添加到队列号顺序来执行
 * @date 16/6/24  09:00
 * @powered by 北京萝卜科技有限公司
 */
public class SerialPolicy implements LoadPolicy {
    @Override
    public int compare(BitmapRequest request1, BitmapRequest request2) {
        //按照添加到队列号顺序来执行
        return request1.serialNum-request2.serialNum;
    }
}
