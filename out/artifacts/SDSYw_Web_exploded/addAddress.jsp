<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->

	<head>
		<meta charset="UTF-8">
		<title>Title</title>
		<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
		<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
		<link href="jules/css/animate.min.css" rel="stylesheet">
		<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="jules/css/iconfont.css">
		<script src="jules/js/jquery.min.js?v=2.1.4"></script>
		<style>
			th {
				background-color: #F5F5F6;
			}
			
			#container {
				width: 500px;
				height: 300px;
				border: 1px solid gray;
				margin: 0 auto;
			}
		</style>
		<script type="text/javascript">
		$(document).ready(function(){
  			$("#displayInput").click(function(){
  				$("#dis").toggle();
  			});
		});
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
									<h3>新增地址</h3>
								</div>
								<div class="ibox-content">

									<form action="addAddress" name="addaddress" method="post" enctype="multipart/form-data">
										<table class="table table-bordered" style="height: auto;">
											<tr>

												<th>教室名称</th>
												<td>
													<input class="form-control " type="text" name="address" required="required">
												</td>

											</tr>
											<tr>

												<th>教室经度</th>
												<td>
													<input class="form-control " type="text" name="longitude" id="jd" required="required">
												</td>
											</tr>
											<tr>
												<th>教室纬度</th>
												<td>
													<input class="form-control " type="text" name="latitude" id="wd" required="required">
												</td>
											</tr>
											<tr>
												<th>扫码范围&nbsp;<i class="iconfont icon-wenhao" id="displayInput" required="required"></th>
												<td>
													<input class="form-control " type="text" name="range" id="displayInput" placeholder="推荐范围10~20">
													<input class="form-control " type="text " name=" " value="为了防止学生拍照扫码，因此设置一个扫码范围" disabled="disabled" id="dis" style="display: none;">
												</td>
											</tr>
										</table>

										<div class="form-group" style="height: auto">

											<input class="btn btn-sm btn-primary m-t-n-xs pull-right" style="margin-left: 20px" type="submit" value="取消">

											<input id="submit" class="btn btn-sm btn-primary m-t-n-xs pull-right" type="submit" value="确定">

										</div>
									</form>

								</div>
							</div>

						</div>
					</div>

				</div>
			</div>
		</div>

		
		<script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
		<script src="jules/js/content.min.js?v=1.0.0"></script>
		<script src="jules/js/layer/laydate/laydate.js"></script>

	</body>

</html>