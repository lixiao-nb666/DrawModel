package com.newbee.drawdevelopmenttool.bean.content;

import android.text.TextUtils;

import com.lixiao.build.mybase.appliction.BaseApplication;
import com.newbee.drawdevelopmenttool.R;


import java.util.ArrayList;
import java.util.List;

public enum  ContentHeadRsType {
    
    ADD(BaseApplication.getRsString(R.string.content_head_add), R.drawable.content_head_add),//添加
    RETRUN(BaseApplication.getRsString(R.string.content_head_retrun),R.drawable.content_head_retrun),//返回的
    FLIE_FOLDER(BaseApplication.getRsString(R.string.content_head_file_folder),R.drawable.content_head_file_folder),//返回的
    NOTE_BOOK_WHITE(BaseApplication.getRsString(R.string.content_head_note_book),R.drawable.content_head_note_book_white),//笔记本白色
    NOTE_BOOK_BLACK(BaseApplication.getRsString(R.string.content_head_note_book),R.drawable.content_head_note_book_black),//笔记本黑色
    NOTE_BOOK_GRAY(BaseApplication.getRsString(R.string.content_head_note_book),R.drawable.content_head_note_book_gray),//笔记本灰色
    DIARY_1(BaseApplication.getRsString(R.string.content_head_diary),R.drawable.content_head_diary_1),//日记样式1
    DIARY_2(BaseApplication.getRsString(R.string.content_head_diary),R.drawable.content_head_diary_2),//日记样式2
    DIARY_3(BaseApplication.getRsString(R.string.content_head_diary),R.drawable.content_head_diary_3),//日记样式3
    DIARY_4(BaseApplication.getRsString(R.string.content_head_diary),R.drawable.content_head_diary_4),//日记样式4
    DRAW_BOARD_1(BaseApplication.getRsString(R.string.content_head_draw_board),R.drawable.content_head_draw_board_1),//画板样式1
    DRAW_BOARD_2(BaseApplication.getRsString(R.string.content_head_draw_board),R.drawable.content_head_draw_board_2),//画板样式2
    DRAW_BOARD_3(BaseApplication.getRsString(R.string.content_head_draw_board),R.drawable.content_head_draw_board_3),//画板样式3
    DRAW_BOARD_4(BaseApplication.getRsString(R.string.content_head_draw_board),R.drawable.content_head_draw_board_4),//画板样式4
    SCENE_ALL(BaseApplication.getRsString(R.string.content_head_scene),R.drawable.content_head_scene_all),//通用场景
//    SCENE_foot_ball(BaseApplication.getRsString(R.string.content_head_scene),R.drawable.content_head_scene_football),//足球场景
    SCENE_basket_ball(BaseApplication.getRsString(R.string.content_head_scene),R.drawable.content_head_scene_basketball),//篮球场景
    IMAGE_1(BaseApplication.getRsString(R.string.content_head_image),R.drawable.content_head_image1),//图片样式1
    IMAGE_2(BaseApplication.getRsString(R.string.content_head_image),R.drawable.content_head_image2),//图片样式2
    IMAGE_3(BaseApplication.getRsString(R.string.content_head_image),R.drawable.content_head_image3),//图片样式3
    IMAGE_4(BaseApplication.getRsString(R.string.content_head_image),R.drawable.content_head_image4),//图片样式4
    PASS_WORD_BOOK_1(BaseApplication.getRsString(R.string.content_head_password_book),R.drawable.content_head_password1),//密码本1
    PASS_WORD_BOOK_2(BaseApplication.getRsString(R.string.content_head_password_book),R.drawable.content_head_password2),//密码本2
    PASS_WORD_BOOK_3(BaseApplication.getRsString(R.string.content_head_password_book),R.drawable.content_head_password3),//密码本3
    ;
    private String str;
    private int rsId;
    private ContentHeadRsType(String str,int rsId){
        this.str=str;
        this.rsId=rsId;
    }

    public String getStr() {
        return str;
    }

    public int getRsId() {
        return rsId;
    }



    public static int getRs(ContentHeadType headType, int ordinal){
       try {
           String str="";
           switch (headType){
               case FILE_FOLDER:
                    str=BaseApplication.getRsString(R.string.content_head_file_folder);
                   break;
               case NOTE_BOOK:
                   str=BaseApplication.getRsString(R.string.content_head_note_book);
                   break;
               case PASSWORD_BOOK:
                   str=BaseApplication.getRsString(R.string.content_head_password_book);
                   break;
               case SCENE:
                   str=BaseApplication.getRsString(R.string.content_head_scene);
                   break;
               case IMAGE:
                   str=BaseApplication.getRsString(R.string.content_head_image);
                   break;
               case DRAW_BOARD:
                   str=BaseApplication.getRsString(R.string.content_head_draw_board);
                   break;
               case DIARY:
                   str=BaseApplication.getRsString(R.string.content_head_diary);
                   break;
           }
           if(!TextUtils.isEmpty(str)){
               int i=0;
               int canUseRsId=0;
               for(ContentHeadRsType contentHeadRsType: ContentHeadRsType.values()){
                   if(contentHeadRsType.getStr().equals(str)){
                       canUseRsId=contentHeadRsType.getRsId();
                        if(i==ordinal){
                            return canUseRsId;
                        }
                        i++;
                   }
               }
               if(canUseRsId!=0){
                   return canUseRsId;
               }
           }

       }catch (Exception e){}


        return R.drawable.content_head_not_find;
    }



    public static List<Integer> getRsTypeList(ContentHeadType headType){
        List<Integer> rsTypeList=new ArrayList<>();
        try {

            String str="";
            switch (headType){
                case FILE_FOLDER:
                    str=BaseApplication.getRsString(R.string.content_head_file_folder);
                    break;
                case NOTE_BOOK:
                    str=BaseApplication.getRsString(R.string.content_head_note_book);
                    break;
                case PASSWORD_BOOK:
                    str=BaseApplication.getRsString(R.string.content_head_password_book);
                    break;
                case SCENE:
                    str=BaseApplication.getRsString(R.string.content_head_scene);
                    break;
                case IMAGE:
                    str=BaseApplication.getRsString(R.string.content_head_image);
                    break;
                case DRAW_BOARD:
                    str=BaseApplication.getRsString(R.string.content_head_draw_board);
                    break;
                case DIARY:
                    str=BaseApplication.getRsString(R.string.content_head_diary);
                    break;
            }
            if(!TextUtils.isEmpty(str)){
                int i=0;

                for(ContentHeadRsType contentHeadRsType: ContentHeadRsType.values()){
                    if(contentHeadRsType.getStr().equals(str)){
                        rsTypeList.add(i);
                        i++;
                    }
                }

            }

        }catch (Exception e){}


        return rsTypeList;
    }


}
