
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


<title></title>

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
						aria-expanded="true"> 良好的学生</a></li>
					<li class=""><a data-toggle="tab" href="#tab-2"
						aria-expanded="false">优秀的学生</a></li>
					<li class=""><a data-toggle="tab" href="#tab-3"
						aria-expanded="false">不及格的学生</a></li>
				</ul>
				<div class="tab-content">
					<div id="tab-1" class="tab-pane active">
						<div class="panel-body">
							<table class="table table-bordered" id="datatable2">
								<thead>
									<tr>
										<th>序号</th>
										<th>学号</th>
										<th>姓名</th>
										<th>班级类别</th>
										<th>联系方式</th>
										<th>是否迟到</th>
										<th>是否早退</th>
										<th>是否迟到</th>
										<th>是否早退</th>
									</tr>
								</thead>

							</table>
							<div class="form-group">
								<button class="btn btn-sm btn-primary m-t-n-xs " id="removeSR1">
									<strong>移至优秀</strong>
								</button>
								<button class="btn btn-sm btn-primary m-t-n-xs " id="removeSR2">
									<strong>移至不及格</strong>
								</button>
							</div>
						</div>
					</div>
					<div id="tab-2" class="tab-pane">
						<div class="panel-body">
							<table class="table table-bordered" id="datatable1">
								<thead>
									<tr>
										<th>序号</th>
										<th>学号</th>
										<th>姓名</th>
										<th>班级类别</th>
										<th>联系方式</th>
										<th>是否迟到</th>
										<th>是否早退</th>
										<th>是否迟到</th>
										<th>是否早退</th>
									</tr>
								</thead>

							</table>
							<div class="form-group">
								<button class="btn btn-sm btn-primary m-t-n-xs " id="removeSL1">
									<strong>移至良好</strong>
								</button>
								<button class="btn btn-sm btn-primary m-t-n-xs " id="removeLL2">
									<strong>移至不及格</strong>
								</button>
							</div>
						</div>
					</div>
					<div id="tab-3" class="tab-pane">
						<div class="panel-body">
							<table class="table table-bordered" id="datatable3">
								<thead>
									<tr>
										<th>序号</th>
										<th>学号</th>
										<th>姓名</th>
										<th>班级类别</th>
										<th>联系方式</th>
										<th>是否迟到</th>
										<th>是否早退</th>
										<th>是否迟到</th>
										<th>是否早退</th>
									</tr>
								</thead>

							</table>
							<div class="form-group">
								<button class="btn btn-sm btn-primary m-t-n-xs " id="removeSL2">
									<strong>移至良好</strong>
								</button>
								<button class="btn btn-sm btn-primary m-t-n-xs " id="removeLL1">
									<strong>移至优秀</strong>
								</button>
							</div>
						</div>
					</div>

				</div>


			</div>
		</div>

	</div>
	<div class="row">
		<div class="ibox">
			<div class="ibox-content">
				<div class="form-group" >
				    <button class="btn btn-sm btn-primary m-t-n-xs "
						style="margin-left:10px" type="submit"
		      	id="mingdan" >
						<strong>名单导出</strong>
					</button>
					<button class="btn btn-sm btn-primary m-t-n-xs "
						style="margin-left: 800px" type="submit" id="submit">
						<strong>确定</strong>
					</button>
					<button class="btn btn-sm btn-primary m-t-n-xs " 
						style="margin-right: 20px;" type="submit">
						<strong>取消</strong>
					</button>

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
	$("#mingdan").click(function() {
		alert("下载名单,请等待生成");
		 $.ajax({
					url: "exportActStudent?id="+deleteid,
					type: "POST",
					async: false,
					dataType: "json",
					success: function(data) {
					if(data.data=="OK"){
					//alert("下载名单");
					window.location.href = "https://sdsy.zzjc.edu.cn/SDSYw/excleFile/mingdan.xls";
					 }
					}
				});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			var left1 = $('#datatable1').DataTable({ 
				// 优秀
				destory : true,
				searching : true,
				bLengthChange : true,
				ordering : false,
				bScrollInfinite : true,
				bScrollCollapse : true,
				aoColumns : [
					{
						"data" : "id"
					},
					{
						"data" : "xuehao"
					},
					{
						"data" : "name"
					},
					{
						"data" : "classleibie"
					},
					{
						"data" : "dianhua"
					},
					{
						"data" : "ischidao"
					},
					{
						"data" : "iszaotui"
					},
					{
						"data" : "ischidao2"
					},
					{
						"data" : "iszaotui2"
					}
				],
			}
			);
			var left2 = $('#datatable3').DataTable({ 
				// 不及格
				destory : true,
				searching : true,
				bLengthChange : true,
				ordering : false,
				bScrollInfinite : true,
				bScrollCollapse : true,
				aoColumns : [
					{
						"data" : "id"
					},
					{
						"data" : "xuehao"
					},
					{
						"data" : "name"
					},
					{
						"data" : "classleibie"
					},
					{
						"data" : "dianhua"
					},
					{
						"data" : "ischidao"
					},
					{
						"data" : "iszaotui"
					},
					{
						"data" : "ischidao2"
					},
					{
						"data" : "iszaotui2"
					}
				],
			}
			);
			var right = $('#datatable2').DataTable(
				{
					// 及格
					destory : true,
					searching : true,
					bLengthChange : true,
					ordering : false,
					bScrollInfinite : true,
					bScrollCollapse : true,
					ajax : "oneActRecord.action?actid=" + deleteid,
					aoColumns : [
						{
							"data" : "id"
						},
						{
							"data" : "xuehao"
						},
						{
							"data" : "name"
						},
						{
							"data" : "classleibie"
						},
						{
							"data" : "dianhua"
						},
						{
							"data" : "ischidao"
						},
						{
							"data" : "iszaotui"
						},
						{
							"data" : "ischidao2"
						},
						{
							"data" : "iszaotui2"
						}
					],
				}
			);
	
	
	
	
	
			$('#datatable1 tbody').on('click', 'tr', function() {
				$(this).toggleClass('selected');
			});
	
			$('#datatable2 tbody').on('click', 'tr', function() {
				$(this).toggleClass('selected');
			});
			$('#datatable3 tbody').on('click', 'tr', function() {
				$(this).toggleClass('selected');
			});
	
	
	
			//移动一个左边->右边
			$("#removeSL1").click(function() { //优秀至良好
				if (left1.rows('.selected').data() == null) {
					alert("请至少选择一行");
				}
				var selectedRow = left1.rows('.selected').data();
				left1.rows('.selected').remove().draw();
				right.rows.add(selectedRow).draw().node();
	
			});
	
			$("#removeLL2").click(function() { //优秀至不及格
				if (left1.rows('.selected').data() == null) {
					alert("请至少选择一行");
				}
				var selectedRow = left1.rows('.selected').data();
				left1.rows('.selected').remove().draw();
				left2.rows.add(selectedRow).draw().node();
	
			});
	
			//移动一个右边->左边
			$("#removeSR1").click(function() { //良好->优秀
				if (right.rows('.selected').data() == null) {
					alert("请至少选择一行");
				}
				var selectedRow = right.rows('.selected').data();
				right.rows('.selected').remove().draw();
				left1.rows.add(selectedRow).draw().node();
			});
	
	
			//移动一个左边->右边
			$("#removeSL2").click(function() { //不及格->良好
				if (left2.rows('.selected').data() == null) {
					alert("请至少选择一行");
				}
				var selectedRow = left2.rows('.selected').data();
				left2.rows('.selected').remove().draw();
				right.rows.add(selectedRow).draw().node();
	
			});
	
			$("#removeLL1").click(function() { //不及格-》优秀
				if (left2.rows('.selected').data() == null) {
					alert("请至少选择一行");
				}
				var selectedRow = left2.rows('.selected').data();
				left2.rows('.selected').remove().draw();
				left1.rows.add(selectedRow).draw().node();
	
			});
	
			//移动一个右边->左边
			$("#removeSR2").click(function() { //良好-》不及格
				if (right.rows('.selected').data() == null) {
					alert("请至少选择一行");
				}
				var selectedRow = right.rows('.selected').data();
				right.rows('.selected').remove().draw();
				left2.rows.add(selectedRow).draw().node();
			});
	
			var pass = "";
			var unpass = "";
			var great = "";
			$("#submit").click(function() {
				left1.column(1)
					.data()
					.each(function(value, index) {
						great += value + ",";
					});
				left2.column(1)
					.data()
					.each(function(value, index) {
						pass += value + ",";
					});
				right.column(1)
					.data()
					.each(function(value, index) {
						unpass += value + ",";
					});
				console.log("pass:" + pass);
				console.log("unpass:" + unpass);
				console.log("great:" + great);
				var url = "updateOneRecord?stuid=" + unpass + "&stuokid=" + pass + "&stugreatid=" + great;
	
				console.log("url:" + url);
				window.location.href = url;
	
			});
	
		});
	</script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
</html>