package com.imanager.blog.dao;

import java.util.List;

import com.imanager.blog.domain.BlogContent;
import com.imanager.blog.domain.input.BlogContentSearchObj;
import com.imanager.blog.domain.output.BlogContentOutput;

public interface IBlogContentDao {

	/**
	 * 添加Blog内容
	 * @param blogContent
	 */
	 public void insertBlogContent(BlogContent blogContent);
	 
	 /**
	  * 根据查询条件获得Blog列表
	  * @param searchObj
	  * @return
	  */
	 public List<BlogContentOutput> getBlogContentListBySearch(BlogContentSearchObj blogSearchObj);
	 
	 /**
	  * 根据Id获得一个Blog
	  * @param blogContentId
	  * @return
	  */
	 public BlogContent getBlogContentById(String blogContentId);
	 
	 /**
	  * 更新一个Blog
	  * @param blogContent
	  * @return
	  */
	 public boolean updateBlogContent(BlogContent blogContent);
	 
	 /**
	 * 逻辑删除一个Blog
	 * @param blogContentId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteBlogContent(String blogContentId, String loginId);
}
