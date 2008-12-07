package com.imanager.user.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.user.dao.IUserDao;
import com.imanager.user.domain.User;

public class UserDaoImpl implements IUserDao {
	private SqlMapClientOperations sqlMapClientTemplate;
	
	/* (non-Javadoc)
	 * @see com.imanager.user.dao.IUserDao#getUserByLoginIdNPassword(java.lang.String, java.lang.String)
	 */
	public User getUserByLoginIdNPassword(String loginId, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginId", loginId);
		map.put("password", password);
		return (User)sqlMapClientTemplate.queryForObject("User.getUserByLoginIdNPassword", map);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.dao.IUserDao#getUserByUserId(java.lang.String)
	 */
	public User getUserByUserId(String userId){
		return (User)sqlMapClientTemplate.queryForObject("User.getUserByUserId", userId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.dao.IUserDao#getUserByLoginId(java.lang.String)
	 */
	public User getUserByLoginId(String loginId){
		return (User)sqlMapClientTemplate.queryForObject("User.getUserByLoginId", loginId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.dao.IUserDao#insertUser(com.imanager.user.domain.User)
	 */
	public void insertUser(User user){
		sqlMapClientTemplate.insert("User.insertUser", user);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.dao.IUserDao#updateUserInfo(com.imanager.user.domain.User)
	 */
	public boolean updateUserInfo(User user){
		Integer result = sqlMapClientTemplate.update("User.updateUserInfo", user);
		return result == 1;
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.dao.IUserDao#updateUserPassword(com.imanager.user.domain.User)
	 */
	public boolean updateUserPassword(User user){
		Integer result = sqlMapClientTemplate.update("User.updateUserPassword", user);
		return result == 1;
	}
	
	
	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
}
