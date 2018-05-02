<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
    window.onload=function(){
        var url   = "${ctx!}/admin/school/schoolList";  //这里填写后端的url
        $.ajax({   //2、发送给后端
            url: url,
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function(ress){
                var string=ress.data;
                var jsonstring=jQuery.parseJSON(string);
                for(var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                        $(".schoolId").prepend("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");
                }
                $(".schoolId").val(${schoolId});
            }

        });
    }
</script>

<script>
    $(".btn-submit").click(function () {
        $.ajax({
            type: "POST",
            url: "${ctx!}/admin/bus/edit",
            data: $(".form-edit").serialize(),
            dataType: "JSON",
            success: function(res){
                layer.msg(res.message, {time: 2000
                }, function(){
                    location.replace("/admin/bus/index");
                });
            }
        });
    });
</script>
</#assign>
<@layout title="用户编辑" active="school">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        车辆编辑
        <small>编辑车辆详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 车辆管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 车辆编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" >
                    <div class="box-body">
                        <div class="col-sm-10">
                            <input type="hidden" id="id" name="id" value="${bus.id}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车牌号：</label>
                                <div class="col-sm-10">
                                    <input id="number" name="number" class="form-control" type="text" value="${bus.number}"  >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">学校：</label>
                                <div class="col-sm-10">
                                    <select name="schoolId" id="schoolId" class="form-control schoolId" >
                                    </select>
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
