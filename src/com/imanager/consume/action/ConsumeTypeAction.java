package com.imanager.consume.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.consume.domain.ConsumeType;
import com.imanager.consume.service.IConsumeService;
import com.imanager.framework.action.BaseAction;
import com.imanager.login.service.ILoginService;

/**
 * ���Ѽ�¼
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
	private List<ConsumeType> consumeTypeList;	//�����б�
	private ConsumeType consumeType = new ConsumeType();	//���Ѽ�¼
	private String consumeTypeId;	//���Ѽ�¼ID
	private String currentLoginId;

	

	/**
	 * ������������б�
	 * @return
	 * @throws Exception
	 */
	public String getConsumeTypeListByLoginId() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			consumeTypeList = consumeService.getConsumeTypeListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺻�����������б����");
			return ERROR;
		}
		return "getConsumeTypeListByLoginId";
	}
	
	/**
	 * ��ʼ�������������
	 * @return
	 * @throws Exception
	 */
	public String initAddConsumType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			consumeType.setLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺳�ʼ������������ͳ���");
			return ERROR;
		}
		
		return "initAddConsumType";
	}
	
	/**
	 * �����������
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
			addActionError("ϵͳ��������������ͳ���");
			return ERROR;
		}
		
		return "addConsumType";
	}

	/**
	 * ���һ����������
	 * @return
	 * @throws Exception
	 */
	public String getConsumTypeById() throws Exception {
		try{
			consumeType = consumeService.getConsumeTypeById(consumeTypeId);
			
			return "getConsumTypeById";
			
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺻��һ���������ͳ���");
			return ERROR;
		}
	}
	
	/**
	 * ��������������ϸ
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
				addActionError("ϵͳ���󣺸�������������ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ���󣺸�������������ϸ����");
			return ERROR;
		}
	}
	
	/**
	 * �߼�ɾ����������
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteConsumType() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			if(consumeService.logicDeleteConsumeTypeById(consumeTypeId, currentLoginId)){
				return "logicDeleteConsumType";
			}else{
				addActionError("ϵͳ����ɾ������������ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage(), e);
			addActionError("ϵͳ����ɾ������������ϸ����");
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
