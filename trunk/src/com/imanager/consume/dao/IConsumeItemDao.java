package com.imanager.consume.dao;

import java.util.List;

import com.imanager.consume.domain.ConsumeItem;
import com.imanager.consume.domain.input.ConsumeSearchObj;

public interface IConsumeItemDao {

	/**
	 * ���һ�����Ѽ�¼
	 * @param consumeItem
	 */
	public void insertConsumeItem(ConsumeItem consumeItem);
	
	/**
	 * ����LoginId�Ͳ�ѯ������������б�
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	public List<ConsumeItem> getConsumeItemListBySearch(ConsumeSearchObj searchObj);
	
	/**
	 * ����LoginId�Ͳ�ѯ������������б������ܶ�
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	public double getConsumeItemListSumBySearch(ConsumeSearchObj searchObj);

	/**
	 * ����ItemId������Ѽ�¼
	 * @param itemId
	 * @return
	 */
	public ConsumeItem getConsumeItemById(String consumeItemId);
	
	/**
	 * ����һ�����Ѽ�¼
	 * @param consumeItem
	 * @return
	 */
	public boolean updateConsumeItem(ConsumeItem consumeItem);
	
	/**
	 * �߼�ɾ�����Ѽ�¼
	 * @param consumeItemId
	 * @param loginId
	 * @return
	 */
	public boolean logicDeleteConsumeItemById(String consumeItemId, String loginId);
}
