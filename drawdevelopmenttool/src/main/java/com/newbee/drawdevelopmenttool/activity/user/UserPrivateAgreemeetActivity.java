package com.newbee.drawdevelopmenttool.activity.user;

import com.lixiao.build.mybase.activity.userprivate.BaseUserPrivateAgreemeet2Activity;
import com.lixiao.build.mybase.activity.userprivate.bean.UserPrivateAgreemeetInfoBean;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/2/22 0022 9:13
 */
public class UserPrivateAgreemeetActivity extends BaseUserPrivateAgreemeet2Activity {
    @Override
    public UserPrivateAgreemeetInfoBean getUserPrivateAgreemeetInfo() {

        return MyDrawBoardConfig.userPrivateAgreemeetInfoBean;
    }
//    @Override
//    public String getTitleStr() {
//        return "lixiao";
//    }
}
