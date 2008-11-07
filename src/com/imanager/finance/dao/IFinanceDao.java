package com.imanager.finance.dao;

import java.util.List;

import com.imanager.finance.domain.ConsumeItem;
import com.imanager.finance.domain.input.FinanceSearchObj;

public interface IFinanceDao {

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
	public List<ConsumeItem> getConsumeItemListByLoginIdNDate(String loginId, FinanceSearchObj searchObj);
	
	/**
	 * 根据LoginId和查询条件获得消费列表消费总额
	 * @param loginId
	 * @param searchObj
	 * @return
	 */
	public double getConsumeItemListSumByIdNDate(String loginId, FinanceSearchObj searchObj);

	/**
	 * 根据ItemId获得消费记录
	 * @param itemId
	 * @return
	 */
	public ConsumeItem getConsumeItemByItemId(String consumeItemId);
	
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
	public boolean logicDeleteConsumeItemByItemId(String consumeItemId, String loginId);
}
