package com.credit.CreditPublicity.controller;

import com.credit.CreditPublicity.entity.QzqdEntity;
import com.credit.CreditPublicity.service.NptCredit2PublicityService;
import com.credit.FTLBox.NPTCreditFTLBox;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.manager.CmsUserExtMng;
import com.jeecms.core.web.util.CmsUtils;
import com.npt.bridge.arch.NptLogicDataProvider;
import com.npt.bridge.arch.manager.impl.NptLogicDataProviderManagerImpl;
import com.npt.bridge.dataBinder.NptWebBridgeBean;
import com.npt.bridge.dataBinder.NptWebFieldDataArray;
import com.npt.bridge.dict.NptDict;
import com.npt.bridge.model.NptBaseModelPool;
import com.npt.bridge.util.NptCommonUtil;
import com.npt.querier.impl.Npt2PublicityQuerierBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目： zxcms
 * 作者： owen
 * 时间： 2017/3/2 14:03
 * 描述：
 *
 *      双公示控制器
 *
 *      基于NPT MODEL进行查询
 *
 *      AL：行政许可
 *      AP：行政处罚
 */
@Controller
@RequestMapping(value = "/pub/2pub")
public class NptCredit2PublicityController {

    @Autowired
    private NptLogicDataProviderManagerImpl dataProviderManager;

    @Autowired
    private Npt2PublicityQuerierBase querier;
    @Autowired
    private NptCredit2PublicityService nptCredit2PublicityService;
    @Autowired
    private CmsUserExtMng cmsUserExtMng;
    /**
     * 作者:owen
     * 时间:2017/3/9 上午11:33
     * 描述:
     *      行政处罚首页
     */
    @RequestMapping(value = "/apIndex.do", method = RequestMethod.GET)
    public String apIndex(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

        Long fOrgId= Long.valueOf("174");//大理市
        //获取传递过来的类型
        String type=request.getParameter("_type");//点击首先链接传递过来的时，值不为空，否则为空值
        String section=request.getParameter("section");
        if(null!=section && ""!=section){
            fOrgId=Long.parseLong(section);
        }
        Map<String, Collection<NptBaseModelPool>> pools = querier.getBaseModelGroupProviderPoolsMap(NptDict.BMHG_2PUB_AP);

        Collection<NptLogicDataProvider> org = dataProviderManager.listChild(fOrgId);
        List listO=dataProviderManager.listSectionCount(83L);

        Map<String, Object> county=new HashMap<String,Object>();

        for(Object m:listO){
            String orgId=  ((Object[])m)[0].toString();

            county.put(orgId, ((Object[])m)[1]);
        }
        Map<String, Collection<NptBaseModelPool>> sectionPools=new HashMap<String, Collection<NptBaseModelPool>>();

        for(NptLogicDataProvider provider:org){
            String orgname= provider.getOrgName();
            Collection<NptBaseModelPool> list=pools.get(orgname);
            if(list!=null && list.size()>0) {
                sectionPools.put(orgname, pools.get(orgname));
            }
        }

        modelMap.put("_POOLS", sectionPools);
        modelMap.put("_section", county);
        modelMap.put("dealType",type==null?"":type);
        return NPTCreditFTLBox.FTL_CREDIT_2PUB_INDEX;
    }
    /**
     * 作者:owen
     * 时间:2017/3/9 上午11:33
     * 描述:
     *      首页行政处罚
     */
    @RequestMapping(value = "/apList.do", method = RequestMethod.GET)
    public String apList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        CmsUser user = CmsUtils.getUser(request);
        if (user != null) {
            CmsUserExt ext = cmsUserExtMng.findById(user.getId());
            modelMap.put("userExt", ext);
        }
        modelMap.put("user", user);
        Map<String, Collection<NptBaseModelPool>> pools = querier.getBaseModelGroupProviderPoolsMap(NptDict.BMHG_2PUB_AP);
        modelMap.put("_POOLS", pools);
        //传递行政处罚标志
        modelMap.put("type","cf");
        return NPTCreditFTLBox.FTL_CREDIT_INDEX_AP_INDEX;
    }


    @RequestMapping(value = "/apIndexMobile.do", method = RequestMethod.GET)
    public String apIndexMobile( ModelMap modelMap) {
        List listO=dataProviderManager.listSectionCount(83L);
        Map<String, Object> county=new HashMap<String,Object>();

        for(Object m:listO){
            String orgId=  ((Object[])m)[0].toString();
            county.put(orgId, ((Object[])m)[1]);
        }

        modelMap.put("_section", county);
        modelMap.put("_type","cf");
        return NPTCreditFTLBox.FTL_CREDIT_2PUB_INDEX_WX;
    }

    /**
     * 作者：owen
     * 时间：2017/3/16 下午5:48
     * 描述：
     *      行政许可首页
     */
    @RequestMapping(value = "/alIndex.do", method = RequestMethod.GET)
    public String alIndex(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Long fOrgId= Long.valueOf("174");//大理市
        String section= request.getParameter("section");
        //获取传递过来的类型
        String type=request.getParameter("_type");//点击首先链接传递过来的时，值不为空，否则为空值
        if(null!=section && ""!=section){
            fOrgId=Long.parseLong(section);
        }
        Map<String, Collection<NptBaseModelPool>> pools = querier.getBaseModelGroupProviderPoolsMap(NptDict.BMHG_2PUB_AL);

        Collection<NptLogicDataProvider> org = dataProviderManager.listChild(fOrgId);
        List listO=dataProviderManager.listSectionCount(84L);

        Map<String, Object> county=new HashMap<String,Object>();

        for(Object m:listO){
            String orgId=  ((Object[])m)[0].toString();

            county.put(orgId, ((Object[])m)[1]);
        }

        Map<String, Collection<NptBaseModelPool>> sectionPools=new HashMap<String, Collection<NptBaseModelPool>>();

        for(NptLogicDataProvider provider:org){
           String orgname= provider.getOrgName();
            Collection<NptBaseModelPool> list=pools.get(orgname);
            if(list!=null && list.size()>0) {
                sectionPools.put(orgname, pools.get(orgname));
            }
        }

        modelMap.put("_POOLS", sectionPools);
        modelMap.put("_section", county);
        modelMap.put("dealType",type==null?"":type);
        return NPTCreditFTLBox.FTL_CREDIT_2PUB_INDEX;
    }

    /**
     * 作者：jss
     * 时间：2018年3月6日11:22:02
     * 描述：
     *      行政许可/行政处罚
     */
    @RequestMapping(value = "/alSection.do", method = RequestMethod.GET)
    public String alSection(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Long fOrgId= Long.valueOf("174");//大理市
        String section=request.getParameter("section");
        String type=request.getParameter("type");
        NptDict _type=NptDict.BMHG_2PUB_AL;
        if(null!=section && ""!=section){
            fOrgId=Long.parseLong(section);
        }
        if("cf".equals(type)){
            _type=NptDict.BMHG_2PUB_AP;
        }
        Map<String, Collection<NptBaseModelPool>> pools = querier.getBaseModelGroupProviderPoolsMap(_type);

        Collection<NptLogicDataProvider> org = dataProviderManager.listChild(fOrgId);

        Map<String, Collection<NptBaseModelPool>> sectionPools=new HashMap<String, Collection<NptBaseModelPool>>();

        for(NptLogicDataProvider provider:org){
            String orgname= provider.getOrgName();
            Collection<NptBaseModelPool> list=pools.get(orgname);
            if(list!=null && list.size()>0) {
                sectionPools.put(orgname, pools.get(orgname));
            }
        }

        modelMap.put("_POOLS", sectionPools);
        return NPTCreditFTLBox.FTL_CREDIT_2PUB_INDEX_SECTION;
    }

    /**
     * 作者：jss
     * 时间：2018年3月6日11:22:02
     * 描述：
     *      手机行政许可/行政处罚
     */
    @RequestMapping(value = "/alSectionMobile.do", method = RequestMethod.GET)
    public String alSectionMobile(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        Long fOrgId= Long.valueOf("174");//大理市
        String section=request.getParameter("section");
        String type=request.getParameter("type");
        NptDict _type=NptDict.BMHG_2PUB_AL;
        if(null!=section && ""!=section){
            fOrgId=Long.parseLong(section);
        }
        if("cf".equals(type)){
            _type=NptDict.BMHG_2PUB_AP;
        }
        Map<String, Collection<NptBaseModelPool>> pools = querier.getBaseModelGroupProviderPoolsMap(_type);

        Collection<NptLogicDataProvider> org = dataProviderManager.listChild(fOrgId);

        Map<String, Collection<NptBaseModelPool>> sectionPools=new HashMap<String, Collection<NptBaseModelPool>>();

        for(NptLogicDataProvider provider:org){
            String orgname= provider.getOrgName();
            Collection<NptBaseModelPool> list=pools.get(orgname);
            if(list!=null && list.size()>0) {
                sectionPools.put(orgname, pools.get(orgname));
            }
        }

        modelMap.put("_POOLS", sectionPools);
        modelMap.put("section",section);
        modelMap.put("type",type);
        return NPTCreditFTLBox.FTL_CREDIT_2PUB_INDEX_WX_CHILD;
    }

    @RequestMapping(value = "/alIndexMobile.do", method = RequestMethod.GET)
    public String alIndexMobile( HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) {
        List listO=dataProviderManager.listSectionCount(84L);
        Map<String, Object> county=new HashMap<String,Object>();

        for(Object m:listO){
            String orgId=  ((Object[])m)[0].toString();
            county.put(orgId, ((Object[])m)[1]);
        }

        modelMap.put("_section", county);
        modelMap.put("_type","xk");
        return NPTCreditFTLBox.FTL_CREDIT_2PUB_INDEX_WX;
    }
    /**
     * 作者：owen
     * 时间：2017/3/16 下午5:48
     * 描述：
     *      首页行政许可
     */
    @RequestMapping(value = "/alList.do", method = RequestMethod.GET)

    public String alList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        CmsUser user = CmsUtils.getUser(request);
        if (user != null) {
            CmsUserExt ext = cmsUserExtMng.findById(user.getId());
            modelMap.put("userExt", ext);
        }
        modelMap.put("user", user);
        Map<String, Collection<NptBaseModelPool>> pools = querier.getBaseModelGroupProviderPoolsMap(NptDict.BMHG_2PUB_AL);
        modelMap.put("_POOLS", pools);
        //传递行政许可标志
        modelMap.put("type","xk");
        return NPTCreditFTLBox.FTL_CREDIT_INDEX_AL_INDEX;
    }

    /**
     * 作者: 张磊
     * 日期: 2017/03/13 下午05:57
     * 备注: 数据列表
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String list(NptWebBridgeBean bean, Long poolId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        NptDict result = querier.getBaseModelPoolPaginationData(poolId, bean,true,false);
        modelMap.put("_RESULT", result);
        modelMap.put(NptCommonUtil.getActionReturnedAttributeName(), bean);
        return NPTCreditFTLBox.FTL_CREDIT_PUB_2PUB_LIST;
    }

    @RequestMapping(value = "/listMoblie.do", method = RequestMethod.GET)
    public String listMobile(NptWebBridgeBean bean, Long poolId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        NptDict result = querier.getBaseModelPoolPaginationData(poolId, bean,true,false);
        modelMap.put("_RESULT", result);
        modelMap.put(NptCommonUtil.getActionReturnedAttributeName(), bean);
        modelMap.put("section",request.getParameter("section"));
        modelMap.put("type",request.getParameter("type"));
        modelMap.put("currPage",request.getParameter("currPage")==null?"":request.getParameter("currPage"));
        return NPTCreditFTLBox.FTL_CREDIT_PUB_2PUB_LIST_WX;
    }

    /**
     * 作者: owen
     * 时间: 2017/3/10 下午4:29
     * 描述:
     *      双公示数据详情方法
     */
    @RequestMapping(value = "/detail.do", method = RequestMethod.POST)
    public String detail(NptWebBridgeBean bean, Long poolId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        CmsUser user = CmsUtils.getUser(request);
        if (user != null) {
            CmsUserExt ext = cmsUserExtMng.findById(user.getId());
            modelMap.put("userExt", ext);
        }
        modelMap.put("user", user);
        NptWebFieldDataArray poolData = querier.getBaseModelPoolRowData(poolId, bean.getPrimaryKeyValue());
        modelMap.put(NptCommonUtil.getActionReturnedAttributeName(), poolData);
        return NPTCreditFTLBox.FTL_CREDIT_PUB_2PUB_DETAIL;
    }

    @RequestMapping(value = "/detailMobile.do", method = RequestMethod.GET)
    public String detailMobile(NptWebBridgeBean bean, Long poolId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        CmsUser user = CmsUtils.getUser(request);
        if (user != null) {
            CmsUserExt ext = cmsUserExtMng.findById(user.getId());
            modelMap.put("userExt", ext);
        }
        modelMap.put("user", user);
        NptWebFieldDataArray poolData = querier.getBaseModelPoolRowData(poolId, bean.getPrimaryKeyValue());
        modelMap.put(NptCommonUtil.getActionReturnedAttributeName(), poolData);
        modelMap.put("section",request.getParameter("section"));
        modelMap.put("type",request.getParameter("type"));
        modelMap.put("poolId",poolId);
        modelMap.put("pageSize","10");
        modelMap.put("currPage",request.getParameter("currPage"));
        return NPTCreditFTLBox.FTL_CREDIT_PUB_2PUB_DETAIL_WX;
    }
    /**
     * 作者: owen
     * 时间: 2017/3/10 下午4:30
     * 描述:
     *      双公示职权信息方法
     */
    @RequestMapping(value = "/province.do",method = RequestMethod.POST)
    public String province(Long poolId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        CmsUser user = CmsUtils.getUser(request);
        if (user != null) {
            CmsUserExt ext = cmsUserExtMng.findById(user.getId());
            modelMap.put("userExt", ext);
        }
        modelMap.put("user", user);
        QzqdEntity province = nptCredit2PublicityService.getProvince(poolId);
        modelMap.put("_QZQD", province);
        return NPTCreditFTLBox.FTL_CREDIT_PUB_2PUB_PROVINCE;
    }
}
