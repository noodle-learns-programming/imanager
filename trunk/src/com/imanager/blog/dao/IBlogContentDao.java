package com.imanager.blog.dao;

import java.util.List;

import com.imanager.blog.domain.BlogContent;
import com.imanager.blog.domain.input.BlogContentSearchObj;
import com.imanager.blog.domain.output.BlogContentOutput;

public interface IBlogContentDao {

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
}
