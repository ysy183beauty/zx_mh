<#include "CommonUtil.ftl"/>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <title>服务机构</title>
    <#include "include/header_link.ftl"/>
    <link rel="stylesheet" href="${ctx}/r/cms/www/red/css/publicity.css" />
    <style>
        .credit_list a{
            font-size:14px!important;
        }
    </style>
</head>
<body onload="test('/fwjg/index.jhtml')">

<div class="wrap">s</div>
<#include "include/footer.ftl"/>
</body>
<script type="text/javascript">
    function load(){
        window.setTimeout("$('.load_main').hide()",100);//100毫秒后，隐藏你的DIV
    }
    function test(url){
        $.ajax({
            url: url,
            data: {},
            timeout: 30000,
            success: function (data) {
                $("#mainContent").html(data);
            }
        });
    }
</script>
</html>