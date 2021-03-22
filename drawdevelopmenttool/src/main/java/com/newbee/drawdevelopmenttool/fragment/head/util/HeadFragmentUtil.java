package com.newbee.drawdevelopmenttool.fragment.head.util;

import android.text.TextUtils;

import com.lixiao.build.mybase.LG;

import java.io.File;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/19 0019 17:27
 */
public class HeadFragmentUtil {

    public static boolean needRetrunt(String lastFilePath) {
        if(TextUtils.isEmpty(lastFilePath)){
            return false;
        }

        return !File.separator.equals(lastFilePath);
    }
}
