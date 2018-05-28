<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>修改地址</title>
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
		<div id="showLargeImage" style="display:none;z-index:9999;width:500px;height:400px;position:absolute;left:50%;">
			<img id="ShowImageBox" src="http://img.52fuqing.com/upload/news/20160125/201601251216541264.jpg" width="500px;" height="380px;" />
		</div>
		<div class="wrapper wrapper-content  animated fadeInRight article">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="ibox">
						<div class="ibox-content">
							<form action="updateAddress" name="cform" method="post" enctype="multipart/form-data" id="upDetailFrom">
								<div class="text-center ">
									<h1>${addre.name}</h1>
									<!--  >input style="display:none" type="text" value="${acItem.AId}" name="itemId" /-->
									<div class="ibox float-e-margins">
										<div class="ibox-content">
										</div>
										<div class="form-group" style="height: 95px">
											<label class="col-sm-2" style="a">地址名：</label>
											<textarea class="form-control " rows="2"  type="text" name="name">${addre.name}</textarea>
										</div>
										<div class="form-group" style="height: 95px">
											<label class="col-sm-2" style="a">经度：</label>
											<textarea class="form-control " rows="2"  type="text" name="longitude">${addre.longitude}</textarea>
										</div>
										<div class="form-group" style="height: 95px">
											<label class="col-sm-2" style="a">纬度：</label>
											<textarea class="form-control " rows="3"  type="text" name="latitude">${addre.latitude}</textarea>
										</div>
										<div class="form-group" style="height: auto">
											<input id="submit" class="btn btn-sm btn-primary m-t-n-xs pull-right" type="submit" value="确定 " />
										</div>
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
		<script src="jules/js/jquery.min.js?v=2.1.4"></script>
		<script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
		<script src="jules/js/content.min.js?v=1.0.0"></script>
	</body>

</html>