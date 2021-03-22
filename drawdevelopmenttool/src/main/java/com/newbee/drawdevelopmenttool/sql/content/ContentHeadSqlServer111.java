//package com.newbee.drawdevelopmenttool.sql.content;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.text.TextUtils;
//
//import com.lixiao.build.gson.MyGson;
//import com.lixiao.build.mybase.LG;
//import com.lixiao.build.mybase.appliction.BaseApplication;
//import com.lixiao.build.mybase.bean.MyClassInfoBean;
//import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
//import com.newbee.drawdevelopmenttool.bean.content.ContentHeadOtherBean;
//import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
//
//
///**
// * Created by Administrator on 2017/7/26 0026.
// */
//
//public class ContentHeadSqlServer111<T> {
//    private final String tag = this.getClass().toString() + ">>>"; // LOG信息
//    private static ContentHeadSqlServer111 sqlServer;
//    private ContentHeadSpl spl;
//    private SQLiteDatabase sqLiteDatabase;
//    private MyClassInfoBean myClassInfoBean;
//
//    private ContentHeadSqlServer111() {
//        this.spl = new ContentHeadSpl(BaseApplication.getContext());
//        myClassInfoBean = new MyClassInfoBean(ContentHeadBean.class);
//    }
//
//    public static ContentHeadSqlServer111 getInstance() {
//        if (sqlServer == null) {
//            synchronized (ContentHeadSqlServer111.class) {
//                if (sqlServer == null) {
//                    sqlServer = new ContentHeadSqlServer111();
//                }
//            }
//        }
//        return sqlServer;
//    }
//
//    public void close() {
//        if (sqLiteDatabase != null) {
//            sqLiteDatabase.close();
//            sqLiteDatabase = null;
//        }
//        if (spl != null) {
//            spl.close();
//            spl = null;
//        }
//        sqlServer = null;
//    }
//
//    public ContentHeadBean add(ContentHeadBean contentHeadBean) {
//        LG.i(tag,"---------sqlcontentheadadd:"+contentHeadBean);
//        if (null == contentHeadBean||contentHeadBean.getId()!=-1) {
//            return null;
//        }
//
//        try {
//            sqLiteDatabase = spl.getReadableDatabase();
//            ContentValues contentValues = new ContentValues();
//            if (TextUtils.isEmpty(contentHeadBean.getName())) {
//                contentValues.put("name", contentHeadBean.getNoNameTitle());
//            } else {
//                contentValues.put("name", contentHeadBean.getName());
//            }
//            contentValues.put("createTime", contentHeadBean.getCreateTime());
//            contentValues.put("type", contentHeadBean.getType());
//            contentValues.put("filePath", contentHeadBean.getFilePath());
//            contentValues.put("isStar", contentHeadBean.isStar() + "");
//            contentValues.put("isNeedDelect", contentHeadBean.isNeedDelect() + "");
//            contentValues.put("isLandscape", contentHeadBean.isLandscape() + "");
//            contentValues.put("textStr", contentHeadBean.getTextStr());
//            contentValues.put("contentHeadOtherBean", MyGson.getInstance().toGsonStr(contentHeadBean.getContentHeadOtherBean()));
//            long id = sqLiteDatabase.insert(spl.tablename, "name", contentValues);
//            contentHeadBean.setId(id);
//        } catch (Exception e) {
//
//            contentHeadBean = null;
//        } finally {
//            if (sqLiteDatabase != null)
//                sqLiteDatabase.close();
//        }
//
//        return contentHeadBean;
//    }
//
//    public boolean delectById(String id) {
//        boolean delect = false;
//        try {
//            sqLiteDatabase = spl.getReadableDatabase();
//            String sql = "delete from " + spl.tablename
//                    + " where id = ?";
//            Object shuzu[] = {id};
//            sqLiteDatabase.execSQL(sql, shuzu);
//            if (sqLiteDatabase != null)
//                sqLiteDatabase.close();
//            delect = true;
//        } catch (Exception e) {
//        } finally {
//            if (sqLiteDatabase != null)
//                sqLiteDatabase.close();
//        }
//        return delect;
//    }
//
//    public boolean delectById(long id){
//       return delectById(id+"");
//    }
//
//
//    public boolean delectAll() {
//        boolean delect = false;
//        try {
//            sqLiteDatabase = spl.getReadableDatabase();
//            String sql = "delete from " + spl.tablename;
//            Object shuzu[] = {};
//            sqLiteDatabase.execSQL(sql, shuzu);
//            delect = true;
//        } catch (Exception e) {
//        } finally {
//            if (sqLiteDatabase != null) {
//                sqLiteDatabase.close();
//            }
//        }
//        return delect;
//    }
//
//
//    public boolean update(ContentHeadBean contentHeadBean) {
//        if(null==contentHeadBean||contentHeadBean.getId()==-1){
//            return false;
//        }
//
//        boolean update = false;
//        try {
//            sqLiteDatabase = spl.getReadableDatabase();
//            ContentValues contentValues = new ContentValues();
//            if (TextUtils.isEmpty(contentHeadBean.getName())) {
//                contentValues.put("name", contentHeadBean.getNoNameTitle());
//            } else {
//                contentValues.put("name", contentHeadBean.getName());
//            }
//            contentValues.put("createTime", contentHeadBean.getCreateTime());
//            contentValues.put("type", contentHeadBean.getType());
//            contentValues.put("filePath", contentHeadBean.getFilePath());
//            contentValues.put("isStar", contentHeadBean.isStar() + "");
//            contentValues.put("isNeedDelect", contentHeadBean.isNeedDelect() + "");
//            contentValues.put("isLandscape", contentHeadBean.isLandscape() + "");
//            contentValues.put("textStr", contentHeadBean.getTextStr());
//            contentValues.put("contentHeadOtherBean", MyGson.getInstance().toGsonStr(contentHeadBean.getContentHeadOtherBean()));
//            String[] whereStrs = {contentHeadBean.getId() + ""};
//            sqLiteDatabase.update(spl.tablename, contentValues, "id=?", whereStrs);
//            update = true;
//        } catch (Exception e) {
//
//        } finally {
//            if (null != sqLiteDatabase) {
//                sqLiteDatabase.close();
//            }
//
//        }
//        return update;
//    }
//
//
//    private String[] queColumns = {"id", "name", "createTime", "type", "filePath", "isStar", "isLandscape","isNeedDelect", "textStr", "contentHeadOtherBean"};
//
//    public ResultContentHeadBean queByType(int type) {
//        Cursor cursor = null;
//        ResultContentHeadBean resultContentHeadBean = null;
//        try {
//            String[] types = {type+""};
//            sqLiteDatabase = spl.getReadableDatabase();
//            cursor = sqLiteDatabase.query(spl.tablename, queColumns, "type=?", types, null, null, null);
//            resultContentHeadBean = useCursorGetList(cursor);
//        } catch (Exception e) {
//            LG.i(tag, "kankangetAllerr:" + e.toString());
//        } finally {
//            if (null != cursor) {
//                cursor.close();
//            }
//            if (null != sqLiteDatabase)
//                sqLiteDatabase.close();
//        }
//        return resultContentHeadBean;
//    }
//
//
//
//    public ResultContentHeadBean queIsRbs() {
//        Cursor cursor = null;
//        ResultContentHeadBean resultContentHeadBean = null;
//        try {
//            String[] strs = {true+""};
//            sqLiteDatabase = spl.getReadableDatabase();
//            cursor = sqLiteDatabase.query(spl.tablename, queColumns, "isNeedDelect=?", strs, null, null, null);
//            resultContentHeadBean = useCursorGetList(cursor);
//
//        } catch (Exception e) {
//            LG.i(tag, "kankangetAllerr:" + e.toString());
//        } finally {
//            if (null != cursor) {
//                cursor.close();
//            }
//            if (null != sqLiteDatabase)
//                sqLiteDatabase.close();
//        }
//        return resultContentHeadBean;
//    }
//
//    public ContentHeadBean queById(long id) {
//        Cursor cursor = null;
//        ContentHeadBean contentHeadBean= null;
//        try {
//            String[] ids = {id+""};
//            sqLiteDatabase = spl.getReadableDatabase();
//            cursor = sqLiteDatabase.query(spl.tablename, queColumns, "id=?", ids, null, null, null);
//            ResultContentHeadBean resultContentHeadBean  = useCursorGetList(cursor);
//            if(null!=resultContentHeadBean&&null!=resultContentHeadBean.getContentHeadBeanList()&&resultContentHeadBean.getContentHeadBeanList().size()>0){
//                contentHeadBean=resultContentHeadBean.getContentHeadBeanList().get(0);
//            }
//        } catch (Exception e) {
//            LG.i(tag, "kankangetAllerr:" + e.toString());
//        } finally {
//            if (null != cursor) {
//                cursor.close();
//            }
//            if (null != sqLiteDatabase)
//                sqLiteDatabase.close();
//        }
//        return contentHeadBean;
//    }
//
//    public ResultContentHeadBean queByFilePath(String filePath) {
//        Cursor cursor = null;
//        ResultContentHeadBean resultContentHeadBean = null;
//        try {
//            String[] filePaths = {filePath};
//            sqLiteDatabase = spl.getReadableDatabase();
//            cursor = sqLiteDatabase.query(spl.tablename, queColumns, "filePath=?", filePaths, null, null, null);
//            resultContentHeadBean = useCursorGetList(cursor);
//
//        } catch (Exception e) {
//            LG.i(tag, "kankangetAllerr:" + e.toString());
//        } finally {
//            if (null != cursor) {
//                cursor.close();
//            }
//            if (null != sqLiteDatabase)
//                sqLiteDatabase.close();
//        }
//        return resultContentHeadBean;
//    }
//
//
//    public ResultContentHeadBean queByIsstar() {
//        Cursor cursor = null;
//        ResultContentHeadBean resultContentHeadBean = null;
//        try {
//            String[] filePaths = {true + ""};
//            sqLiteDatabase = spl.getReadableDatabase();
//            cursor = sqLiteDatabase.query(spl.tablename, queColumns, "isStar=?", filePaths, null, null, null);
//            resultContentHeadBean = useCursorGetList(cursor);
//
//        } catch (Exception e) {
//            LG.i(tag, "kankangetAllerr:" + e.toString());
//        } finally {
//            if (null != cursor) {
//                cursor.close();
//            }
//            if (null != sqLiteDatabase)
//                sqLiteDatabase.close();
//        }
//        return resultContentHeadBean;
//    }
//
//    public ResultContentHeadBean getAll() {
//        Cursor cursor = null;
//        ResultContentHeadBean resultContentHeadBean = null;
//        try {
//            sqLiteDatabase = spl.getReadableDatabase();
//            cursor = sqLiteDatabase.query(spl.tablename, queColumns, null, null, null, null, null);
//            resultContentHeadBean = useCursorGetList(cursor);
//        } catch (Exception e) {
//            LG.i(tag, "kankangetAllerr:" + e.toString());
//        } finally {
//            if (null != cursor) {
//                cursor.close();
//            }
//            if (null != sqLiteDatabase)
//                sqLiteDatabase.close();
//        }
//        return resultContentHeadBean;
//    }
//
//
//    public ResultContentHeadBean fuzzyQue(String fuzzyStr) {
//        Cursor cursor = null;
//        ResultContentHeadBean resultContentHeadBean = null;
//        try {
//            String[] fuzzyS = {"%" + fuzzyStr + "%"};
//            sqLiteDatabase = spl.getReadableDatabase();
//            cursor = sqLiteDatabase.query(spl.tablename, queColumns, "name" + " LIKE ? ",
//                    fuzzyS, null, null, null);
//            resultContentHeadBean = useCursorGetList(cursor);
//
//        } catch (Exception e) {
//            LG.i(tag, "kankangetAllerr:" + e.toString());
//        } finally {
//            if (null != cursor) {
//                cursor.close();
//            }
//            if (null != sqLiteDatabase)
//                sqLiteDatabase.close();
//        }
//        return resultContentHeadBean;
//    }
//
//    private ResultContentHeadBean useCursorGetList(Cursor cursor) {
//        ResultContentHeadBean resultContentHeadBean = new ResultContentHeadBean();
//
//        while (cursor.moveToNext()) {
//            ContentHeadBean contentHeadBean = new ContentHeadBean();
//            for (int i = 0; i < queColumns.length; i++) {
//                String column = queColumns[i];
//                LG.i(tag,"------------kankanchachulaidezhi:isNeedDelect--11111---"+column);
//                switch (column) {
//                    case "id":
//                        int id = cursor.getInt(i);
//                        contentHeadBean.setId(id);
//                        break;
//                    case "name":
//                        String name = cursor.getString(i);
//                        contentHeadBean.setName(name);
//                        break;
//                    case "createTime":
//                        long createTime = cursor.getLong(i);
//                        contentHeadBean.setCreateTime(createTime);
//                        break;
//                    case "type":
//                        int type = cursor.getInt(i);
//                        contentHeadBean.setType(type);
//                        break;
//                    case "filePath":
//                        String filePath = cursor.getString(i);
//                        contentHeadBean.setFilePath(filePath);
//                        break;
//                    case "isStar":
//                        String isStarStr = cursor.getString(i);
//
//                        try {
//                            contentHeadBean.setStar(Boolean.valueOf(isStarStr));
//                        } catch (Exception e) {
//                            contentHeadBean.setStar(false);
//                        }
//                        break;
//                    case "isNeedDelect":
//                        String isNeedDelectStr = cursor.getString(i);
//                        LG.i(tag,"------------kankanchachulaidezhi:isNeedDelect--2222"+isNeedDelectStr+contentHeadBean);
//                        try {
//                            contentHeadBean.setNeedDelect(Boolean.valueOf(isNeedDelectStr));
//                        } catch (Exception e) {
//                            contentHeadBean.setNeedDelect(false);
//                        }
//                        break;
//
//                    case "isLandscape":
//                        String isLandscapeStr = cursor.getString(i);
//                        try {
//                            contentHeadBean.setLandscape(Boolean.valueOf(isLandscapeStr));
//                        } catch (Exception e) {
//                            contentHeadBean.setLandscape(false);
//                        }
//                        break;
//                    case "textStr":
//                        String textStr = cursor.getString(i);
//                        contentHeadBean.setTextStr(textStr);
//                        break;
//                    case "contentHeadOtherBean":
//                        String contentHeadOtherBeanStr = cursor.getString(i);
//                        contentHeadBean.setContentHeadOtherBean(MyGson.getInstance().fromJson(contentHeadOtherBeanStr, ContentHeadOtherBean.class));
//                        break;
//                }
//            }
//            resultContentHeadBean.add(contentHeadBean);
//        }
//        return resultContentHeadBean;
//    }
//}
