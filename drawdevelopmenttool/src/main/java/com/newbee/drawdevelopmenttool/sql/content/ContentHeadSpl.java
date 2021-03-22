//package com.newbee.drawdevelopmenttool.sql.content;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by Administrator on 2017/7/26 0026.
// *
// * REAL
// * integer
// * text
// *
// */
//
//public class ContentHeadSpl extends SQLiteOpenHelper {
//    public static final String sqlName="draw";
//    public  final String tablename = "content_head";
//    private final String createtable = "create table " + tablename + "("
//            + "id integer primary key autoincrement, "
//            + "name text, "
//            + "createTime integer, "
//            + "type integer, "
//            + "filePath text, "
//            + "isStar text, "
//            + "isNeedDelect text, "
//            + "isLandscape text, "
//            + "textStr text, "
//            + "contentHeadOtherBean text)";
//
//
//
//    //版本号
//    private static final int sqlVersion = 1;
//
//
//    public ContentHeadSpl(Context context) {
//        super(context, sqlName, null, sqlVersion);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(createtable);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        switch (oldVersion) {
//            case sqlVersion:
//                break;
//            default:
//                break;
//        }
//    }
//}
