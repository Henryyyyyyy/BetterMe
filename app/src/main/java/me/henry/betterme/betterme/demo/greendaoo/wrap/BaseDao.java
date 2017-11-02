package me.henry.betterme.betterme.demo.greendaoo.wrap;

import android.content.Context;
import android.util.Log;

import java.util.List;

import me.henry.betterme.betterme.demo.greendaoo.DaoSession;

/**
 * Created by zj on 2017/9/30.
 * me.henry.betterme.betterme.demo.greendaoo.wrap
 */

public abstract class BaseDao<T> {
    public static final String TAG = BaseDao.class.getSimpleName();
    public DataBaseManager manager;
    public DaoSession daoSession;

    public BaseDao(Context context) {
        manager = DataBaseManager.getInstance();
        manager.init(context);
        daoSession = manager.getDaoSession();

    }

    /**************************数据库插入操作***********************/
    /**
     * 插入单个对象
     * @param object
     * @return
     */
    public boolean insertObject(T object){
        boolean flag = false;
        try {
            flag = manager.getDaoSession().insert(object) != -1 ? true:false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return flag;
    }
    /**
     * 插入多个对象，并开启新的线程
     * @param objects
     * @return
     */
    public boolean insertMultObject(final List<T> objects){
        boolean flag = false;
        if (null == objects || objects.isEmpty()){
            return false;
        }
        try {

            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T object : objects) {
                        manager.getDaoSession().insertOrReplace(object);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            flag = false;
        }finally {
//            manager.CloseDataBase();
        }
        return flag;
    }
}
