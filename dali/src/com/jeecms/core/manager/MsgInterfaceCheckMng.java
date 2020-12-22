package com.jeecms.core.manager;

import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MsgInterfaceCheck;

import java.util.Date;
import java.util.List;

/**
 * 用户DAO接口
 */
public interface MsgInterfaceCheckMng {

	public List<MsgInterfaceCheck> getList(Date getTime, String phone);

	public int countByMobile(Date getTime, String phone);

	public MsgInterfaceCheck save(MsgInterfaceCheck bean);
}