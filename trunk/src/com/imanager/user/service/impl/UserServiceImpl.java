package com.imanager.user.service.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;

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
	public boolean registerUser(User user) throws UserServiceException {
		//∑¿÷π≤¢∑¢
		synchronized(MY_LOCK){
			if (userDao.getUserByLoginId(user.getLoginId()) != null) {
				throw new UserServiceException(UserServiceErrMsg.Login_id_already_exist.getDescription());
			}
			userDao.insertUser(user);
		}
		return true;
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
	
	/* (non-Javadoc)
	 * @see com.imanager.user.service.IUserService#createFolders(java.lang.String)
	 */
	public boolean createFolders(String srcDir, String currentLoginId, String folders){
		if (StringUtils.isBlank(srcDir) || StringUtils.isBlank(currentLoginId) ||StringUtils.isBlank(folders)) {
			return false;
		}
		
		String[] arrayFolders = folders.split(",");
		File fold = new File(srcDir + "/" + currentLoginId);
		boolean hasFold = false;
		
		if (fold.exists() || (!fold.exists() && fold.mkdir())) {
			hasFold = true;
		} else {
			return false;
		}
		
		if (hasFold) {
			for (int i = 0; i < arrayFolders.length; i++) {
				fold = new File(srcDir + "/" + currentLoginId + "/" + arrayFolders[i].trim());
				if (fold.exists()) {
					continue;
				} else if (fold.mkdir()){
					continue;
				} else {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}
	

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
