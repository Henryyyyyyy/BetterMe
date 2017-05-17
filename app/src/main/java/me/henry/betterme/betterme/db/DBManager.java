package me.henry.betterme.betterme.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.henry.betterme.betterme.model.Note;

import static android.R.attr.id;
import static android.R.attr.tag;
import static android.R.attr.version;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.db
 */

public class DBManager {
    public static final String TAG = "DBManager";
    private static DBManager mInstance;
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDataBase;

    public DBManager(Context context) {
        mHelper = new DatabaseHelper(context);
        mDataBase = mHelper.getWritableDatabase();
    }

    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBManager(context);
        }
        return mInstance;
    }

    public <T> void delete(T t) {
        try {
            Class<?> clz = t.getClass();//获取泛型的类
            String idName = DBUtil.getIdColumnName(clz);//获取id的column名称
            Field idField = clz.getDeclaredField(idName);
            idField.setAccessible(true);
            int idValue = (int) idField.get(t);//设置accessible为true之后，通过field获得id的值
            mDataBase.delete(DBUtil.getTableName(clz), idName + "=?", new String[]{String.valueOf(idValue)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public <T> T queryById(Class<?>clz,int id){
    Cursor cursor=mDataBase.rawQuery("select * from "+DBUtil.getTableName(clz)+" where "+DBUtil.getIdColumnName(clz)+" =?",new String[]{String.valueOf(id)});
    T t=null;
    if (cursor.moveToNext()){
        try {
            t= (T) clz.newInstance();
            Field[] fields = clz.getDeclaredFields();
            for (Field field:fields) {
                if (field.isAnnotationPresent(Column.class)){
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    if (fieldType==Integer.class||fieldType==int.class){
                        field.setInt(t,cursor.getInt(cursor.getColumnIndex(DBUtil.getColumnName(field))));
                    }else if (fieldType==String.class){
                        field.set(t,cursor.getString(cursor.getColumnIndex(DBUtil.getColumnName(field))));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return  t;
}
    public <T> List<T> queryAll(Class<?>clz){
        Cursor cursor=mDataBase.rawQuery("select * from "+DBUtil.getTableName(clz),null);
        T t=null;
        List<T> list=new ArrayList<>();
        while (cursor.moveToNext()){
            try {
                t= (T) clz.newInstance();
                Field[] fields = clz.getDeclaredFields();
                for (Field field:fields) {
                    if (field.isAnnotationPresent(Column.class)){
                        field.setAccessible(true);
                        Class<?> fieldType = field.getType();
                        if (fieldType==Integer.class||fieldType==int.class){
                            field.setInt(t,cursor.getInt(cursor.getColumnIndex(DBUtil.getColumnName(field))));
                        }else if (fieldType==String.class){
                            field.set(t,cursor.getString(cursor.getColumnIndex(DBUtil.getColumnName(field))));
                        }
                    }
                }
                list.add(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  list;
    }
    public synchronized <T> void addOrUpdate (T t){
        try {
            Class<?> clz = t.getClass();
            ContentValues values=new ContentValues();
            Field[] fields = clz.getDeclaredFields();
            Column column;
            for (Field field:fields){
                if (field.isAnnotationPresent(Column.class)){
                    Class<?> fieldType = field.getType();
                    field.setAccessible(true);
                     column = field.getAnnotation(Column.class);
                    if (!column.auto()){
                        if (fieldType==Integer.class||fieldType==int.class){
                            values.put(DBUtil.getColumnName(field),field.getInt(t));
                        }else if (fieldType==String.class){
                            Object value=field.get(t);
                            if (value!=null){
                                values.put(DBUtil.getColumnName(field),value.toString());
                            }
                        }
                    }
                }
            }
            mDataBase.replace(DBUtil.getTableName(clz),null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    class DatabaseHelper extends SQLiteOpenHelper {
        public static final String DB_NAME = "henry.db";
        public static final int DB_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            DBUtil.createTable(db, Note.class);
            Log.e(TAG, "createdatabase");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
