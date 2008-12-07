package com.imanager.consume.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.DateUtil;
import com.imanager.consume.domain.ConsumeItem;
import com.imanager.consume.domain.ConsumeType;
import com.imanager.consume.domain.input.ConsumeSearchObj;
import com.imanager.consume.service.IConsumeService;
import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;

/**
 * ���Ѽ�¼
 * @author Yang Qiang
 * @since 2008-08-03
 *
 */
public class ConsumeItemAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ConsumeItemAction.class);
	
	// Service
	private IConsumeService consumeService;
	private ILoginService loginService;
	
	// Domain or Var
	private ConsumeSearchObj searchObj = new ConsumeSearchObj();	//��ѯ����
	private List<ConsumeItem> consumeItemList;	//������ϸ�б�
	private List<ConsumeType> consumeTypeList;	//���������б�
	private double consumeItemListSum;	//�����ܺ�
	private ConsumeItem consumeItem = new ConsumeItem();	//���Ѽ�¼
	private String consumeItemId;	//���Ѽ�¼ID
	private String currentLoginId;
	

	/**
	 * ��ʼ����������б�
	 * @return
	 * @throws Exception
	 */
	public String doInitGetConsumeItemList() throws Exception {
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			Date startDate = DateUtil.getMinDate();
			Date endDate = DateUtil.getMaxDate();
			
			searchObj.setStartDate(DateUtil.dateOnlyExt(startDate));
			searchObj.setEndDate(DateUtil.dateLastTime(endDate));
			searchObj.setLoginId(currentLoginId);
			
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
			
			consumeItemList = consumeService.getConsumeItemListBySearch(searchObj);
			
			consumeItemListSum = consumeService.getConsumeItemListSumBySearch(searchObj);
			
			return "doInitGetConsumeItemList";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeItemAction.class + ", doInitGetConsumeItemList()", e);
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
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String itemNameTrim = searchObj.getItemName().trim();
		String addressTrim = searchObj.getAddress().trim();
		
		searchObj.setItemName(itemNameTrim);
		searchObj.setAddress(addressTrim);
		Date endDate = searchObj.getEndDate();
		searchObj.setEndDate(DateUtil.dateLastTime(endDate));
		searchObj.setLoginId(currentLoginId);
		
		try{
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
			
			consumeItemList = consumeService.getConsumeItemListBySearch(searchObj);
			
			consumeItemListSum = consumeService.getConsumeItemListSumBySearch(searchObj);
			
			return "doGetConsumeItemList";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeItemAction.class + ", doGetConsumeItemList()", e);
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
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		consumeItem.setFeeDate(new Date());
		consumeItem.setLoginId(currentLoginId);
		consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
		
		return "doInitAddConsumItem";
	}
	
	/**
	 * ������Ѽ�¼
	 * @return
	 * @throws Exception
	 */
	public String doAddConsumItem() throws Exception {
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
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
			
			consumeService.insertConsumeItem(consumeItem);
			
			return "doAddConsumItem";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeItemAction.class + ", doAddConsumItem()", e);
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

		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
			consumeItem = consumeService.getConsumeItemById(consumeItemId);
			
			if("in".equalsIgnoreCase(consumeItem.getInOrOut())){
				consumeItem.setPrice(-consumeItem.getPrice());
			}
			
			//���������Ͳ����ڻ��߱�ɾ���������������ÿ�
			if (consumeItem.getConsumeType().getConsumeType() == null) {
				ConsumeType consumeType = new ConsumeType();
				consumeType.setConsumeTypeId(0);
				consumeType.setConsumeType("");
				consumeItem.setConsumeType(consumeType);
			}
			
			return "doGetConsumItem";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeItemAction.class + ", doGetConsumItem()", e);
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
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
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
			
			if(consumeService.updateConsumeItem(consumeItem)){
				return "doUpdateConsumItem";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error("Error: " + ConsumeItemAction.class + ", doUpdateConsumItem()", e);
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
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			if(consumeService.logicDeleteConsumeItemById(consumeItemId, currentLoginId)){
				return "doLogicDeleteConsumItem";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error("Error: " + ConsumeItemAction.class + ", doLogicDeleteConsumItem()", e);
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

	public List<ConsumeType> getConsumeTypeList() {
		return consumeTypeList;
	}

	public void setConsumeTypeList(List<ConsumeType> consumeTypeList) {
		this.consumeTypeList = consumeTypeList;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public IConsumeService getConsumeService() {
		return consumeService;
	}

	public void setConsumeService(IConsumeService consumeService) {
		this.consumeService = consumeService;
	}

}
