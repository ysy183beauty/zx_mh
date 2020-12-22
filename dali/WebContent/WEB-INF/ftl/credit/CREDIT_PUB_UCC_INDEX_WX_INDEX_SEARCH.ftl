<#include "CommonUtil.ftl"/>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/bootstrap.css" />
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/jquery.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/bootstrap.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/pager/js/kkpager.min.js"></script>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/js/pager/css/kkpager.css" />
<style type="text/css">
    *{padding:0px; margin:0px;list-style: none;font-size:1em;}
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
        margin-bottom:2em;
    }

    .gover{
        /*width:90%;*/
        margin:0 auto;
        border-bottom:1px solid #ccc;
        height:4em;
    }
    .gover>div{
        display:inline-block;
        height:100%;
        text-align: left;
        position:relative;
    }
    .gover>.col-xs-5>span{
        position:absolute;
        bottom:0;
    }
    .gover>.col-xs-7{
        height:100%;
        position:relative;
    }
    .gover input[type=text]{
       position:absolute;
        bottom:0;
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
    .table-responsive th,.table-responsive td{
        text-align: center;
        padding:1em 0;
    }
</style>
<#assign data =NPT_ACTION_RETURNED_JSON>
<div class="container-fluid">
    <div class="con_top">
        <div class="alert alert-warning">
            <p>查询说明：</p>
            <p>1、输入“企业名称”或“统一信用代码（18位数字或字母）”查询企业的统一社会信用代码信息。</p>
            <p>2、目前查询的企业统一社会信用代码信息，是有关政府部门按照有关规定向社会公开的信息。</p>
            <p>3、对查看到的信用信息有异议，可在信用信用大理提处异议处理。</p>

        </div>
        <div class="top_con">
            <ul>
                <li class="row">
                <#list data.webQueryCondition as cond>
                    <div class=" row gover">
                        <div class="col-xs-5"><span>${cond.fieldTitle}：</span></div>
                        <div class="col-xs-7" >
                            <input type="text" name="${cond.fieldDBName}" placeholder="请输入${cond.fieldTitle}" value="${cond.fieldQueryValue!}"/>
                        </div>

                    </div>
                </#list>
                </li>
                <li class="button">
                    <input class="btn btn-danger btn-block" type="button" value="查询" onclick="search(1)"/>
                </li>
                <li class="button">
                    <input class="btn btn-primary btn-block" type="button" value="重置" onclick="resetSearch()"/>
                </li>
            </ul>
        </div>
    </div>

</div>

<#--</body>-->
<#--</html>-->
<script>
    /**
     * 作者: 张磊
     * 日期: 2017/03/15 下午03:05
     * 备注: 分页查询
     */
    function search(n) {
        $.ajax({
            url: "/credit/pub/ucc/indexSearchMobile.do",
            data: {
                poolId: 121,
                pageSize: ${data.pageSize},
                currPage: n,
            <#list data.webQueryCondition as cond>
                "webQueryCondition[${cond?index}].fieldTitle": "${cond.fieldTitle}",
                "webQueryCondition[${cond?index}].fieldDBName": "${cond.fieldDBName}",
                "webQueryCondition[${cond?index}].fieldQueryValue": $("input[name='${cond.fieldDBName}']").val(),
            </#list>
            },
            success: function (html) {
                document.write(html);

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
</script>