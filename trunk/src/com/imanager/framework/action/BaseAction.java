package com.imanager.framework.action;

import java.util.Properties;

import com.opensymphony.xwork.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	
	protected Properties env;

	public Properties getEnv() {
		return env;
	}

	public void setEnv(Properties env) {
		this.env = env;
	}
	
	public String getEnv(String key) {
		return env.getProperty(key);
	}

}
