/**   
 * @Project: tfs-plm-biz 
 * @Title: JdbcDaoSupport.java 
 * @Package com.tfstec.plm.biz.util.jdbc 
 * @Description: Jdbc操作数据库模板 
 * @author lx 
 * @date 2016年4月13日 上午10:50:43 
 * @Copyright: 2016 年 海钜信达. All rights reserved  
 * @version V1.0   
 */
package com.ecs.util.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.kaka.base.jdbc.mapper.BeanRowMapper;
import com.kaka.base.jdbc.mapper.MapRowMapper;
import com.kaka.base.jdbc.mapper.SqlSupport;
import com.kaka.base.jdbc.tools.BeanUtils;


/**
 * @ClassName JdbcDaoSupport  
 * @Description Jdbc操作数据库模板 
 * @author lx 
 * @date 2016年4月13日  
 */
public abstract class JdbcDaoSupport {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	/** 
	 * @Title: findForListMap 
	 * @Description: 根据sql语句，返回List对象集合 
	 * @param sql 执行语句(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @return 参数说明
	 * @return List<Map<String,Object>>    返回类型
	 */
	protected List<Map<String, Object>> findForListMap(final String sql, Map parameters) {
		logger.debug(sql);
		logger.debug(parameters.toString());
		Map map = SqlSupport.paramBeanSql(sql);
		List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
		String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
		Object[] robj = BeanUtils.paramBeanMapper(parameters, obj);
		return jdbcTemplate.query(rsql, robj, new MapRowMapper());
	}

	/** 
	 * @Title: findUniqueObject 
	 * @Description: 根据sql语句，返回Object对象 
	 * @param sql 执行语句(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @param clazz  需要自动生成的Bean对象类型
	 * @return 参数说明
	 * @return Object    返回类型
	 */
	protected Object findUniqueObject(final String sql, Map parameters, Class clazz) {
		Map map = SqlSupport.paramBeanSql(sql);
		List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
		String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
		Object[] robj = BeanUtils.paramBeanMapper(parameters, obj);
		List rs = jdbcTemplate.query(rsql, robj, new BeanRowMapper(clazz));
		if (rs != null && rs.size() > 0){
			return rs.get(0);
		}else{
			return null;
		}
	}

	/** 
	 * @Title: findForListObject 
	 * @Description: 根据sql语句，返回List对象集合 
	 * @param sql 执行语句(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @param clazz 需要自动生成的Bean对象类型
	 * @return 参数说明
	 * @return List    返回类型
	 */
	protected List findForListObject(final String sql, Map parameters, final Class clazz) {
		logger.debug(sql);
		logger.debug(parameters.toString());
		Map map = SqlSupport.paramBeanSql(sql);
		List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
		String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
		Object[] robj = BeanUtils.paramBeanMapper(parameters, obj);
		return jdbcTemplate.query(rsql, robj, new BeanRowMapper(clazz));
	}

	/** 
	 * @Title: findUniqueMap 
	 * @Description: 根据sql语句，返回Map对象 
	 * @param sql 执行语句(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @return 参数说明
	 * @return Map    返回类型
	 */
	protected Map findUniqueMap(final String sql, Map parameters) {
		Map map = SqlSupport.paramBeanSql(sql);
		List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
		String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
		Object[] robj = BeanUtils.paramBeanMapper(parameters, obj);
		List<Map> lm = jdbcTemplate.query(rsql, robj, new MapRowMapper());
		if (lm.size() > 0){
			return lm.get(0);
		}else{
			return null;
		}
	}

	/** 
	 * @Title: getUniqueColumn 
	 * @Description: 返回单列的数据集合
	 * @param sql 执行语句(参数用冒号加参数名，例如select
	 *            count(*) from tb where id=:id)
	 * @param columnName 列名称
	 * @param parameters 参数集合(key为参数名，value为参数值)
	 * @return 参数说明
	 * @return List    返回类型
	 */
	protected final List getUniqueColumn(String sql, String columnName, Map parameters) {
		List<Map<String, Object>> datas = this.findForListMap(sql, parameters);
		List rlist = new ArrayList();
		if (datas != null) {
			for (Map<String, Object> map : datas) {
				rlist.add(map.get(columnName));
			}
		}
		return rlist;
	}

	/** 
	 * @Title: getRecordCountBySql 
	 * @Description: 提取记录总行数 
	 * @param sql 执行语句(参数用冒号加参数名，例如select 
	 *            col from tb where userno=:userno)
	 * @param param  参数集合(key为参数名，value为参数值)
	 * @return 参数说明
	 * @return int    返回类型
	 */
	protected final int getRecordCountBySql(String sql, Map param) {
		String nsql = "select count(1) as DATACOUNT from (" + sql + ") abc ";
		Map map = SqlSupport.paramBeanSql(nsql);
		List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
		String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
		Object[] robj = BeanUtils.paramBeanMapper(param, obj);
		return jdbcTemplate.queryForInt(rsql, robj);
	}

	/** 
	 * @Title: getPagingQueryResultBySql 
	 * @Description: 查询SQL分页后数据(oracle) 
	 * @param sql 执行语句(参数用冒号加参数名，例如select 
	 *            col from tb where userno=:userno)
	 * @param parameterMap 参数集合(key为参数名，value为参数值)
	 * @param startNumber 起始行号
	 * @param endNumber 结束行号
	 * @param orderBy 是否需要排序
	 * @return 参数说明
	 * @return List<Map<String,Object>>    返回类型
	 */
	protected final List<Map<String, Object>> getPagingQueryResultBySql(
			String sql, Map parameterMap, int startNumber, int endNumber,
			boolean orderBy) {
		String nsql = "";
		if (orderBy) {
			nsql = " SELECT * from( "
					+ " SELECT  t.*,row_number() over(order by null) as rowNumber "
					+ "        FROM (" + sql + ") t) " + " WHERE rowNumber > "
					+ startNumber + " and rowNumber <=" + endNumber;
		} else {
			nsql = "select * from (" + "select t.*, rownum as my_rownum from ("
					+ sql + ") t where rownum <= " + endNumber
					+ ") where my_rownum > " + startNumber;
		}
		return this.findForListMap(nsql, parameterMap);
	}
	
	/** 
	 * @Title: getPagingQueryResultBySql 
	 * @Description: 查询SQL分页后数据(mysql) 
	 * @param sql 执行语句(参数用冒号加参数名，例如select 
	 *            col from tb where userno=:userno)
	 * @param parameterMap 参数集合(key为参数名，value为参数值)
	 * @param startNumber 起始行号
	 * @param endNumber 结束行号
	 * @return 参数说明
	 * @return List<Map<String,Object>>    返回类型
	 */
	protected final List<Map<String, Object>> getPagingQueryResultBySql(String sql,
			Map parameterMap, int startNumber, int endNumber) {
		String nsql = sql + "limit " + startNumber + "," + endNumber;
		return this.findForListMap(nsql, parameterMap);
	}
	
	/** 
	 * @Title: executeForObject 
	 * @Description: 执行insert，update，delete等操作 
	 * @param sql 执行语句(参数用冒号,参数为bean的属性名，例如
	 *            insert into users (name,login_name,password) values(:name,:loginName,:password))
	 * @param bean 执行的参数
	 * @return 参数说明
	 * @return int    返回类型
	 */
	protected int executeForObject(final String sql, Object bean) {
		logger.debug(sql);
		if (bean != null) {
			Map map = SqlSupport.paramBeanSql(sql);
			List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
			String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
			final Object[] robj = BeanUtils.paramBeanMapper(bean, obj);
			return jdbcTemplate.update(rsql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstate) throws SQLException {
					for (int i = 0; i < robj.length; i++) {
						if (robj[i] != null) {
							if (robj[i] instanceof java.util.Date) {
								java.sql.Timestamp sdate = new Timestamp(((java.util.Date) robj[i])
										.getTime());
								pstate.setTimestamp(i + 1, sdate);
							} else {
								pstate.setObject(i + 1, robj[i]);
							}
							logger.debug("==============" + robj[i]);
						} else {
							pstate.setNull(i + 1, Types.VARCHAR);
						}
					}
				}
			});
		} else {
			return jdbcTemplate.update(sql);
		}
	}

	/** 
	 * @Title: executeForMap 
	 * @Description: 执行insert，update，delete等操作 
	 * @param sql 执行语句(参数用冒号,参数为bean的属性名，例如
	 *            insert into users (name,login_name,password) values(:name,:loginName,:password))
	 * @param parameters 参数集合(key为参数名，value为参数值), 参数用冒号,参数为Map的key名
	 * @return 参数说明
	 * @return int    返回类型
	 */
	protected int executeForMap(final String sql, Map parameters) {
		return executeForObject(sql, parameters);
	}

	/** 
	 * @Title: batchUpdateForObject 
	 * @Description: 通用的批量处理方法 
	 * @param sql 执行语句
	 * @param batch 批量操作数据
	 * @return 参数说明
	 * @return int[]    返回类型
	 */
	private int[] batchUpdateForObject(final String sql, List<?> batch) {
		logger.debug(sql);
		Map map = SqlSupport.paramBeanSql(sql);
		List obj = (List) map.get(SqlSupport.DAO_PARAM_PROERTY);
		String rsql = (String) map.get(SqlSupport.DAO_PARAM_SQL);
		if (batch != null && batch.size() > 0) {
			final List<Object[]> list = new ArrayList<Object[]>();
			for (int i = 0; i < batch.size(); i++) {
				Object[] robj = BeanUtils.paramBeanMapper(batch.get(i), obj);
				list.add(robj);
			}
			return jdbcTemplate.batchUpdate(rsql, new BatchPreparedStatementSetter() {
				@Override
				public int getBatchSize() {
					return list.size();
				}

				@Override
				public void setValues(PreparedStatement pstate, int index) throws SQLException {
					Object[] trobj = list.get(index);
					for (int i = 0; i < trobj.length; i++) {
						if (trobj[i] != null) {
							if (trobj[i] instanceof java.util.Date) {
								java.sql.Timestamp sdate = new Timestamp(
										((java.util.Date) trobj[i]).getTime());
								pstate.setTimestamp(i + 1, sdate);
							} else {
								pstate.setObject(i + 1, trobj[i]);
							}
						} else {
							pstate.setNull(i + 1, Types.VARCHAR);
						}
					}
				}
			});
		} else {
			return new int[] { jdbcTemplate.update(sql) };
		}
	}

	/** 
	 * @Title: batchUpdateForBean 
	 * @Description: 批量处理操作 
	 * @param sql 执行语句，例如：update t_actor set first_name = :firstName, last_name = :lastName where id = :id
	 * @param batch 批量操作数据
	 * @return 参数说明
	 * @return int[]    返回类型
	 */
	public int[] batchUpdateForBean(final String sql, List batch) {
		return batchUpdateForObject(sql, batch);
	}

	/** 
	 * @Title: batchUpdateForMap 
	 * @Description: 批量处理操作 
	 * @param sql 执行语句，例如：update t_actor set first_name = :firstName, last_name = :lastName where id = :id
	 * @param batch 批量操作数据，参数集合(key为参数名，value为参数值), 参数用冒号,参数为Map的key名
	 * @return 参数说明
	 * @return int[]    返回类型
	 */
	public int[] batchUpdateForMap(final String sql, List<Map> batch) {
		return batchUpdateForObject(sql, batch);
	}
}
