<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<script>
    function del(id) {
        layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/admin/bus/delete/" + id,
                success: function (res) {
                    layer.msg(res.message, {time: 2000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }
</script>
</#assign>
<@layout title="车辆列表" active="bus">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        车辆列表
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 车辆管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 车辆列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
        <@shiro.hasPermission name="system:bus:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/bus/add">新增</a>
        </@shiro.hasPermission>
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>车辆车号</th>
                </tr>
                 <#list pageInfo.content as busInfo>
                <tr>
                    <td>${busInfo.id}</td>
                    <td>${busInfo.number}</td>
                    <td>
                    <@shiro.hasPermission name="system:bus:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/bus/edit/${busInfo.id}">编辑</a>
                    </@shiro.hasPermission>
                    <#--<@shiro.hasPermission name="system:school:grant">
                        <a class="btn btn-sm btn-warning" href="${ctx!}/admin/school/grant/${schoolInfo.id}">分配</a>
                    </@shiro.hasPermission>-->
                    <@shiro.hasPermission name="system:bus:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${busInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
        <div class="box-footer clearfix">
            <@macro.page pageInfo=busInfo url="${ctx!}/admin/info/bus/index?" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>