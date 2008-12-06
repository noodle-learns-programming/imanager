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
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.dao.IContactTypeDao#insertContactType(com.imanager.contact.domain.ContactType)
	 */
	public void insertContactType(ContactType contactType){
		sqlMapClientTemplate.insert("ContactType.insertContactType", contactType);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.dao.IContactTypeDao#getContactTypeListByLoginId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ContactType> getContactTypeListByLoginId(String loginId){
		List contactTypeList = sqlMapClientTemplate.queryForList("ContactType.getContactTypeListByLoginId", loginId);
		 if(contactTypeList != null){
			 return contactTypeList;
		 }else{
			 return new ArrayList<ContactType>();
		 }
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.dao.IContactTypeDao#getContactTypeById(java.lang.String)
	 */
	public ContactType getContactTypeById(String contactTypeId){
		return (ContactType)sqlMapClientTemplate.queryForObject("ContactType.getContactTypeById", contactTypeId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.dao.IContactTypeDao#updateContactType(com.imanager.contact.domain.ContactType)
	 */
	public boolean updateContactType(ContactType contactType){
		Integer result = sqlMapClientTemplate.update("ContactType.updateContactType", contactType);
		return result == 1;
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.contact.dao.IContactTypeDao#logicDeleteContactType(java.lang.String, java.lang.String)
	 */
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
