package me.henry.betterme.betterme.demo.greendaoo.wrap;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

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
    private DaoSession mDaoSession=null;
    private HenryUserDao mUserDao=null;
    private TemPasswordDao mPswDao=null;
private static  final  class Holder{
    private static  final  DataBaseManager INSTANCE =new DataBaseManager();
}
    public static DataBaseManager getInstance(){
        return Holder.INSTANCE;
    }
    private DataBaseManager(){

    }
    public DataBaseManager  init(Context context){
        initData(context);
        return this;
    }
    private void initData(Context context) {
        final ReleaseOpenHelper helper=new ReleaseOpenHelper(context,"better_me.db");
        final Database db=helper.getWritableDb();
        mDaoSession=new DaoMaster(db).newSession();
        mUserDao=mDaoSession.getHenryUserDao();
        mPswDao=mDaoSession.getTemPasswordDao();
    }
    public final HenryUserDao getHenryUserDao(){
        return mUserDao;
    }
    public final TemPasswordDao getTemPasswordDao(){
        return mPswDao;
    }
}
