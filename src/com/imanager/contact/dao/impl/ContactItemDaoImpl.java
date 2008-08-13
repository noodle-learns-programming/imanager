package com.imanager.contact.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.contact.dao.IContactItemDao;
import com.imanager.contact.domain.ContactItem;
import com.imanager.contact.domain.input.ContactItemSearchObj;

public class ContactItemDaoImpl implements IContactItemDao {
	
	private SqlMapClientOperations sqlMapClientTemplate;

	public void insertContactItem(ContactItem contactItem) {
		sqlMapClientTemplate.insert("ContactItem.insertContactItem", contactItem);
	}

	@SuppressWarnings("unchecked")
	public List<ContactItem> getContactItemListBySearch(ContactItemSearchObj contactSearchObj) {
		List contactItemList = sqlMapClientTemplate.queryForList("ContactItem.getContactItemListBySearch", contactSearchObj);
		 if(contactItemList != null){
			 return contactItemList;
		 }else{
			 return new ArrayList<ContactItem>();
		 }
	}

	public ContactItem getContactItemById(String contactItemId) {
		return (ContactItem)sqlMapClientTemplate.queryForObject("ContactItem.getContactItemById", contactItemId);
	}

	public boolean updateContactItem(ContactItem contactItem) {
		Integer result = sqlMapClientTemplate.update("ContactItem.updateContactItem", contactItem);
		return result == 1;
	}
	
	public boolean logicDeleteContactItem(String contactItemId, String currentLoginId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("contactItemId", contactItemId);
		map.put("modifier", currentLoginId);
		
		Integer result = sqlMapClientTemplate.update("ContactItem.logicDeleteContactItem", map);
		return result == 1;
	}

	

	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
