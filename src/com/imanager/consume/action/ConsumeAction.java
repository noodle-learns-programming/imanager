package com.imanager.consume.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.DateUtil;
import com.imanager.common.LoginUtil;
import com.imanager.consume.dao.IConsumeDao;
import com.imanager.consume.domain.ConsumeItem;
import com.imanager.consume.domain.input.ConsumeSearchObj;
import com.opensymphony.xwork.ActionSupport;

/**
 * ���Ѽ�¼
 * @author Yang Qiang
 * @since 2008-08-03
 *
 */
public class ConsumeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ConsumeAction.class);
	
	private IConsumeDao financeDao;
	
	private ConsumeSearchObj searchObj = new ConsumeSearchObj();	//��ѯ����
	
	private List<ConsumeItem> consumeItemList;	//�����б�
	
	private double consumeItemListSum;	//�����ܺ�
	
	private ConsumeItem consumeItem = new ConsumeItem();	//���Ѽ�¼
	
	private String consumeItemId;	//���Ѽ�¼ID
	
	String currentLoginId;

	

	/**
	 * ��ʼ����������б�
	 * @return
	 * @throws Exception
	 */
	public String doInitGetConsumeItemList() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			Date startDate = DateUtil.getMinDate();
			Date endDate = DateUtil.getMaxDate();
			
			searchObj.setStartDate(DateUtil.dateOnlyExt(startDate));
			searchObj.setEndDate(DateUtil.dateLastTime(endDate));
			
			consumeItemList = financeDao.getConsumeItemListByLoginIdNDate(currentLoginId, searchObj);
			
			consumeItemListSum = financeDao.getConsumeItemListSumByIdNDate(currentLoginId, searchObj);
			
			return "doInitGetConsumeItemList";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeAction.class + ", doInitGetConsumeItemList()", e);
			//e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * ���ݲ�ѯ������������б�
	 * @return
	 * @throws Exception
	 */
	public String doGetConsumeItemList() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			Date endDate = searchObj.getEndDate();
			searchObj.setEndDate(DateUtil.dateLastTime(endDate));
			
			consumeItemList = financeDao.getConsumeItemListByLoginIdNDate(currentLoginId, searchObj);
			
			consumeItemListSum = financeDao.getConsumeItemListSumByIdNDate(currentLoginId, searchObj);
			
			return "doGetConsumeItemList";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeAction.class + ", doGetConsumeItemList()", e);
			//e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * ��ʼ��������Ѽ�¼
	 * @return
	 * @throws Exception
	 */
	public String doInitAddConsumItem() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		consumeItem.setFeeDate(new Date());
		consumeItem.setLoginId(currentLoginId);
		
		return "doInitAddConsumItem";
	}
	
	/**
	 * ������Ѽ�¼
	 * @return
	 * @throws Exception
	 */
	public String doAddConsumItem() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		String itemNameTrim = consumeItem.getItemName().trim();
		String addressTrim = consumeItem.getAddress().trim();
		
		try{
			consumeItem.setItemName(itemNameTrim);
			consumeItem.setAddress(addressTrim);
			consumeItem.setCreator(currentLoginId);
			consumeItem.setModifier(currentLoginId);
			BigDecimal priceBig = new BigDecimal(String.valueOf(consumeItem.getPrice()));
			BigDecimal quantityBig = new BigDecimal(String.valueOf(consumeItem.getQuantity()));
			double totalPrice = priceBig.multiply(quantityBig).doubleValue();
			
			if("in".equals(consumeItem.getInOrOut())){
				consumeItem.setPrice(-priceBig.doubleValue()); 
				consumeItem.setTotalPrice(-totalPrice);
			}else if("out".equals(consumeItem.getInOrOut())){
				consumeItem.setTotalPrice(totalPrice);
			}
			
			financeDao.insertConsumeItem(consumeItem);
			
			return "doAddConsumItem";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeAction.class + ", doAddConsumItem()", e);
			//e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * ���һ�����Ѽ�¼
	 * @return
	 * @throws Exception
	 */
	public String doGetConsumItem() throws Exception {
		
		try{
			consumeItem = financeDao.getConsumeItemByItemId(consumeItemId);
			
			if("in".equalsIgnoreCase(consumeItem.getInOrOut())){
				consumeItem.setPrice(-consumeItem.getPrice());
			}
			
			return "doGetConsumItem";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeAction.class + ", doGetConsumItem()", e);
			//e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * ����һ�����Ѽ�¼
	 * @return
	 * @throws Exception
	 */
	public String doUpdateConsumItem() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		String itemNameTrim = consumeItem.getItemName().trim();
		String addressTrim = consumeItem.getAddress().trim();
	
		try{
			consumeItem.setModifier(currentLoginId);
			consumeItem.setItemName(itemNameTrim);
			consumeItem.setAddress(addressTrim);
			BigDecimal priceBig = new BigDecimal(String.valueOf(consumeItem.getPrice()));
			BigDecimal quantityBig = new BigDecimal(String.valueOf(consumeItem.getQuantity()));
			double totalPrice = priceBig.multiply(quantityBig).doubleValue();
			
			if("in".equals(consumeItem.getInOrOut())){
				consumeItem.setPrice(-priceBig.doubleValue()); 
				consumeItem.setTotalPrice(-totalPrice);
			}else if("out".equals(consumeItem.getInOrOut())){
				consumeItem.setTotalPrice(totalPrice);
			}
			
			if(financeDao.updateConsumeItem(consumeItem)){
				return "doUpdateConsumItem";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error("Error: " + ConsumeAction.class + ", doUpdateConsumItem()", e);
			//e.printStackTrace();
			return ERROR;
		}
		
	}
	
	/**
	 * �߼�ɾ�����Ѽ�¼
	 * @return
	 * @throws Exception
	 */
	public String doLogicDeleteConsumItem() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			if(financeDao.logicDeleteConsumeItemByItemId(consumeItemId, currentLoginId)){
				return "doLogicDeleteConsumItem";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error("Error: " + ConsumeAction.class + ", doLogicDeleteConsumItem()", e);
			//e.printStackTrace();
			return ERROR;
		}
	}
	
	
	public ConsumeSearchObj getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(ConsumeSearchObj searchObj) {
		this.searchObj = searchObj;
	}

	public List<ConsumeItem> getConsumeItemList() {
		return consumeItemList;
	}

	public void setConsumeItemList(List<ConsumeItem> consumeItemList) {
		this.consumeItemList = consumeItemList;
	}
	
	public double getConsumeItemListSum() {
		return consumeItemListSum;
	}

	public void setConsumeItemListSum(double consumeItemListSum) {
		this.consumeItemListSum = consumeItemListSum;
	}

	public IConsumeDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(IConsumeDao financeDao) {
		this.financeDao = financeDao;
	}

	public ConsumeItem getConsumeItem() {
		return consumeItem;
	}

	public void setConsumeItem(ConsumeItem consumeItem) {
		this.consumeItem = consumeItem;
	}

	public String getConsumeItemId() {
		return consumeItemId;
	}

	public void setConsumeItemId(String consumeItemId) {
		this.consumeItemId = consumeItemId;
	}

}
