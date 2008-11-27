package com.imanager.login.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.common.Md5Encode;
import com.imanager.login.dao.ILoginDao;
import com.imanager.login.domain.User;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * 登录
 * @author Yang Qiang
 * @since 2008-08-03
 */

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	private ILoginDao loginDao;
	
	private String loginId;
	private String password;


	/**
	 * 初始化登录页面
	 * @return
	 * @throws Exception
	 */
	public String init() throws Exception {
		return "init";
	}
	
	/**
	 * 验证用户登录
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String validateUser() throws Exception {
		try {
			//根据用户名和密码获取用户
			User user = loginDao.getUserByLoginId(loginId.trim(), Md5Encode.MD5(password.trim()));
			
			if(user == null){
				addActionError("用户名或密码错误！");
				return INPUT;
			}
			
			//更新上次登录时间
			if (!loginDao.updateLastLoginDate(user.getLoginId())) {
				addActionError("系统错误：更新上次登录时间出错！");
				return ERROR;
			}
			
			ActionContext ctx = ActionContext.getContext();
			Map<String, String> session = ctx.getSession();
			session.put("loginId", user.getLoginId());
			
			HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
			Cookie cookie = new Cookie("loginId", user.getLoginId());
			//cookie.setDomain("localhost");
			cookie.setPath("/");
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			
		}catch (Exception e){
			log.error("Error: " + LoginAction.class + ", validateUser()",e);
			//e.printStackTrace();
			addActionError("系统错误：验证是否合法用户出错！");
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

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
}
