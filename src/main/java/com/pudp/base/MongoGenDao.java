

package com.pudp.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pudp.model.Article;

/**
 * description:
 *
 * @author <a href='mailto:dennisit@163.com'> Cn.苏若年 (En.dennisit)</a> Copy Right since 2013-10-13 
 *
 * com.pudp.base.MongoGenDao.java
 *
 */

public abstract class MongoGenDao<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    
    /**
     * 保存一个对象
     *
     * @author <a href='mailto:dennisit@163.com'>Cn.苏若年(En.dennisit)</a> Copy Right since 2013-10-13 下午03:37:28
     *                
     * @param t
     * @return
     */
    public void save(T t){
        this.mongoTemplate.save(t);
    }    
    
    /**
     * 为属性自动注入bean服务
     *
     * @author <a href='mailto:dennisit@163.com'>Cn.pudp(En.dennisit)</a> Copy Right since 2013-10-13 下午03:21:23
     *                
     * @param mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

	protected Class<T> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
    
}

