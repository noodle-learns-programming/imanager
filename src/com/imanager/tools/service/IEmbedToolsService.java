package com.imanager.tools.service;

import java.util.List;

import com.imanager.tools.domain.EmbedTools;

public interface IEmbedToolsService {
	
	/**
	  * 添加嵌入工具
	 * @param embedTools
	 */
	public void insertEmbedTools(EmbedTools embedTools);
	 
	 /**
	  * 获得嵌入工具列表
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public List<EmbedTools> getEmbedToolsList(String loginId);
	/**
	 * 根据Id获得一个嵌入工具
	 * @param embedToolsId
	 * @return
	 */
	public EmbedTools getEmbedToolsById(String embedToolsId);
	 
	/**
	 * 更新一个嵌入工具
	 * @param embedTools
	 * @return
	 */
	public boolean updateEmbedTools(EmbedTools embedTools);
	 
	/**
	 * 逻辑删除一个嵌入工具
	 * @param embedToolsId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteEmbedToolsById(String embedToolsId, String loginId);

}
