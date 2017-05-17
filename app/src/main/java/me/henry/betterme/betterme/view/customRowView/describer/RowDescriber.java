package me.henry.betterme.betterme.view.customRowView.describer;

import me.henry.betterme.betterme.view.customRowView.RowActionEnum;
import me.henry.betterme.betterme.view.customRowView.describer.BaseRowDescriber;

/**
 * Created by zj on 2017/5/16.
 * me.henry.betterme.betterme.view.customRowView
 */

public class RowDescriber extends BaseRowDescriber {
    public int logoRes;
    public String title;
    public int entranceRes;
    public RowActionEnum action;

    public RowDescriber(int logoRes, String title, int entranceRes, RowActionEnum action) {
        this.logoRes = logoRes;
        this.title = title;
        this.entranceRes = entranceRes;
        this.action = action;
    }
}
