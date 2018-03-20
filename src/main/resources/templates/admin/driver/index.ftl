<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<script>
    window.onload=function(){
        var url   = "${ctx!}/admin/school/schoolList";  //这里填写后端的url
        $.ajax({   //2、发送给后端
            url: url,
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function(res){
                var string=res.data;
                var jsonstring=jQuery.parseJSON(string);
                for(var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1

                }
            }

        });


    }


</script>
<script>
    function del(id){
        console.log("id"+id)
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/admin/driver/delete/" + id,
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
<@layout title="用户管理" active="driver">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        司机列表
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
        <@shiro.hasPermission name="system:driver:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/driver/add">新增</a>
        </@shiro.hasPermission>
        </div>
     <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>车辆(车牌号)</th>
                    <th>驾照</th>
                    <th>所属单位</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as userInfo>
                <tr>
                    <td>${userInfo.id}</td>
                    <td>${userInfo.name}</td>
                    <td>${userInfo.phone}</td>
                    <td>${userInfo.busNumber}</td>
                    <td><img src="${userInfo.driverImage}"></td>
                    <td>${userInfo.workUnitName}</td>

                    <td>
                    <@shiro.hasPermission name="system:driver:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/driver/edit/${userInfo.id}">编辑</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:driver:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${userInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/dirver/index/"/>
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>