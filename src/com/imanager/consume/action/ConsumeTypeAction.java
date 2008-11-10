package com.imanager.consume.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.LoginUtil;
import com.imanager.consume.dao.IConsumeTypeDao;
import com.imanager.consume.domain.ConsumeType;
import com.opensymphony.xwork.ActionSupport;

/**
 * ���Ѽ�¼
 * @author Yang Qiang
 * @since 2008-11-10
 *
 */
public class ConsumeTypeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ConsumeTypeAction.class);
	
	private IConsumeTypeDao consumeTypeDao;
	
	private List<ConsumeType> consumeTypeList;	//�����б�
	
	private ConsumeType consumeType = new ConsumeType();	//���Ѽ�¼
	
	private String consumeTypeId;	//���Ѽ�¼ID
	
	String currentLoginId;

	

	/**
	 * ������������б�
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
			addActionError("ϵͳ���󣺻�����������б����");
			return ERROR;
		}
	}
	
	/**
	 * ��ʼ�������������
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
	 * �����������
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
			addActionError("ϵͳ��������������ͳ���");
			return ERROR;
		}
	}

	/**
	 * ���һ����������
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
			addActionError("ϵͳ���󣺻��һ���������ͳ���");
			return ERROR;
		}
	}
	
	/**
	 * ����һ����������
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
			addActionError("ϵͳ���󣺸���һ���������ͳ���");
			return ERROR;
		}
		
	}
	
	/**
	 * �߼�ɾ����������
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
			addActionError("ϵͳ�����߼�ɾ���������ͳ���");
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
