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
                url: "${ctx!}/admin/school/delete/" + id,
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
<@layout title="学校管理" active="school">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        学校列表
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 学校管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 学校列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
        <@shiro.hasPermission name="system:school:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/school/add">新增</a>
        </@shiro.hasPermission>
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>名字</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as schoolInfo>
                <tr>
                    <td>${schoolInfo.id}</td>
                    <td>${schoolInfo.name}</td>
                    <td>
                    <@shiro.hasPermission name="system:school:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/school/edit/${schoolInfo.id}">编辑</a>
                    </@shiro.hasPermission>
                   <@shiro.hasPermission name="system:school:grant">
                        <a class="btn btn-sm btn-warning" href="${ctx!}/admin/school/grant/${schoolInfo.id}">分配</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:school:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${schoolInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/school/index?" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>