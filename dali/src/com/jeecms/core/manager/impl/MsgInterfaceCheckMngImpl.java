package com.jeecms.core.manager.impl;

import com.jeecms.core.dao.FtpDao;
import com.jeecms.core.dao.MsgInterfaceCheckDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MsgInterfaceCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户DAO接口
 */
@Service
@Transactional
public class  MsgInterfaceCheckMngImpl {

	@Transactional(readOnly = true)
	public List<MsgInterfaceCheck> getList(Date getTime, String phone){
		return dao.getList(getTime,phone);
	}
	@Transactional(readOnly = true)
	public int countByMobile(Date getTime, String phone){
		return dao.countByMobile(getTime,phone);
	}

	public MsgInterfaceCheck save(MsgInterfaceCheck bean){
		return dao.save(bean);
	}

	private MsgInterfaceCheckDao dao;

	@Autowired
	public void setDao(MsgInterfaceCheckDao dao) {
		this.dao = dao;
	}
}