package com.ecs.dao.impl;

import org.springframework.stereotype.Repository;

import com.ecs.dao.UserDao;
import com.ecs.util.jdbc.BaseJdbcDao;

@Repository("userDao")
public class UserDaoImpl extends BaseJdbcDao implements UserDao {

	
}
