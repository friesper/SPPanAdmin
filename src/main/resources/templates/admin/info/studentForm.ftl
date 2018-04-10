<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<script>
    function nofind(_this){
        _this.src="/admin/image/default";
        _this.onerror=null;
    }
</script>
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
                url: "${ctx!}/admin/info/student/delete/" + id,
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
<@layout title="校车接送信息" active="info">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        校车接送信息列表
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 信息管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 校车接送信息列表</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
       <#-- <@shiro.hasPermission name="system:info:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/info/studnet/add">新增</a>
        </@shiro.hasPermission>-->
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>车辆(车牌号)</th>
                    <th>司机姓名</th>
                    <th>时间</th>
                    <th>学生姓名</th>
                    <th>乘车情况</th>
                    <th>照管员姓名</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as statusInfo>
                <tr>
                    <td>${statusInfo.busNumber}</td>
                    <td>${statusInfo.driverName}</td>
                    <td>${statusInfo.takeTime}</td>
                    <td>${statusInfo.studentName}</td>
                    <td> <#if statusInfo.status ==0>未乘车</#if> <#if statusInfo.status==1>乘车</#if></td>
                    <td>${statusInfo.nurseName}</td>
                    <td>
                    <#--<@shiro.hasPermission name="system:info:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/driver/edit/${userInfo.id}">编辑</a>
                    </@shiro.hasPermission>-->
                    <@shiro.hasPermission name="system:info:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${statusInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
    </div>
    <!-- /.box -->
</section>
<!-- /.content -->
</@layout>