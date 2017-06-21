

package com.pudp.dao;

import org.springframework.stereotype.Repository;

import com.pudp.base.MongoGenDao;
import com.pudp.model.Member;

/**
 * description:
 *
 * @author <a href='mailto:dennisit@163.com'> Cn.苏若年 (En.dennisit)</a> Copy Right since 2013-10-13 
 *
 * com.pudp.dao.MemberDao.java
 *
 */
@Repository
public class MemberDao extends MongoGenDao<Member>{

    /**
     * 实现钩子方法,返回反射的类型
     * @author <a href='mailto:dennisit@163.com'>Cn.苏若年(En.dennisit)</a> Copy Right since 2013-10-13 
     *                
     * @return
     */
    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}

