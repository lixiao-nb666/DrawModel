package com.newbee.drawdevelopmenttool.sql.content;

import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.sqlite.BaseSqlServer;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;

import java.util.List;


/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class ContentHeadSqlServer extends BaseSqlServer {
    private final String tag = this.getClass().toString() + ">>>"; // LOG信息
    private static ContentHeadSqlServer sqlServer;

    private ContentHeadSqlServer(Object o) {
        super(o);
        setNeedSendEvent(true);
    }

    public static ContentHeadSqlServer getInstance() {
        if (sqlServer == null) {
            synchronized (ContentHeadSqlServer.class) {
                if (sqlServer == null) {
                    sqlServer = new ContentHeadSqlServer(new ContentHeadBean());
                }
            }
        }
        return sqlServer;
    }

    @Override
    public void close() {
        super.close();
    }


    public ContentHeadBean add(ContentHeadBean contentHeadBean) {

        ContentHeadBean cb= (ContentHeadBean) super.add(contentHeadBean);
        LG.i(tag,"-----------kankanadd:"+cb);
        return cb;
    }

    public ResultContentHeadBean queIsRbs() {
        ResultContentHeadBean resultContentHeadBean = new ResultContentHeadBean();
        List<ContentHeadBean> list = que("isNeedDelect", true + "");
        resultContentHeadBean.setContentHeadBeanList(list);
        return resultContentHeadBean;
    }

    public ResultContentHeadBean queByIsstar() {
        ResultContentHeadBean resultContentHeadBean = new ResultContentHeadBean();
        String[] keyS={"isStar","isNeedDelect"};
        String[] vueS={true+"",false+""};
        List<ContentHeadBean> list = que(keyS,vueS);
        resultContentHeadBean.setContentHeadBeanList(list);
        return resultContentHeadBean;
    }


    public ResultContentHeadBean queByFilePath(String filePath) {
        ResultContentHeadBean resultContentHeadBean = new ResultContentHeadBean();
        String[] keyS={"filePath","isNeedDelect"};
        String[] vueS={filePath,false+""};
        List<ContentHeadBean> list = que(keyS,vueS);
        resultContentHeadBean.setContentHeadBeanList(list);
        return resultContentHeadBean;
    }

    public ResultContentHeadBean fuzzyQueByName(String fuzzyStr,boolean isNeedSearchDelect) {
        ResultContentHeadBean resultContentHeadBean = new ResultContentHeadBean();
        List<ContentHeadBean> list=null;
        if(isNeedSearchDelect){
            list = fuzzyQue("name",fuzzyStr );
        }else {
            String[] keyS={"name","isNeedDelect"};
            String[] vueS={fuzzyStr,false+""};
            list = fuzzyQue(keyS,vueS );
        }


        resultContentHeadBean.setContentHeadBeanList(list);
        return resultContentHeadBean;
    }
}
