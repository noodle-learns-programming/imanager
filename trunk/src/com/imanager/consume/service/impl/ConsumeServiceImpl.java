package com.imanager.consume.service.impl;

import java.util.List;

import com.imanager.consume.dao.IConsumeItemDao;
import com.imanager.consume.dao.IConsumeTypeDao;
import com.imanager.consume.domain.ConsumeItem;
import com.imanager.consume.domain.ConsumeType;
import com.imanager.consume.domain.input.ConsumeSearchObj;
import com.imanager.consume.service.IConsumeService;

public class ConsumeServiceImpl implements IConsumeService {
	
	private IConsumeItemDao consumeItemDao;
	
	private IConsumeTypeDao consumeTypeDao;
	
	
	/**
	 * ���һ�����Ѽ�¼
	 * @param consumeItem
	 */
	public void insertConsumeItem(ConsumeItem consumeItem) {
		consumeItemDao.insertConsumeItem(consumeItem);		
	}
	
	/**
	 * ����LoginId�Ͳ�ѯ������������б�
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConsumeItem> getConsumeItemListBySearch(ConsumeSearchObj searchObj) {
		return consumeItemDao.getConsumeItemListBySearch(searchObj);
	}

	/**
	 * ����LoginId�Ͳ�ѯ������������б������ܶ�
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public double getConsumeItemListSumBySearch(ConsumeSearchObj searchObj) {
		return consumeItemDao.getConsumeItemListSumBySearch(searchObj);
	}

	/**
	 * ����ItemId������Ѽ�¼
	 * @param itemId
	 * @return
	 */
	public ConsumeItem getConsumeItemById(String consumeItemId){
		return consumeItemDao.getConsumeItemById(consumeItemId);
	}
	
	/**
	 * ����һ�����Ѽ�¼
	 * @param consumeItem
	 * @return
	 */
	public boolean updateConsumeItem(ConsumeItem consumeItem){
		return consumeItemDao.updateConsumeItem(consumeItem);
	}
	
	/**
	 * �߼�ɾ�����Ѽ�¼
	 * @param consumeItemId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteConsumeItemById(String consumeItemId, String loginId){
		return consumeItemDao.logicDeleteConsumeItemById(consumeItemId, loginId);
	}

	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#insertConsumeType(com.imanager.consume.domain.ConsumeType)
	 */
	public void insertConsumeType(ConsumeType consumeType) {
		consumeTypeDao.insertConsumeType(consumeType);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#getConsumeTypeListByLoginId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ConsumeType> getConsumeTypeListByLoginId(String loginId) {
		return consumeTypeDao.getConsumeTypeListByLoginId(loginId);
	}


	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#getConsumeTypeByTypeId(java.lang.String)
	 */
	public ConsumeType getConsumeTypeById(String consumeTypeId) {
		return consumeTypeDao.getConsumeTypeById(consumeTypeId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#updateConsumeType(com.imanager.consume.domain.ConsumeType)
	 */
	public boolean updateConsumeType(ConsumeType consumeType) {
		return consumeTypeDao.updateConsumeType(consumeType);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.consume.dao.IConsumeTypeDao#logicDeleteConsumeTypeByTypeId(java.lang.String, java.lang.String)
	 */
	public boolean logicDeleteConsumeTypeById(String consumeTypeId, String loginId) {
		return consumeTypeDao.logicDeleteConsumeTypeById(consumeTypeId, loginId);
	}
	

	public IConsumeItemDao getConsumeItemDao() {
		return consumeItemDao;
	}

	public void setConsumeItemDao(IConsumeItemDao consumeItemDao) {
		this.consumeItemDao = consumeItemDao;
	}

	public IConsumeTypeDao getConsumeTypeDao() {
		return consumeTypeDao;
	}

	public void setConsumeTypeDao(IConsumeTypeDao consumeTypeDao) {
		this.consumeTypeDao = consumeTypeDao;
	}

}
