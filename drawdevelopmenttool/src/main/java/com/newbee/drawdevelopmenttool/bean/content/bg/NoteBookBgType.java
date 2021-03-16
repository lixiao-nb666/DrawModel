package com.newbee.drawdevelopmenttool.bean.content.bg;


import com.newbee.drawdevelopmenttool.R;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/6 0006 16:17
 */
public enum NoteBookBgType {
    NONE,//空是白线
    LIST,//横格
    GRIL,//田字格
    PINYIN,//拼音格
    TIANZI;//田字格

    public static int getRsId(int  noteBookBgTypeInt){
        try {

            NoteBookBgType noteBookBgType=NoteBookBgType.values()[noteBookBgTypeInt];
            return getRsId(noteBookBgType);
        }catch (Exception e){
            return 0;
        }


    }


    public static int getRsId(NoteBookBgType noteBookBgType){
        if(null==noteBookBgType){
            return 0;
        }
        switch (noteBookBgType){
            case LIST:
                return R.drawable.eink_bg_note_book_list_v;
            case GRIL:
                return R.drawable.eink_bg_note_book_grild_v;
            case PINYIN:
                return R.drawable.eink_bg_note_book_pinyin_v;
            case TIANZI:
                return R.drawable.eink_bg_note_book_tianzi_v;
            case NONE:
            default:
                return R.drawable.eink_bg_note_book_white_v;
        }
    }
}
