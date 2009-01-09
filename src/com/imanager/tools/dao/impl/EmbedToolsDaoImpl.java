package com.imanager.tools.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.tools.dao.IEmbedToolsDao;
import com.imanager.tools.domain.EmbedTools;

public class EmbedToolsDaoImpl implements IEmbedToolsDao {
	
	private SqlMapClientOperations sqlMapClientTemplate;
	
	 /**
	  * ���Ƕ�빤��
	 * @param embedTools
	 */
	public void insertEmbedTools(EmbedTools embedTools){
		 sqlMapClientTemplate.insert("EmbedTools.insertEmbedTools", embedTools);
	 }
	 
	 /**
	  * ���Ƕ�빤���б�
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public List<EmbedTools> getEmbedToolsList(String loginId){
		 return sqlMapClientTemplate.queryForList("EmbedTools.getEmbedToolsList", loginId);
	 }
		
	/**
	 * ����Id���һ��Ƕ�빤��
	 * @param embedToolsId
	 * @return
	 */
	public EmbedTools getEmbedToolsById(String embedToolsId){
		return (EmbedTools)sqlMapClientTemplate.queryForObject("EmbedTools.getEmbedToolsById", embedToolsId);
	}
	 
	/**
	 * ����һ��Ƕ�빤��
	 * @param embedTools
	 * @return
	 */
	public boolean updateEmbedTools(EmbedTools embedTools){
		return sqlMapClientTemplate.update("EmbedTools.updateEmbedTools", embedTools) == 1;
	}
	 
	/**
	 * �߼�ɾ��һ��Ƕ�빤��
	 * @param embedToolsId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteEmbedToolsById(String embedToolsId, String loginId){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("embedToolsId", embedToolsId);
		 map.put("modifier", loginId);
		 return sqlMapClientTemplate.update("EmbedTools.logicDeleteEmbedToolsById", map) == 1;
	 }
	

	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
