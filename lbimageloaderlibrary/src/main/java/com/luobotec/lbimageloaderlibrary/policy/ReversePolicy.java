package com.luobotec.lbimageloaderlibrary.policy;

import com.luobotec.lbimageloaderlibrary.BitmapRequest;

/**
 * @author lichong@luobotec.com
 * @version V1.0
 * @Description: 逆序加载策略，即从最后加入队列的请求进行加载
 * @date 16/6/24  09:03
 * @powered by 北京萝卜科技有限公司
 */
public class ReversePolicy implements LoadPolicy {
    @Override
    public int compare(BitmapRequest request1, BitmapRequest request2) {
        return request2.serialNum-request1.serialNum;
    }
}
