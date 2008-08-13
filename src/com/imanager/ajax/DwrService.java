package com.imanager.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.blog.dao.IBlogItemDao;
import com.imanager.blog.domain.BlogItem2;
import com.imanager.common.LoginUtil;

public class DwrService {
	
	private static final Log log = LogFactory.getLog(DwrService.class);
	
	private IBlogItemDao blogItemDao;
	
	private String currentLoginId;
	
	public String isItem1HasItem2(String blogItem1Id) throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";

		try{
			if(blogItemDao.isItem1HasItem2(blogItem1Id, currentLoginId)){
				return "true";
			}else{
				return "false";
			}
		}catch (Exception e){
			log.error("Error: " + DwrService.class + ", isItem1HasItem2()");
			e.printStackTrace();
			return "error";
		}
	}
	

	public Map<String, String> getBlogItem1List(String blogItem1Id) throws Exception {
		
		currentLoginId = new LoginUtil().getCurrentLogin();
		//TODO currentLoginId = "yangqiang";
		
		Map<String, String> result = new HashMap<String, String>();
		
		List<BlogItem2> blogItem2List = blogItemDao.getBlogItem2ByItem1IdNLoginId(blogItem1Id, currentLoginId);
		if(blogItem2List.size() == 0){
			result.put("", "«Î—°‘Ò");
		}else{
			for(BlogItem2 item2 : blogItem2List){
				result.put(String.valueOf(item2.getBlogItem2Id()), item2.getItemChn());
			}
		}
		return result;
		
	}

	public IBlogItemDao getBlogItemDao() {
		return blogItemDao;
	}

	public void setBlogItemDao(IBlogItemDao blogItemDao) {
		this.blogItemDao = blogItemDao;
	}
}
