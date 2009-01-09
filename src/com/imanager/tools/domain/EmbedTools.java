package com.imanager.tools.domain;

import com.imanager.common.Basement;

public class EmbedTools extends Basement{
	
	int embedToolsId;
	
	String toolsName;

	String toolsDescription;
	
	String toolsPicPath;
	
	String toolsScript;
	
	String toolsType;

	public int getEmbedToolsId() {
		return embedToolsId;
	}

	public void setEmbedToolsId(int embedToolsId) {
		this.embedToolsId = embedToolsId;
	}

	public String getToolsName() {
		return toolsName;
	}

	public void setToolsName(String toolsName) {
		this.toolsName = toolsName;
	}

	public String getToolsDescription() {
		return toolsDescription;
	}

	public void setToolsDescription(String toolsDescription) {
		this.toolsDescription = toolsDescription;
	}

	public String getToolsPicPath() {
		return toolsPicPath;
	}

	public void setToolsPicPath(String toolsPicPath) {
		this.toolsPicPath = toolsPicPath;
	}

	public String getToolsScript() {
		return toolsScript;
	}

	public void setToolsScript(String toolsScript) {
		this.toolsScript = toolsScript;
	}

	public String getToolsType() {
		return toolsType;
	}

	public void setToolsType(String toolsType) {
		this.toolsType = toolsType;
	}
}
