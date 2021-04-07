package com.newbee.drawdevelopmenttool.draw.base;


import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawUserFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawViewFunctionType;

public class DrawFunctionUtil {

    public static int useDrawTypeGetImgRs(BaseDrawType drawType){
        switch (drawType){
            case PEN://钢笔，根据压感，调节粗细，粗细渐变比较慢
                return R.drawable.icon_pen;
            case  PENCIL://铅笔，根据压感，调节颜色度
                return R.drawable.icon_pencil;
            case BALL_PEN://圆珠笔,没有压感
                return R.drawable.icon_ball_pen;
            case   BRUSH_PEN://毛笔，根据压感，调节粗细，粗细渐变比较慢
                return R.drawable.icon_brush_pen;
            case MARK_PEN://，马克笔
                return R.drawable.icon_mark_pen;
            case NITE_WRITER_PEN:
                return R.drawable.icon_nite_writer_pen;
            case PIZHU_PEN:
                return R.drawable.icon_pizhu_pen;
            case   ERASER://橡皮擦
                return R.drawable.icon_eraser;
            case       LINE://直线
                return R.drawable.icon_line;
            case      RECT://矩形
                return R.drawable.icon_rect;
            case      FILL_RECT://实心的矩形
                return R.drawable.icon_fill_rect;
            case       CIRCLE://圆
                return R.drawable.icon_circle;
            case      FILL_CIRCLE://实心圆
                return R.drawable.icon_fill_circle;
            case      OVAL://椭圆
                return R.drawable.icon_ovalu;
            case        FILL_OVAL://实心椭圆
                return R.drawable.icon_fill_ovalu;
            case      PLYGON://多边形
                return R.drawable.icon_triangle;
            case       SPRAYGUN://喷枪
                return R.drawable.icon_spraygun;
            case       PAINTPOT://油漆桶
                return R.drawable.icon_paintpot;
            default:
                return R.drawable.icon_no_know;
        }
    }

    public static int useDrawViewFunctionTypeGetImgRs(BaseDrawViewFunctionType drawFunctionType){
        switch (drawFunctionType){
            case SET_COLOR:
                return R.drawable.icon_set_color;
            case SET_SIZE:
                return R.drawable.icon_set_size;
            case       CHANGE_DOTTED_LINE://切换虚实线
                return R.drawable.icon_dotted_line;
            case       ADD_IMG://添加图片
                return R.drawable.icon_add_img;
            case      RESET_DRAW_IMG://重设画画的图片，前面所有的画笔全部清空，并且这张图片为全屏
                return R.drawable.icon_reset_img;
            case       TAKE_PHOTO://拍照
                return R.drawable.icon_take_photo;
            case       ADD_TEXT://添加文字
                return R.drawable.icon_edit_text;
            case      UNDO://取消操作
                return R.drawable.icon_undo;
            case       REDO://返回操作
                return R.drawable.icon_redo;
            case      CLEAR://清空
                return R.drawable.icon_clear_all;
            case      SAVE://保存
                return R.drawable.icon_save;
            default:
                return R.drawable.icon_no_know;
        }
    }



    public static int useDrawUserFunctionTypeGetImgRs(BaseDrawUserFunctionType drawUserFunctionType){
        switch (drawUserFunctionType){
            case      SET_BACKGOUND://设置背景
                return R.drawable.icon_change_backgound;

            case      USER://用户
                return R.drawable.icon_user;
            case       SETTING://设置
                return R.drawable.launcher_setting;
            case       SEND_EMAIL://发送邮件
                return R.drawable.icon_send_email;
            case SCAN_RQ:
                return R.drawable.icon_scan_rq_share;
            case TO_MAIN:
                return R.drawable.icon_to_main_pager;
            case QUIE:
                return R.drawable.icon_quit;
            case LAST_PAGER:
                return R.drawable.icon_last_pager;
            case NEXT_PAGER:
                return R.drawable.icon_next_pager;
            case PAGER_ADD:
                return R.drawable.icon_pager_add;
            case TO_FIRST_PAGER:
                return R.drawable.icon_to_first;
            case TO_BOTTOM_PAGER:
                return R.drawable.icon_to_bottom;
            case DRAW_LINE_SELECT:
                return R.drawable.icon_draw_line_select;
            case CAN_NOT_DRAW:
                return R.drawable.icon_can_not_draw;
            case PREVIEW:
                return R.drawable.icon_preview;
            case PUSH_OUT:
                return R.drawable.icon_push_out;
            case PULL_IN:
                return R.drawable.icon_pull_in;
            case SELECT_PEN:
                return R.drawable.launcher_pen;
            case SELECT_ERASER:
                return R.drawable.launcher_eraser;
            case SELECT_SHAPE:
                return R.drawable.launcher_shape;
            case SELECT_TABLE:
                return R.drawable.launcher_table;
            case SELECT_WORD:
                return R.drawable.launcher_word;
            case SHOW_WEB:
                return R.drawable.launcher_web;
            case SELECT_MORE:
                return R.drawable.launcher_more;
            case SELECT_CLEAR:
                return R.drawable.launcher_clear;
            case SELECT_SMALL_PIC:
                return R.drawable.launcher_small_pic_select;
            default:
                return R.drawable.icon_no_know;
        }
    }

}
