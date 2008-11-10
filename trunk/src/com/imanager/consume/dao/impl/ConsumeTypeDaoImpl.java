package com.imanager.consume.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.consume.dao.IConsumeTypeDao;
import com.imanager.consume.domain.ConsumeType;

public class ConsumeTypeDaoImpl implements IConsumeTypeDao {

	private SqlMapClientOperations sqlMapClientTemplate;
	
	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#insertConsumeType(com.imanager.consume.domain.ConsumeType)
	 */
	public void insertConsumeType(ConsumeType consumeType) {
		sqlMapClientTemplate.insert("ConsumeType.insertConsumeType", consumeType);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#getConsumeTypeListByLoginId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ConsumeType> getConsumeTypeListByLoginId(String loginId) {
		List consumeTypeList = sqlMapClientTemplate.queryForList("ConsumeType.getConsumeTypeListByLoginId", loginId);
		if(consumeTypeList != null)
			return consumeTypeList;
		else
			return new ArrayList<ConsumeType>();
	}


	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#getConsumeTypeByTypeId(java.lang.String)
	 */
	public ConsumeType getConsumeTypeById(String consumeTypeId) {
		return (ConsumeType)sqlMapClientTemplate.queryForObject("ConsumeType.getConsumeTypeById", consumeTypeId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#updateConsumeType(com.imanager.consume.domain.ConsumeType)
	 */
	public boolean updateConsumeType(ConsumeType consumeType) {
		Integer result = sqlMapClientTemplate.update("ConsumeType.updateConsumeType", consumeType);
		return result == 1;
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#logicDeleteConsumeTypeByTypeId(java.lang.String, java.lang.String)
	 */
	public boolean logicDeleteConsumeTypeById(String consumeTypeId, String loginId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("consumeTypeId", consumeTypeId);
		map.put("modifier", loginId);
		Integer result = sqlMapClientTemplate.update("ConsumeType.logicDeleteConsumeTypeById", map);
		return result == 1;
	}
	
	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
}
