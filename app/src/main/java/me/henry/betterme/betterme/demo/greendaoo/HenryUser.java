package me.henry.betterme.betterme.demo.greendaoo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zj on 2017/9/29.
 * me.henry.betterme.betterme.demo.greendaoo
 */
@Entity(nameInDb = "default_user")
public class HenryUser {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String age;
    private Boolean sex;
    private Integer phone;
    @Generated(hash = 1716794524)
    public HenryUser(Long id, String name, String age, Boolean sex, Integer phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }
    public HenryUser( String name, String age, Boolean sex, Integer phone) {

        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }
    @Generated(hash = 1293809910)
    public HenryUser() {
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
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public Boolean getSex() {
        return this.sex;
    }
    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    public Integer getPhone() {
        return this.phone;
    }
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

}
