<#include "CommonUtil.ftl"/>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/xzxk.css"/>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/tableDate.css"/>
<#assign pools=_POOLS>
<#assign section=_section>
<#assign dealType=dealType>
<#if pools?size gt 0>
    <div class="row top_nav">
        <div class="gov-box">
            <div id="gov-dali" style="margin-left:15px;">
                <button style="height:28px;padding: 3px 30px!important;" class="btn btn-<#if section['-2']?? && section['-2'] gt 0>info<#else>default</#if>">大理州</button>
            </div>
            <ul style="overflow: hidden">

                <li id="dali_174" class="col-sm-2">
                    <button class="btn btn-<#if section['174']?? && section['174'] gt 0>info<#else>default</#if> checked">大理市</button>
                </li>
                <li id="nanjian_173" class="col-sm-2">
                    <button class="btn btn-<#if section['173']?? && section['173'] gt 0>info<#else>default</#if>">南涧县</button>
                </li>
                <li id="jianchuan_177" class="col-sm-2">
                    <button class="btn btn-<#if section['177']?? && section['177'] gt 0>info<#else>default</#if>">剑川县</button>
                </li>
                <li id="heqing_178" class="col-sm-2">
                    <button class="btn btn-<#if section['178']?? && section['178'] gt 0>info<#else>default</#if>">鹤庆县</button>
                </li>
                <li id="yunlong_179" class="col-sm-2">
                    <button class="btn btn-<#if section['179']?? && section['179'] gt 0>info<#else>default</#if>">云龙县</button>
                </li>
                <li id="eryuan_180" class="col-sm-2">
                    <button class="btn btn-<#if section['180']?? && section['180'] gt 0>info<#else>default</#if>">洱源县</button>
                </li>
                <li id="yongping_181" class="col-sm-2">
                    <button class="btn btn-<#if section['181']?? && section['181'] gt 0>info<#else>default</#if>">永平县</button>
                </li>
                <li id="yangbi_182" class="col-sm-2">
                    <button class="btn btn-<#if section['182']?? && section['182'] gt 0>info<#else>default</#if>">漾濞县</button>
                </li>
                <li id="binchuan_175" class="col-sm-2">
                    <button class="btn btn-<#if section['175']?? && section['175'] gt 0>info<#else>default</#if>">宾川县</button>
                </li>
                <li id="weishan_176" class="col-sm-2">
                    <button class="btn btn-<#if section['176']?? && section['176'] gt 0>info<#else>default</#if>">巍山县</button>
                </li>
                <li id="midu_183" class="col-sm-2">
                    <button class="btn btn-<#if section['183']?? && section['183'] gt 0>info<#else>default</#if>">弥渡县</button>
                </li>
                <li id="xiangyun_184" class="col-sm-2">
                    <button class="btn btn-<#if section['184']?? && section['184'] gt 0>info<#else>default</#if>">祥云县</button>
                </li>
                <li id="xiangyun_242" class="col-sm-2">
                    <button class="btn btn-<#if section['242']?? && section['242'] gt 0>info<#else>default</#if>">海开委</button>
                </li>
                <li id="xiangyun_241" class="col-sm-2">
                    <button class="btn btn-<#if section['241']?? && section['241'] gt 0>info<#else>default</#if>" style="padding: 3px 16px!important;">旅游度假区</button>
                </li>
                <li id="xiangyun_243" class="col-sm-4">
                    <button class="btn btn-<#if section['243']?? && section['243'] gt 0>info<#else>default</#if>" style="padding: 3px 9px!important;">创新工业园区</button>
                </li>
            </ul>
        </div>
        <div>
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
                                        <div class="box-nav" onclick="show_info(${value.id})">
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
    <div id="md_content">

    </div>

<#else>
    <div style="font-size: 18px;text-align: center;font-weight: bold;padding-top: 50px;">
        暂无数据
    </div>
</#if>

<#--</body>-->
<script>
    $(".box-nav").bind("click",function () {
        $(".load_main").show()//显示你的DIV
//        $(".top_title span b").text( $(this).find(".box-text").text());
    });

    function show_info(id) {
        $.ajax({
            url:"/credit/pub/2pub/list.do",
            data:{
                poolId : id,
                pageSize : 10,
                currPage : 1
            },
            timeout:30000,
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
        getData(-2);
    });
    $(".gov-box ul li").bind("click",function(){
        $(".load_main").show()
        $(this).stop().find("button").addClass("checked");
        $("#gov-dali").find("button").removeClass("checked");
        $(this).stop().siblings().find("button").removeClass("checked");
        var id= $(this).attr("id").split("_")[1];
       getData(id);
    });

    function getData(id){
        //判断对象是否被申明了
        if(!_type){
            var _type="${dealType}";
        }
        $.ajax({
            url:"/credit/pub/2pub/alSection.do",
            data:{
                section : id,
                type:_type
            },
            timeout:30000,
            success:function (html) {
                $("#accordion").html(html);
                $("#md_content").html("");
                $("#gov-data").hide();
                load();
            },
            error:function () {
                $("#accordion").html("数据请求失败！");
                load();
            },
            timeout:function(){
                $("#accordion").html("数据请求超时！");
                load();
            }
        });
    }
</script>
<#--</html>-->