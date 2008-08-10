package com.imanager.finance.dao;

import java.util.List;

import com.imanager.finance.domain.ConsumeItem;
import com.imanager.finance.domain.input.FinanceSearchObj;

public interface IFinanceDao {

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
	public List<ConsumeItem> getConsumeItemListByLoginIdNDate(String loginId, FinanceSearchObj searchObj);
	
	/**
	 * ����LoginId�Ͳ�ѯ������������б������ܶ�
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	public double getConsumeItemListSumByIdNDate(String loginId, FinanceSearchObj searchObj);

	/**
	 * ����ItemId������Ѽ�¼
	 * @param itemId
	 * @return
	 */
	public ConsumeItem getConsumeItemByItemId(String consumeItemId);
	
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
	public boolean logicDeleteConsumeItemByItemId(String consumeItemId, String loginId);
}
