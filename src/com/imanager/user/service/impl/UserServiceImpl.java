package com.imanager.user.service.impl;

import com.imanager.user.dao.IUserDao;
import com.imanager.user.domain.User;
import com.imanager.user.exception.UserServiceErrMsg;
import com.imanager.user.exception.UserServiceException;
import com.imanager.user.service.IUserService;

public class UserServiceImpl implements IUserService {
	// Dao
	private IUserDao userDao;
	// Daomain or Var
	private static final Object MY_LOCK = new Object();
	
	
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
	
	/* (non-Javadoc)
	 * @see com.imanager.user.service.IUserService#registerUser(com.imanager.user.domain.User)
	 */
	public void registerUser(User user) throws UserServiceException {
		//∑¿÷π≤¢∑¢
		synchronized(MY_LOCK){
			if (userDao.getUserByLoginId(user.getLoginId()) != null) {
				throw new UserServiceException(UserServiceErrMsg.Login_id_already_exist.getDescription());
			}
			userDao.insertUser(user);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.imanager.user.service.IUserService#updateUserInfo(com.imanager.user.domain.User)
	 */
	public boolean updateUserInfo(User user){
		return userDao.updateUserInfo(user);
	}

	/* (non-Javadoc)
	 * @see com.imanager.user.service.IUserService#updateUserPassword(com.imanager.user.domain.User)
	 */
	public boolean updateUserPassword(User user){
		return userDao.updateUserPassword(user);
	}
	

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
