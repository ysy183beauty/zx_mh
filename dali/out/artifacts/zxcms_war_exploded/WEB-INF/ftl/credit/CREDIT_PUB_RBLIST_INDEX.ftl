
<#include "CommonUtil.ftl"/>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/xzxk.css"/>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/tableDate.css"/>
<#--<#if NPT_BASEMODEL_GROUPOOL??>-->
    <#assign pools=_POOLS>
    <#if pools?size gt 0>
        <div class="row top_nav">
            <div class="gov-box">
                <div id="gov-dali"    style="margin-left: 15px;">
                    <button style="height:28px;padding: 3px 30px!important;" class="btn btn-default">大理州</button>
                </div>
                <ul style="overflow: hidden">
                    <li id="dali" class="col-sm-2">
                        <button class="btn btn-info checked">大理市</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">南涧县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">剑川县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">鹤庆县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">云龙县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">洱源县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">永平县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">漾濞县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">宾川县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">巍山县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">弥渡县</button>
                    </li>
                    <li class="col-sm-2">
                        <button class="btn btn-default">祥云县</button>
                    </li>
                    <li id="xiangyun_242" class="col-sm-2">
                        <button class="btn btn-default">海开委</button>
                    </li>
                    <li id="xiangyun_241" class="col-sm-2">
                        <button class="btn btn-default" style="padding: 3px 16px!important;">旅游度假区</button>
                    </li>
                    <li id="xiangyun_243" class="col-sm-4">
                        <button class="btn btn-default" style="padding: 3px 9px!important;">创新工业园区</button>
                    </li>
                </ul>
            </div>
            <div >
                <div id="gov-data">暂无数据</div>
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <#list pools?keys as key>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="heading${key_index}">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${key_index}" aria-expanded="false" aria-controls="collapse${key_index}">
                                    ${key}
                                        <span>[${pools[key]?size}]</span>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse${key_index}" class="panel-collapse collapse " role="tabpanel" aria-labelledby="heading${key_index}">
                                <div class="panel-body">
                                <p class="row">
                                    <#list pools[key] as value>
                                        <div class="col-md-4 col-sm-6">
                                            <div class="box-nav" onclick="show_info(null,${value.id},0)">
                                                <div class="box-text">
                                                ${value.poolTitle}
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
        <div id="md_content"></div>
        <#else>
        <div style="font-size:24px;text-align:center;">
            暂无数据
        </div>
    </#if>
<#--<#else>-->
    <#--<div>暂无数据</div>-->
<#--</#if>-->

    <#--</body>-->
<script>
    function show_info(param,id,_index) {
        $(".load_main").show();
        $.ajax({
            url:"/credit/pub/rbl/list.do",
            data:{
                poolId : id,
                pageSize : 10,
                currPage : 1,
                fromIndex: _index,
                bean  : param
            },
            success:function (html) {
                $("#md_content").html(html);
                $(".focus").eq("0").focus();
                $.scrollTo('#md_content',500);
                load();
            },
            error:function () {
                $("#md_content").html("数据请求失败！");
                load();
            },
            timeout:function(){
                $("#md_content").html("数据请求超时！");
                load();
            }
        })
    }
    //    行政区域划分
    $("#gov-dali").click(function(){
        $(this).find("button").addClass("checked").parent().siblings().find("button").removeClass("checked");
        $("#gov-data").show();
        $("#accordion").hide();
        $("#md_content").html("");
    });
    $(".gov-box ul li").bind("click",function(){
        $(this).stop().find("button").addClass("checked");
        $("#gov-dali").find("button").removeClass("checked");
        $(this).stop().siblings().find("button").removeClass("checked");
        if($(this).attr("id") != "dali"){
            $("#gov-data").show();
            $("#accordion").hide();
            $("#md_content").html("");
        }
        else{
            $("#gov-data").hide();
            $("#accordion").show();
            $("#md_content").html("");
        }
    })
</script>
<#--</html>-->