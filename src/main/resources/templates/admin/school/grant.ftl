<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>
    function del(id){
        console.log("id"+id)
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(grant){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/admin/school/grant/delete/" + id,
                success: function(res){
                    layer.msg(res.message, {time: 2000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }
</script>
</#assign>
<@layout title="分配校车" active="school" >
<!-- Content Header (Page header) -->
<section class="content-header" onload="load()">
    <h1>
        分配校车
        <small>分配校车详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 学校管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 分配</li>
    </ol>
</section>
<!-- Main content -->
 <div class="box box-primary">
     <div class="box-header">
        <@shiro.hasPermission name="system:school:grant:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/school/grant/add">新增</a>
        </@shiro.hasPermission>
     </div>
     <div class="box-body">
         <table class="table table-striped">
             <tr>
                 <th>ID</th>
                 <th>司机姓名</th>
                 <th>照管员姓名</th>
             </tr>
                <#list pageInfo.content as moreInfo >
                <tr>
                    <td>${moreInfo.id}</td>
                    <td>${moreInfo.driverName}</td>
                    <td>${moreInfo.nurseName}</td>
                    <td>
                    <@shiro.hasPermission name="system:school:grant:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/school/grant/edit/${moreInfo.id}">编辑</a>
                    </@shiro.hasPermission>
                    <#--<@shiro.hasPermission name="system:school:grant">
                        <a class="btn btn-sm btn-warning" href="${ctx!}/admin/school/grant/${schoolInfo.id}">分配</a>
                    </@shiro.hasPermission>-->
                    <@shiro.hasPermission name="system:school:grant:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${moreInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
         </table>
     </div>
     <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo  url="${ctx!}/admin/school/grant" />
     </div>
 </div>
<!-- /.content -->
</@layout>