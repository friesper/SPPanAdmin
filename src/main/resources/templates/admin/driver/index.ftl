<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<script>
</script>
</#assign>
<@layout title="用户管理" active="user">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        用户列表
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 司机管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 司机列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
        <#--<@shiro.hasPermission name="system:user:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/user/add">新增</a>
        </@shiro.hasPermission>-->
        </div>
     <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as userInfo>
                <tr>
                    <td>${userInfo.id}</td>
                    <td>${userInfo.name}</td>
                    <td>${userInfo.phone}</td>
                    <td>
                    <@shiro.hasPermission name="system:role:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/role/edit/${roleInfo.id}">编辑</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:role:grant">
                        <a class="btn btn-sm btn-warning" href="${ctx!}/admin/role/grant/${roleInfo.id}">分配</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:role:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${roleInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/dirver/index?" />
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>