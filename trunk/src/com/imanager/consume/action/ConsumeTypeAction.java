package com.imanager.consume.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.consume.domain.ConsumeType;
import com.imanager.consume.service.IConsumeService;
import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;

/**
 * 消费记录
 * @author Yang Qiang
 * @since 2008-11-10
 *
 */
public class ConsumeTypeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ConsumeTypeAction.class);
	
	// Service
	private IConsumeService consumeService;
	private ILoginService loginService;
	
	// Domain or Var
	private List<ConsumeType> consumeTypeList;	//消费列表
	private ConsumeType consumeType = new ConsumeType();	//消费记录
	private String consumeTypeId;	//消费记录ID
	private String currentLoginId;

	

	/**
	 * 获得消费类型列表
	 * @return
	 * @throws Exception
	 */
	public String getConsumeTypeListByLoginId() throws Exception {
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{			
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
			
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
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		consumeType.setLoginId(currentLoginId);
		
		return "initAddConsumType";
	}
	
	/**
	 * 添加消费类型
	 * @return
	 * @throws Exception
	 */
	public String addConsumType() throws Exception {
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String consumeTypeTrim = consumeType.getConsumeType().trim();
		
		try{
			consumeType.setConsumeType(consumeTypeTrim);
			consumeType.setCreator(currentLoginId);
			consumeType.setModifier(currentLoginId);
			
			consumeService.insertConsumeType(consumeType);
			
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
			consumeType = consumeService.getConsumeTypeById(consumeTypeId);
			
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
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String consumeTypeTrim = consumeType.getConsumeType().trim();
	
		try{
			consumeType.setModifier(currentLoginId);
			consumeType.setConsumeType(consumeTypeTrim);
			
			if(consumeService.updateConsumeType(consumeType)){
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
		
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			if(consumeService.logicDeleteConsumeTypeById(consumeTypeId, currentLoginId)){
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

	public IConsumeService getConsumeService() {
		return consumeService;
	}

	public void setConsumeService(IConsumeService consumeService) {
		this.consumeService = consumeService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
}
