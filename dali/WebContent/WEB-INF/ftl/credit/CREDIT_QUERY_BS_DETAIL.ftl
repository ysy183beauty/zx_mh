<#include "CommonUtil.ftl"/>
<#assign data=NPT_ACTION_RETURNED_JSON>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <title>数据详情</title>
    <#include "include/header_link.ftl"/>
    <#--<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/qyxycx.css"/>-->
    <link rel="stylesheet" href="${ctx}/r/cms/www/red/css/zqsjxq.css"/>
</head>
<body>
<div class="wrap">
<#include "include/header.ftl"/>
    <div id="container">
        <div class="sit height_auto m-t-md">
            <ul>
                <li>
                    <a id="sy" href="/">首页</a>
                </li>
                <li>&gt;</li>
                <li>
                    <a href="/credit/pub/index.do">信用查询</a>
                </li>
                <li class="last-tit">
                    <li>&gt;</li>
                    <li>企业基础查询</li>
                </li>
            </ul>
        </div>
        <div class="content">
            <div class="con_top">
                <div class="con_top_shu">
                    <h2></h2>
                    <#--<div class="error" >-->
                        <#--<img src="${ctx}/r/cms/www/red/img/sjxq/u87.png" alt=""/>信息纠错-->
                    <#--</div>-->
                    <#--<ul class="alert alert-info">-->
                    <#--<#list data.dataList as list>-->
                        <#--<li>-->
                            <#--<div class="info">${list.groupTitle}：</div>-->
                            <#--<div class="info-con">-->
                                <#--<#list list.blockList as blockList>-->
                                    <#--<span class="col-sm-6">${blockList.blockInfo.poolTitle}</span>-->
                                <#--</#list>-->
                            <#--</div>-->
                        <#--</li>-->
                    <#--</#list>-->
                    <#--</ul>-->
                       <#assign list=data.dataList[0].dataArray/>
                        <#if list?size gt 0>
                            <table  border="0" width="100%" cellpadding="0" cellspacing="0">
                                <#list list as array>
                                    <tr class="row">
                                        <td class="col-md-3 td_left">${array.title}：</td>
                                        <td class="col-md-9 td_right">${array.value}</td>
                                    </tr>
                                </#list>
                            </table>
                        <#else>
                            <div class="no-found">无相关信息</div>
                        </#if>
                        </div>
        </div>
            </div>
    </div>
</div>
<#include "include/footer.ftl"/>
</body>
</html>