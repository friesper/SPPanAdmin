<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<script src="/static/assets/plugins/jquery/jquery.js"></script>
<script src="/static/assets/plugins/jQueryUI/jquery-ui.js"></script>
<script>
    window.onload=function() {
        console.log("onload");
        $.ajax({   //2、发送给后端
            url: "${ctx!}/admin/bus/busList",
            type: "GET",
            dataType: "JSON",  //返回的数据类型
            success: function (ress) {
                console.log("success");

                var string = ress.data;
                var jsonstring = jQuery.parseJSON(string);
                for (var p in jsonstring) {//遍历json数组时，这么写p为索引，0,1
                    $("#busId").append("<option value =" + jsonstring[p].id + ">" + jsonstring[p].number + "</option>");
                }

            }
        });
    }

</script>
<script>
    function nofind(_this){
        _this.src="/admin/image/default";
        _this.onerror=null;
    }
</script>
<script>
    $(function() {
        $("#createTime").datepicker();
        $( "#createTime" ).datepicker( "option", "dateFormat", "yy-mm-dd");
    });
</script>
<script>
    $("#find").click(function () {
        if($("#createTime").val()==null||$("#createTime").val()==""){
            layer.msg("请选择时间", {time: 2000}, function () {
                location.reload();
            });
        }
        else {
            location.href = "/admin/info/bus/find/" + $("#busId").val() + "/" + $("#createTime").val();
            console.log("/admin/info/bus/find/" + $("#busId").val() + "/" + $("#createTime").val());
        }
    });
</script>
<script>
    function del(id){
        console.log("id"+id)
        layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctx!}/admin/info/bus/delete/" + id,
                success: function(res){
                    layer.msg(res.message, {time: 2000}, function () {
                        location.reload();
                    });
                }
            });
        });
    }
</script>
<script>
    $("#export").click(function () {
        window.open("/admin/info/status/info/getBusInfoExcel");
    });

</script>
</#assign>
<@layout title="校车每日检查" active="busInfo">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        校车每日检查
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 校车每日检查</a></li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
        <div class="box-header">
        <#--<@shiro.hasPermission name="system:nurse:add">
            <a class="btn btn-sm btn-success" href="${ctx!}/admin/add">新增</a>
        </@shiro.hasPermission>-->
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>车牌号</th>
                    <th>时间</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td>
                        <select id="busId" name="busId" class="form-control busId" >
                        </select></td>
                    <td> <input id="createTime" name="createTime" class="form-control "  value="">
                    </td>
                    <td>
                    <@shiro.hasPermission name="system:info:edit">
                        <a class="btn btn-sm btn-primary"  id="find"  >查找</a>
                    </@shiro.hasPermission>
                    </td>
                </tr>
            </table>
            <@shiro.hasPermission name="system:info:edit">
                        <a class="btn btn-sm btn-primary export"  id="export"  >导出全部</a>
            </@shiro.hasPermission>
        </div>
        <div class="box-footer clearfix">
        </div>
    </div>
    <!-- /.box -->

</section>
<!-- /.content -->
</@layout>