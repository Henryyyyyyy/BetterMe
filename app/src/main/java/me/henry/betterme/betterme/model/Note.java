package me.henry.betterme.betterme.model;

import java.io.Serializable;

import me.henry.betterme.betterme.db.Column;
import me.henry.betterme.betterme.db.Table;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.model
 */
@Table(name = "note")
public class Note implements Serializable {
    @Column(id = true,auto = true)
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String startTime;
    @Column
    private String alarmTime;
    @Column
    private int count;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAlarmTime() {
        return alarmTime;
    }

    public int getCount() {
        return count;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startTime='" + startTime + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                ", count=" + count +
                '}';
    }
}
