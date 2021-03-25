package com.newbee.drawdevelopmenttool.draw.save;


import java.util.HashMap;
import java.util.Map;

public class SaveBitMapConfig {
    public static Map<String,String> nowSaveFileMap=new HashMap<>();


    public static boolean checkFilePathCanOpen(String filePath){
        try {

            for(String k:nowSaveFileMap.keySet()){
                String v=nowSaveFileMap.get(k);
                if(v.equals(filePath)){

                    return false;
                }
            }
        }catch (Exception e){

            return false;
        }


        return true;
    }
}
