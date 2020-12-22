<#include "CommonUtil.ftl"/>
<#assign data = NPT_ACTION_RETURNED_JSON>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/bootstrap.css" />
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/jquery.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/bootstrap.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/pager/js/kkpager.min.js"></script>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/js/pager/css/kkpager.css" />
<style type="text/css">
    .table th,.table td{
        padding:1em 0;
        border-bottom:1px solid #dedede;
    }
    #rDetailbtn{
        margin-top: 5px;
        margin-bottom: 5px;
        width: 120px;
        cursor:hand;
        margin-left:5px;
    }
</style>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>数据详情</title>
    <link rel="stylesheet" href="${ctx}/r/cms/www/red/css/zqsjxq.css"/>
</head>
<body>
<#assign section=section>
<#assign type=type>
<#assign poolId=poolId>
<button class="btn btn-success" id="rDetailbtn" onclick="doReturnList();">返回</button>
<#--<div class="wrap">-->
<#--<#include "include/header.ftl"/>-->
<#if data.dataArray??>
<#--<div id="container">-->
<#--<div class="title">-->
<#--<span><a href="">当前位置</a> ：<a href="">首页</a> > <a href="">信用公示 </a> > <a href=""> 双公示</a> > <a href="">行政许可公示</a></span>-->
<#--</div>-->
<#--<div class="content">-->
<#--<div class="con_top">-->
<div >
<#--<div class="con_top_shu">-->
<#--<h2></h2>-->
<#--<div class="error">-->
<#--<img src="${ctx}/r/cms/www/red/img/sjxq/u87.png" alt=""/>信息纠错-->
<#--</div>-->

    <table class="table"  border="0" width="100%" cellpadding="0" cellspacing="0" >
        <#list data.dataArray as array>
            <#if array_index gt 0>
                <tr class="row">
                    <td class="col-md-3 td_left">${array.title}：</td>
                    <td class="col-md-9 td_right" style="word-wrap:break-word;">${array.value!}</td>

                </tr>
            </#if>
        </#list>
    </table>
<#--</div>-->
<#--</div>-->
<#--</div>-->
<#--</div>-->
</div>
<#else>
暂无数据
</#if>
<#--</div>-->
<#--<#include "include/footer.ftl"/>-->
<script>
    function doReturnList() {
        var type="${type}";
        //统一信用代码
        if(""==type&&type!="null"){
            <#assign pageSize=pageSize>
            <#assign currPage=currPage>
            window.location="/credit/pub/ucc/indexSearchMobile.do?poolId=${poolId}&pageSize=${pageSize}&currPage=${currPage}";
        }else{//行政处罚与行政许可
            window.location.href="/credit/pub/2pub/listMoblie.do?poolId=${poolId}&pageSize=10&currPage=${currPage}&section=${section}&type=${type}";
        }
    }
</script>
</body>
</html>