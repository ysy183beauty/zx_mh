package com.jeecms.core.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.MsgInterfaceCheckDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MsgInterfaceCheck;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class MsgInterfaceCheckDaoImpl extends HibernateBaseDao<MsgInterfaceCheck, Integer>
        implements MsgInterfaceCheckDao {


    @SuppressWarnings("unchecked")
    public List<MsgInterfaceCheck> getList(Date getTime, String phone) {
        Finder f = Finder.create("select bean from MsgInterfaceCheck bean where 1=1 ");


        if (!StringUtils.isBlank(phone)) {
            f.append(" and bean.phone = :phone");
            f.setParam("phone", phone);
        }

        if (getTime != null) {
            f.append(" and bean.getTime>:getTime");
            f.setParam("getTime", getTime);
        }

        f.append(" order by bean.id desc");
        return find(f);
    }


    public int countByMobile(Date getTime, String mobile) {
        String hql = "select count(*) from MsgInterfaceCheck bean where phone=:phone and bean.getTime>:getTime";
        Query query = getSession().createQuery(hql);
        query.setParameter("phone", mobile);
        query.setParameter("getTime", getTime);
        return ((Number) query.iterate().next()).intValue();
    }

    public MsgInterfaceCheck save(MsgInterfaceCheck bean) {
        getSession().save(bean);
        return bean;
    }


    @Override
    protected Class<MsgInterfaceCheck> getEntityClass() {
        return MsgInterfaceCheck.class;
    }
}