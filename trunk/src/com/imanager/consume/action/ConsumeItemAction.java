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
import com.imanager.login.service.ILoginService;
import com.imanager.util.VeDate;

/**
 * 消费记录
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
	private ConsumeSearchObj searchObj = new ConsumeSearchObj();	//查询对象
	private List<ConsumeItem> consumeItemList;	//消费明细列表
	private List<ConsumeType> consumeTypeList;	//消费类型列表
	private double consumeItemListSum;	//消费总和
	private ConsumeItem consumeItem = new ConsumeItem();	//消费记录
	private String consumeItemId;	//消费记录ID
	private String currentLoginId;
	

	/**
	 * 初始化获得消费列表
	 * @return
	 * @throws Exception
	 */
	public String doInitGetConsumeItemList() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
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
			log.error(e.getMessage());
			addActionError("系统错误：获得本月消费列表出错！");
			return ERROR;
		}
	}
	
	/**
	 * 根据查询条件获得消费列表
	 * @return
	 * @throws Exception
	 */
	public String doGetConsumeItemListBySearch() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String itemNameTrim = searchObj.getItemName().trim();
			String addressTrim = searchObj.getAddress().trim();
			
			searchObj.setItemName(itemNameTrim);
			searchObj.setAddress(addressTrim);
			Date endDate = searchObj.getEndDate();
			searchObj.setEndDate(DateUtil.dateLastTime(endDate));
			searchObj.setLoginId(currentLoginId);
		
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
			consumeItemList = consumeService.getConsumeItemListBySearch(searchObj);
			consumeItemListSum = consumeService.getConsumeItemListSumBySearch(searchObj);
			
			return "doGetConsumeItemListBySearch";
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：查询消费列表出错！");
			return ERROR;
		}
	}
	
	/**
	 * 初始化添加消费记录
	 * @return
	 * @throws Exception
	 */
	public String doInitAddConsumItem() throws Exception {
		try {
			currentLoginId = loginService.getCurrentLoginId();
			consumeItem.setFeeDate(new Date());
			consumeItem.setLoginId(currentLoginId);
			consumeItem.setWeekday(VeDate.getWeekStr(DateUtil.date(new Date(), DateUtil.DEFAULT_DATE_FORMAT)));
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
			
			return "doInitAddConsumItem";
		} catch (Exception e) {
			log.error(e.getMessage());
			addActionError("系统错误：初始化添加消费列表出错！");
			return ERROR;
		}
	}
	
	/**
	 * 添加消费记录
	 * @return
	 * @throws Exception
	 */
	public String doAddConsumItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String itemNameTrim = consumeItem.getItemName().trim();
			String addressTrim = consumeItem.getAddress().trim();
			
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
			consumeItem.setWeekday(VeDate.getWeekStr(DateUtil.date(consumeItem.getFeeDate(), DateUtil.DEFAULT_DATE_FORMAT)));
			
			consumeService.insertConsumeItem(consumeItem);
			
			return "doAddConsumItem";
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：添加消费记录出错！");
			return ERROR;
		}
	}

	/**
	 * 获得一条消费记录
	 * @return
	 * @throws Exception
	 */
	public String doGetConsumItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
			consumeItem = consumeService.getConsumeItemById(consumeItemId);
			
			if("in".equalsIgnoreCase(consumeItem.getInOrOut())){
				consumeItem.setPrice(-consumeItem.getPrice());
			}
			
			//若消费类型不存在或者被删除，则消费类型置空
			if (consumeItem.getConsumeType().getConsumeType() == null) {
				ConsumeType consumeType = new ConsumeType();
				consumeType.setConsumeTypeId(0);
				consumeType.setConsumeType("");
				consumeItem.setConsumeType(consumeType);
			}
			
			return "doGetConsumItem";
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：查看该消费记录出错！");
			return ERROR;
		}
	}
	
	/**
	 * 更新一条消费记录
	 * @return
	 * @throws Exception
	 */
	public String doUpdateConsumItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String itemNameTrim = consumeItem.getItemName().trim();
			String addressTrim = consumeItem.getAddress().trim();
	
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
			consumeItem.setWeekday(VeDate.getWeekStr(DateUtil.date(consumeItem.getFeeDate(), DateUtil.DEFAULT_DATE_FORMAT)));
			
			if(consumeService.updateConsumeItem(consumeItem)){
				return "doUpdateConsumItem";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：更新该消费记录出错！");
			return ERROR;
		}
		
	}
	
	/**
	 * 逻辑删除消费记录
	 * @return
	 * @throws Exception
	 */
	public String doLogicDeleteConsumItem() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			if(consumeService.logicDeleteConsumeItemById(consumeItemId, currentLoginId)){
				return "doLogicDeleteConsumItem";
			}else{
				addActionError("系统错误：删除该消费记录出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：删除该消费记录出错！");
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
