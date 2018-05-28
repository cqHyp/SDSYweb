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
		<title>修改模板</title>
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
		<link href="jules/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
		<link rel="stylesheet" href="css/select2.css" />

		<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
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
							<form action="updateActivity" name="cform" method="post" enctype="multipart/form-data" id="upDetailFrom">
								<div class="text-center ">
									<h1>${detailactivity.RName}</h1>
									<input style="display:none" type="text" value="${detailactivity.RId}" name="itemId" />
									<div class="ibox float-e-margins">
										<div class="ibox-content">
										</div>
										<div class="form-group" style="height: 95px">
										   <input  name="id" type="hidden" value="${detailactivity.RId}">
												<th>起始时间</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="startTime1" placeholder="开始时间" value="${detailactivity.RStartTime}" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
												<th>结束时间</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="endTime1" placeholder="结束时间" value="${detailactivity.REndTime}" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
												</div>
												<div class="form-group" style="height: 95px">
												<th>起始时间2</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="startTime2" placeholder="开始时间" value="${detailactivity.RStartTimeTwo}" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
												<th>结束时间2</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="endTime2" placeholder="结束时间" value="${detailactivity.REndTimeTwo}" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
											
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
		<script src="jules/js/jquery.min.js?v=2.1.4 "></script>
	<script src="js/jquery-ui.js "></script>
	<script src="jules/js/bootstrap.min.js?v=3.3.6 "></script>
	<script src="js/bootstrap-datetimepicker.js "></script>
	<script src="js/bootstrap-datetimepicker.zh-CN.js "></script>
	<script src="jules/js/content.min.js?v=1.0.0 "></script>
	<script src="jules/js/layer/laydate/laydate.js "></script>
	<script src="jules/js/plugins/chosen/chosen.jquery.js "></script>
	<script src="jules/js/plugins/cropper/cropper.min.js "></script>
	<script src="jules/js/demo/form-advanced-demo.min.js "></script>
	<script src="jules/js/plugins/iCheck/icheck.min.js "></script>
	</body>

</html>