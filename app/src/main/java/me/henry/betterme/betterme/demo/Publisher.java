package me.henry.betterme.betterme.demo;

import android.database.DataSetObservable;

import java.util.Observable;

/**
 * Created by zj on 2017/3/24.
 * me.henry.betterme.betterme.demo
 */

public class Publisher extends Observable {
    private String magazineName="haha";


    public String getMagazineName() {
        return magazineName;
    }

    public void publish(String magazineName) {
        this.magazineName = magazineName;
        setChanged();

        notifyObservers(this);
    }

}
