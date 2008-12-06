package com.imanager.blog.service;

import java.util.List;

import com.imanager.blog.domain.BlogContent;
import com.imanager.blog.domain.BlogItem1;
import com.imanager.blog.domain.BlogItem2;
import com.imanager.blog.domain.input.BlogContentSearchObj;
import com.imanager.blog.domain.output.BlogContentOutput;

public interface IBlogService {
	
	/**
	 * ���Blog����
	 * @param blogContent
	 */
	 public void insertBlogContent(BlogContent blogContent);
	 
	 /**
	  * ���ݲ�ѯ�������Blog�б�
	  * @param searchObj
	  * @return
	  */
	 public List<BlogContentOutput> getBlogContentListBySearch(BlogContentSearchObj blogSearchObj);
	 
	 /**
	  * ����Id���һ��Blog
	  * @param blogContentId
	  * @return
	  */
	 public BlogContent getBlogContentById(String blogContentId);
	 
	 /**
	  * ����һ��Blog
	  * @param blogContent
	  * @return
	  */
	 public boolean updateBlogContent(BlogContent blogContent);
	 
	 /**
	 * �߼�ɾ��һ��Blog
	 * @param blogContentId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteBlogContent(String blogContentId, String loginId);
	
	/**
	 * ���һ������
	 * @param blogItem1
	 */
	 public void insertBlogItem1(BlogItem1 blogItem1);
	 
	 /**
	  * ����loginId���һ�������б�
	  * @param loginId
	  * @return
	  */
	 public List<BlogItem1> getBlogItem1ListByLoginId(String loginId);
	 
	 /**
	  * ����itemId���һ��һ������
	  * @param blogItem1Id
	  * @return
	  */
	 public BlogItem1 getBlogItem1ByItemId(String blogItem1Id);
	 
	 /**
	  * ����һ��һ������
	  * @param blogItem1
	  * @return
	  */
	 public boolean updateBlogItem1(BlogItem1 blogItem1);
	 
	 /**
	  * �߼�ɾ��һ��һ������
	  * @param blogItem1Id
	  * @param currentLoginId
	  * @return
	  */
	 public boolean logicDeleteBlogItem1(String blogItem1Id, String loginId);
	 
	 /**
	  * ����loginId��ö��������б�
	  * @param loginId
	  * @return
	  */
	 public List<BlogItem2> getBlogItem2ListByLoginId(String loginId);
	 
	/**
	 * ��Ӷ�������
	 * @param blogItem2
	 */
	 public void insertBlogItem2(BlogItem2 blogItem2);
	 
	 /**
	  * ����itemId���һ����������
	  * @param blogItem2Id
	  * @return
	  */
	 public BlogItem2 getBlogItem2ByItemId(String blogItem2Id);
	 
	 /**
	  * ����һ����������
	  * @param blogItem2
	  * @return
	  */
	 public boolean updateBlogItem2(BlogItem2 blogItem2);
	 
	 /**
	  * �߼�ɾ��һ����������
	  * @param blogItem2Id
	  * @param currentLoginId
	  * @return
	  */
	 public boolean logicDeleteBlogItem2(String blogItem2Id, String loginId);
	 
	 /**
	  * �ж�һ�����������Ƿ���ڶ������ͣ������򷵻�true����û���򷵻�false
	  * @param blogItem1Id
	  * @return
	  */
	 public boolean isItem1HasItem2(String blogItem1Id, String loginId);
	 
	 /**
	  * ����һ�����ͺ�LoginId���ض�������
	  * @param blogItem1Id
	  * @param loginId
	  * @return
	  */
	 public List<BlogItem2> getBlogItem2ByItem1IdNLoginId(String blogItem1Id, String loginId);


}
