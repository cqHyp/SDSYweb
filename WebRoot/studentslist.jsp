
<%@page contentType="text/html; charset=UTF-8"%>
<%--<%@taglib uri="/struts-tags" prefix="s"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>学生列表</title>

<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link href="jules/css/animate.min.css" rel="stylesheet">
<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="jules/css/dataTables.bootstrap.css" rel="stylesheet">

<style type="text/css">
.selected {
	background-color: #a7aaab;
	cursor: pointer;
}
</style>
</head>

<body class="gray-bg">
	<div class="row">
		<div class="col-sm-12">
			<div class="tabs-container">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#tab-1"
						aria-expanded="true"> 学生列表</a></li>
				</ul>
				<div class="tab-content">
					<div id="tab-1" class="tab-pane active">
						<div class="panel-body">
							<table class="table table-bordered" id="datatable1">
								<thead>
									<tr>
									    <th>学号</th>
										<th>姓名</th>
										<th>性别</th>
										<th>学院</th>
										<th>专业</th>
										<th>班级</th>
										<th style="text-align:center" >班级类型</th>
										<th style="text-align:center" >任职</th>
										<th style="text-align:center" >导师</th>
										<th style="text-align:center" >联系方式</th>
										<th style="text-align:center" >身份证</th>
									</tr>
								</thead>

							</table>
						</div>
					</div>
						</div>
					</div>

				</div>


			</div>

	<script src="jules/js/jquery.min.js?v=2.1.4"></script>
	<script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="jules/js/content.min.js?v=1.0.0"></script>
	<script src="jules/js/jquery.dataTables.js"></script>
	<script src="jules/js/dataTables.bootstrap.js"></script>
	
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
		var deleteid = getUrlParam(url, 'actid');
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			var right = $('#datatable1').DataTable(
				{
					// 及格
					destory : true,
					searching : true,
					bLengthChange : true,
					ordering : false,
					bScrollInfinite : true,
					bScrollCollapse : true,
					ajax : "allStudent.action",
					aoColumns : [
					    {
					        "data" :   "id"
						},
						{
							"data" : "name"
						},
						{
							"data" : "sex"
						},
						{
							"data" : "college"
						},
						{
							"data" : "pr"
						},
						{
							"data" : "clasz"
						},
						{
							"data" : "type"
						},{
							"data" : "job"
						},{
							"data" : "te"
						},{
							"data" : "phone"
						},{
							"data" : "scard"
						}
					],
				}
			);
	
			$('#datatable1 tbody').on('click', 'tr', function() {
				$(this).toggleClass('selected');
			});
		});
	</script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
</html>