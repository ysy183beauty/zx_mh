package com.jeecms.core.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MsgInterfaceCheck;

import java.util.Date;
import java.util.List;

/**
 * 用户DAO接口
 */
public interface MsgInterfaceCheckDao {

	public List<MsgInterfaceCheck> getList(Date getTime, String phone);

	public int countByMobile(Date getTime,String phone);

	public MsgInterfaceCheck save(MsgInterfaceCheck bean);
}