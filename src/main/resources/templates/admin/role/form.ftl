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
                        $(".schoolId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].name + "</option>");

                 }
                $(".schoolId").val(${role.schoolId});

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
        });
    });
</script>
</#assign>
<@layout title="角色编辑" active="role" >
<!-- Content Header (Page header) -->
<section class="content-header" onload="load()">
    <h1>
        角色编辑
        <small>编辑角色详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 角色管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 角色编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content" >
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" id="frm" method="post" action="${ctx!}/admin/role/edit" onload="load()">
                    <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${role.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">角色key：</label>
                            <div class="col-sm-8">
                                <input id="roleKey" name="roleKey" class="form-control" type="text" value="${role.roleKey}" <#if role?exists> readonly="readonly"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">角色单位：</label>
                            <div class="col-sm-8">

                                <select name="schoolId" class="form-control schoolId" >
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">角色名称：</label>
                            <div class="col-sm-8">
                                <input id="name" name="name" class="form-control" type="text" value="${role.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select name="status" class="form-control">
                                    <option value="0" <#if role.status == 0>selected="selected"</#if>>正常</option>
                                    <option value="1" <#if role.status == 1>selected="selected"</#if>>禁用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <textarea id="description" name="description" class="form-control" rows="6">${role.description}</textarea>
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