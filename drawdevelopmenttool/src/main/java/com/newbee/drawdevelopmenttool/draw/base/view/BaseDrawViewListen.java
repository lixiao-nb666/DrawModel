package com.newbee.drawdevelopmenttool.draw.base.view;

import com.newbee.drawdevelopmenttool.draw.type.OpenFileNeedDoType;
import com.newbee.drawdevelopmenttool.draw.type.SaveBitMapType;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/11/13 0013 10:52
 */
public interface BaseDrawViewListen {

    /**
     * 初始化成功
     */
    public void initOk();

    /**
     * 保存图片的状态回调
     * @param fileName
     * @param isSave
     * @param otherStr
     * @param saveBitMapType
     */
    public void save(String fileName, String filePath, boolean isSave, String otherStr, SaveBitMapType saveBitMapType);

    public void err(String str);

    public void openFile(String filePath, OpenFileNeedDoType openFileNeedDoType);
}
