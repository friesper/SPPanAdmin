<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
    window.onload=function() {
        console.log("onload");
        $.ajax({   //2、发送给后端
            url: "${ctx!}/admin/bus/busList",
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function (ress) {
                console.log("success");

                var string = ress.data;
                var jsonstring = jQuery.parseJSON(string);
                for (var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                    $("#busId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].number + "</option>");
                }
                $("#busId").val( ${pageInfo.busId});
            }
        });

        $.ajax({   //2、发送给后端
            url: "${ctx!}/admin/driver/driverList",
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function (ress) {
                console.log("success");
                var string = ress.data;
                var jsonstring = jQuery.parseJSON(string);
                for (var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                    $("#driverId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");
                }
                $("#driverId").val(${pageInfo.driverId});
            }
        });
        $.ajax({   //2、发送给后端
            url: "${ctx!}/admin/nurse/nurseList",
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function (ress) {
                console.log("success");
                var string = ress.data;
                var jsonstring = jQuery.parseJSON(string);
                for (var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                    $("#nurseId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");
                }
                $("#nurseId").val( ${pageInfo.nurseId});
            }
        });
    }
</script>
<script>
    $(".btn-submit").click(function () {
        $("")
        console.log($(".form-edit").serialize().toString());
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "${ctx!}/admin/school/grant/edit",
            data: $(".form-edit").serialize(),
            success: function(res){
                layer.msg(res.message, {time: 2000
                }, function(){
                    location.replace("/admin/school/grant/"+${pageInfo.schoolId});
                });
            }
        });
    });

</script>
</#assign>
<@layout title="校车信息编辑" active="school" >
<!-- Content Header (Page header) -->
<section class="content-header" onload="load()">
    <h1>
        校车信息
        <small>校车信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 学校信息</a></li>
        <li class="active"><i class="fa fa-edit"></i> 校车信息编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content" >
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" id="frm" method="post" action="${ctx!}/admin/grant/edit" onload="load()">
                    <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${pageInfo.id}">
                        <input type="hidden" id="schoolId" name="schoolId" value="${pageInfo.schoolId}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">校车：</label>
                            <div class="col-sm-8">
                                <select id="busId" name="busId" class="form-control busId" >
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">司机：</label>
                            <div class="col-sm-8">
                                <select id="driverId" name="driverId" class="form-control driverId" >
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">照管员：</label>
                            <div class="col-sm-8">
                                <select  id="nurseId" name="nurseId" class="form-control nurseId" >
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