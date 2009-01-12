package com.imanager.tools.dao;

import java.util.List;

import com.imanager.tools.domain.EmbedTools;

public interface IEmbedToolsDao {
	
	/**
	  * ���Ƕ�빤��
	 * @param embedTools
	 */
	public void insertEmbedTools(EmbedTools embedTools);
	 
	 /**
	  * ���Ƕ�빤���б�
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public List<EmbedTools> getEmbedToolsList(String loginId);
	
	 /**
	  * �������ͻ��Ƕ�빤���б�
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public List<EmbedTools> getEmbedToolsListByType(String loginId, String toolsType);
	
	/**
	 * ����Id���һ��Ƕ�빤��
	 * @param embedToolsId
	 * @return
	 */
	public EmbedTools getEmbedToolsById(String embedToolsId);
	 
	/**
	 * ����һ��Ƕ�빤��
	 * @param embedTools
	 * @return
	 */
	public boolean updateEmbedTools(EmbedTools embedTools);
	 
	/**
	 * �߼�ɾ��һ��Ƕ�빤��
	 * @param embedToolsId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteEmbedToolsById(String embedToolsId, String loginId);

}
