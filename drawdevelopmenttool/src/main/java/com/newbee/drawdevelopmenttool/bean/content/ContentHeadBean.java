package com.newbee.drawdevelopmenttool.bean.content;


import android.text.TextUtils;


import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.appliction.MyApplicationFile;
import com.lixiao.build.util.Md5Util;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

import java.io.File;
import java.io.Serializable;

public class ContentHeadBean implements Serializable {
    private long id;
    private String name;
    private long createTime;
    private String filePath;
    private boolean isStar;
    private boolean isNeedDelect;
    private int orientation;
    private String textStr;//文本信息
    private int type;//是哪种类型
    private ContentHeadOtherBean contentHeadOtherBean;

    public ContentHeadBean() {
        contentHeadOtherBean = new ContentHeadOtherBean();
        this.filePath= File.separator;
        id=-1;
        createTime=System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        if(TextUtils.isEmpty(name)){
            name=getNoNameTitle();
        }
        return name;
    }

    public String getNoNameTitle() {
        String noNameStr = "";
        switch (getEnumType()) {
            case NOTE_BOOK:
                noNameStr = BaseApplication.getRsString(R.string.item_note_book);
                break;
            case FILE_FOLDER:
                noNameStr = BaseApplication.getRsString(R.string.item_folder);
                break;
            case SCENE:
                noNameStr = BaseApplication.getRsString(R.string.item_scene);
                break;
            case IMAGE:
                noNameStr = BaseApplication.getRsString(R.string.item_image);
                break;
            case DRAW_BOARD:
                noNameStr =BaseApplication.getRsString(R.string.item_draw_board);
                break;
            case DIARY:
                noNameStr =BaseApplication.getRsString(R.string.item_diary);
                break;
            case PASSWORD_BOOK:
                noNameStr = BaseApplication.getRsString(R.string.item_password_book);
                break;

            default:
                noNameStr = getEnumType() + "";
                break;
        }
        return BaseApplication.getRsString(R.string.no_name_content) + noNameStr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        if(TextUtils.isEmpty(filePath)){
            filePath=File.separator;
        }

        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    public boolean isNeedDelect() {
        return isNeedDelect;
    }

    public void setNeedDelect(boolean needDelect) {
        isNeedDelect = needDelect;
    }



    public ContentHeadOtherBean getContentHeadOtherBean() {
        if(null==contentHeadOtherBean){
            contentHeadOtherBean=new ContentHeadOtherBean();
        }
        return contentHeadOtherBean;
    }

    public void setContentHeadOtherBean(ContentHeadOtherBean contentHeadOtherBean) {


        this.contentHeadOtherBean = contentHeadOtherBean;
        if(null==this.contentHeadOtherBean){
            this.contentHeadOtherBean=new ContentHeadOtherBean();
        }

    }

    public int getBgType() {
        return contentHeadOtherBean.getBgType();
    }

    public void setBgType(int bgType) {
        contentHeadOtherBean.setBgType(bgType);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public void setEnumType(ContentHeadType contentHeadType) {
        this.type = contentHeadType.ordinal();
    }

    public ContentHeadType getEnumType() {
        return ContentHeadType.values()[type];
    }

    public String getEnumTypeName() {
        String typeName="";
        switch (getEnumType()){
            case FILE_FOLDER:
                typeName=BaseApplication.getRsString(R.string.item_folder);
                break;
            case NOTE_BOOK:
                typeName=BaseApplication.getRsString(R.string.item_note_book);
                break;
            case DIARY:
                typeName=BaseApplication.getRsString(R.string.item_diary);
                break;
            case DRAW_BOARD:
                typeName=BaseApplication.getRsString(R.string.item_draw_board);
                break;
            case IMAGE:
                typeName=BaseApplication.getRsString(R.string.item_image);
                break;
            case SCENE:
                typeName=BaseApplication.getRsString(R.string.item_scene);
                break;
            case PASSWORD_BOOK:
                typeName=BaseApplication.getRsString(R.string.item_password_book);
                break;
            case FAST_CREATE:
                typeName=BaseApplication.getRsString(R.string.item_fast_create);
                break;
            default:
                typeName=BaseApplication.getRsString(R.string.item_no_know);
                break;
        }

        return typeName;
    }

//    public boolean isFolder() {
//        if (getEnumType() == ContentHeadType.FILE_FOLDER) {
//            return true;
//        }
//        return false;
//    }


    public int getClickNumb() {
        return contentHeadOtherBean.getClickNumb();
    }

    public void setClickNumb(int clickNumb) {
        contentHeadOtherBean.setClickNumb(clickNumb);
    }

    public String getTextStr() {
        return textStr;
    }

    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }




    public String getFileName(int pager) {
        String md5= Md5Util.MD5(getCreateTime()+""+getId());
        return  md5+"_"+pager+ ".png";
    }

    public String getLocalFilePath(int pager){
        String fileName=getFileName(pager);
        if(!TextUtils.isEmpty(fileName)){
            String filePath= MyApplicationFile.getInstance().getPic_files() + File.separator + fileName;
            return filePath;
        }
        return "";
    }


    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "ContentHeadBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", filePath='" + filePath + '\'' +
                ", isStar=" + isStar +
                ", isNeedDelect=" + isNeedDelect +
                ", orientation=" + orientation +
                ", textStr='" + textStr + '\'' +
                ", type=" + type +
                ", contentHeadOtherBean=" + contentHeadOtherBean +
                '}';
    }
}
