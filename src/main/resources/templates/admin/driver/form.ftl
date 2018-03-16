<#include "/admin/layout/layout.ftl">
<#import "/admin/layout/macro.ftl" as macro>
<#assign css>

</#assign>
<#assign js>
<script>

    $(function() {
        $('#driverImage').change(function(event) {
            // 根据这个 <input> 获取文件的 HTML5 js 对象
            var files = event.target.files, file;
            if (files && files.length > 0) {
                // 获取目前上传的文件
                file = files[0];
                // 来在控制台看看到底这个对象是什么
                console.log(file);
                // 那么我们可以做一下诸如文件大小校验的动作
                if(file.size > 1024 * 1024 * 2) {
                    alert('图片大小不能超过 2MB!');
                    return false;
                }
                // !!!!!!
                // 下面是关键的关键，通过这个 file 对象生成一个可用的图像 URL
                // 获取 window 的 URL 工具
                var URL = window.URL || window.webkitURL;
                // 通过 file 生成目标 url
                var imgURL = URL.createObjectURL(file);
                // 用这个 URL 产生一个 <img> 将其显示出来
                $('body').append($('<img/>').attr('src', imgURL));
                // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
                // URL.revokeObjectURL(imgURL);
            }
        });
    });
    $(".btn-submit").click(function () {
        $.ajax({
            type: "POST",
            url: "${ctx!}/admin/driver/edit",
            data: $(".form-edit").serialize(),
            dataType: "JSON",
            success: function(res){
                layer.msg(res.message, {time: 2000
                }, function(){
                    location.reload();
                });
            }
        });
    });
</script>
</#assign>
<@layout title="用户编辑" active="driver">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        司机信息编辑
        <small>编辑司机详细信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>
        <li><a href="#"><i class="fa fa-list-ul"></i> 司机管理</a></li>
        <li class="active"><i class="fa fa-edit"></i> 司机编辑</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-10">
            <!-- Default box -->
            <div class="box  box-primary">
                <form class="form-horizontal form-edit" method="post" action="${ctx!}/admin/driver/edit"   enctype="multipart/form-data" target="uploadIframe">

                        <div class="box-body">
                        <input type="hidden" id="id" name="id" value="${driver.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名：</label>
                            <div class="col-sm-10">
                                <input id="name" name="name" class="form-control" type="url" value="${driver.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">电话：</label>
                            <div class="col-sm-10">
                                <input id="phone" name="phone" class="form-control" type="url" value="${driver.phone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">车辆Id：</label>
                            <div class="col-sm-10">
                                <input id="busId" name="busId" class="form-control" value="${driver.busId}">
                            </div>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="button" class="btn btn-default btn-back">返回</button>
                        <button type="button" class="btn btn-info pull-right btn-submit">提交</button>
                    </div>
                </form>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
<!-- /.content -->
</@layout>
