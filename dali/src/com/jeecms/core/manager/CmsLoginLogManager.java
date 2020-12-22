package com.jeecms.core.manager;

import com.jeecms.core.entity.CmsLoginLog;
public interface CmsLoginLogManager {
    //保存数据信息
   public CmsLoginLog save(CmsLoginLog bean);
    //统计个数
    public int selectLoginFailByIP(String ip);
    //通过ip查询信息
    public CmsLoginLog selectCmsLoginLogByIP(String ip);
}
