package me.henry.betterme.betterme.demo.greendaoo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zj on 2017/9/29.
 * me.henry.betterme.betterme.demo.greendaoo
 */
@Entity(nameInDb = "tempsw_data")
public class TemPassword {
    @Id
    private Long id;
    private String name;
    private String data;
    @Generated(hash = 1317281699)
    public TemPassword(Long id, String name, String data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }
    @Generated(hash = 1901372978)
    public TemPassword() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
