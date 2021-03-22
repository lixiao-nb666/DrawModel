package com.newbee.drawdevelopmenttool.util.draw.thread;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.lixiao.build.mybase.appliction.BaseApplication;
import java.io.File;
import java.io.FileInputStream;

public class UseFilePathGetBitMapThread extends Thread{
    private String filePath;
    private int imgRs;
    private GetBitMapListen getBitMapListen;
    public UseFilePathGetBitMapThread(String filePath,GetBitMapListen getBitMapListen){
        this.filePath=filePath;
        this.getBitMapListen=getBitMapListen;
    }

    public UseFilePathGetBitMapThread(int imgRs,GetBitMapListen getBitMapListen){
        this.imgRs=imgRs;
        this.getBitMapListen=getBitMapListen;
    }

    @Override
    public void run() {
        super.run();
        try {
            if(!TextUtils.isEmpty(filePath)){
                File file = new File(filePath);
                if (file.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                    getBitMapListen.getBitMapListen(bitmap);
                }
            }else if(imgRs!=0){
                Resources res= BaseApplication.getContext().getResources();
                Bitmap bmp=BitmapFactory.decodeResource(res, imgRs);
                getBitMapListen.getBitMapListen(bmp);
            }


        } catch (Exception e) {
        }
    }

    public interface GetBitMapListen{
        public void getBitMapListen(Bitmap bitmap);
    }
}
