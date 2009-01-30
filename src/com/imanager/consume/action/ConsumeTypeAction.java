package com.imanager.consume.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.consume.domain.ConsumeType;
import com.imanager.consume.service.IConsumeService;
import com.imanager.framework.action.BaseAction;
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
		try{
			currentLoginId = loginService.getCurrentLoginId();
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：获得消费类型列表出错！");
			return ERROR;
		}
		return "getConsumeTypeListByLoginId";
	}
	
	/**
	 * 初始化添加消费类型
	 * @return
	 * @throws Exception
	 */
	public String initAddConsumType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			consumeType.setLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：初始化添加消费类型出错！");
			return ERROR;
		}
		
		return "initAddConsumType";
	}
	
	/**
	 * 添加消费类型
	 * @return
	 * @throws Exception
	 */
	public String addConsumType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String consumeTypeTrim = consumeType.getConsumeType().trim();
		
			consumeType.setConsumeType(consumeTypeTrim);
			consumeType.setCreator(currentLoginId);
			consumeType.setModifier(currentLoginId);
			
			consumeService.insertConsumeType(consumeType);
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：添加消费类型出错！");
			return ERROR;
		}
		
		return "addConsumType";
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
			log.error(e.getMessage(), e);
			addActionError("系统错误：获得一个消费类型出错！");
			return ERROR;
		}
	}
	
	/**
	 * 更新消费类型详细
	 * @return
	 * @throws Exception
	 */
	public String updateConsumType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			consumeType.setModifier(currentLoginId);
			consumeType.setConsumeType(consumeType.getConsumeType().trim());
			
			if(consumeService.updateConsumeType(consumeType)){
				return "updateConsumType";
			}else{
				addActionError("系统错误：更新消费类型详细出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：更新消费类型详细出错！");
			return ERROR;
		}
	}
	
	/**
	 * 逻辑删除消费类型
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteConsumType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			if(consumeService.logicDeleteConsumeTypeById(consumeTypeId, currentLoginId)){
				return "logicDeleteConsumType";
			}else{
				addActionError("系统错误：删除消费类型详细出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("系统错误：删除消费类型详细出错！");
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
