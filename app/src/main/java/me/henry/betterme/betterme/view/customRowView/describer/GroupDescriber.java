package me.henry.betterme.betterme.view.customRowView.describer;

import java.util.ArrayList;

/**
 *
 * Created by zj on 2017/5/16.
 * me.henry.betterme.betterme.view.customRowView
 *
 */

public class GroupDescriber {
    public String title;
    public ArrayList<BaseRowDescriber> rowDescribers;

    public GroupDescriber(String title, ArrayList<BaseRowDescriber> rowDescribers) {
        this.title = title;
        this.rowDescribers = rowDescribers;
    }

    public GroupDescriber(ArrayList<BaseRowDescriber> rowDescribers) {
        this.rowDescribers = rowDescribers;
    }
}
