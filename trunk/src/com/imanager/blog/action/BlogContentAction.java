package com.imanager.blog.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.blog.domain.BlogContent;
import com.imanager.blog.domain.BlogItem1;
import com.imanager.blog.domain.BlogItem2;
import com.imanager.blog.domain.input.BlogContentSearchObj;
import com.imanager.blog.domain.output.BlogContentOutput;
import com.imanager.blog.service.IBlogService;
import com.imanager.common.DateUtil;
import com.imanager.framework.action.BaseAction;
import com.imanager.framework.service.EnvService;
import com.imanager.login.service.ILoginService;

public class BlogContentAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(BlogContentAction.class);
	
	// Service
	private IBlogService blogService;
	private ILoginService loginService;
	
	// Domain or Var	
	private BlogContent blogContent = new BlogContent();
	private BlogContentSearchObj blogSearchObj = new BlogContentSearchObj();
	private String currentLoginId;
	private String blogContentId;
	private List<BlogItem1> blogItem1List;
	private List<BlogItem2> blogItem2List;
	private List<BlogContentOutput> blogContentOutputList;
	
	
	/**
	 * 初始化获得Blog列表
	 * @return
	 * @throws Exception
	 */
	public String initGetBlogContentListBySearch() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		Date startDate = DateUtil.getMinDate();
		Date endDate = DateUtil.getMaxDate();
		
		blogSearchObj.setStartBlogDate(DateUtil.dateOnlyExt(startDate));
		blogSearchObj.setEngBlogDate(DateUtil.dateLastTime(endDate));
		blogSearchObj.setLoginId(currentLoginId);
		
		try{
			blogContentOutputList = blogService.getBlogContentListBySearch(blogSearchObj);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error("Error: " + BlogContentAction.class + ", initGetBlogContentListBySearch()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "initGetBlogContentListBySearch";
	}

	/**
	 * 通过查找获得Blog列表
	 * @return
	 * @throws Exception
	 */
	public String getBlogContentListBySearch() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String titleTrim = blogSearchObj.getTitle().trim();
		blogSearchObj.setTitle(titleTrim);
		blogSearchObj.setLoginId(currentLoginId);
		
		
		try{
			blogContentOutputList = blogService.getBlogContentListBySearch(blogSearchObj);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error("Error: " + BlogContentAction.class + ", getBlogContentListBySearch()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "getBlogContentListBySearch";
	}

	/**
	 * 初始化添加Blog
	 * @return
	 * @throws Exception
	 */
	public String initAddBlogContent() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			blogContent.setBlogDate(new Date());
			blogContent.setLoginId(currentLoginId);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			
		}catch (Exception e){
			log.error("Error: " + BlogContentAction.class + ", initAddBlogContent()");
			e.printStackTrace();
			return ERROR;
		}
		
		blogContent.setLoginId(currentLoginId);
		
		return "initAddBlogContent";
	}
	
	/**
	 * 添加Blog
	 * @return
	 * @throws Exception
	 */
	public String addBlogContent() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String titleTrim = blogContent.getTitle().trim();
		String contentTrim = blogContent.getContent().trim();
		String weatherTrim = blogContent.getWeather().trim();
		
		try{
			blogContent.setTitle(titleTrim);
			blogContent.setContent(contentTrim);
			blogContent.setWeather(weatherTrim);
			blogContent.setCreator(currentLoginId);
			blogContent.setModifier(currentLoginId);
			
			//暂时不用的字段
			blogContent.setFullFileName("fullFileName");
			blogContent.setFullFilePath("fullFilePath");
			blogContent.setWeekday("weekday");
			//---暂时不用的字段
			
			blogService.insertBlogContent(blogContent);
		}catch (Exception e){
			log.error("Error: " + BlogContentAction.class + ", addBlogContent()");
			e.printStackTrace();
			return ERROR;
		}
		
		return "addBlogContent";
	}
	
	/**
	 * 初始化更新Blog
	 * @return
	 * @throws Exception
	 */
	public String initUpdateBlogContent() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			blogContent = blogService.getBlogContentById(blogContentId);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			blogItem2List = blogService.getBlogItem2ByItem1IdNLoginId(String.valueOf(blogContent.getBlogItem1Id()), currentLoginId);
			
		}catch (Exception e){
			log.error("Error: " + BlogContentAction.class + ", initUpdateBlogContent()");
			e.printStackTrace();
			return ERROR;
		}

		return "initUpdateBlogContent";
	}
	
	/**
	 * 更新Blog
	 * @return
	 * @throws Exception
	 */
	public String updateBlogContent() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		String titleTrim = blogContent.getTitle().trim();
		String contentTrim = blogContent.getContent().trim();
		String weatherTrim = blogContent.getWeather().trim();
		
		try{
			blogContent.setTitle(titleTrim);
			blogContent.setContent(contentTrim);
			blogContent.setWeather(weatherTrim);
			blogContent.setModifier(currentLoginId);
			
			if(blogService.updateBlogContent(blogContent)){
				return "updateBlogContent";
			}else{
				return ERROR;
			}
			
		}catch (Exception e){
			log.error("Error: " + BlogContentAction.class + ", updateBlogContent()");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 逻辑删除Blog
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteBlogContent() throws Exception {
		currentLoginId = loginService.getCurrentLoginId(env.get(EnvService.RECORD_TYPE).toString());
		
		try{
			if(blogService.logicDeleteBlogContent(blogContentId, currentLoginId)){
				return "logicDeleteBlogContent";
			}else{
				return ERROR;
			}
		}catch (Exception e){
			log.error("Error: " + BlogContentAction.class + ", logicDeleteBlogContent()");
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	public BlogContent getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(BlogContent blogContent) {
		this.blogContent = blogContent;
	}

	public List<BlogItem1> getBlogItem1List() {
		return blogItem1List;
	}

	public void setBlogItem1List(List<BlogItem1> blogItem1List) {
		this.blogItem1List = blogItem1List;
	}

	public BlogContentSearchObj getBlogSearchObj() {
		return blogSearchObj;
	}

	public void setBlogSearchObj(BlogContentSearchObj blogSearchObj) {
		this.blogSearchObj = blogSearchObj;
	}

	public List<BlogContentOutput> getBlogContentOutputList() {
		return blogContentOutputList;
	}

	public void setBlogContentOutputList(
			List<BlogContentOutput> blogContentOutputList) {
		this.blogContentOutputList = blogContentOutputList;
	}

	public String getBlogContentId() {
		return blogContentId;
	}

	public void setBlogContentId(String blogContentId) {
		this.blogContentId = blogContentId;
	}

	public List<BlogItem2> getBlogItem2List() {
		return blogItem2List;
	}

	public void setBlogItem2List(List<BlogItem2> blogItem2List) {
		this.blogItem2List = blogItem2List;
	}

	public IBlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

}
