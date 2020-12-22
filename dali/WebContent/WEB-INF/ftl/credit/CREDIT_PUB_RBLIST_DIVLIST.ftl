<#include "CommonUtil.ftl">
<#assign data = NPT_ACTION_RETURNED_JSON>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <title>红黑榜</title>
<#include "include/header_link.ftl"/>
    <link rel="stylesheet" href="${ctx}/r/cms/www/red/css/xzxk.css"/>
    <link rel="stylesheet" href="${ctx}/r/cms/www/red/css/publicity.css"/>
    <link rel="stylesheet" href="${ctx}/r/cms/www/red/css/tableDate.css"/>
    <script>
        function detail(uid,kval) {
            $.ajax({
                type:"POST",
                url:"/credit/pub/rbl/detail.do",
                data:{
                    poolId : uid ,
                    primaryKeyValue : kval
                },
                success:function(html){
                    var obj = window.open("about:blank");
                    obj.document.write(html);
                }
            });
//        window.open("/credit/pub/rbl/detail.do?poolId=" + uid + "&primaryKeyValue=" + kval);
        }
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
        function search(n) {
            $.ajax({
                url: "/credit/pub/rbl/list.do",
                data: {
                    poolId: ${RequestParameters.poolId},
                    pageSize: ${data.pageSize},
                    currPage: n,
            <#list data.webQueryCondition as cond>
                "webQueryCondition[${cond?index}].fieldTitle": "${cond.fieldTitle}",
                "webQueryCondition[${cond?index}].fieldDBName": "${cond.fieldDBName}",
                "webQueryCondition[${cond?index}].fieldQueryValue": $("input[name='${cond.fieldDBName}']").val(),
            </#list>
                },
                success: function (html) {
                    $("#md_content").html(html);
                },
                error:function () {
                    $("#md_content").html("请求失败！");
                },
                timeout:function () {
                    $("#md_content").html("数据请求超时！");
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
</head>
<body>
    <div class="wrap">
    <#include "include/header.ftl"/>
        <div class="main">
	<div id="container">
            <div id="md_content" >
                <div class="con_top">
                    <div class="top_con">
                        <ul>
                            <li class="criteria row">
                            <#list data.webQueryCondition as cond>
                                <div class="col-sm-6 row gover">
                                    <span class="col-sm-6">${cond.fieldTitle}：</span>
                                    <input  class="focus col-sm-6" type="text" name="${cond.fieldDBName}" placeholder="请输入${cond.fieldTitle}" value="${cond.fieldQueryValue!}"/>
                                </div>
                            </#list>
                            </li>
                            <li>
                                <input type="button" value="查询" onclick="search(1)"/>
                                <input type="button" value="重置" onclick="resetSearch()"/>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="con-bottom">
                    <div class="bot_con">
                        <table class="table-striped table-hover" border="1" width="100%" cellpadding="0" cellspacing="0" style="border-collapse:collapse">
                            <thead>
                            <#list data.columnTitles as title>
                                <#if title_index gt 0>
                                <th>${title}</th>
                                </#if>
                            </#list>
                            <#--<th>数据详情</th>-->
                            </thead>
                            <tbody>
                            <#if data.dataList??>
                                <#list data.dataList as list>
                                <tr onclick="detail(${RequestParameters.poolId},${list.dataArray[0].value})">
                                    <#list list.dataArray as array>
                                        <#if array_index gt 0>
                                            <td> ${array.value}</td>
                                        </#if>
                                    </#list>
                                    <#--<td><a href="/credit/pub/rbl/detail.do?poolId=${RequestParameters.poolId}&primaryKeyValue=${list.dataArray[0].value}" target="_blank">详细数据</a></td>-->
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
                        <div id="kkpager" class="bot_bot kkpager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <#include "include/footer.ftl"/>

    </body>
</html>
