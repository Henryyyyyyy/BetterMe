package me.henry.betterme.betterme.demo.aboutinherit;

import android.util.Log;

/**
 * Created by zj on 2017/5/27.
 * me.henry.betterme.betterme.demo.aboutinherit
 */

public class OB1 implements IMethods{
    @Override
    public void Me1() {
        Log.e("??","ob1,m1");
    }

    @Override
    public void Me2() {
        Log.e("??","ob1,m2");
    }
    public void fuck(){
        Log.e("??","fuck");
    }
}
