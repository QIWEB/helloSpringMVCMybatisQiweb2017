

package com.pudp.dao;

import org.springframework.stereotype.Repository;

import com.pudp.base.MongoGenDao;
import com.pudp.model.Article;

/**
 * description:
 *
 * @author <a href='mailto:dennisit@163.com'> Cn.������ (En.dennisit)</a> Copy Right since 2013-10-16 
 *
 * com.pudp.dao.ArticleDao.java
 *
 */

@Repository
public class ArticleDao extends MongoGenDao<Article>{
    
    /**
     * ʵ�ֹ��ӷ���,���ط��������
     * @author <a href='mailto:dennisit@163.com'>Cn.������(En.dennisit)</a> Copy Right since 2013-10-13 
     *                
     * @return
     */
    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }

}

