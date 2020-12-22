package com.npt.arch;

import com.npt.bridge.arch.dao.NptLogicDataTableDao;
import com.npt.base.NptBaseDaoImpl;
import com.npt.bridge.arch.NptLogicDataType;
import com.npt.bridge.dict.NptDict;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 项目：CreditPortal
 * 作者：owen
 * 时间：2017/1/17 16:51
 * 描述:
 */
@Repository
public class NptLogicDataTableDaoImpl extends NptBaseDaoImpl<NptLogicDataType, Long> implements NptLogicDataTableDao {

    /**
     * 获得Dao对于的实体类
     *
     * @return
     */
    @Override
    protected Class<NptLogicDataType> getEntityClass() {
        return NptLogicDataType.class;
    }

    @Override
    public NptLogicDataType findDataType(long id) {
        Criteria criteria = getSession().createCriteria(NptLogicDataType.class);
        criteria.add(Restrictions.eq(NptLogicDataType.PROPERTY_STATUS, NptDict.IDS_ENABLED.getCode()));
        criteria.add(Restrictions.eq(NptLogicDataType.PROPERTY_ID,id));
        return (NptLogicDataType)criteria.uniqueResult();
    }
}
