package com.newbee.drawdevelopmenttool.bean.content;

import com.lixiao.build.mybase.LG;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultContentHeadBean implements Serializable {
    private List<ContentHeadBean> contentHeadBeanList;

    public ResultContentHeadBean() {
        contentHeadBeanList = new ArrayList<>();
    }

    public List<ContentHeadBean> getContentHeadBeanList() {
        return contentHeadBeanList;
    }

    public void setContentHeadBeanList(List<ContentHeadBean> contentHeadBeanList) {
        this.contentHeadBeanList = contentHeadBeanList;
    }

    @Override
    public String toString() {
        return "ResultContentHeadBean{" +
                "contentHeadBeanList=" + contentHeadBeanList +
                '}';
    }

    public void add(ContentHeadBean contentHeadBean) {
        if (null == contentHeadBeanList) {
            contentHeadBeanList = new ArrayList<>();
        }
        if (checkContenHeadBeanCanAdd(contentHeadBean)) {

            contentHeadBeanList.add(contentHeadBean);
        }else {

        }
    }

    public void addToFirst(ContentHeadBean contentHeadBean) {
        if (null == contentHeadBeanList) {
            contentHeadBeanList = new ArrayList<>();
        }
        int index = checkContenHeadBeanCanAddIndex(contentHeadBean);
        if (-1 != index) {
            contentHeadBeanList.remove(index);
        }
        contentHeadBeanList.add(0, contentHeadBean);
    }


    private boolean checkContenHeadBeanCanAdd(ContentHeadBean contentHeadBean) {
        if (null == contentHeadBean || contentHeadBean.getId() == 0) {
            return false;
        }
        LG.i("kankantianjiashuju","kankantianjiashuju0:-------------------------"+contentHeadBeanList.size());
        LG.i("kankantianjiashuju","kankantianjiashuju1:"+contentHeadBean);
        for (ContentHeadBean chb : contentHeadBeanList) {
            LG.i("kankantianjiashuju","kankantianjiashuju2:"+chb);

            if (contentHeadBean.getId() == (chb.getId())) {
                LG.i("kankantianjiashuju","kankantianjiashuju3:"+chb);

                return false;
            }
        }
        return true;
    }

    private int checkContenHeadBeanCanAddIndex(ContentHeadBean contentHeadBean) {
        if (null == contentHeadBean || contentHeadBean.getId() == 0) {
            return -1;
        }
        int i = 0;
        for (ContentHeadBean chb : contentHeadBeanList) {
            if (contentHeadBean.getId() == (chb.getId())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void remove(ContentHeadBean contentHeadBean) {
        if (null == contentHeadBean || contentHeadBean.getId() == 0) {
            return;
        }
        for (int i = 0; i < contentHeadBeanList.size(); i++) {
            if (contentHeadBean.getId() == (contentHeadBeanList.get(i).getId())) {
                contentHeadBeanList.remove(i);
                return;
            }
        }
    }


    private boolean isDateUp;
    private boolean isNameUp;
    private boolean isAuto;

    public void sortBy(ContentHeadSortType sortType, boolean needReverse) {
        switch (sortType) {
            case DATE:
                if (needReverse) {
                    isDateUp = !isDateUp;
                }

                Collections.sort(contentHeadBeanList, new Comparator<ContentHeadBean>() {
                    @Override
                    public int compare(ContentHeadBean o1, ContentHeadBean o2) {
                        if (o1.getCreateTime() > o2.getCreateTime()) {
                            if (isDateUp) {
                                return -1;
                            } else {
                                return 1;
                            }

                        } else if (o1.getCreateTime() < o2.getCreateTime()) {
                            if (isDateUp) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else {
                            return 0;
                        }

                    }
                });
                break;

            case NAME:
                if (needReverse) {
                    isNameUp = !isNameUp;
                }

                Collections.sort(contentHeadBeanList, new Comparator<ContentHeadBean>() {
                    @Override
                    public int compare(ContentHeadBean o1, ContentHeadBean o2) {
                        if (isNameUp) {
                            return o1.getName().compareTo(o2.getName());
                        } else {
                            return o2.getName().compareTo(o1.getName());
                        }
                    }
                });
                break;
            case AUTO:
                if (needReverse) {
                    isAuto = !isAuto;
                }

                Collections.sort(contentHeadBeanList, new Comparator<ContentHeadBean>() {
                    @Override
                    public int compare(ContentHeadBean o1, ContentHeadBean o2) {
                        if (o1.getType() > o2.getType()) {
                            if (isAuto) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else if (o1.getType() < o2.getType()) {
                            if (isAuto) {
                                return -1;
                            } else {
                                return 1;
                            }
                        } else {
                            if (o1.getClickNumb() > o2.getClickNumb()) {
                                return 1;
                            } else if (o1.getClickNumb() < o2.getClickNumb()) {
                                return -1;
                            } else {
                                return 0;
                            }


                        }
                    }
                });


                break;
        }

    }

    public int getPosition(ContentHeadBean contentHeadBean) {
        if (null == contentHeadBean) {
            return -1;
        }
        for (int i = 0; i < contentHeadBeanList.size(); i++) {
            ContentHeadBean chb = contentHeadBeanList.get(i);
            if (chb.getId() == contentHeadBean.getId()) {
                return i;
            }
        }
        return -1;
    }

    public ResultContentHeadBean checkNeedDelectGetList(){
        ResultContentHeadBean needList=new ResultContentHeadBean();
        if(null==contentHeadBeanList||contentHeadBeanList.size()==0){
            return needList;
        }
        for(ContentHeadBean contentHeadBean:contentHeadBeanList){
            if(!contentHeadBean.isNeedDelect()){
                needList.add(contentHeadBean);
            }
        }
        return needList;
    }
}
