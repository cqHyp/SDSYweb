<%@page contentType="text/html; charset=UTF-8" %>
<%--<%@taglib uri="/struts-tags" prefix="s"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
<head>
    <meta charset="UTF-8">
    <title>添加公告</title>
    <link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="jules/css/animate.min.css" rel="stylesheet">
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	
	<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="jules/css/animate.min.css" rel="stylesheet"> 
	
    <link href="jules/css/plugins/chosen/chosen.css" rel="stylesheet">    
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="jules/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="jules/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
	<link rel="stylesheet" href="css/select2.css" />
	
	<link rel="stylesheet" href="zyupload/skins/zyupload-1.0.0.min.css " type="text/css">


   <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	
   <script type="text/javascript" src="zyupload/zyupload-1.0.0.min.js"></script>

    <style>
        th{
            background-color: #F5F5F6;
        }
    </style>
	  <script type="text/javascript">
function getPhotoSize(obj){
		photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
		if(photoExt!='.jpg'&&photoExt!='.png'&&photoExt!='.jpeg'){
	        alert("文件格式错误！");
	 	    var nf = obj.cloneNode(true); 
            nf.value=''; 
            obj.parentNode.replaceChild(nf, obj);
			return false;
	}
	var fileSize = 0;
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
	if (isIE && !obj.files) { 
	var filePath = obj.value; 
	var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
	var file = fileSystem.GetFile (filePath); 
	fileSize = file.Size; 
	}else { 
	fileSize = obj.files[0].size; 
	}
	fileSize=Math.round(fileSize/1024*100)/100; //µ¥Î»ÎªKB
	if(fileSize>2048){
	alert("不接受超过2m的图片！");
	return false;
}
}
</script>
<script type="text/javascript">
</script>
</head>
<body class="gray-bg">

    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content">


                        <div class="ibox float-e-margins">
                            <div class="text-center">
                                <h3>发布公告详情</h3>
                            </div>

                            <div class="ibox-content">

                                <form action="addAnnounce" name="cform" method="post" enctype="multipart/form-data">
                                    <table class="table table-bordered" style="height: auto;">
                                        <tr>

                                            <th>公告名称</th>
                                            <td><input class="form-control " type="text" name="title"></td>

										<tr>
                                            <th>公告内容</th>
                                            <td colspan="3"><textarea class="form-control " rows="10" type="text" name="desc"></textarea></td>
                                        </tr>
										
                                    </table>

                                    <div class="form-group" style="height: auto">

                                        <input class="btn btn-sm btn-primary m-t-n-xs pull-right" style="margin-left: 20px"
                                               type="submit" value="取消">
                                        </input>
                                        <input id="submit" class="btn btn-sm btn-primary m-t-n-xs pull-right" type="submit"
                                               value="确定">
                                        </input>
                                    </div>
                                </form>


                            </div>
                        </div>


                    </div>
                </div>


            </div>
        </div>
    </div>


    <script src="jules/js/jquery.min.js?v=2.1.4"></script>
	<script src="js/jquery-ui.js"></script>
    <script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="bootstrap-datetimepicker.js"></script>
	<script src="bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="jules/js/content.min.js?v=1.0.0"></script>
	<script src="jules/js/layer/laydate/laydate.js"></script>
    <script src="jules/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="jules/js/plugins/cropper/cropper.min.js"></script>
    <script src="jules/js/demo/form-advanced-demo.min.js"></script>  
    <script src="jules/js/plugins/iCheck/icheck.min.js"></script>
	

    <script>
        $(document).ready(function () {
			
			 $(".form_datetime").datetimepicker({language:'zh-CN',format: 'yyyy-mm-dd hh:ii'});
        });
    </script>

</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
</html>
