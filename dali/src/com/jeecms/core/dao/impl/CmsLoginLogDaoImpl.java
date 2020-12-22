package com.jeecms.core.dao.impl;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.core.dao.CmsLoginLogDao;
import com.jeecms.core.entity.CmsLoginLog;
import org.springframework.stereotype.Repository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
@Repository
public class CmsLoginLogDaoImpl extends HibernateBaseDao<CmsLoginLog, Integer> implements CmsLoginLogDao {
    @Override
    public CmsLoginLog save(CmsLoginLog bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    public int selectLoginFailByIP(String ip) {
        int errorCount=0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, -30);//30分钟前的时间
            String startTime = sdf.format(nowTime.getTime());
            String hql="select failCount from CmsLoginLog where iP=? and indate>=? and title=?";
            errorCount=((Long)getSession().createQuery(hql).setParameter(0,ip).
                    setParameter(1,sdf.parse(startTime)).
                    setParameter(2,"login failure").iterate().next()).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return errorCount;
    }

    @Override
    public CmsLoginLog selectCmsLoginLogByIP(String ip) {
        CmsLoginLog bean=null;
        Object object=getSession().createQuery("from CmsLoginLog where iP=?").setParameter(0,ip).uniqueResult();
        if(object!=null){
            bean=(CmsLoginLog)object;
        }else{
            bean=new CmsLoginLog();
        }
        return bean;
    }

    @Override
    protected Class<CmsLoginLog> getEntityClass() {
        return CmsLoginLog.class;
    }
}
