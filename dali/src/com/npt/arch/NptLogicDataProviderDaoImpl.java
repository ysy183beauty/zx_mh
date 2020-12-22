package com.npt.arch;

import com.npt.bridge.arch.dao.NptLogicDataProviderDao;
import com.npt.base.NptBaseDaoImpl;
import com.npt.bridge.arch.NptLogicDataProvider;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 项目：NPTWebApp
 * 作者：owen
 * 时间：2017/1/16 21:05
 * 描述:
 */
@Repository
public class NptLogicDataProviderDaoImpl extends NptBaseDaoImpl<NptLogicDataProvider, Long> implements NptLogicDataProviderDao {
    /**
     * 获得Dao对于的实体类
     *
     * @return
     */
    @Override
    protected Class<NptLogicDataProvider> getEntityClass() {
        return NptLogicDataProvider.class;
    }

    /**
     * 作者: jss
     * 日期: 2018年3月5日15:14:11
     * 备注:查询组织机构
     */
    @Override
    public List<NptLogicDataProvider> listChild(Long fOrgId) {
        Criteria criteria = getSession().createCriteria(NptLogicDataProvider.class);
        criteria.add(Restrictions.eq(NptLogicDataProvider.PROPERTY_PARENT_ID,fOrgId));
        if(fOrgId==-2){
            criteria.add(Restrictions.like(NptLogicDataProvider.PROPERTY_ORG_NAME,"大理州", MatchMode.START));
        }
        criteria.addOrder(Order.asc(NptLogicDataProvider.PROPERTY_ORG_NAME) );

        return criteria.list();
    }

    /**
     * 作者: jss
     * 日期: 2018年3月5日15:14:11
     * 备注:查询组织机构
     */
    @Override
    public List listSectionCount(Long goupId) {
        SQLQuery query  = getSession().createSQLQuery("select p.parent_id pid,count(*) sum from ( " +
                " select *  from NPT_ORGANIZATION n right join (" +
                "        select substr(data_type_dbname,instr(data_type_dbname,'_',-1,1)+1) f,data_type_name from npt_data_type s left join npt_basemodel_groupool g on s.id=g.datatype_id where g.status=1 and s.status=1 and g.group_id="+ goupId+
                "        ) t" +
                "        on n.org_code=t.f where  n.parent_id != -1" +
                "    order by" +
                "      ORG_NAME asc  " +
                "       ) p  group by parent_id");

        return query.list();
    }
}
