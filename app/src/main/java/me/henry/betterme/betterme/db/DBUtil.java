package me.henry.betterme.betterme.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Field;

import me.henry.betterme.betterme.utils.TextUtil;

import static android.R.attr.id;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.db
 */

public class DBUtil {
    private static final String TAG = "DBUtil";

    public static String getTableName(Class<?> clz) {
        String name;
        if (clz.isAnnotationPresent(Table.class)) {
            name = clz.getAnnotation(Table.class).name();
            if (TextUtil.isValidate(name)) {
                return name;
            } else {
                return clz.getSimpleName().toLowerCase();
            }
        }
        Log.e(TAG, "333");
        return clz.getSimpleName();
    }

    /**
     * 创建建表语句
     *
     * @param clz
     * @return
     * @throws SQLException
     */
    public static String getCreateTableStmt(Class<?> clz) throws SQLException {
        StringBuilder mStatement = new StringBuilder();
        Field[] fields = clz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Column.class)) {
                mStatement.append(getOneColumnStr(fields[i]));
                if (i != fields.length - 1) {
                    mStatement.append(",");
                }
            }
        }
        //  db.execSQL("CREATE TABLE IF NOT EXISTS slaver_table (ID INTEGER DEFAULT 0,name TEXT, baseType INTEGER,type INTEGER, mac TEXT, sn TEXT, remoterID TEXT, lastTime INTEGER,m_format_id INTEGER,m_code TEXT DEFAULT NULL,tag INTEGER,DataMark INTEGER DEFAULT 0,ralayMac TEXT,attention INTEGER DEFAULT 0,safeCode TEXT,productType TEXT,projectCode TEXT)");
        String createStr = "create table if not exists " + getTableName(clz) + " (" + mStatement + ")";
        Log.e(TAG, "createStr=" + createStr);
        return createStr;
    }

    public static String getOneColumnStr(Field field) {
        String str = null;
        String name = null;
        String type = null;
        Column column = field.getAnnotation(Column.class);
        name = column.name();
        if (!TextUtil.isValidate(name)) {
            name = "[" + field.getName() + "]";
        } else {
            name = "[" + name + "]";
        }
        str = name;
        Class<?> clz = field.getType();
        if (clz == Integer.class || clz == int.class) {
            type = " INTEGER ";
        } else if (clz == String.class) {
            type = " TEXT ";
        }
        str = name + type;
        if (column.id()) {
            str += " PRIMARY KEY";
        }
        if (column.auto()){
            str+=" AUTOINCREMENT";
        }
        return str;
    }

    /**
     * 字段两边加中括号，防止字段与java内部的冲突，例如groupby。。。所以要变成[name]
     *
     * @param db
     * @param clz
     * @throws SQLException
     */
    public static void createTable(SQLiteDatabase db, Class<?> clz) throws SQLException {
        db.execSQL(getCreateTableStmt(clz));

    }


    /**
     * 删除数据库
     *
     * @param db
     * @param clz
     * @throws SQLException
     */
    public static void dropTable(SQLiteDatabase db, Class<?> clz) throws SQLException {
        db.execSQL(getDropTableStmt(clz));
    }

    /**
     * 获取删除表的sql描述
     *
     * @return
     */
    public static String getDropTableStmt(Class<?> clz) throws SQLException {
        return "drop table if exists " + getTableName(clz);
    }

    /**
     * 获取主键的名称
     * 步骤：
     * 1.通过class获得field
     * 2.获得每个成员的column，判断注解是否有值，有的话就去注解的，没就用field的值
     *
     * @param clz
     * @return
     */
    public static String getIdColumnName(Class<?> clz) {
        if (clz.isAnnotationPresent(Table.class)) {
            Field[] fields = clz.getDeclaredFields();
            Column column;
            String id;
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(Column.class)) {
                    column = fields[i].getAnnotation(Column.class);
                    if (column.id()) {
                        id = column.name();
                        if (!TextUtil.isValidate(id)) {
                            id = fields[i].getName();
                        }
                        return id;
                    }
                }

            }
        }
        return null;
    }

    public static String getColumnName(Field field) {
        String name;
        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            name = column.name();
            if (!TextUtil.isValidate(name)) {
                name = field.getName();
            }
            return name;
        }
        return null;
    }

}
