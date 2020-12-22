package com.jeecms.core.manager.impl;
import com.jeecms.core.dao.CmsLoginLogDao;
import com.jeecms.core.entity.CmsLoginLog;
import com.jeecms.core.manager.CmsLoginLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class CmsLoginLogManagerImpl implements CmsLoginLogManager {
    @Autowired
    private CmsLoginLogDao cmsLoginLogDao;

    @Override
    public CmsLoginLog save(CmsLoginLog bean) {
        return cmsLoginLogDao.save(bean);
    }

    @Override
    public int selectLoginFailByIP(String ip) {
        return cmsLoginLogDao.selectLoginFailByIP(ip);
    }

    @Override
    public CmsLoginLog selectCmsLoginLogByIP(String ip) {
        return cmsLoginLogDao.selectCmsLoginLogByIP(ip);
    }
}
