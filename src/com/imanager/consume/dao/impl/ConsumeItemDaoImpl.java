package com.imanager.consume.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.consume.dao.IConsumeItemDao;
import com.imanager.consume.domain.ConsumeItem;
import com.imanager.consume.domain.input.ConsumeSearchObj;

public class ConsumeItemDaoImpl implements IConsumeItemDao {

	private SqlMapClientOperations sqlMapClientTemplate;
	
	/**
	 * 添加一条消费记录
	 * @param consumeItem
	 */
	public void insertConsumeItem(ConsumeItem consumeItem) {
		sqlMapClientTemplate.insert("ConsumeItem.insertConsumeItem", consumeItem);		
	}
	
	/**
	 * 根据LoginId和查询条件获得消费列表
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConsumeItem> getConsumeItemListBySearch(ConsumeSearchObj searchObj) {
		/*Map map = new HashMap();
		map.put("itemName", searchObj.getItemName());
		map.put("address", searchObj.getAddress());
		map.put("startDate", searchObj.getStartDate());
		map.put("endDate", searchObj.getEndDate());
		map.put("consumeType", searchObj.getConsumeType());
		map.put("loginId", loginId);*/
		List consumeItemList = sqlMapClientTemplate.queryForList("ConsumeItem.getConsumeItemListBySearch", searchObj);
		if(consumeItemList != null)
			return consumeItemList;
		else
			return new ArrayList<ConsumeItem>();
	}

	/**
	 * 根据LoginId和查询条件获得消费列表消费总额
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public double getConsumeItemListSumBySearch(ConsumeSearchObj searchObj) {
		/*Map map = new HashMap();
		map.put("itemName", searchObj.getItemName());
		map.put("address", searchObj.getAddress());
		map.put("startDate", searchObj.getStartDate());
		map.put("endDate", searchObj.getEndDate());
		map.put("consumeType", searchObj.getConsumeType());
		map.put("loginId", loginId);*/
		Object obj = sqlMapClientTemplate.queryForObject("ConsumeItem.getConsumeItemListSumBySearch", searchObj);
		if(obj != null)
			return ((Double)obj).doubleValue();
		else
			return 0;
	}

	/**
	 * 根据ItemId获得消费记录
	 * @param itemId
	 * @return
	 */
	public ConsumeItem getConsumeItemById(String consumeItemId){
		return (ConsumeItem)sqlMapClientTemplate.queryForObject("ConsumeItem.getConsumeItemById", consumeItemId);
	}
	
	/**
	 * 更新一条消费记录
	 * @param consumeItem
	 * @return
	 */
	public boolean updateConsumeItem(ConsumeItem consumeItem){
		Integer result = sqlMapClientTemplate.update("ConsumeItem.updateConsumeItem", consumeItem);
		return result == 1;
	}
	
	/**
	 * 逻辑删除消费记录
	 * @param consumeItemId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteConsumeItemById(String consumeItemId, String loginId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("consumeItemId", consumeItemId);
		map.put("modifier", loginId);
		Integer result = sqlMapClientTemplate.update("ConsumeItem.logicDeleteConsumeItemById", map);
		return result == 1;
	}
	
	
	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
