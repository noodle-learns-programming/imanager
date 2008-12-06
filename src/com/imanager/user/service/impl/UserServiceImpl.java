package com.imanager.user.service.impl;

import com.imanager.user.dao.IUserDao;
import com.imanager.user.domain.User;
import com.imanager.user.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.imanager.user.service.IUserService#getUserByLoginId(java.lang.String, java.lang.String)
	 */
	public User getUserByLoginIdNPassword(String loginId, String password){
		return userDao.getUserByLoginIdNPassword(loginId, password);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.service.IUserService#getUserByUserId(java.lang.String)
	 */
	public User getUserByUserId(String userId){
		return userDao.getUserByUserId(userId);
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.service.IUserService#getUserByLoginId(java.lang.String)
	 */
	public User getUserByLoginId(String loginId){
		return userDao.getUserByLoginId(loginId);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
