package com.newbee.drawdevelopmenttool.draw.base;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 10:01
 */
public enum BaseDrawViewFunctionType {
    SET_COLOR,//设置颜色
    SELECT_COLOR,//选择颜色
    SET_SIZE,//设置画笔的尺寸
    SET_PEN_NUMB,//设置画笔的数量
    CHANGE_DOTTED_LINE,//切换虚实线
    ADD_IMG,//添加图片
    RESET_DRAW_IMG,//重设画画的图片，前面所有的画笔全部清空，并且这张图片为全屏
    FACE_RS,//表情，资源画出来
    SET_BACKGOUND,//设置背景
    TAKE_PHOTO,//拍照
    ADD_TEXT,//添加文字
    UNDO,//取消操作
    REDO,//返回操作
    CLEAR,//清空
    SAVE,//保存
    USER,//用户
    SETTING,//设置
    SEND_EMAIL,//发送邮件
    SCAN_RQ,//扫码
    TO_MAIN,//去主页
    QUIE,//退出
    LAST_PAGER,//上一页
    NEXT_PAGER,//下一页
    PAGER_ADD,//加页
    TO_FIRST_PAGER,//去首页
    TO_BOTTOM_PAGER,//去最后一页
    DRAW_LINE_SELECT,//画线选择
    CAN_NOT_DRAW,//禁止画画
    PREVIEW,//预览
    PUSH_OUT,//导出
    PULL_IN,//导入
    SELECT_MOVE,//选择移动
    SELECT_PEN,//选择笔
    SELECT_ERASER,//橡皮擦
    SELECT_TABLE,//选择表格
    SHOW_WEB,//显示网页
    SELECT_SHAPE,//选择图形
    SELECT_WORD,//选择文档
    SELECT_MORE,//选择更多
    SELECT_CLEAR,//选择清空
    SELECT_SMALL_PIC,//选择小图片
    OPEN_FILE,//打开文件
}
