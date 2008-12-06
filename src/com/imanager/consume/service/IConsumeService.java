package com.imanager.consume.service;

import java.util.List;

import com.imanager.consume.domain.ConsumeItem;
import com.imanager.consume.domain.ConsumeType;
import com.imanager.consume.domain.input.ConsumeSearchObj;

public interface IConsumeService {
	/**
	 * 添加一条消费记录
	 * @param consumeItem
	 */
	public void insertConsumeItem(ConsumeItem consumeItem);
	
	/**
	 * 根据LoginId和查询条件获得消费列表
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	public List<ConsumeItem> getConsumeItemListBySearch(ConsumeSearchObj searchObj);
	
	/**
	 * 根据LoginId和查询条件获得消费列表消费总额
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	public double getConsumeItemListSumBySearch(ConsumeSearchObj searchObj);

	/**
	 * 根据ItemId获得消费记录
	 * @param itemId
	 * @return
	 */
	public ConsumeItem getConsumeItemById(String consumeItemId);
	
	/**
	 * 更新一条消费记录
	 * @param consumeItem
	 * @return
	 */
	public boolean updateConsumeItem(ConsumeItem consumeItem);
	
	/**
	 * 逻辑删除消费记录
	 * @param consumeItemId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteConsumeItemById(String consumeItemId, String loginId);

	/**
	 * 添加一条消费类型
	 * @param consumeType
	 */
	public void insertConsumeType(ConsumeType consumeType);
	
	/**
	 * 根据LoginId获得类型列表
	 * @param loginId
	 * @return
	 */
	public List<ConsumeType> getConsumeTypeListByLoginId(String loginId);
	
	/**
	 * 根据TypeId获得消费类型
	 * @param typeId
	 * @return
	 */
	public ConsumeType getConsumeTypeById(String consumeTypeId);
	
	/**
	 * 更新一个消费类型
	 * @param consumeType
	 * @return
	 */
	public boolean updateConsumeType(ConsumeType consumeType);
	
	/**
	 * 逻辑删除消费类型
	 * @param consumeTypeId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteConsumeTypeById(String consumeTypeId, String loginId);


}
