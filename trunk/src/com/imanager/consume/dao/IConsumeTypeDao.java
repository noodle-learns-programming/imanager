package com.imanager.consume.dao;

import java.util.List;

import com.imanager.consume.domain.ConsumeType;

public interface IConsumeTypeDao {

	/**
	 * ���һ����������
	 * @param consumeType
	 */
	public void insertConsumeType(ConsumeType consumeType);
	
	/**
	 * ����LoginId��������б�
	 * @param loginId
	 * @return
	 */
	public List<ConsumeType> getConsumeTypeListByLoginId(String loginId);
	
	/**
	 * ����TypeId�����������
	 * @param typeId
	 * @return
	 */
	public ConsumeType getConsumeTypeById(String consumeTypeId);
	
	/**
	 * ����һ����������
	 * @param consumeType
	 * @return
	 */
	public boolean updateConsumeType(ConsumeType consumeType);
	
	/**
	 * �߼�ɾ����������
	 * @param consumeTypeId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteConsumeTypeById(String consumeTypeId, String loginId);
}
