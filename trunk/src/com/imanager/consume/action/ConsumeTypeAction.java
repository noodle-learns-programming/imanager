package com.imanager.consume.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.LoginUtil;
import com.imanager.consume.dao.IConsumeTypeDao;
import com.imanager.consume.domain.ConsumeType;
import com.opensymphony.xwork.ActionSupport;

/**
 * 消费记录
 * @author Yang Qiang
 * @since 2008-11-10
 *
 */
public class ConsumeTypeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ConsumeTypeAction.class);
	
	private IConsumeTypeDao consumeTypeDao;
	
	private List<ConsumeType> consumeTypeList;	//消费列表
	
	private ConsumeType consumeType = new ConsumeType();	//消费记录
	
	private String consumeTypeId;	//消费记录ID
	
	String currentLoginId;

	

	/**
	 * 获得消费类型列表
	 * @return
	 * @throws Exception
	 */
	public String getConsumeTypeListByLoginId() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{			
			consumeTypeList = consumeTypeDao.getConsumeTypeListByLoginId(currentLoginId);
			
			return "getConsumeTypeListByLoginId";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeTypeAction.class + ", getConsumeTypeListByLoginId()", e);
			//e.printStackTrace();
			addActionError("系统错误：获得消费类型列表出错！");
			return ERROR;
		}
	}
	
	/**
	 * 初始化添加消费类型
	 * @return
	 * @throws Exception
	 */
	public String initAddConsumType() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		consumeType.setLoginId(currentLoginId);
		
		return "initAddConsumType";
	}
	
	/**
	 * 添加消费类型
	 * @return
	 * @throws Exception
	 */
	public String addConsumType() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		String consumeTypeTrim = consumeType.getConsumeType().trim();
		
		try{
			consumeType.setConsumeType(consumeTypeTrim);
			consumeType.setCreator(currentLoginId);
			consumeType.setModifier(currentLoginId);
			
			consumeTypeDao.insertConsumeType(consumeType);
			
			return "addConsumType";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeTypeAction.class + ", addConsumType()", e);
			//e.printStackTrace();
			addActionError("系统错误：添加消费类型出错！");
			return ERROR;
		}
	}

	/**
	 * 获得一个消费类型
	 * @return
	 * @throws Exception
	 */
	public String getConsumTypeById() throws Exception {
		
		try{
			consumeType = consumeTypeDao.getConsumeTypeById(consumeTypeId);
			
			return "getConsumTypeById";
			
		}catch (Exception e){
			log.error("Error: " + ConsumeTypeAction.class + ", getConsumTypeById()", e);
			//e.printStackTrace();
			addActionError("系统错误：获得一个消费类型出错！");
			return ERROR;
		}
	}
	
	/**
	 * 更新一个消费类型
	 * @return
	 * @throws Exception
	 */
	public String updateConsumType() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		String consumeTypeTrim = consumeType.getConsumeType().trim();
	
		try{
			consumeType.setModifier(currentLoginId);
			consumeType.setConsumeType(consumeTypeTrim);
			
			if(consumeTypeDao.updateConsumeType(consumeType)){
				return "updateConsumType";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error("Error: " + ConsumeTypeAction.class + ", updateConsumType()", e);
			//e.printStackTrace();
			addActionError("系统错误：更新一个消费类型出错！");
			return ERROR;
		}
		
	}
	
	/**
	 * 逻辑删除消费类型
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteConsumType() throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		try{
			if(consumeTypeDao.logicDeleteConsumeTypeById(consumeTypeId, currentLoginId)){
				return "logicDeleteConsumType";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error("Error: " + ConsumeTypeAction.class + ", logicDeleteConsumType()", e);
			//e.printStackTrace();
			addActionError("系统错误：逻辑删除消费类型出错！");
			return ERROR;
		}
	}

	public IConsumeTypeDao getConsumeTypeDao() {
		return consumeTypeDao;
	}

	public void setConsumeTypeDao(IConsumeTypeDao consumeTypeDao) {
		this.consumeTypeDao = consumeTypeDao;
	}

	public List<ConsumeType> getConsumeTypeList() {
		return consumeTypeList;
	}

	public void setConsumeTypeList(List<ConsumeType> consumeTypeList) {
		this.consumeTypeList = consumeTypeList;
	}

	public ConsumeType getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(ConsumeType consumeType) {
		this.consumeType = consumeType;
	}

	public String getConsumeTypeId() {
		return consumeTypeId;
	}

	public void setConsumeTypeId(String consumeTypeId) {
		this.consumeTypeId = consumeTypeId;
	}

	public String getCurrentLoginId() {
		return currentLoginId;
	}

	public void setCurrentLoginId(String currentLoginId) {
		this.currentLoginId = currentLoginId;
	}

	public static Log getLog() {
		return log;
	}
}
