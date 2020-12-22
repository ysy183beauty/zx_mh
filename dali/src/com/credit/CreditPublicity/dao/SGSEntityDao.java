package com.credit.CreditPublicity.dao;

import com.credit.CreditPublicity.entity.QzqdEntity;
import com.credit.CreditPublicity.entity.SGSEntity;
import com.npt.bridge.base.NptBaseDao;

import java.util.List;

/**
 * 项目: 〔大理〕征信
 * 作者: jiaoss
 * 日期: 2018年3月20日16:08:45
 * 备注:
 */
public interface SGSEntityDao extends NptBaseDao<SGSEntity, Long> {
    List getCountGroupByDataType(String type);
    List getCountGroupBySection();
}
