package com.imanager.tools.action;

import com.imanager.framework.action.BaseAction;

public class ToolsAction extends BaseAction {
	
	public String weather() throws Exception {
		return "weather";
	}
	
	public String googleMap() throws Exception {
		return "googlemap";
	}

}
