package com.imanager.blog.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientOperations;

import com.imanager.blog.dao.IBlogContentDao;
import com.imanager.blog.domain.BlogContent;
import com.imanager.blog.domain.input.BlogContentSearchObj;
import com.imanager.blog.domain.output.BlogContentOutput;

public class BlogContentDaoImpl implements IBlogContentDao{

	private SqlMapClientOperations sqlMapClientTemplate;
	
	/**
	 * 添加Blog内容
	 * @param blogContent
	 */
	 public void insertBlogContent(BlogContent blogContent){
		 sqlMapClientTemplate.insert("BlogContent.insertBlogContent", blogContent);
	 }
	 
	 /**
	  * 根据查询条件获得Blog列表
	  * @param searchObj
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public List<BlogContentOutput> getBlogContentListBySearch(BlogContentSearchObj blogSearchObj){
		 List blogContentList = sqlMapClientTemplate.queryForList("BlogContent.getBlogContentListBySearch", blogSearchObj);
		 if(blogContentList != null){
			 return blogContentList;
		 }else{
			 return new ArrayList<BlogContentOutput>();
		 }
	 }
		
	 /**
	  * 根据Id获得一个Blog
	  * @param blogContentId
	  * @return
	  */
	public BlogContent getBlogContentById(String blogContentId){
		return (BlogContent)sqlMapClientTemplate.queryForObject("BlogContent.getBlogContentById", blogContentId);
	}
	 
	/**
	 * 更新一个Blog
	 * @param blogContent
	 * @return
	 */
	public boolean updateBlogContent(BlogContent blogContent){
		Integer result = sqlMapClientTemplate.update("BlogContent.updateBlogContent", blogContent);
		return result == 1;
	}
	 
	/**
	 * 逻辑删除一个Blog
	 * @param blogContentId
	 * @param currentLoginId
	 * @return
	 */
	public boolean logicDeleteBlogContent(String blogContentId, String loginId){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("blogContentId", blogContentId);
		 map.put("modifier", loginId);
		 Integer result = sqlMapClientTemplate.update("BlogContent.logicDeleteBlogContent", map);
		 return result == 1;
	 }
	 

	public SqlMapClientOperations getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientOperations sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
}
