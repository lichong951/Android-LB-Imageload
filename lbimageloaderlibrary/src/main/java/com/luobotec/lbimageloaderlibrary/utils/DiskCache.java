package com.luobotec.lbimageloaderlibrary.utils;

import java.io.File;

/**
 * @author lichong_951@163.com
 * @version V1.0
 * @Description: TODO
 * @date 2017/4/7  14:59
 * @powered by lichong
 */

public interface DiskCache {

    File getDirectory();

    File get(String imageUri);
}
