package com.imanager.framework.velocity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.tools.view.ToolboxManager;
import org.apache.velocity.tools.view.XMLToolboxManager;
import org.springframework.util.ResourceUtils;

import com.opensymphony.webwork.WebWorkConstants;
import com.opensymphony.webwork.config.Configuration;

/**
 * @author Yang Qiang
 * @since 2008-05-01
 *
 */
public class VelocityManagerEx extends
		com.opensymphony.webwork.views.velocity.VelocityManager {
	
	private static final Log log = LogFactory.getLog(VelocityManagerEx.class);
	
	public VelocityManagerEx() {
		String toolboxLocation = "toolbox.xml";
		if (Configuration.isSet(WebWorkConstants.WEBWORK_VELOCITY_TOOLBOXLOCATION)) {
            toolboxLocation = Configuration.get(WebWorkConstants.WEBWORK_VELOCITY_TOOLBOXLOCATION).toString();
        }
		
		if (toolboxLocation != null) {
            XMLToolboxManager mgr = new XMLToolboxManager();
            
            try {
	            File config = ResourceUtils.getFile(toolboxLocation);
	            if (config != null) {
	            	InputStream in = new FileInputStream(config);
	            	try {
		            	mgr.load(in);
		            	toolboxManager = mgr;
		            } finally {
		            	try {
		            		in.close();
						} catch (IOException e) {
							log.warn("failed to close file: " + toolboxLocation);
						}
		            }
	            }
            } catch (Exception ex) {
            	log.error("failed to load configuration file: " + toolboxLocation, ex);
            }
        }
	}
	
	protected void initToolbox(ServletContext context) {
		
	}

	public ToolboxManager getToolboxManager() {
		return toolboxManager;
	}
	
}

