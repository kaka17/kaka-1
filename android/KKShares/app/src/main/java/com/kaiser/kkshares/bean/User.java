package com.kaiser.kkshares.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by JackWaiting on 2016/8/5.
 */
@Entity
public class User {
    @Id
    private long id;
    private String name;
    private int age;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 555886430)
    public User(long id, String name, int age, String code) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.code = code;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    //下面省去了 setter/getter
}
