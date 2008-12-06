package com.imanager.blog.service.impl;

import java.util.List;
import com.imanager.blog.dao.IBlogContentDao;
import com.imanager.blog.dao.IBlogItemDao;
import com.imanager.blog.domain.BlogContent;
import com.imanager.blog.domain.BlogItem1;
import com.imanager.blog.domain.BlogItem2;
import com.imanager.blog.domain.input.BlogContentSearchObj;
import com.imanager.blog.domain.output.BlogContentOutput;
import com.imanager.blog.service.IBlogService;

public class BlogServiceImpl implements IBlogService {
	
	private IBlogContentDao blogContentDao;
	
	private IBlogItemDao blogItemDao;
	
	/**
	 * ���Blog����
	 * @param blogContent
	 */
	 public void insertBlogContent(BlogContent blogContent){
		 blogContentDao.insertBlogContent(blogContent);
	 }
	 
	 /**
	  * ���ݲ�ѯ�������Blog�б�
	  * @param searchObj
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogContentOutput> getBlogContentListBySearch(BlogContentSearchObj blogSearchObj){
		 return blogContentDao.getBlogContentListBySearch(blogSearchObj);
	 }
		
	 /**
	  * ����Id���һ��Blog
	  * @param blogContentId
	  * @return
	  */
	public BlogContent getBlogContentById(String blogContentId){
		return blogContentDao.getBlogContentById(blogContentId);
	}
	 
	/**
	 * ����һ��Blog
	 * @param blogContent
	 * @return
	 */
	public boolean updateBlogContent(BlogContent blogContent){
		return blogContentDao.updateBlogContent(blogContent);
	}
	 
	/**
	 * �߼�ɾ��һ��Blog
	 * @param blogContentId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteBlogContent(String blogContentId, String loginId){
		return blogContentDao.logicDeleteBlogContent(blogContentId, loginId);
	 }
	
	/**
	 * ���һ������
	 * @param blogItem1
	 */
	 public void insertBlogItem1(BlogItem1 blogItem1){
		 blogItemDao.insertBlogItem1(blogItem1);
	 }
	 
	 /**
	  * ����loginId���һ�������б�
	  * @param loginId
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogItem1> getBlogItem1ListByLoginId(String loginId){
		 return blogItemDao.getBlogItem1ListByLoginId(loginId);
	 }
		
	 /**
	  * ����itemId���һ��һ������
	  * @param blogItem1Id
	  * @return
	  */
	 public BlogItem1 getBlogItem1ByItemId(String blogItem1Id){
		 return blogItemDao.getBlogItem1ByItemId(blogItem1Id);
	 }
	
	 /**
	  * ����һ��һ������
	  * @param blogItem1
	  * @return
	  */
	 public boolean updateBlogItem1(BlogItem1 blogItem1){
		 return blogItemDao.updateBlogItem1(blogItem1);
	 }
	 
	 /**
	  * �߼�ɾ��һ��һ������
	  * @param blogItem1Id
	  * @param currentLoginId
	  * @return
	  */
	 public boolean logicDeleteBlogItem1(String blogItem1Id, String loginId){
		 return blogItemDao.logicDeleteBlogItem1(blogItem1Id, loginId);
	 }
	 
	 /**
	  * ����loginId��ö��������б�
	  * @param loginId
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogItem2> getBlogItem2ListByLoginId(String loginId){
		 return blogItemDao.getBlogItem2ListByLoginId(loginId);
	 }
	 
	/**
	 * ��Ӷ�������
	 * @param blogItem2
	 */
	 public void insertBlogItem2(BlogItem2 blogItem2){
		 blogItemDao.insertBlogItem2(blogItem2);
	 }
	 
	 /**
	  * ����itemId���һ����������
	  * @param blogItem2Id
	  * @return
	  */
	 public BlogItem2 getBlogItem2ByItemId(String blogItem2Id){
		 return blogItemDao.getBlogItem2ByItemId(blogItem2Id);
	 }
	 
	 /**
	  * ����һ����������
	  * @param blogItem2
	  * @return
	  */
	 public boolean updateBlogItem2(BlogItem2 blogItem2){
		 return blogItemDao.updateBlogItem2(blogItem2);
	 }
	 
	 /**
	  * �߼�ɾ��һ����������
	  * @param blogItem2Id
	  * @param currentLoginId
	  * @return
	  */
	public boolean logicDeleteBlogItem2(String blogItem2Id, String loginId){
		return blogItemDao.logicDeleteBlogItem2(blogItem2Id, loginId);
	}
	 
	/**
	 * �ж�һ�����������Ƿ���ڶ������ͣ������򷵻�true����û���򷵻�false
	 * @param blogItem1Id
	 * @return
	 */
	public boolean isItem1HasItem2(String blogItem1Id, String loginId){
		return blogItemDao.isItem1HasItem2(blogItem1Id, loginId);
	}
	
	 /**
	  * ����һ�����ͺ�LoginId���ض�������
	  * @param blogItem1Id
	  * @param loginId
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogItem2> getBlogItem2ByItem1IdNLoginId(String blogItem1Id, String loginId){
		return blogItemDao.getBlogItem2ByItem1IdNLoginId(blogItem1Id, loginId);
	 }
	
	
	public IBlogContentDao getBlogContentDao() {
		return blogContentDao;
	}

	public void setBlogContentDao(IBlogContentDao blogContentDao) {
		this.blogContentDao = blogContentDao;
	}

	public IBlogItemDao getBlogItemDao() {
		return blogItemDao;
	}

	public void setBlogItemDao(IBlogItemDao blogItemDao) {
		this.blogItemDao = blogItemDao;
	}

}
