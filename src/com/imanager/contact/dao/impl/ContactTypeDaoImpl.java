package com.imanager.contact.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.contact.dao.IContactTypeDao;
import com.imanager.contact.domain.ContactType;

public class ContactTypeDaoImpl implements IContactTypeDao {
	
	private SqlMapClientOperations sqlMapClientTemplate;
	
	public void insertContactType(ContactType contactType){
		sqlMapClientTemplate.insert("ContactType.insertContactType", contactType);
	}
	
	@SuppressWarnings("unchecked")
	public List<ContactType> getContactTypeListByLoginId(String loginId){
		List contactTypeList = sqlMapClientTemplate.queryForList("ContactType.getContactTypeListByLoginId", loginId);
		 if(contactTypeList != null){
			 return contactTypeList;
		 }else{
			 return new ArrayList<ContactType>();
		 }
	}
	
	public ContactType getContactTypeById(String contactTypeId){
		return (ContactType)sqlMapClientTemplate.queryForObject("ContactType.getContactTypeById", contactTypeId);
	}
	
	public boolean updateContactType(ContactType contactType){
		Integer result = sqlMapClientTemplate.update("ContactType.updateContactType", contactType);
		return result == 1;
	}
	
	public boolean logicDeleteContactType(String contactTypeId, String currentLoginId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("contactTypeId", contactTypeId);
		map.put("modifier", currentLoginId);
		Integer result = sqlMapClientTemplate.update("ContactType.logicDeleteContactType", map);
		return result == 1;
	}
	

	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
