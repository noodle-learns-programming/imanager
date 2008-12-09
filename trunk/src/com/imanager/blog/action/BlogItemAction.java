package com.imanager.blog.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imanager.blog.domain.BlogItem1;
import com.imanager.blog.domain.BlogItem2;
import com.imanager.blog.service.IBlogService;
import com.imanager.framework.action.BaseAction;
import com.imanager.login.service.ILoginService;

public class BlogItemAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(BlogItemAction.class);
	
	// Service
	private IBlogService blogService;
	private ILoginService loginService;
	
	// Domain or Var
	private BlogItem1 blogItem1 = new BlogItem1();
	private BlogItem2 blogItem2 = new BlogItem2();
	private String currentLoginId;
	private List<BlogItem1> blogItem1List;
	private List<BlogItem2> blogItem2List;
	private String blogItem1Id;
	private String blogItem2Id;

	/**
	 * ��ʼ�����Item1
	 * @return
	 * @throws Exception
	 */
	public String initAddBlogItem1() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			blogItem1.setLoginId(currentLoginId);
			return "initAddBlogItem1";
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺳�ʼ�����һ�����ͳ���");
			return ERROR;
		}
	}
	
	/**
	 * ���Item1
	 * @return
	 * @throws Exception
	 */
	public String addBlogItem1() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			
			String itemChnTrim = blogItem1.getItemChn().trim();
			String itemEngTrim = blogItem1.getItemEng().trim();
			
			blogItem1.setItemChn(itemChnTrim);
			blogItem1.setItemEng(itemEngTrim);
			blogItem1.setCreator(currentLoginId);
			blogItem1.setModifier(currentLoginId);
		
			blogService.insertBlogItem1(blogItem1);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ�������һ�����ͳ���");
			return ERROR;
		}
		
		return "addBlogItem1";
	}
	
	/**
	 * ���Item1�б�
	 * @return
	 * @throws Exception
	 */
	public String getBlogItem1ListByLoginId() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺻��һ�������б����");
			return ERROR;
		}
		
		return "getBlogItem1ListByLoginId";
	}
	
	/**
	 * ��ʼ������Item1
	 * @return
	 * @throws Exception
	 */
	public String initUpdateItem1() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			blogItem1 = blogService.getBlogItem1ByItemId(blogItem1Id);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺲鿴һ��������ϸ����");
			return ERROR;
		}
		
		return "initUpdateItem1";
	}
	
	/**
	 * ����Item1
	 * @return
	 * @throws Exception
	 */
	public String updateBlogItem1() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			
			String itemChnTrim = blogItem1.getItemChn().trim();
			String itemEngTrim = blogItem1.getItemEng().trim();
			
			blogItem1.setItemChn(itemChnTrim);
			blogItem1.setItemEng(itemEngTrim);
			blogItem1.setModifier(currentLoginId);
		
			if(blogService.updateBlogItem1(blogItem1)){
				return "updateBlogItem1";
			}else{
				addActionError("ϵͳ���󣺸���һ��������ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺸���һ��������ϸ����");
			return ERROR;
		}
		
	}
	
	/**
	 * �߼�ɾ��Item1
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteBlogItem1() throws Exception{
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			if(blogService.logicDeleteBlogItem1(blogItem1Id, currentLoginId)){
				return "logicDeleteBlogItem1";
			}else{
				addActionError("ϵͳ����ɾ��һ��������ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ����ɾ��һ��������ϸ����");
			return ERROR;
		}
	}
	
	/**
	 * ���Item2�б�
	 * @return
	 * @throws Exception
	 */
	public String getBlogItem2ListByLoginId() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			blogItem2List = blogService.getBlogItem2ListByLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺻�ö��������б����");
			return ERROR;
		}
		
		return "getBlogItem2ListByLoginId";
	}
	
	/**
	 * ��ʼ�����Item2
	 * @return
	 * @throws Exception
	 */
	public String initAddBlogItem2() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			blogItem2.setLoginId(currentLoginId);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺳�ʼ����Ӷ������ͳ���");
			return ERROR;
		}
		
		return "initAddBlogItem2";
	}
	
	/**
	 * ���Item2
	 * @return
	 * @throws Exception
	 */
	public String addBlogItem2() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			String itemChnTrim = blogItem2.getItemChn().trim();
			String itemEngTrim = blogItem2.getItemEng().trim();
			
			blogItem2.setItemChn(itemChnTrim);
			blogItem2.setItemEng(itemEngTrim);
			blogItem2.setCreator(currentLoginId);
			blogItem2.setModifier(currentLoginId);
		
			blogService.insertBlogItem2(blogItem2);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ������Ӷ������ͳ���");
			return ERROR;
		}
		
		return "addBlogItem2";
	}
	
	/**
	 * ��ʼ������Item2
	 * @return
	 * @throws Exception
	 */
	public String initUpdateItem2() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			blogItem1List = blogService.getBlogItem1ListByLoginId(currentLoginId);
			blogItem2 = blogService.getBlogItem2ByItemId(blogItem2Id);
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺲鿴����������ϸ����");
			return ERROR;
		}
		
		return "initUpdateItem2";
	}
	
	/**
	 * ����Item2
	 * @return
	 * @throws Exception
	 */
	public String updateBlogItem2() throws Exception {
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			String itemChnTrim = blogItem2.getItemChn().trim();
			String itemEngTrim = blogItem2.getItemEng().trim();
			
			blogItem2.setItemChn(itemChnTrim);
			blogItem2.setItemEng(itemEngTrim);
			blogItem2.setModifier(currentLoginId);
		
			if(blogService.updateBlogItem2(blogItem2)){
				return "updateBlogItem2";
			}else{
				addActionError("ϵͳ���󣺸��¶���������ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ���󣺸��¶���������ϸ����");
			return ERROR;
		}
		
	}
	
	/**
	 * �߼�ɾ��Item2
	 * @return
	 * @throws Exception
	 */
	public String logicDeleteBlogItem2() throws Exception{
		try{
			currentLoginId = loginService.getCurrentLoginId();
		
			if(blogService.logicDeleteBlogItem2(blogItem2Id, currentLoginId)){
				return "logicDeleteBlogItem2";
			}else{
				addActionError("ϵͳ����ɾ������������ϸ����");
				return ERROR;
			}
		}catch (Exception e){
			log.error(e.getMessage());
			addActionError("ϵͳ����ɾ������������ϸ����");
			return ERROR;
		}
	}
	

	public BlogItem1 getBlogItem1() {
		return blogItem1;
	}

	public void setBlogItem1(BlogItem1 blogItem1) {
		this.blogItem1 = blogItem1;
	}

	public List<BlogItem1> getBlogItem1List() {
		return blogItem1List;
	}

	public void setBlogItem1List(List<BlogItem1> blogItem1List) {
		this.blogItem1List = blogItem1List;
	}

	public String getBlogItem1Id() {
		return blogItem1Id;
	}

	public void setBlogItem1Id(String blogItem1Id) {
		this.blogItem1Id = blogItem1Id;
	}

	public List<BlogItem2> getBlogItem2List() {
		return blogItem2List;
	}

	public void setBlogItem2List(List<BlogItem2> blogItem2List) {
		this.blogItem2List = blogItem2List;
	}

	public BlogItem2 getBlogItem2() {
		return blogItem2;
	}

	public void setBlogItem2(BlogItem2 blogItem2) {
		this.blogItem2 = blogItem2;
	}

	public String getBlogItem2Id() {
		return blogItem2Id;
	}

	public void setBlogItem2Id(String blogItem2Id) {
		this.blogItem2Id = blogItem2Id;
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
