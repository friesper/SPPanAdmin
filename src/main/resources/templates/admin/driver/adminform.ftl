<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
</script>
<script>
    window.onload=function() {

        var url = "${ctx!}/admin/school/schoolList";  //这里填写后端的url
        $.ajax({   //2、发送给后端
            url: url,
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function (ress) {
                var string = ress.data;
                var jsonstring = jQuery.parseJSON(string);
                for (var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                    $("#workUnitId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");
                }
                $("#workUnitId").val(1);
                $.ajax({   //2、发送给后端
                    url: "${ctx!}/admin/bus/busList/"+$("#workUnitId").val(),
                    type: "GET",
                    dataType: "JSON",  //返回的数据类型
                    success: function (ress) {
                        console.log("success");
                        var string = ress.data;
                        var jsonstring = jQuery.parseJSON(string);
                        for (var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                            $("#busId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].number + "</option>");
                        }
                        $("#busId").val( ${driver.busId});
                    }
                });
            }
        });

    }
</script>
<script>
    $(document).ready(function(){
        $("#workUnitId").change(function(){
            $("#busId").empty();
            $.ajax({   //2、发送给后端
                url: "${ctx!}/admin/bus/busList/"+$("#workUnitId").val(),
                type: "GET",
                dataType: "JSON",  //返回的数据类型
                success: function (ress) {
                    console.log("success");
                    var string = ress.data;
                    var jsonstring = jQuery.parseJSON(string);
                    for (var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                        $("#busId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].number + "</option>");
                    }
                    $("#busId").val( ${driver.busId});
                }
            });
        });
    });


</script>
<script>
    $(".btn-submit").click(function () {
        var phonenumber=$("#phone").val();
        if(phonenumber.length>11){
            layer.msg("手机号不超过11位", {time: 2000
            }, function(){
            });
        }
        else if($("#userName").val()==null||$("#userName").val()==""){
            layer.msg("请输入用户名", {time: 2000
            }, function(){
            });
        }
        else if ($("#passWord").val()==null||$("#passWord").val()==""){
            layer.msg("请输入密码", {time: 2000
            }, function(){
            });
        }

        else {
            var formData = new FormData(document.getElementById("form"))
            $("#workUnitName").val($("#workUnitId").find("option:selected").text());
            $("#busNumber").val($("#busId").find("option:selected").text());
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/admin/driver/edit",
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                data: formData,
                success: function (ress) {
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
<@layout title=" 司机信息" active="driver">
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
                <form  id="form" class="form-horizontal form-edit"   enctype="multipart/form-data" target="uploadIframe">
                    <div class="box-body">
                        <input type="hidden" id="driverImage" name="driverImage" value="${driver.driverImage}">
                        <input type="hidden" id="id" name="id" value="${driver.id}">
                        <input type="hidden" id="busNumber" name="busNumber" <#if driver.busNumber?exists> value="${driver.busNumber}" <#else >value=""</#if>>
                        <input type="hidden" id="workUnitName" name="workUnitName"  <#if driver.workUnitName?exists>value="${driver.workUnitName}"<#else >value=""</#if>>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名：</label>
                            <div class="col-sm-10">
                                <input id="name" name="name" class="form-control" type="text" value="${driver.name}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名(登陆用)：</label>
                            <div class="col-sm-10">
                                <input id="userName" name="userName" class="form-control" type="text" value="${driver.userName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-10">
                                <input id="passWord" name="passWord" class="form-control" type="text" value="${driver.passWord}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">电话：</label>
                            <div class="col-sm-10">
                                <input id="phone" name="phone" class="form-control" value="${driver.phone}">
                            </div>
                        </div>
                        <div class="form-group" >
                            <label class="col-sm-2 control-label">工作单位：</label>
                            <div class="col-sm-10">
                                <select  id="workUnitId" name="workUnitId" class="form-control workUnitId" <#if roleId!=1>readonly="readonly" </#if>     >
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">车辆号(车牌号)：</label>
                            <div class="col-sm-10">
                                <select  id="busId" name="busId" class="form-control busId" <#if roleId!=1>readonly="readonly" </#if>     >
                                </select>
                            </div>
                        </div>

                        <div class="form-group"  id="driverImageDiv"  >
                            <label class="col-sm-2 control-label">驾照图片：</label>
                            <div class="col-sm-10" >
                                <img id="showImage"  width="50" height="50" src="/admin/image/${driver.driverImage}" onerror='var list=document.getElementById("driverImageDiv");
list.remove();'>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">上传驾照图片：</label>
                            <div class="col-sm-10">
                                <input id="driverImageFile" name="driverImageFile" class="form-control" type="file" value="${driver.driverImageFile}">
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
