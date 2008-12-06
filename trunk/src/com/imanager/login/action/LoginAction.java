package com.imanager.login.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.Md5Encode;
import com.imanager.framework.action.BaseAction;
import com.imanager.login.service.ILoginService;
import com.imanager.user.domain.User;
import com.imanager.user.service.IUserService;

/**
 * ��¼
 * @author Yang Qiang
 * @since 2008-08-03
 */

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	// Service
	private ILoginService loginService;
	private IUserService userService;
	
	// Domain or Var
	private String loginId;
	private String password;


	/**
	 * ��ʼ����¼ҳ��
	 * @return
	 * @throws Exception
	 */
	public String init() throws Exception {
		return "init";
	}
	
	/**
	 * ��֤�û���¼
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String validateUser() throws Exception {
		try {
			//�����û����������ȡ�û�
			User user = userService.getUserByLoginIdNPassword(loginId.trim(), Md5Encode.MD5(password.trim()));
			
			String str = env.get("recordType").toString();
			log.info(str);
			
			
			if(user == null){
				addActionError("�û������������");
				return INPUT;
			}
			
			//�����ϴε�¼ʱ��
			if (!loginService.updateLastLoginDate(user.getLoginId())) {
				addActionError("ϵͳ���󣺸����ϴε�¼ʱ�����");
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + LoginAction.class + ", validateUser()",e);
			//e.printStackTrace();
			addActionError("ϵͳ������֤�Ƿ�Ϸ��û�����");
			return ERROR;
		}
		
		return SUCCESS;
	}
	

	
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
}
