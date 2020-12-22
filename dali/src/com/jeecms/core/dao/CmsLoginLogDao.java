package com.jeecms.core.dao;

import com.jeecms.core.entity.CmsLoginLog;

public interface CmsLoginLogDao {
    //保存数据信息
    CmsLoginLog save(CmsLoginLog bean);
    //统计个数
    int selectLoginFailByIP(String ip);
    //通过ip查询信息
    CmsLoginLog selectCmsLoginLogByIP(String ip);
}
