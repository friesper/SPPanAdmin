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
                    if (${role.schoolId} == jsonstring[p].id ) {
                        $(".schoolList").prepend("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");

                    } else {
                        $(".schoolList").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");

                    }
                }
                $(".schoolList").val(${role.schoolId});

            }

        });
    }
</script>
<script>
    $(".btn-submit").click(function () {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "${ctx!}/admin/role/edit",
            data: $(".form-edit").serialize(),
            success: function(res){
                layer.msg(res.message, {time: 2000
                }, function(){
                    location.replace("/admin/role/index");
                });
            }
            error:function () {

            }
        });
    });
</script>
</#assign>
<@layout title="角色编辑" active="role" >
<!-- Content Header (Page header) -->
<section class="content-header" onload="load()">
    <h1>
        分配校车
        <small>分配校车详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 学校管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 分配校车</li>
    </ol>
</section>
<!-- Main content -->
<section class="content" >
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" id="frm"   onload="load()">
                    <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${Moreinfo.bus.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">校车车牌号：</label>
                            <div class="col-sm-8">
                                <input id="busNumber" name="busNumber" class="form-control" type="text" value="${Moreinfo.bus.number}" <#if Moreinfo.bus?exists> readonly="readonly"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">司机姓名：</label>
                            <div class="col-sm-8">

                                <select name="driverNameList" class="form-control driverNameList" >
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