package com.newbee.drawdevelopmenttool.bean.content.bg;

import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.share.DrawShare;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/4/7 0007 9:20
 */
public class ContentBgUtil {

    public static int getBgRs(ContentHeadBean headBean){
        ContentPagerBean pagerBean=DrawShare.getInstance().getContentPagerBean(headBean.getId());
        int pagerNumb=pagerBean.getShowPagerNumb();
        return getBgRs(headBean,pagerNumb);
    }

    public static int getBgRs(ContentHeadBean headBean,int pagerNumb){
        int headTypeIndex=headBean.getType();
        int bgTypeIndex=getBgIndex(headBean,pagerNumb);
        return getBgRs(headTypeIndex,bgTypeIndex);
    }


    private static int getBgRs(int headTypeIndex,int bgType){
        ContentHeadType headType=ContentHeadType.values()[headTypeIndex];
        switch (headType){
            case NOTE_BOOK:
                return NoteBookBgType.getRsId(bgType);
        }
        return R.drawable.content_head_not_find;
    }

    public static int getBgIndex(ContentHeadBean headBean,int pagerNumb){
        ContentPagerBean pagerBean= DrawShare.getInstance().getContentPagerBean(headBean.getId());
        int bgTypeIndex=0;
        try {
            bgTypeIndex  = pagerBean.getDrawBgMap().get(pagerNumb+"") ;
        }catch (Exception e){
            bgTypeIndex = headBean.getContentHeadOtherBean().getDefDrawBgType();
        }

        return bgTypeIndex;
    }

    public static int getBgTempRs(ContentHeadBean headBean, ContentPagerBean pagerBean){
        int pagerNumb=pagerBean.getShowPagerNumb();
        return getBgTempRs(headBean,pagerNumb);
    }

    public static int getBgTempRs(ContentHeadBean headBean,int pagerNumb){
        int headTypeIndex=headBean.getType();
        int bgTypeIndex=getBgIndex(headBean,pagerNumb);
        return getBgTempRs(headTypeIndex,bgTypeIndex);
    }


    private static int getBgTempRs(int headTypeIndex,int bgType){
        ContentHeadType headType=ContentHeadType.values()[headTypeIndex];
        switch (headType){
            case NOTE_BOOK:
                return NoteBookBgType.getTempRsId(bgType);
        }
        return R.drawable.content_head_not_find;
    }

    public static List<Integer> getBgRsTempList(ContentHeadBean headBean){

        List<Integer>rsList=new ArrayList<>();
        int headTypeIndex=headBean.getType();
        ContentHeadType headType=ContentHeadType.values()[headTypeIndex];
        switch (headType){
            case NOTE_BOOK:
                NoteBookBgType[] noteBookBgTypes=NoteBookBgType.values();
                for(NoteBookBgType noteBookBgType:noteBookBgTypes){
                    rsList.add(NoteBookBgType.getTempRsId(noteBookBgType));
                }
                break;
        }
        return rsList;
    }

}
