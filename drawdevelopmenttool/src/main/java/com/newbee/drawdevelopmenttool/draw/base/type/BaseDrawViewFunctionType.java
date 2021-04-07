package com.newbee.drawdevelopmenttool.draw.base.type;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 10:01
 * 畫板的基本功能設置
 */
public enum BaseDrawViewFunctionType {
    SET_COLOR,//设置颜色

    SET_SIZE,//设置画笔的尺寸
    SET_PEN_NUMB,//设置画笔的数量
    CHANGE_DOTTED_LINE,//切换虚实线
    ADD_IMG,//添加图片
    RESET_DRAW_IMG,//重设画画的图片，前面所有的画笔全部清空，并且这张图片为全屏
    FACE_RS,//表情，资源画出来
    TAKE_PHOTO,//拍照
    ADD_TEXT,//添加文字
    UNDO,//取消操作
    REDO,//返回操作
    CLEAR,//清空
    SAVE,//保存
    DRAW_LINE_SELECT,//画线选择
    CAN_NOT_DRAW,//禁止画画
}
