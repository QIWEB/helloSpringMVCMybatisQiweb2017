

package com.pudp.dao;

import org.springframework.stereotype.Repository;

import com.pudp.base.MongoGenDao;
import com.pudp.model.Member;

/**
 * description:
 *
 * @author <a href='mailto:dennisit@163.com'> Cn.������ (En.dennisit)</a> Copy Right since 2013-10-13 
 *
 * com.pudp.dao.MemberDao.java
 *
 */
@Repository
public class MemberDao extends MongoGenDao<Member>{

    /**
     * ʵ�ֹ��ӷ���,���ط��������
     * @author <a href='mailto:dennisit@163.com'>Cn.������(En.dennisit)</a> Copy Right since 2013-10-13 
     *                
     * @return
     */
    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}

