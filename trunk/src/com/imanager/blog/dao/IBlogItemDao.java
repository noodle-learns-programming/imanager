package com.imanager.blog.dao;

import java.util.List;

import com.imanager.blog.domain.BlogItem1;
import com.imanager.blog.domain.BlogItem2;

public interface IBlogItemDao {

	/**
	 * 添加一级类型
	 * @param blogItem1
	 */
	 public void insertBlogItem1(BlogItem1 blogItem1);
	 
	 /**
	  * 根据loginId获得一级类型列表
	  * @param loginId
	  * @return
	  */
	 public List<BlogItem1> getBlogItem1ListByLoginId(String loginId);
	 
	 /**
	  * 根据itemId获得一个一级类型
	  * @param blogItem1Id
	  * @return
	  */
	 public BlogItem1 getBlogItem1ByItemId(String blogItem1Id);
	 
	 /**
	  * 更新一个一级类型
	  * @param blogItem1
	  * @return
	  */
	 public boolean updateBlogItem1(BlogItem1 blogItem1);
	 
	 /**
	  * 逻辑删除一个一级类型
	  * @param blogItem1Id
	  * @param currentLoginId
	  * @return
	  */
	 public boolean logicDeleteBlogItem1(String blogItem1Id, String loginId);
	 
	 /**
	  * 根据loginId获得二级类型列表
	  * @param loginId
	  * @return
	  */
	 public List<BlogItem2> getBlogItem2ListByLoginId(String loginId);
	 
	/**
	 * 添加二级类型
	 * @param blogItem2
	 */
	 public void insertBlogItem2(BlogItem2 blogItem2);
	 
	 /**
	  * 根据itemId获得一个二级类型
	  * @param blogItem2Id
	  * @return
	  */
	 public BlogItem2 getBlogItem2ByItemId(String blogItem2Id);
	 
	 /**
	  * 更新一个二级类型
	  * @param blogItem2
	  * @return
	  */
	 public boolean updateBlogItem2(BlogItem2 blogItem2);
	 
	 /**
	  * 逻辑删除一个二级类型
	  * @param blogItem2Id
	  * @param currentLoginId
	  * @return
	  */
	 public boolean logicDeleteBlogItem2(String blogItem2Id, String loginId);
	 
	 /**
	  * 判断一级类型下面是否存在二级类型，若有则返回true，若没有则返回false
	  * @param blogItem1Id
	  * @return
	  */
	 public boolean isItem1HasItem2(String blogItem1Id, String loginId);
	 
	 /**
	  * 根据一级类型和LoginId返回二级类型
	  * @param blogItem1Id
	  * @param loginId
	  * @return
	  */
	 public List<BlogItem2> getBlogItem2ByItem1IdNLoginId(String blogItem1Id, String loginId);
}
