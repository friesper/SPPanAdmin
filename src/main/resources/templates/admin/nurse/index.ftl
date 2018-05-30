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
                url: "${ctx!}/admin/nurse/delete/" + id,
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
<@layout title="照管员管理" active="nurse">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        照管员列表
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 照管员管理</a></li>
        <li class="active"><i class="fa fa-table"></i> 照管员列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
        <@shiro.hasPermission name="system:nurse:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/nurse/add">新增</a>
        </@shiro.hasPermission>
        </div>
     <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>所属单位</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as userInfo>
                <tr>
                    <td>${userInfo.id}</td>
                    <td>${userInfo.name}</td>
                    <td>${userInfo.phone}</td>
                    <td>${userInfo.workUnitName}</td>
                    <td><img src="${ctx!}/admin/image/${userInfo.nurseImage}"  width="50" height="50" onerror='nofind(this)'></td>
                    <td>
                    <@shiro.hasPermission name="system:nurse:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/nurse/edit/${userInfo.id}">编辑</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="system:nurse:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${userInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
        <div class="box-footer clearfix">
            <@macro.page pageInfo=pageInfo url="${ctx!}/admin/nurse/index/"/>
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>