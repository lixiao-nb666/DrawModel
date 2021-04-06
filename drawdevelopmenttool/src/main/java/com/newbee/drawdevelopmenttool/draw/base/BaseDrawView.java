package com.newbee.drawdevelopmenttool.draw.base;

import android.view.View;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 9:57
 */
public interface BaseDrawView {

    public View getView();

    public boolean canSaveOrOpen();

    public void setListen(BaseDrawViewListen listen);

    public void setDrawType(BaseDrawType drawType, Object... objects);

    public void setFunctionType(BaseDrawViewFunctionType functionType,Object... objects);
}
