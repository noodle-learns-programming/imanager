package com.imanager.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.imanager.blog.domain.BlogItem2;
import com.imanager.blog.service.IBlogService;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;
import com.imanager.util.EncodeUtil;

public class DwrService {
	
	private static final Log log = LogFactory.getLog(DwrService.class);
	
	// Service
	private IBlogService blogService;
	private ILoginService loginService;
	
	// Domain or Var
	private String currentLoginId;
	
	/**
	 * 一级类型下面是否包含二级类型
	 * @param blogItem1Id
	 * @return
	 * @throws Exception
	 */
	public String isItem1HasItem2(String blogItem1Id) throws Exception {
		try{
			String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
			currentLoginId = loginService.getCurrentLoginId(recordType);
		
			if(blogService.isItem1HasItem2(blogItem1Id, currentLoginId)){
				return "true";
			}else{
				return "false";
			}
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
	

	/**
	 * 根据一级类型ID获得下面的二级类型列表
	 * @param blogItem1Id
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBlogItem1List(String blogItem1Id) throws Exception {
		
		String recordType = EnvService.getValueByProperty(EnvService.RECORD_TYPE);
		currentLoginId = loginService.getCurrentLoginId(recordType);
		
		Map<String, String> result = new HashMap<String, String>();
		
		List<BlogItem2> blogItem2List = blogService.getBlogItem2ByItem1IdNLoginId(blogItem1Id, currentLoginId);
		if(blogItem2List.size() == 0){
			result.put("0", "请选择");
		}else{
			for(BlogItem2 item2 : blogItem2List){
				result.put(String.valueOf(item2.getBlogItem2Id()), EncodeUtil.html(item2.getItemChn()));
			}
		}
		return result;
		
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public IBlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}
}
