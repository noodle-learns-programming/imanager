package com.imanager.login.dao.impl;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.login.dao.ILoginDao;


public class LoginDaoImpl implements ILoginDao {
	
	private SqlMapClientOperations sqlMapClientTemplate;
	
	public boolean updateLastLoginDate(String loginId){
		Integer result = sqlMapClientTemplate.update("Login.updateLastLoginDate", loginId);
		return result == 1;
	}
	
	
	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
}
