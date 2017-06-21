

package com.pudp.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pudp.model.Article;

/**
 * description:
 *
 * @author <a href='mailto:dennisit@163.com'> Cn.������ (En.dennisit)</a> Copy Right since 2013-10-13 
 *
 * com.pudp.base.MongoGenDao.java
 *
 */

public abstract class MongoGenDao<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    
    /**
     * ����һ������
     *
     * @author <a href='mailto:dennisit@163.com'>Cn.������(En.dennisit)</a> Copy Right since 2013-10-13 ����03:37:28
     *                
     * @param t
     * @return
     */
    public void save(T t){
        this.mongoTemplate.save(t);
    }    
    
    /**
     * Ϊ�����Զ�ע��bean����
     *
     * @author <a href='mailto:dennisit@163.com'>Cn.pudp(En.dennisit)</a> Copy Right since 2013-10-13 ����03:21:23
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

