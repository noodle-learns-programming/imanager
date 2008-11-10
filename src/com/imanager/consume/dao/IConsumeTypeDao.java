package com.imanager.consume.dao;

import java.util.List;

import com.imanager.consume.domain.ConsumeType;

public interface IConsumeTypeDao {

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
