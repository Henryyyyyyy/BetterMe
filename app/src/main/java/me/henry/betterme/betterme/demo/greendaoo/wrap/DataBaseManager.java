package me.henry.betterme.betterme.demo.greendaoo.wrap;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import me.henry.betterme.betterme.demo.greendaoo.DaoMaster;
import me.henry.betterme.betterme.demo.greendaoo.DaoSession;
import me.henry.betterme.betterme.demo.greendaoo.HenryUserDao;
import me.henry.betterme.betterme.demo.greendaoo.TemPasswordDao;

/**
 * Created by zj on 2017/8/21.
 * me.henry.canuteec.database
 * 待优化：
 * 1.会生成2个_id字段
 * 2.书写数据库其他增删改查操作
 * 3.数据库升级
 */

public class DataBaseManager {
    private static final String TAG = DataBaseManager.class.getSimpleName();
    private static final String DB_NAME = "better_me.db";//数据库名称
    private DaoSession mDaoSession = null;
    private DaoMaster mDaoMaster = null;
    private HenryUserDao mUserDao = null;
    private TemPasswordDao mPswDao = null;
    private static boolean IS_DEBUG = true;

    private static final class Holder {
        private static final DataBaseManager INSTANCE = new DataBaseManager();
    }

    public static DataBaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private DataBaseManager() {
    }

    /**
     * 初始化数据
     *
     * @param context
     */
    private void initData(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, DB_NAME);
        final Database db = helper.getWritableDb();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        mUserDao = mDaoSession.getHenryUserDao();
        mPswDao = mDaoSession.getTemPasswordDao();

        setDebug(true);

    }

    /**
     * 建议application中初始化数据
     *
     * @param context
     * @return
     */
    public DataBaseManager init(Context context) {
        initData(context);
        return this;
    }

    /**
     * 这个master是关于数据库的创建，或者是否存在的判断
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    /**
     * 这个session是关于对数据库的增删查找
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    /**
     * 设置debug模式开启或关闭，默认关闭
     */
    private void setDebug(boolean flag) {
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
        IS_DEBUG = true;
    }

    public final HenryUserDao getHenryUserDao() {
        return mUserDao;
    }

    public final TemPasswordDao getTemPasswordDao() {
        return mPswDao;
    }
    public <T> AbstractDao<?, ?> getDao(Class<T> clz) {

        AbstractDao<?, ?> dd = mDaoSession.getDao(clz);
        return dd;
    }

}
