package com.imanager.tools.action;

import com.imanager.framework.action.BaseAction;

public class ToolsAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	public String weather() throws Exception {
		return "weather";
	}
	
	public String calendar() throws Exception {
		return "calendar";
	}
	
	public String googleMap() throws Exception {
		return "googlemap";
	}

}
