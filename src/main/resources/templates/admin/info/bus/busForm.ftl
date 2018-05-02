<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>
<style>
</style>
</#assign>
<#assign js>
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
    $("#export").click(function(){
       window.open("${uus}");
    });

</script>
</#assign>
<@layout title="校车接送信息" active="businfo">
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
            <a class="btn btn-sm btn-success" id="export">导出Excel</a>
        </div>
        <div class="box-body">
            <table class="table table-striped">
                <tr>
                    <th>车辆(车牌号)</th>
                    <th>司机姓名</th>
                    <th>时间</th>
                    <th>发动机卫生</th>
                    <th>空滤卫生</th>
                    <th>电瓶卫生</th>
                    <th>药品箱</th>
                    <th>gps和监控</th>
                    <th>灭火器</th>
                    <th>应急门</th>
                    <th>安全锤</th>
                    <th>机油量</th>
                    <th>防冻液量</th>
                    <th>制动液</th>
                    <th>皮带松紧度</th>
                    <th>轮胎气压及螺丝</th>
                    <th>灯光</th>
                    <th>路牌</th>
                    <th>离合</th>
                    <th>制动器</th>
                    <th>方向盘</th>
                    <th>仪表盘</th>
                    <th>操作</th>
                </tr>
                <#list pageInfo.content as busInfo>
                <tr>
                    <td>${busInfo.busNumber}</td>
                    <td>${busInfo.driverName}</td>
                    <td>${busInfo.createTime}</td>
                    <td>${busInfo.engineHygiene}</td>
                    <td>${busInfo.airFilter}</td>
                    <td>${busInfo.batteryHealth}</td>
                    <td>${busInfo.medicineBox}</td>
                    <td>${busInfo.gpsMonitoring}</td>
                    <td>${busInfo.fireExtinguisher}</td>
                    <td>${busInfo.escapeDoor}</td>
                    <td>${busInfo.safetyHammer}</td>
                    <td>${busInfo.oillOilLevel}</td>
                    <td>${busInfo.amountOfAntifreeze}</td>
                    <td>${busInfo.bakeFluid}</td>
                    <td>${busInfo.beltTightness}</td>
                    <td>${busInfo.tirePressureScrews}</td>
                    <td>${busInfo.lights}</td>
                    <td>${busInfo.guideBoard}</td>
                    <td>${busInfo.clutch}</td>
                    <td>${busInfo.brake}</td>
                    <td>${busInfo.steeringWheel}</td>
                    <td>${busInfo.instrumentPanel}</td>
                    <td>
                    <#--<@shiro.hasPermission name="system:info:edit">
                        <a class="btn btn-sm btn-primary" href="${ctx!}/admin/driver/edit/${userInfo.id}">编辑</a>
                    </@shiro.hasPermission>-->
                    <@shiro.hasPermission name="system:info:deleteBatch">
                        <button class="btn btn-sm btn-danger" onclick="del(${busInfo.id})">删除</button>
                    </@shiro.hasPermission>
                    </td>
                </tr>
                </#list>
            </table>
            <div class="box-footer">
                <@macro.page pageInfo=pageInfo url=urls+"?" />
                <button type="button" class="btn btn-default btn-back">返回</button>
            </div>
        </div>
    </div>
    <!-- /.box -->
</section>
<!-- /.content -->
</@layout>