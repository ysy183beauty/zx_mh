package com.credit.CreditPublicity.service.impl;


import com.credit.CreditPublicity.dao.SGSEntityDao;
import com.credit.CreditPublicity.entity.SGSEntity;
import com.credit.CreditPublicity.service.SGSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目: 〔大理〕征信
 * 作者: zhanglei
 * 日期: 2017/4/1
 * 备注:
 */
@Service
@Transactional
public class SGSServiceImpl implements SGSService {
    @Autowired
    private SGSEntityDao sgsEntityDao;



    @Override
    public List getCountGroupByDataType(String type) {

        return sgsEntityDao.getCountGroupByDataType(type);
    }

    @Override
    public List getCountGroupBySection() {
        return sgsEntityDao.getCountGroupBySection();
    }

    @Override
    public SGSEntity save(SGSEntity sgs) {
        return sgsEntityDao.save(sgs);
    }
}
