<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>

	<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->

	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<title>个人信息</title>

		<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
		<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

		<link href="jules/css/animate.min.css" rel="stylesheet">
		<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
		<style type="text/css">

		</style>
		<script type="text/javascript" src="js/jquery.min.js">
		</script>
		<script src="js/qrcode.min.js">
		</script>
	</head>

	<body class="gray-bg">
		<div class="wrapper wrapper-content  animated fadeInRight article">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="ibox">
						<div class="ibox-content">
							<div class="text-center ">

								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h5>个人信息</h5>
									</div>
									<div class="ibox-content">
										<div class="form-group" style="height: 70px;">
											<label class="col-sm-2" style="a">照片：</label>
											<span><img src="${teacher.TPicture}" width="60px;" height="80px;"/></span>
										</div>
										<div class="form-group" style="height: 70px;">
											<label class="col-sm-2" style="a">姓名：</label>
											<span>${teacher.TName}</span>
										</div>
										<div class="form-group" style="height: 70px">
											<label class="col-sm-2 control-label">手机号：</label>
											<span>${teacher.TPhone}</span>
										</div>
										<div class="form-group" style="height: 70px">
										
											<label class="col-sm-2 control-label">邮箱：</label>
											<span>${teacher.TEmail}</span>
										</div>
										<div class="form-group" style="height: 70px;">
											<label class="col-sm-2" style="a">工号：</label>
											<span>${teacher.id.TTeacherId}</span>
										</div>
										<div class="form-group" style="height: 70px">
											<label class="col-sm-2 control-label">所属分院</label>
											<span>${teacher.TCollege}</span>
										</div>
										

										<div class="hr-line-dashed"></div>

										<div class="form-group" style="height: 80px;">
											<label class="col-sm-2 control-label">导师简介</label>
											<textarea disabled="disabled" rows="4" type="text" name="feature" class="form-control" style="width:600px">${teacher.TIntroduction}</textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		</div>
		<script src="jules/js/jquery.min.js?v=2.1.4"></script>
		<script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
		<script src="jules/js/content.min.js?v=1.0.0"></script>
	</body>
</html>