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
    .table th,.table td{
        text-align: center;
        padding:1em 0;
    }
    thead th{
        background: #999!important;
        color:#fff!important;
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
<#if _RESULT.code == 0>
    <#assign data =NPT_ACTION_RETURNED_JSON>
    <#assign poolId=poolId>
    <#assign pageSize=pageSize>
    <#assign currPage=currPage>
<div class="con-bottom">
    <div class="bot_con">
        <button class="btn btn-success" id="rbtn" onclick="doReturn();">返回</button>
        <table class="table table-striped" border="1" width="100%" cellpadding="0" cellspacing="0" style="text-align: left;border-collapse:collapse">
            <thead>
                <#list data.columnTitles as title>
                    <#if title_index < 3>
                    <th>${title}</th>
                    </#if>
                </#list>
            </thead>
            <tbody>
                <#if data.dataList??>
                    <#list data.dataList as list>
                    <tr onclick="detail(${list.dataArray[0].value})">
                        <td style="width:20%;text-align: center;">${list_index+1}</td>
                        <#list list.dataArray as array>
                            <#if array_index lt 3 && array_index gt 0>
                                <td> ${array.value}</td>
                            </#if>
                        </#list>
                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td colspan="${data.columnTitles?size}">暂无数据</td>
                </tr>
                </#if>
            </tbody>
        </table>
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
<#else >
<table class="table-hover table-striped" border="1" width="100%" cellpadding="0" cellspacing="0" style="text-align: left;border-collapse:collapse">
    <tr>
        <td>${_RESULT.title}</td>
    </tr>
</table>
</#if>
<#--</body>-->
<#--</html>-->
<script>

    function detail(kval) {
        //当前页数
        var currPage=creditQueryCurrPage;
        window.location="/credit/pub/ucc/detailMobile.do?ukValue=" + kval+"&poolId=${poolId}&pageSize=${pageSize}&currPage="+currPage;

    }


    $(function () {
        //生成分页控件
        kkpager.generPageHtml({
            pagerid: "kkpager",
        pno: <#if data??>${data.currPage}<#else>0</#if>,
            mode : 'click',
            //总页码
        total:<#if data??>Math.ceil(${data.totalCount/data.pageSize})<#else>0</#if> ,
            //总数据条数
        totalRecords: <#if data??>${data.totalCount}<#else>0</#if>,
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
            <#--url: "/credit/pub/ucc/index.do",-->
            <#--data: {-->
                <#--poolId: 121,-->
            <#--pageSize:<#if data??> ${data.pageSize}<#else>0</#if>,-->
                <#--currPage: n,-->
            <#--<#if data??>-->
                <#--<#list data.webQueryCondition as cond>-->
                    <#--"webQueryCondition[${cond?index}].fieldTitle": "${cond.fieldTitle}",-->
                    <#--"webQueryCondition[${cond?index}].fieldDBName": "${cond.fieldDBName}",-->
                    <#--"webQueryCondition[${cond?index}].fieldQueryValue": $("input[name='${cond.fieldDBName}']").val(),-->
                <#--</#list>-->
            <#--</#if>-->
            <#--},-->
            <#--success: function (html) {-->
                <#--$("#mainContent").html(html);-->
            <#--}-->
        <#--})-->
    <#--}-->

    if (window.creditQueryCurrPage === undefined) {
        window.creditQueryCurrPage = 1;
    }
    var total =  Math.ceil(${data.totalCount/data.pageSize});
    var currPage="${currPage}";
    if(currPage!=""&&parseInt(currPage)>creditQueryCurrPage){
        window.creditQueryCurrPage=parseInt(currPage);
    }
    if(creditQueryCurrPage === 1 ){
        $(".btn-prev").remove();
    }
    if(creditQueryCurrPage === total || total===0){
        $(".btn-next").remove();
    }

    function next(n) {
        $(".load").css("display","block");
        window.creditQueryCurrPage=creditQueryCurrPage+n;
        console.log(creditQueryCurrPage);

        if(creditQueryCurrPage < 1){
            window.creditQueryCurrPage=1;
        }
        else{
            $.ajax({
                url: "/credit/pub/ucc/indexSearchMobile.do",
                data: {
                    poolId: 121,
                    pageSize:<#if data??> ${data.pageSize}<#else>0</#if>,
                    currPage:creditQueryCurrPage ,
                <#if data??>
                    <#list data.webQueryCondition as cond>
                        "webQueryCondition[${cond?index}].fieldTitle": "${cond.fieldTitle}",
                        "webQueryCondition[${cond?index}].fieldDBName": "${cond.fieldDBName}",
                        "webQueryCondition[${cond?index}].fieldQueryValue": "${cond.fieldQueryValue!}",
                    </#list>
                </#if>
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

    }
    /**
     * 作者: 张磊
     * 日期: 2017/03/15 下午03:05
     * 备注: 重置搜索框
     */
    function resetSearch() {
    <#if data??>
        <#list data.webQueryCondition as cond>
            $("input[name='${cond.fieldDBName}']").val("");
        </#list>
    </#if>

    }



    //    function detail(kval) {
    //        $.ajax({
    //            type:"POST",
    //            url:"/credit/pub/ucc/detailMobile.do",
    //            data:{
    ////                poolId : uid ,
    //                ukValue : kval
    //            },
    //            success:function(html){
    //                var obj = window.open("about:blank");
    //                //var obj = window.location.href="/credit/pub/ucc/detailMobile.do?ukValue=" + kval;
    //                obj.document.write(html);
    //            }
    //        });
    //    }
    //返回
    function doReturn() {
        window.location="/credit/pub/ucc/indexMobile.do";
    }
</script>