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
    $(function() {
        $("#takeTime").datepicker();
        $( "#takeTime" ).datepicker( "option", "dateFormat", "yy-mm-dd");
    });
</script>
<script>
    $("#find").click(function () {
        if($("#takeTime").val()==null||$("#takeTime").val()==""){
            layer.msg("请选择时间", {time: 2000}, function () {
                location.reload();
            });
        }
        else {
            location.href = "/admin/info/student/find/" + $("#busId").val() + "/" + $("#takeTime").val();
            console.log("/admin/info/student/find/" + $("#busId").val() + "/" + $("#takeTime").val());
        }
    });
</script>
<script>
    $("#export").click(function () {
        window.open("/admin/info/status/info/getExcel");
    });

</script>
</#assign>
<@layout title="接送学生信息" active="info">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        接送学生信息
        <small>一切从这里开始</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 接送学生信息</a></li>
        <li class="active"><i class="fa fa-table"></i> 接送学生信息列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <!-- Default box -->
    <div class="box box-primary">
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
                    <td>
                        <input id="takeTime" name="takeTime" class="form-control"  value="">
                    </td>
                    <td>
                    <@shiro.hasPermission name="system:info:edit">
                        <a class="btn btn-sm btn-primary"  id="find"  >查找</a>
                    </@shiro.hasPermission>

                        <form action='ajax_url' hidden="hidden">
                        <input type="text" name="export" value="export">
                        <input type="submit" value="提交">
                        </form>
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