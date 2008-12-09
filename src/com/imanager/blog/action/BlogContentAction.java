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
import com.imanager.login.service.ILoginService;
import com.imanager.util.VeDate;

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
	public String initGetBlogContentList() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			
			Date startDate = DateUtil.getMinDate();
			Date endDate = DateUtil.getMaxDate();
			
			blogSearchObj.setStartBlogDate(DateUtil.dateOnlyExt(startDate));
			blogSearchObj.setEngBlogDate(DateUtil.dateLastTime(endDate));
			blogSearchObj.setLoginId(currentLoginId);
		
			blogContentOutputList = blogService.getBlogContentListBySearch(blogSearchObj);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：获得本月Blog列表出错！");
			return ERROR;
		}
		
		return "initGetBlogContentList";
	}

	/**
	 * 通过查找获得Blog列表
	 * @return
	 * @throws Exception
	 */
	public String getBlogContentListBySearch() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			String titleTrim = blogSearchObj.getTitle().trim();
			blogSearchObj.setTitle(titleTrim);
			blogSearchObj.setLoginId(currentLoginId);
		
			blogContentOutputList = blogService.getBlogContentListBySearch(blogSearchObj);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			blogItem2List = blogService.getBlogItem2ByItem1IdNLoginId(blogSearchObj.getBlogItem1Id(), currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：查找Blog列表出错！");
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
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			blogContent.setBlogDate(new Date());
			blogContent.setLoginId(currentLoginId);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			blogContent.setLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：初始化添加Blog出错！");
			return ERROR;
		}
		
		return "initAddBlogContent";
	}
	
	/**
	 * 添加Blog
	 * @return
	 * @throws Exception
	 */
	public String addBlogContent() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			String titleTrim = blogContent.getTitle().trim();
			String contentTrim = blogContent.getContent().trim();
			String weatherTrim = blogContent.getWeather().trim();
		
			blogContent.setTitle(titleTrim);
			blogContent.setContent(contentTrim);
			blogContent.setWeather(weatherTrim);
			blogContent.setCreator(currentLoginId);
			blogContent.setModifier(currentLoginId);
			blogContent.setWeekday(VeDate.getWeekStr(DateUtil.date(blogContent.getBlogDate(), DateUtil.DEFAULT_DATE_FORMAT)));
			
			//暂时不用的字段
			blogContent.setFullFileName("fullFileName");
			blogContent.setFullFilePath("fullFilePath");
			//---暂时不用的字段
			
			blogService.insertBlogContent(blogContent);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：添加Blog出错！");
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
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			blogContent = blogService.getBlogContentById(blogContentId);
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			blogItem2List = blogService.getBlogItem2ByItem1IdNLoginId(String.valueOf(blogContent.getBlogItem1Id()), currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：查看Blog出错！");
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
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			String titleTrim = blogContent.getTitle().trim();
			String contentTrim = blogContent.getContent().trim();
			String weatherTrim = blogContent.getWeather().trim();
		
			blogContent.setTitle(titleTrim);
			blogContent.setContent(contentTrim);
			blogContent.setWeather(weatherTrim);
			blogContent.setModifier(currentLoginId);
			blogContent.setWeekday(VeDate.getWeekStr(DateUtil.date(blogContent.getBlogDate(), DateUtil.DEFAULT_DATE_FORMAT)));
			
			if(blogService.updateBlogContent(blogContent)){
				return "updateBlogContent";
			}else{
				addActionError("系统错误：更新Blog出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：更新Blog出错！");
			return ERROR;
		}
	}
	
	/**
	 * 逻辑删除Blog
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteBlogContent() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			if(blogService.logicDeleteBlogContent(blogContentId, currentLoginId)){
				return "logicDeleteBlogContent";
			}else{
				addActionError("系统错误：删除Blog出错！");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("系统错误：删除Blog出错！");
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
