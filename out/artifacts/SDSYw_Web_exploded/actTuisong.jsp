<%@page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活动提醒推送</title>
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
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight article">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1">
				<div class="ibox">
					<div class="ibox-content">
						<div class="ibox float-e-margins">
							<div class="text-center">
								<h5>
									活动提醒推送<small></small>
								</h5>
							</div>
							<div class="ibox-content">
								<form method="post" name="changepsd" action="activityTixing"
									class="form-horizontal form-horizontal m-t">
									<div class="form-group">
										<label class="col-sm-2 control-label">推送内容</label>
										 <input  name="actid" id="actid" type="hidden" >
										<div class="col-sm-10">
											<textarea class="form-control " rows="3" type="text" name="message"
											maxsize="10"></textarea>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button id="btnsub" class="btn btn-primary" type="submit">提交</button>
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
<script type="text/javascript">
		var url = window.location;
		function getUrlParam(url, name) {
			var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
			var matcher = pattern.exec(url);
			var items = null;
			if (matcher != null) {
				try {
					items = decodeURIComponent(decodeURIComponent(matcher[1]));
				} catch (e) {
					try {
						items = decodeURIComponent(matcher[1]);
					} catch (e) {
						items = matcher[1];
					}
				}
			}
			return items;
		}
		var id = getUrlParam(url, 'id');
		//alert(id);
		$("#actid").val(id);
			//document.getElementById("actid").value=id;
		
	</script>
</html>