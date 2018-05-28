<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%request.setCharacterEncoding("gb2312");%>


<!DOCTYPE html>
<html>



<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>导入学生</title>
<link rel="stylesheet" href="jules/css/bootstrap.min.css"
	type="text/css" />
<script type="text/javascript" src="jules/js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- Include the plugin's CSS and JS: -->
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="jules/css/animate.min.css" rel="stylesheet">
<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="css/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="jules/css/animate.min.css" rel="stylesheet">
<link href="jules/css/plugins/chosen/chosen.css" rel="stylesheet">
<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="jules/css/plugins/iCheck/custom.css" rel="stylesheet">
<link
	href="jules/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/select2.css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>

<link rel="stylesheet" href="zyupload/skins/zyupload-1.0.0.min.css "
	type="text/css">
</head>
<body class="gray-bg">

	<div class="wrapper wrapper-content  animated fadeInRight article">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1">
				<div class="ibox">
					<div class="ibox-content">
						<div class="ibox float-e-margins">
							<div class="text-center">
								<h5>导入学生</h5>
							</div>
							<div class="ibox-content">
								<form id="file_form" action="importExcleStudent"
									enctype="multipart/form-data" method="post">
									<div class="form-group">
										<label class="col-sm-2 control-label">请选择导入文件</label>
										<div class="col-sm-10">
											<input type="file" name="importStudent" id="file_input"
												class="filename" /> <br /><input type="submit" class="btn btn-primary"
												value="确定" id='upFile-btn'>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript">
		
  	    $("#clerar").click(function() {
	alter("本功能禁用");
		//window.location.href ="clearStudents.action";
	   });
	   
        $(function() {
            $("#file_form").submit(
                function() {
                    //首先验证文件格式
                    var fileName = $('#file_input').val();
                    if (fileName === '') {
                        alert('请选择文件');
                        return false;
                    }
                    var fileType = (fileName.substring(fileName
                        .lastIndexOf(".") + 1, fileName.length))
                        .toLowerCase();
                        if (fileType !== 'xls') {
                            alert('文件格式不正确，excel文件！');
                            return false;
                        }

                    $("#file_form").ajaxSubmit({
                        dataType : "json",
                        success : function(data, textStatus) {
                            if (data['s'] === 'OK') {
					     		alert("文件上传成功");
								$('#file_input').html("");
                                console.log('上传文件成功');
                            } else {
                                console.log('文件格式错误');
                            }
                                return false;
                            }
                        });
                    return false;
            });
			
        });
    </script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/clients.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>
