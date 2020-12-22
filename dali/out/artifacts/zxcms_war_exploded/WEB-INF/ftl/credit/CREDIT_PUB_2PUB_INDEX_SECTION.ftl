<#include "CommonUtil.ftl"/>
<#assign pools=_POOLS>
<#if pools?size gt 0>
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
<#else>
     <div style="font-size: 18px;text-align: center;font-weight: bold;padding-top: 50px;">
         暂无数据
     </div>
</#if>