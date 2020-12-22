package com.npt.bridge.database.service;

import com.npt.bridge.arch.NptLogicDataField;
import com.npt.bridge.arch.NptLogicDataType;
import com.npt.bridge.dataBinder.NptWebLimitBean;
import com.npt.bridge.dict.NptDict;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 项目：CreditPortal
 * 作者：owen
 * 时间：2017/1/17 12:03
 * 描述:
 */
public interface NptDataBaseService {
    String SQL_ROWNUM_ENGLISH = "A_ROWNUM";
    /**
     *作者: owen
     *时间: 2016/9/28 11:49
     *备注:
     *      通过数据ID查询配置表，以组装出查询SQL
     *      dataId:数据类别ID，即要查询哪类数据
     *      后三项参数为分页所需要
     *
     *      最终返回基于某类数据库的查询SQL
     */
    String[] makePaginationSql(
            String dtName,
            Collection<NptLogicDataField> fields,
            int currentPage,
            int pageSize,
            Map<String, String> condition,
            List<NptWebLimitBean> limitCondition, Map<String, String> orderBy,
            Boolean exact);

    //拼接通过主键查询业务表数据信息
    String makeSql(String dtName,Collection<NptLogicDataField> fields,Map<String, String> condition);
    /**
     *作者: owen
     *时间: 2016/9/28 11:53
     *备注:
     *      执行makeSql返回的查询SQL
     */
    List<Object> queryList(String sql, Collection<NptLogicDataField> fields);


    /**
     * 作者：owen
     * 日期：2016/10/13 13:14
     * 备注：
     *      精确查询
     * 参数：
     * 返回：
     */
    String makeUniqueSql(
            String dtName,
            Collection<NptLogicDataField> fields,
            Map<String,String> condition,
            NptDict type);

    String makeCountSql(
            String dtName,
            Collection<NptLogicDataField> fields,
            Map<String,String> condition);

    /**
     * 作者：owen
     * 日期：2016/11/7 22:04
     * 备注：
     *      模糊查询
     * 参数：
     * 返回：
     */
    String makeLikeSql(
            String dtName,
            Collection<NptLogicDataField> fields,
            Map<String,String> condition,
            NptDict type);

    /**
     *  author: owen
     *  date:   2017/3/23 下午2:29
     *  note:
     *          多字段或关系的模糊查询
     */
    String[] makeMultiLikeSql(
            String dtName,
            Collection<NptLogicDataField> fields,
            Map<String,String> orWhere,
            Integer currentPage,
            Integer pageSize,
            NptDict type);

    /**
     * 作者：owen
     * 日期：2016/10/25 21:53
     * 备注：
     *      查询指定数据池的详细数据
     * 参数：
     * 返回：
     */
    String[] makeLastedDataSql(String dtName,
                               Collection<NptLogicDataField> fields,
                               Map<String,String> condition,
                               String orderBy,
                               Integer count);

    /**
     * 作者：owen
     * 日期：2016/10/13 13:15
     * 备注：
     *      精确查询
     * 参数：
     * 返回：
     */
    Object queryUnique(String sql);


    int getCount(String sql);

    Long getRowCount(NptLogicDataType type);

    /**
     *作者: owen
     *时间: 2016/9/28 11:56
     *备注:
     *      检测数据库类型
     */
    DB_TYPE checkDBType();

    /**
     * 作者: 张磊
     * 日期: 2017/03/26 下午05:54
     * 备注: 执行sql
     */
    void execute(String sql);


    enum DB_TYPE {
        ORACLE,
        MYSQL
    }

    /**
     *作者: xuqinyuan
     *时间: 2016/11/28 13:38
     *备注:
     *      修改真实表数据
     * @param dtName
     * @param changeInfo
     * @param condition
     * @return
     */
    Boolean updateData(String dtName,Collection<NptLogicDataField> fields,Map<String,String> changeInfo,Map<String,String> condition);

    /**
     *作者：owen
     *时间：2016/12/13 15:25
     *描述:
     *      检测表是否存在
     */
    Boolean isTableExisted(String dtName);

    /**
     *作者：owen
     *时间：2016/12/13 15:29
     *描述:
     *      检测表是否已完全包含指定的字段名称
     */
    Collection<NptLogicDataField> isTableCoverFields(String dtName,Collection<NptLogicDataField> fields);

    /**
     *作者：owen
     *时间：2016/12/13 15:37
     *描述:
     *      创建表并创建业务主键创建索引
     */
    Boolean createTable(String dtName,Collection<NptLogicDataField> fields);

    /**
     *作者：owen
     *时间：2016/12/13 17:31
     *描述:
     *      为表添加字段
     */
    Boolean addColumn(String dtName,Collection<NptLogicDataField> fields);

    /**
     *作者：owen
     *时间：2016/12/13 15:38
     *描述:
     *      删除表及索引
     */
    Boolean dropTable(String dtName,String pkName);

    /**
     *作者：owen
     *时间：2016/12/13 21:14
     *描述:
     *      获取控制实体数据的保留字段
     */
    Collection<NptLogicDataField> getReservedDataField();


    /**
     *作者：owen
     *时间：2016/12/14 16:23
     *描述:
     *      向指定的表中插入一行记录
     */
    Boolean insertRow(String dtName,Collection<NptLogicDataField> fields,Map<String,String> keyValue);

    /**
     *  author: owen
     *  date:   2017/4/17 下午3:00
     *  note:
     *          获取逻辑数据表的总记录数
     */
    Long qureyTableRows(NptLogicDataType dataType);
}
