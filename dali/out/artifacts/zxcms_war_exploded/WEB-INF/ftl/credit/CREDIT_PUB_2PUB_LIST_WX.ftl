<#include "CommonUtil.ftl"/>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/bootstrap.css" />
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/jquery.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/bootstrap.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/pager/js/kkpager.min.js"></script>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/js/pager/css/kkpager.css" />
<style type="text/css">
    *{padding:0px; margin:0px;list-style: none;font-size:14px;}
    body{
        background-color:#f2f2f2;
        overflow: scroll;
        /*overflow-x: hidden;*/
        /*overflow-y: hidden;*/
    }
    .con_top{
        margin-top:2em;
        margin-bottom:5em;
    }
    .con_top li.row{
        background-color:#fff;
        margin-bottom:4em;
    }

    .gover{
        /*width:90%;*/
        margin:0 auto;
        border-bottom:1px solid #ccc;
        line-height: 6em;
        height:6em;
    }
    .gover span{
        display:inline-block;
        height:100%;
        text-align: left;
    }
    .gover input[type=text]{
        height:100%;
        padding:25px 0;
        border:none;
        outline:none;
        text-align: right;
        white-space: nowrap;
    }
    .button{
        margin-bottom:1em;
    }
    .table-responsive{
        background-color:#fff;
    }
    .table-responsive th,.table-responsive td {
        text-align: center;
        padding: 1em 0;
    }
    .wrap{
        width: 100px;
        margin: 0 auto;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
    }
    thead th{
        background: #999!important;
        color:#fff!important;
    }
    table th:first-child{
        width:15%;
    }
    .con-bottom{
        background:#fff;
        min-height:100%;
    }
    .load {
        width: 100%;
        text-align: center;
        margin-bottom: 5px;
        display: none;
    }
    .load img{
        height:30px;
    }
    tbody tr:hover{
        background: #ffffcc !important;
    }
    #rbtn{
        margin-top: 5px;
        margin-bottom: 5px;
        width: 120px;
        cursor:hand;
    }
</style>
<#assign data =NPT_ACTION_RETURNED_JSON>
<#assign section=section>
<#assign type=type>
<div class="container-fluid">
<div class="con-bottom">
    <div class="">
        <div class="bot_con">
            <button class="btn btn-success" id="rbtn" onclick="doReturn();">返回</button>
            <table class="table table-striped table-responsive" border="0" width="100%" cellpadding="0" cellspacing="0" style="word-break:break-all;border-collapse:collapse;table-layout:fixed;">
                <thead style="background: #999;">
                <#list data.columnTitles as title>
                    <#if title_index < 3>
                    <th>${title}</th>
                    </#if>
                </#list>
                <#--<th>职权详情</th>-->
                <#--<th>数据详情</th>-->
                </thead>
                <tbody>
                <#if data.dataList??>
                    <#list data.dataList as list>
                    <tr>

                        <td class="wrap">
                            ${list_index+1}
                            <span style="display:none;">${list.dataArray[0].value}</span>
                        </td>
                        <#list list.dataArray as array>
                            <#if array_index gt 0 && array_index lt 3>
                                <td class="wrap"> ${array.value}</td>
                            </#if>

                        </#list>

                    <#--<td><a href="/credit/pub/2pub/province.do" target="_blank">详细信息</a></td>-->
                    <#--<td><a href="/credit/pub/2pub/detail.do?poolId=${RequestParameters.poolId}&primaryKeyValue=${list.dataArray[0].value}" target="_blank">详细数据</a></td>-->
                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td colspan="3">暂无数据</td>
                </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="bot_bot">
        <div class="load">
            <img src="/r/cms/www/red/img/load.gif">
        </div>
        <div >
            <li class="button btn-prev">
                <input class="btn btn-primary btn-block" type="button" value="上一页" onclick="next(-1)"/>
            </li>
        <#--<span id="count">1</span>-->
            <li class="button btn-next">
                <input class="btn btn-danger btn-block " type="button" value="下一页" onclick="next(1)"/>
            </li>
        </div>
    </div>
</div>
</div>
<script>

//        function detail(uid,kval) {
//            $.ajax({
//                type:"POST",
//                url:"/credit/pub/2pub/detailMobile.do",
//                data:{
//                    poolId : uid ,
//                    primaryKeyValue : kval
//                },
//                success:function(html){
//                    window.location.href="/credit/pub/2pub/detailMobile.do?poolId="+uid+"&primaryKeyValue="+kval;
//
//                }
//            });
//
//        }

    $("tbody tr").bind("click",function(){

        var va = $(this).find(".wrap span").text();
        console.log(va);
        //传递当前页数
        var currPage=creditQueryCurrPage;
        window.location.href="/credit/pub/2pub/detailMobile.do?poolId=${RequestParameters.poolId}&primaryKeyValue="+va+"&section=${section}&type=${type}&currPage="+currPage;
    });


    $(".more").bind("click",function () {
        $(".top_nav").slideDown(1000);
    });
    $(function () {
       //生成分页控件
        kkpager.generPageHtml({
            pagerid: "kkpager",
            pno: ${data.currPage},
            mode : 'click',
            //总页码
            total: Math.ceil(${data.totalCount/data.pageSize}),
            //总数据条数
            totalRecords: ${data.totalCount},
            //链接算法
            click: function (n) {
                //获取第n页数据
                search(n);
            }
        },true);
    });

    /**
     * 作者: 张磊
     * 日期: 2017/03/15 下午03:05
     * 备注: 分页查询
     */
    <#--function search(n) {-->
        <#--$.ajax({-->
            <#--url: "/credit/pub/2pub/listMoblie.do",-->
            <#--data: {-->
                <#--poolId: ${RequestParameters.poolId},-->
                <#--pageSize: ${data.pageSize},-->
                <#--currPage: n,-->
            <#--<#list data.webQueryCondition as cond>-->
                <#--"webQueryCondition[${cond?index}].fieldTitle": "${cond.fieldTitle}",-->
                <#--"webQueryCondition[${cond?index}].fieldDBName": "${cond.fieldDBName}",-->
                <#--"webQueryCondition[${cond?index}].fieldQueryValue": $("input[name='${cond.fieldDBName}']").val(),-->
            <#--</#list>-->
            <#--},-->
            <#--success: function (html) {-->
                <#--$("body").html(html);-->
            <#--}-->
        <#--})-->
    <#--}-->
        if (window.creditQueryCurrPage === undefined) {
            window.creditQueryCurrPage = 1;
        }
        var total =  Math.ceil(${data.totalCount/data.pageSize});

        $(function () {
            var currPage="${currPage}";
            if(currPage!=""&&parseInt(currPage)>creditQueryCurrPage){
                window.creditQueryCurrPage=parseInt(currPage);
            }
            if(creditQueryCurrPage === 1){
                $(".btn-prev").remove();
            }
            if(creditQueryCurrPage === total){
                $(".btn-next").remove();
            }
        });

        function next(n) {
            $(".load").css("display","block");
            window.creditQueryCurrPage=creditQueryCurrPage+n;

                $.ajax({
                    url: "/credit/pub/2pub/listMoblie.do",
                    data: {
                        poolId: ${RequestParameters.poolId},
                        pageSize: ${data.pageSize},
                        currPage: creditQueryCurrPage,
                        section:'${section}',
                        type:'${type}',
                    <#list data.webQueryCondition as cond>
                        "webQueryCondition[${cond?index}].fieldTitle": "${cond.fieldTitle}",
                        "webQueryCondition[${cond?index}].fieldDBName": "${cond.fieldDBName}",
                        "webQueryCondition[${cond?index}].fieldQueryValue": $("input[name='${cond.fieldDBName}']").val(),
                    </#list>
                    },
                    success: function (html) {
                        $(".load").css("display","none");
                        //$("html").html(html);
                        $("body").html(html);
                        if(creditQueryCurrPage == total){
                            $(".btn-next").remove();
                        }
                    }
                })
        }
    /**
     * 作者: 张磊
     * 日期: 2017/03/15 下午03:05
     * 备注: 重置搜索框
     */
    function resetSearch() {
    <#list data.webQueryCondition as cond>
        $("input[name='${cond.fieldDBName}']").val("");
    </#list>
    }
    //返回
    function doReturn() {
        window.location.href="/credit/pub/2pub/alSectionMobile.do?section=${section}&type=${type}";
    }
</script>