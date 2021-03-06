package com.kaiser.kkshares.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.kaiser.kkshares.bean.User;

import java.util.ArrayList;
import java.util.List;


public class DBManager {
    UserDao userDao;
    DBManager dbManagers;

    public static DBManager dbManager;
    public static final DBManager getInstance(Context context){
        if (dbManager==null){
            dbManager=new DBManager(context);
        }
        return dbManager;
    }


    public DBManager(Context context) {
        DaoMaster.DevOpenHelper  helper = new DaoMaster.DevOpenHelper(context, "JackWaiting-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    public ArrayList<User> getDatasByType(int type){
        return (ArrayList<User>) userDao.queryBuilder().where(UserDao.Properties.Name.eq(type)).list();
    }

    public List<User> getUserAll(){
        return  userDao.loadAll();
    }

    public void insertUser(User user){
        userDao.insert(user);
    }

    public void deleteUser(User user){
        userDao.delete(user);



    }

    public void deleteAll(){
        userDao.deleteAll();
    }

    public void updateUser(User user){

        userDao.update(user);
    }


}
