/**   
 * @Project: tfs-plm-biz
 * @Title: BaseJdbcDao.java 
 * @Package com.tfstec.plm.biz.util.jdbc 
 * @Description: Jdbc的Dao服务器基础类 
 * @author lx 
 * @date 2016年4月14日 上午11:45:15 
 * @Copyright: 2016 年 海钜信达. All rights reserved  
 * @version V1.0   
 */
package com.ecs.util.jdbc;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.kaka.base.StringUtils;
import com.kaka.base.jdbc.mapper.SqlSupport;
import com.kaka.base.jdbc.tools.BeanUtils;


/** 
 * @ClassName BaseJdbcDao  
 * @Description Jdbc的Dao服务器基础类 
 * @author lx 
 * @date 2016年4月14日  
 *   
 */
public abstract class BaseJdbcDao extends JdbcDaoSupport {
	protected final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	/** 
	 * @Title: insert 
	 * @Description: 根据某个Map对象自动生成SQL插入数据库 
	 * @param tableName 表名称
	 * @param dataMap 插入数据<key:字段，value:值>
	 * @return void    返回类型
	 */
	public void insert(String tableName,Map dataMap){
		String[] cls = StringUtils.getMapKeys(dataMap);  
		String cs = SqlSupport.columnsToQueryString(cls);
		String vs = SqlSupport.columnsToInsertValueString(cls);
		String sql = "insert into "+ tableName+"("+cs+") values("+vs+")"; 
		super.executeForObject(sql, dataMap); 
	}
	
	/** 
	 * @Title: batchInsert 
	 * @Description: 批量插入 
	 * @param sql 执行语句,例如：insert into tab(userName,age) values(:userName,:age)
	 * @param tbObj1 集合数据，List<Map<String,Object>>
	 * @return void    返回类型
	 */
	public void batchInsert(String sql,List tbObj1){
		super.batchUpdateForBean(sql, tbObj1);
	}
	
	/** 
	 * @Title: update 
	 * @Description: 根据某个Map对象自动生成SQL更新数据库 
	 * @param tableName  表名称
	 * @param dataMap  更新数据<key:字段，value:值>
	 * @param sqlWhere 用于做过滤 ，where id=:id;
	 * @return 参数说明
	 * @return int    返回类型
	 */
	public int update(String tableName,Map dataMap,String sqlWhere){ 
		if(sqlWhere==null){
			sqlWhere="";
		}
		String[] cls = StringUtils.getMapKeys(dataMap); 
		String us = SqlSupport.columnsToUpdateString(cls); 
		String sql = "update "+ tableName+" set "+us+" "+sqlWhere;  
		return super.executeForObject(sql, dataMap); 
	}
	
	/** 
	 * @Title: queryForInteger 
	 * @Description: 查询单行单列Integer类型数据 
	 * @param sql 执行语句
	 * @param parameters 查询条件
	 * @return 参数说明
	 * @return Integer    返回类型
	 */
	public Integer queryForInteger(String sql, Map parameters){ 
		try {
			Map map = SqlSupport.paramBeanSql(sql);
			List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
			String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
			Object[] robj = BeanUtils.paramBeanMapper(parameters, obj);
			return (Integer)jdbcTemplate.queryForObject(rsql, robj, java.lang.Integer.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	/** 
	 * @Title: queryForLong 
	 * @Description: 查询单行单列Long类型数据 
	 * @param sql 执行语句
	 * @return 参数说明
	 * @return long    返回类型
	 */
	public long queryForLong(String sql){ 
		return jdbcTemplate.queryForLong(sql);
	}
	
	/** 
	 * @Title: queryForString 
	 * @Description: 查询单行单列String类型数据 
	 * @param sql 执行语句
	 * @param args 查询条件
	 * @return 参数说明
	 * @return String    返回类型
	 */
	public String queryForString(String sql,Object[] args){ 
		try {
			//String name = (String) jdbcTemplate.queryForObject( "SELECT name FROM USER WHERE id = ?", new Object[] {id}, java.lang.String.class);
			return  (String) jdbcTemplate.queryForObject(sql, args, java.lang.String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	/** 
	 * @Title: queryForString 
	 * @Description: 查询单行单列String类型数据 
	 * @param sql 执行语句：(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @return 参数说明
	 * @return String    返回类型
	 */
	public String queryForString(String sql, Map parameters){ 
		try {
			Map map = SqlSupport.paramBeanSql(sql);
			List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
			String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
			Object[] robj = BeanUtils.paramBeanMapper(parameters, obj);
			return (String)jdbcTemplate.queryForObject(rsql, robj, java.lang.String.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	/** 
	 * @Title: queryForDouble 
	 * @Description: 查询单行单列Double类型数据 
	 * @param sql 执行语句
	 * @param args 查询条件
	 * @return 参数说明
	 * @return Double    返回类型
	 */
	public Double queryForDouble(String sql,Object[] args){ 
		try {
			return  (Double) jdbcTemplate.queryForObject(sql, args, java.lang.Double.class);
		} catch (DataAccessException e) {
			return null;
		}
	}	
	
	/** 
	 * @Title: queryForDouble 
	 * @Description: 查询单行单列Double类型数据 
	 * @param sql 执行语句：(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @return 参数说明
	 * @return Double    返回类型
	 */
	public Double queryForDouble(String sql, Map parameters){ 
		try {
			Map map = SqlSupport.paramBeanSql(sql);
			List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
			String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
			Object[] robj = BeanUtils.paramBeanMapper(parameters, obj);
			return (Double)jdbcTemplate.queryForObject(rsql, robj, java.lang.Double.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/** 
	 * @Title: executeForObject 
	 * @Description: 执行insert，update，delete等操作 
	 * @param sql 执行语句，例如insert into users (name,login_name,password)
	 *                          values(:name,:loginName,:password)
	 *                          参数用冒号,参数为bean的属性名
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @return 参数说明
	 * @return int    返回类型
	 */
	public int executeForObject(String sql, Object parameters) { 
		return super.executeForObject(sql, parameters);
	}
	
	/** 
	 * @Title: findForListObject 
	 * @Description: 根据sql语句，返回List对象集合 
	 * @param sql 执行语句，语句(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @param clazz 需要自动生成的Bean对象类型
	 * @return List bean对象
	 * @return List    返回类型
	 */
	public List findForListObject(final String sql, Map parameters,
			final Class clazz) {
		return super.findForListObject(sql, parameters, clazz);
	}
}
