package com.imanager.blog.action;

import java.io.File;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;

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
import com.imanager.util.FileUtils;
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
	private File attach;
	private String attachContentType;
	private String attachFileName;
	
	
	/**
	 * ��ʼ�����Blog�б�
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
			addActionError("ϵͳ���󣺻�ñ���Blog�б����");
			return ERROR;
		}
		
		return "initGetBlogContentList";
	}

	/**
	 * ͨ�����һ��Blog�б�
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
			addActionError("ϵͳ���󣺲���Blog�б����");
			return ERROR;
		}
		
		return "getBlogContentListBySearch";
	}

	/**
	 * ��ʼ�����Blog
	 * @return
	 * @throws Exception
	 */
	public String initAddBlogContent() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			blogContent.setBlogDate(new Date());
			blogContent.setLoginId(currentLoginId);
			blogContent.setWeekday(VeDate.getWeekStr(DateUtil.date(new Date(), DateUtil.DEFAULT_DATE_FORMAT)));
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			blogContent.setLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺳�ʼ�����Blog����");
			return ERROR;
		}
		
		return "initAddBlogContent";
	}
	
	/**
	 * ���Blog
	 * @return
	 * @throws Exception
	 */
	public String addBlogContent() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String srcDir = env.get(EnvService.SRC_DIR).toString();
			String titleTrim = blogContent.getTitle().trim();
			String contentTrim = blogContent.getContent().trim();
			String weatherTrim = blogContent.getWeather().trim();
		
			blogContent.setTitle(titleTrim);
			blogContent.setContent(contentTrim);
			blogContent.setWeather(weatherTrim);
			blogContent.setCreator(currentLoginId);
			blogContent.setModifier(currentLoginId);
			blogContent.setWeekday(VeDate.getWeekStr(DateUtil.date(blogContent.getBlogDate(), DateUtil.DEFAULT_DATE_FORMAT)));
			
			if (attach != null) {
				String checkAttachResult = checkAttachFolder(srcDir, currentLoginId);
				if (!"has".equals(checkAttachResult)) {
					addActionError(checkAttachResult);
					return ERROR;
				}
				
				//���û����洢·��
				StringBuffer baseFilePath = new StringBuffer();
				baseFilePath.append("/").
					append(loginService.getCurrentLoginId()).
					append("/blog/attach/").
					append(new Date().getTime()).
					append(FileUtils.getSuffixOfFile(attachFileName));
					//append(URLEncoder.encode(attachFileName, "gbk"));
				
				//����Ƭ���浽�̶��ļ�����
				String absFilePath = srcDir + baseFilePath.toString();
				FileCopyUtils.copy(attach, new File(absFilePath));
				
				blogContent.setFullFileName(attachFileName);
				blogContent.setFullFilePath(baseFilePath.toString());
			}
			
			blogService.insertBlogContent(blogContent);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ�������Blog����");
			return ERROR;
		}
		
		return "addBlogContent";
	}
	
	/**
	 * ��ʼ������Blog
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
			addActionError("ϵͳ���󣺲鿴Blog����");
			return ERROR;
		}

		return "initUpdateBlogContent";
	}
	
	/**
	 * ����Blog
	 * @return
	 * @throws Exception
	 */
	public String updateBlogContent() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			String srcDir = env.get(EnvService.SRC_DIR).toString();
			String titleTrim = blogContent.getTitle().trim();
			String contentTrim = blogContent.getContent().trim();
			String weatherTrim = blogContent.getWeather().trim();
		
			blogContent.setTitle(titleTrim);
			blogContent.setContent(contentTrim);
			blogContent.setWeather(weatherTrim);
			blogContent.setModifier(currentLoginId);
			blogContent.setWeekday(VeDate.getWeekStr(DateUtil.date(blogContent.getBlogDate(), DateUtil.DEFAULT_DATE_FORMAT)));
			
			if (attach != null) {
				String checkAttachResult = checkAttachFolder(srcDir, currentLoginId);
				if (!"has".equals(checkAttachResult)) {
					addActionError(checkAttachResult);
					return ERROR;
				}
				
				//ɾ��֮ǰ���ļ�
				String deleteFilePath = srcDir + blogContent.getFullFilePath();
				File file = new File(deleteFilePath);
				if (file != null && file.exists() && file.isFile()) {
					if (!file.delete()) {
						addActionError("ϵͳ����ɾ�����ļ�����");
						return ERROR;
					}
				}
				
				//���û����洢·��
				StringBuffer baseFilePath = new StringBuffer();
				baseFilePath.append("/").
					append(loginService.getCurrentLoginId()).
					append("/blog/attach/").
					append(new Date().getTime()).
					append(FileUtils.getSuffixOfFile(attachFileName));
				
				//����Ƭ���浽�̶��ļ�����
				String absFilePath = srcDir + baseFilePath.toString();
				FileCopyUtils.copy(attach, new File(absFilePath));
				
				blogContent.setFullFileName(attachFileName);
				blogContent.setFullFilePath(baseFilePath.toString());
			}
			
			if(blogService.updateBlogContent(blogContent)){
				return "updateBlogContent";
			}else{
				addActionError("ϵͳ���󣺸���Blog����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺸���Blog����");
			return ERROR;
		}
	}
	
	/**
	 * �߼�ɾ��Blog
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteBlogContent() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			if(blogService.logicDeleteBlogContent(blogContentId, currentLoginId)){
				return "logicDeleteBlogContent";
			}else{
				addActionError("ϵͳ����ɾ��Blog����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ����ɾ��Blog����");
			return ERROR;
		}
	}
	
	/**
	 * ����ļ����Ƿ񴴽�
	 * @param srcDir
	 * @param currentLoginId
	 * @return
	 */
	private String checkAttachFolder(String srcDir, String currentLoginId) {
		File file = new File(srcDir + "/" + currentLoginId);
		if (!file.exists()) {	//��¼�û��ļ���δ����
			return "ϵͳ���󣺵�¼�û��ļ���δ������";
		}
		
		file = new File(srcDir + "/" + currentLoginId + "/blog");
		if (!file.exists()) {	//��ϵ���ļ���δ����
			return "ϵͳ����Blog�ļ���δ������";
		}
		
		file = new File(srcDir + "/" + currentLoginId + "/blog/attach");
		if (file.exists() || (!file.exists() && file.mkdir())) {	//��ϵ����Ƭ�ļ���δ����
			return "has";
		} else {
			return "ϵͳ���󣺴��������ļ��г���";
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
	public File getAttach() {
		return attach;
	}

	public void setAttach(File attach) {
		this.attach = attach;
	}

	public String getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}

	public String getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

}
