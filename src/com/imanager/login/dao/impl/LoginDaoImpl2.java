package com.imanager.login.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.login.dao.ILoginDao;
import com.imanager.user.domain.User;


public class LoginDaoImpl2 implements ILoginDao {
	
	private SqlMapClientOperations sqlMapClientTemplate;
	
	public User getUserByLoginId(String loginId, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginId", loginId);
		map.put("password", password);
		return (User)sqlMapClientTemplate.queryForObject("User.getUserByLoginId", map);
	}
	
	public boolean updateLastLoginDate(String loginId){
		Integer result = sqlMapClientTemplate.update("User.updateLastLoginDate", loginId);
		return result == 1;
	}
	
	
	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
}
