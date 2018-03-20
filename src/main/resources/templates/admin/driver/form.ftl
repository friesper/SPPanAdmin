<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
    window.onload=function(){
        console.log("hellomload");
        var url   = "${ctx!}/admin/school/schoolList";  //这里填写后端的url
        $.ajax({   //2、发送给后端
            url: url,
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function(ress){
                var string=ress.data;
                var jsonstring=jQuery.parseJSON(string);
                for(var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                        $(".workUnitId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");
                }
            }
        });
    }
</script>
<script>
    $(".btn-submit").click(function () {
        var phonenumber=$("#phone").val();
          if(phonenumber.length>11){
              layer.msg("手机号不超过11位", {time: 2000
              }, function(){
              });
          }

        else {
              $("#workUnitName").val($("#workUnitId").find("option:selected").text());
              $.ajax({
                  type: "POST",
                  dataType: "json",
                  url: "${ctx!}/admin/driver/edit",
                  data: $(".form-edit").serialize(),
                  success: function (ress) {
                      console.log($(".form-edit").serialize());
                      layer.msg(ress.message, {
                          time: 2000
                      }, function () {
                          location.replace("/admin/driver/index");
                      });
                  }
                  /*error: function (ress) {
                      layer.msg(ress.message, {time: 2000
                      }, function(){
                      });
                  }*/
              });
          }
    });
</script>
</#assign>
<@layout title="用户编辑" active="driver">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        司机信息编辑
        <small>编辑司机详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 司机管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 司机编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" method="post" action="${ctx!}/admin/driver/edit"   enctype="multipart/form-data" target="uploadIframe">
                        <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${driver.id}">
                        <input type="hidden" id="busId" name="busId" <#if driver.busId?exists> value="${driver.busId}" <#else >value=""</#if>>
                        <input type="hidden" id="workUnitName" name="workUnitName"  <#if driver.workUnitName?exists>value="${driver.workUnitName}"<#else >value=""</#if>>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名：</label>
                            <div class="col-sm-10">
                                <input id="name" name="name" class="form-control" type="text" value="${driver.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">电话：</label>
                            <div class="col-sm-10">
                                <input id="phone" name="phone" class="form-control" value="${driver.phone}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">车辆号(车牌号)：</label>
                            <div class="col-sm-10">
                                <input id="busNumber" name="busNumber" class="form-control" type="text" value="${driver.busNumber}">
                            </div>
                        </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">工作单位：</label>
                                <div class="col-sm-10">
                                    <select  id="workUnitId" name="workUnitId" class="form-control workUnitId"  >
                                    </select>
                                </div>
                            </div>
                    </div>
                    <div class="box-footer">
                        <button type="button" class="btn btn-default btn-back">返回</button>
                        <button type="button" class="btn btn-info pull-right btn-submit">提交</button>
                    </div>
                </form>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
<!-- /.content -->
</@layout>
