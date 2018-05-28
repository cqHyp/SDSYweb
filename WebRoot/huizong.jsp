<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	request.setCharacterEncoding("gb2312");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>学生成绩汇总下载</title>
<link rel="stylesheet" href="jules/css/bootstrap.min.css"
	type="text/css" />
<script type="text/javascript" src="jules/js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- Include the plugin's CSS and JS: -->
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="jules/css/bootstrap-multiselect.css"
	type="text/css" />

<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link href="jules/css/animate.min.css" rel="stylesheet">
<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">

<link href="bootstrap-datetimepicker.css" rel="stylesheet">

<link href="jules/css/plugins/chosen/chosen.css" rel="stylesheet">
<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="jules/css/plugins/iCheck/custom.css" rel="stylesheet">
<link
	href="jules/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/select2.css" />

<link rel="stylesheet" href="zyupload/skins/zyupload-1.0.0.min.css "
	type="text/css">
<style type="text/css">
/*预加载动画样式  */
* {
	padding: 0;
	margin: 0;
}

html {
	font-size: 40px;
}

body, html {
	width: 100%;
	height: 100%;
}

.wrap {
	position: relative;
	width: 100%;
	height: 100%;
}

.home {
	width: 100%;
	height: 100%;
	background: url(images/bg.jpg) no-repeat;
	background-size: 100% 100%;
}

.loading {
	position: absolute;
	width: 100%;
	height: 100%;
	background: #fff;
	z-index: 99;
}

.loading p {
	position: fixed;
	left: 50%;
	top: 50%;
	-webkit-transform: translate(-50%, -50%);
	text-align: center;
	color: #222;
	font-size: 0.8rem;
}
</style>
<script type="text/javascript">
			document.onreadystatechange = loadingChange;

			function loadingChange() {
				if(document.readyState == "complete") {
					$(".loading").hide();
				}
			}
		</script>
</head>

<body>

	<div class="wrapper wrapper-content  animated fadeInRight article">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1">
				<div class="ibox">
					<div class="ibox-content">
						<div class="ibox float-e-margins">
							<div class="text-center">
								<h5>学生成绩下载</h5>
							</div>
							<div id="wait" >
 <img src="image/wait.gif" />
 </div>
							<div class="ibox-content">
								<div class="form-group">
									<label class="col-sm-2 control-label">点击按钮下载成绩</label>
									<div class="col-sm-10">
								<button class="btn btn-sm btn-primary m-t-n-xs" type="submit"
											id="xiazai">
											<strong>下载</strong>
										</button>
									</div>
								</div>
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
			$(document).ready(function() {
				$.ajax({
					url: "exportAll",
					type: "POST",
					async: false,
					dataType: "json",
					  beforeSend:function(XMLHttpRequest){
            alert("正在处理，请稍后···"); 
        }, 
					success: function(ss) {
							document.getElementById("wait").style.display="none";
							}
				});
			
			});
			$("#xiazai").click(function() {
				alert("汇总下载");
				window.location.href = "https://sdsy.zzjc.edu.cn/SDSYw/excleFile/huizong.xls";
			});
		
		</script>
</body>

<!-- Mirrored from www.zi-han.net/theme/hplus/clients.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->

</html>