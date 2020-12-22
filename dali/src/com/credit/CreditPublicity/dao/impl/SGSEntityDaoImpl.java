package com.credit.CreditPublicity.dao.impl;

import com.credit.CreditPublicity.dao.SGSEntityDao;
import com.credit.CreditPublicity.entity.SGSEntity;
import com.npt.base.NptBaseDaoImpl;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目: 〔大理〕征信
 * 作者: zhanglei
 * 日期: 2017/4/1
 * 备注:
 */
@Repository
public class SGSEntityDaoImpl extends NptBaseDaoImpl<SGSEntity, Long> implements SGSEntityDao {
    @Override
    protected Class<SGSEntity> getEntityClass() {
        return SGSEntity.class;
    }

    @Override
    public List getCountGroupByDataType(String type) {
        SQLQuery query  =  getSession().createSQLQuery("select sum(data_count) sum, n.org_code, n.org_name,type" +
                "  from (select t.*, o.parent_id" +
                "          from (SELECT * FROM  SGS_TJ  j WHERE TO_CHAR(j.UPDATE_TIME,'yyyy-MM-dd')=(SELECT TO_CHAR(max(UPDATE_TIME),'yyyy-mm-dd') FROM SGS_TJ)) t" +
                "          left join npt_organization o" +
                "            on t.data_wbj = o.org_code) s" +
                "  left join npt_organization n" +
                "    on s.parent_id = n.id where type ='"+type+"' group by org_code,org_name,type order by n.org_name");
        return query.list();
    }

    @Override
    public List getCountGroupBySection() {
        SQLQuery query  =  getSession().createSQLQuery("select sum(data_count) sum, n.org_code, n.org_name" +
                "  from (select t.*, o.parent_id" +
                "          from SGS_TJ t" +
                "          left join npt_organization o" +
                "            on t.data_wbj = o.org_code) s" +
                "  left join npt_organization n" +
                "    on s.parent_id = n.id group by org_code,org_name order by n.org_name");
        return query.list();
    }
}
