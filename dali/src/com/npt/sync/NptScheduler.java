package com.npt.sync;


import com.credit.CreditPublicity.entity.SGSEntity;
import com.credit.CreditPublicity.service.SGSService;
import com.npt.bridge.arch.NptLogicDataType;
import com.npt.bridge.database.service.NptDataBaseService;
import com.npt.bridge.model.NptBaseModel;
import com.npt.bridge.model.NptBaseModelGroup;
import com.npt.bridge.model.NptBaseModelPool;
import com.npt.bridge.model.service.NptBaseModelService;
import com.npt.bridge.util.NptHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 *  author: owen
 *  date:   2017/4/26 14:07
 *  note:
 *          门户中需要定时处理的任务都写在此类中
 */
@Component
public class NptScheduler {

    @Autowired
    private Properties creditResources;


    /**
     *  author: owen
     *  date:   2017/4/26 14:07
     *  note:
     *          每天定时检测用户申请的个人实名数据是否已过期，若过期则删除之
     *
     *          每天23点执行
     *          0 0 23 * * ?
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void checkExpiredAuthData(){


        String hostIP = creditResources.getProperty("CPC_LOCAL_IP");
        String hostPort = creditResources.getProperty("CPC_ACCESS_PORT");

        String actionUrl= creditResources.getProperty("CPC_CHECK_EXPIRED_AUTHDATA");

        String url = "http://" + hostIP + ":" + hostPort + actionUrl;

        NptHttpUtil.httpPost(url,"");
    }
}
