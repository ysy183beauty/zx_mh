package com.npt.sync;


import com.credit.CreditPublicity.entity.SGSEntity;
import com.credit.CreditPublicity.service.SGSService;
import com.npt.bridge.arch.NptLogicDataType;
import com.npt.bridge.database.service.NptDataBaseService;
import com.npt.bridge.model.NptBaseModel;
import com.npt.bridge.model.NptBaseModelGroup;
import com.npt.bridge.model.NptBaseModelPool;
import com.npt.bridge.model.service.NptBaseModelService;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Collection;
import java.util.Date;

/*
 *  author: owen
 *  date:   2017/4/26 14:07
 *  note:
 *          门户中需要定时处理的任务都写在此类中
 */
@Component
public class NptSchedulerAction {

    @Autowired
    private NptBaseModelService baseModelService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private NptDataBaseService dataBaseService;

    @Autowired
    private SGSService sgsService;


    /**
     *  author: jiaoss
     *  date:   2018年3月20日14:55:31
     *  note:
     *          每天定时计算双公示数据数量
     *          每天24点执行
     *          0 0 24 * * ?
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void execute(){
        boolean participate = bindHibernateSessionToThread(sessionFactory);
        try{
            getNptCreditPublicityData();
        }finally{
            closeHibernateSessionFromThread(participate, sessionFactory);
        }

    }


    private void getNptCreditPublicityData(){
        NptBaseModel model=  new NptBaseModel();
        model.setId(82L);//model_id
        Collection<NptBaseModelGroup> groups=baseModelService.getBaseModelGroups(model);

        for(NptBaseModelGroup group:groups) {
            //1.按照行政区划计算行政许可数据量
            //2.按照行政区域计算行政处罚数据量
            Collection<NptBaseModelPool> pools = baseModelService.getBaseModelGrouPools(group, null);
            String type=group.getId()==84?"xzxk":"xzcf";
            for (NptBaseModelPool p : pools) {

                //查询数据类的数据库表名
                NptLogicDataType dataType = baseModelService.getBaseModelGrouPoolDataType(p);

                String tableName = dataType.getTypeDbName();

                String[] orgs = tableName.split("_");
                String org = orgs[orgs.length - 1];

                int dataSum = dataBaseService.getCount("select count(1) from " + tableName);

                Date date=new Date();
                SGSEntity sgs=new SGSEntity();
                sgs.setDataCount(dataSum);
                sgs.setDataTypeDbname(tableName);
                sgs.setDataTypeName(dataType.getTypeName());
                sgs.setdataWbj(org);
                sgs.setType(type);
                sgs.setUpdateTime(date);
                sgsService.save(sgs);
            }
        }
    }
    public static boolean bindHibernateSessionToThread(SessionFactory sessionFactory) {
        if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
            // Do not modify the Session: just set the participate flag.
            return true;
        } else {
            Session session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.MANUAL);
            SessionHolder sessionHolder = new SessionHolder(session);
            TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
        }
        return false;
    }

    public static void closeHibernateSessionFromThread(boolean participate, Object sessionFactory) {
        if (!participate) {
            SessionHolder sessionHolder = (SessionHolder)TransactionSynchronizationManager.unbindResource(sessionFactory);
            SessionFactoryUtils.closeSession(sessionHolder.getSession());
        }
    }

}
