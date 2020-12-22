
<#include "CommonUtil.ftl"/>

<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/bootstrap.css" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/comm.css" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/font-awesome.css" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/style.css" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/main.css" />
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/xzxk.css"/>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/tableDate.css"/>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/jquery.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/bootstrap.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/style.js" ></script>
<script type="text/javascript" src="${ctx}/r/cms/www/red/js/pager/js/kkpager.min.js"></script>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/nav.css"/>
<link rel="stylesheet" href="${ctx}/r/cms/www/red/css/publicity.css"/>
<style>
    .left{
        float: left;
        width:50%;
    }
    .right{
        float: right;
        width:50%;
    }
    .all{
        width: 98% !important;
        height: 50px;
    }
</style>
<#assign section=_section>
<div id="data">
    <div class="row top_nav">
        <div class="gov-box" style="padding-right: 10px;margin-bottom: 20px;">
            <div id="gov-dali" style="width: 100% ;margin-top: 20px;">
                <button class="btn btn-default all" style="width: 100% !important;margin-left: 0px !important;">大理州</button>
            </div>
            <ul style="overflow: hidden">
                <li id="dali_174" class="left">
                    <button class="btn btn-<#if section['174']?? && section['174'] gt 0>info<#else>default</#if>  all">大理市</button>
                </li>
                <li id="nanjian_173" class="left">
                    <button class="right btn btn-<#if section['173']?? && section['173'] gt 0>info<#else>default</#if> all">南涧县</button>
                </li>
                <li id="jianchuan_177" class="left">
                    <button class="btn btn-<#if section['177']?? && section['177'] gt 0>info<#else>default</#if> all">剑川县</button>
                </li>
                <li id="heqing_178" class="left">
                    <button class="right btn btn-<#if section['178']?? && section['178'] gt 0>info<#else>default</#if> all">鹤庆县</button>
                </li>
                <li id="yunlong_179" class="left">
                    <button class="btn btn-<#if section['179']?? && section['179'] gt 0>info<#else>default</#if> all">云龙县</button>
                </li>
                <li id="eryuan_180" class="left">
                    <button class="right btn btn-<#if section['180']?? && section['180'] gt 0>info<#else>default</#if> all">洱源县</button>
                </li>
                <li id="yongping_181" class="left">
                    <button class="btn btn-<#if section['181']?? && section['181'] gt 0>info<#else>default</#if> all">永平县</button>
                </li>
                <li id="yangbi_182" class="left">
                    <button class="right btn btn-<#if section['182']?? && section['182'] gt 0>info<#else>default</#if> all">漾濞县</button>
                </li>
                <li id="binchuan_175" class="left">
                    <button class="btn btn-<#if section['175']?? && section['175'] gt 0>info<#else>default</#if> all">宾川县</button>
                </li>
                <li id="weishan_176" class="left">
                    <button class="right btn btn-<#if section['176']?? && section['176'] gt 0>info<#else>default</#if> all">巍山县</button>
                </li>
                <li id="midu_183" class="left">
                    <button class="btn btn-<#if section['183']?? && section['183'] gt 0>info<#else>default</#if> all">弥渡县</button>
                </li>
                <li id="xiangyun_184" class="left">
                    <button class="right btn btn-<#if section['184']?? && section['184'] gt 0>info<#else>default</#if> all">祥云县</button>
                </li>
                <li id="xiangyun_242" class="left">
                    <button class="btn btn-<#if section['242']?? && section['242'] gt 0>info<#else>default</#if> all">海开委</button>
                </li>
                <li id="xiangyun_241" class="left">
                    <button class="right btn btn-<#if section['241']?? && section['241'] gt 0>info<#else>default</#if> all" style="padding: 3px 16px!important;">旅游度假区</button>
                </li>
                <li id="xiangyun_243" class="left">
                    <button class="btn btn-<#if section['243']?? && section['243'] gt 0>info<#else>default</#if> all" style="padding: 3px 9px!important;">创新工业园区</button>
                </li>
            </ul>
        </div>
    </div>
</div>
<#--</body>-->
<script>
    $(".gov-box ul li").bind("click",function(){
        $(".load_main").show()
        $(this).stop().find("button").addClass("checked");
        $("#gov-dali").find("button").removeClass("checked");
        $(this).stop().siblings().find("button").removeClass("checked");
        var id= $(this).attr("id").split("_")[1];
        $.ajax({
            url:"/credit/pub/rbl/alSectionMobile.do",
            data:{
                section : id,
                type:'${_type}'
            },
            timeout:30000,
            success:function (html) {
                $("#data").html(html);
                $("#md_content").html("");
                $("#gov-data").hide();
            },
            error:function () {
                $("#data").html("数据请求失败！");
            },
            timeout:function(){
                $("#data").html("数据请求超时！");
            }
        });
    });

</script>