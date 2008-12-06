package com.imanager.login.service.impl;

import com.imanager.login.dao.ILoginDao;
import com.imanager.login.service.ILoginService;

public class LoginServiceImpl implements ILoginService {
	
	private ILoginDao loginDao;
	
	/* (non-Javadoc)
	 * @see com.imanager.login.service.ILoginService#updateLastLoginDate(java.lang.String)
	 */
	public boolean updateLastLoginDate(String loginId){
		return loginDao.updateLastLoginDate(loginId);
	}

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
}
