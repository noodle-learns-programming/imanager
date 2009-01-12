package com.imanager.tools.service.impl;

import java.util.List;

import com.imanager.tools.dao.IEmbedToolsDao;
import com.imanager.tools.domain.EmbedTools;
import com.imanager.tools.service.IEmbedToolsService;

public class EmbedToolsServiceImpl implements IEmbedToolsService {
	
	private IEmbedToolsDao embedToolsDao;

	/* (non-Javadoc)
	 * @see com.imanager.tools.service.IEmbedToolsService#getEmbedToolsById(java.lang.String)
	 */
	public EmbedTools getEmbedToolsById(String embedToolsId) {
		return embedToolsDao.getEmbedToolsById(embedToolsId);
	}

	/* (non-Javadoc)
	 * @see com.imanager.tools.service.IEmbedToolsService#getEmbedToolsList()
	 */
	public List<EmbedTools> getEmbedToolsList(String loginId) {
		return embedToolsDao.getEmbedToolsList(loginId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.tools.service.IEmbedToolsService#getEmbedToolsListByType(java.lang.String, java.lang.String)
	 */
	public List<EmbedTools> getEmbedToolsListByType(String loginId, String toolsType){
		return embedToolsDao.getEmbedToolsListByType(loginId, toolsType);
	}

	/* (non-Javadoc)
	 * @see com.imanager.tools.service.IEmbedToolsService#insertEmbedTools(com.imanager.tools.domain.EmbedTools)
	 */
	public void insertEmbedTools(EmbedTools embedTools) {
		embedToolsDao.insertEmbedTools(embedTools);		
	}

	/* (non-Javadoc)
	 * @see com.imanager.tools.service.IEmbedToolsService#logicDeleteEmbedToolsById(java.lang.String, java.lang.String)
	 */
	public boolean logicDeleteEmbedToolsById(String embedToolsId, String loginId) {
		return embedToolsDao.logicDeleteEmbedToolsById(embedToolsId, loginId);
	}

	/* (non-Javadoc)
	 * @see com.imanager.tools.service.IEmbedToolsService#updateEmbedTools(com.imanager.tools.domain.EmbedTools)
	 */
	public boolean updateEmbedTools(EmbedTools embedTools) {
		return embedToolsDao.updateEmbedTools(embedTools);
	}

	public IEmbedToolsDao getEmbedToolsDao() {
		return embedToolsDao;
	}

	public void setEmbedToolsDao(IEmbedToolsDao embedToolsDao) {
		this.embedToolsDao = embedToolsDao;
	}

}
