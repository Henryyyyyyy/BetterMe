package me.henry.betterme.betterme.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.model
 */

public class User implements Serializable{
    private String id;
    private String name;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
