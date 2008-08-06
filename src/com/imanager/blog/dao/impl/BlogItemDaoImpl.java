package com.imanager.blog.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.blog.dao.IBlogItemDao;
import com.imanager.blog.domain.BlogItem1;
import com.imanager.blog.domain.BlogItem2;

public class BlogItemDaoImpl implements IBlogItemDao{

	private SqlMapClientOperations sqlMapClientTemplate;
	
	/**
	 * 添加一级类型
	 * @param blogItem1
	 */
	 public void insertBlogItem1(BlogItem1 blogItem1){
		 sqlMapClientTemplate.insert("BlogItem.insertBlogItem1", blogItem1);
	 }
	 
	 /**
	  * 根据loginId获得一级类型列表
	  * @param loginId
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogItem1> getBlogItem1ListByLoginId(String loginId){
		 List blogItem1List = sqlMapClientTemplate.queryForList("BlogItem.getBlogItem1ListByLoginId", loginId);
		 if(blogItem1List != null){
			 return blogItem1List;
		 }else{
			 return new ArrayList<BlogItem1>();
		 }
	 }
		
	 /**
	  * 根据itemId获得一个一级类型
	  * @param blogItem1Id
	  * @return
	  */
	 public BlogItem1 getBlogItem1ByItemId(String blogItem1Id){
		 return (BlogItem1)sqlMapClientTemplate.queryForObject("BlogItem.getBlogItem1ByItemId", blogItem1Id);
	 }
	
	 /**
	  * 更新一个一级类型
	  * @param blogItem1
	  * @return
	  */
	 public boolean updateBlogItem1(BlogItem1 blogItem1){
		 Integer result = sqlMapClientTemplate.update("BlogItem.updateBlogItem1", blogItem1);
		 return result == 1;
	 }
	 
	 /**
	  * 逻辑删除一个一级类型
	  * @param blogItem1Id
	  * @param currentLoginId
	  * @return
	  */
	 public boolean logicDeleteBlogItem1(String blogItem1Id, String loginId){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("blogItem1Id", blogItem1Id);
		 map.put("modifier", loginId);
		 Integer result = sqlMapClientTemplate.update("BlogItem.logicDeleteBlogItem1", map);
		 return result == 1;
	 }
	 
	 /**
	  * 根据loginId获得二级类型列表
	  * @param loginId
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogItem2> getBlogItem2ListByLoginId(String loginId){
		 List blogItem2List = sqlMapClientTemplate.queryForList("BlogItem.getBlogItem2ListByLoginId", loginId);
		 if(blogItem2List != null){
			 return blogItem2List;
		 }else{
			 return new ArrayList<BlogItem2>();
		 }
	 }
	 
	/**
	 * 添加二级类型
	 * @param blogItem2
	 */
	 public void insertBlogItem2(BlogItem2 blogItem2){
		 sqlMapClientTemplate.insert("BlogItem.insertBlogItem2", blogItem2);
	 }
	 
	 /**
	  * 根据itemId获得一个二级类型
	  * @param blogItem2Id
	  * @return
	  */
	 public BlogItem2 getBlogItem2ByItemId(String blogItem2Id){
		 return (BlogItem2)sqlMapClientTemplate.queryForObject("BlogItem.getBlogItem2ByItemId", blogItem2Id);
	 }
	 
	 /**
	  * 更新一个二级类型
	  * @param blogItem2
	  * @return
	  */
	 public boolean updateBlogItem2(BlogItem2 blogItem2){
		 Integer result = sqlMapClientTemplate.update("BlogItem.updateBlogItem2", blogItem2);
		 return result == 1;
	 }
	 
	 /**
	  * 逻辑删除一个二级类型
	  * @param blogItem2Id
	  * @param currentLoginId
	  * @return
	  */
	 public boolean logicDeleteBlogItem2(String blogItem2Id, String loginId){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("blogItem2Id", blogItem2Id);
		 map.put("modifier", loginId);
		 Integer result = sqlMapClientTemplate.update("BlogItem.logicDeleteBlogItem2", map);
		 return result == 1;
	 }
	 
	/**
	 * 判断一级类型下面是否存在二级类型，若有则返回true，若没有则返回false
	 * @param blogItem1Id
	 * @return
	 */
	public boolean isItem1HasItem2(String blogItem1Id, String loginId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("blogItem1Id", blogItem1Id);
		map.put("loginId", loginId);
		Object obj = sqlMapClientTemplate.queryForObject("BlogItem.isItem1HasItem2", map);
		if(obj != null)
			return ((Integer)obj).intValue() > 0;
		else
			return false;
	}
	
	 /**
	  * 根据一级类型和LoginId返回二级类型
	  * @param blogItem1Id
	  * @param loginId
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogItem2> getBlogItem2ByItem1IdNLoginId(String blogItem1Id, String loginId){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("blogItem1Id", blogItem1Id);
		 map.put("loginId", loginId);
		 List blogItem2List = sqlMapClientTemplate.queryForList("BlogItem.getBlogItem2ByItem1IdNLoginId", map);
		 if(blogItem2List != null){
			 return blogItem2List;
		 }else{
			 return new ArrayList<BlogItem2>();
		 }
	 }
	 

	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
}
