package com.newbee.drawdevelopmenttool.draw.thread;


import android.graphics.Bitmap;

import com.lixiao.build.mybase.appliction.MyApplicationFile;
import com.lixiao.build.util.Md5Util;
import com.lixiao.build.util.RandomUtil;
import com.newbee.drawdevelopmenttool.draw.save.SaveBitMapConfig;
import com.newbee.drawdevelopmenttool.draw.save.SaveBitMapSubscriptionSubject;
import com.newbee.drawdevelopmenttool.draw.type.SaveBitMapType;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavaBitMapThread extends Thread{
    private Bitmap bitmap;
    private String fileName;

    private SaveBitMapListen saveBitMapListen;
    private SaveBitMapType type;
    private String key;
    public SavaBitMapThread(String fileName, Bitmap bitmap, SaveBitMapListen saveBitMapListen, SaveBitMapType type){
        this.bitmap=bitmap;
        this.fileName=fileName;
        this.saveBitMapListen=saveBitMapListen;
        this.type=type;
        key= Md5Util.MD5(System.currentTimeMillis()+""+ RandomUtil.getInstance().getRandomString(10));

    }

    @Override
    public void run() {
        super.run();
//        BufferedOutputStream bos=null;
//        File file = FileUtils.createSaveFile(fileName, ConstansUserDefine.DOWNFILECATCLOG, ConstansUserDefine.DOWNFILE_DOWN);
        String filePath= MyApplicationFile.getInstance().getPic_files()+File.separator+fileName;
        SaveBitMapConfig.nowSaveFileMap.put(key,filePath);
        try {
            MyApplicationFile.getInstance().mkFile();
            saveMyBitmap(filePath,bitmap);
            //////////////////////////////////////////
//            if(!file.exists()){
//                if(!file.getParentFile().exists()){
//                    file.getParentFile().mkdirs();
//                }
//                file.createNewFile();
//            }
//             bos = new BufferedOutputStream(new FileOutputStream(file));
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
//            bos.flush();
           saveBitMapListen.saveBitMapOk(filePath,fileName,type);
        } catch (IOException e) {
            saveBitMapListen.saveBitMapErr(fileName,e.toString(),type);
        }finally {
        }

        SaveBitMapConfig.nowSaveFileMap.remove(key);
        SaveBitMapSubscriptionSubject.getInstance().over(fileName,filePath);
    }


    private void saveMyBitmap(String bitFilePath, Bitmap mBitmap) throws IOException {
        FileOutputStream fOut = null;
        try {
            if(null==mBitmap||mBitmap.isRecycled()){
                return;
            }
            File f = new File(bitFilePath);
            f.createNewFile();
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
        }catch (Exception e){
        }finally {
            if(null!=fOut){
                fOut.close();
            }
        }
    }


    public interface  SaveBitMapListen{
        public void saveBitMapOk(String filePath, String fileName, SaveBitMapType type);
        public void saveBitMapErr(String fileName, String err, SaveBitMapType type);
    }
}
