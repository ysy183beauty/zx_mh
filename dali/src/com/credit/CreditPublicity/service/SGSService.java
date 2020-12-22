package com.credit.CreditPublicity.service;

import com.credit.CreditPublicity.entity.SGSEntity;

import java.util.List;

/**
 * 项目: 〔大理〕征信
 * 作者: jiaoss
 * 日期: 2018年3月20日16:31:31
 * 备注:
 */
public interface SGSService {

    public List getCountGroupByDataType(String type);

    public List getCountGroupBySection();

    public SGSEntity save(SGSEntity sgs);
}
