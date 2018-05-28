<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<base >
<title>后台登陆</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />

<link rel="stylesheet" href="css/reset2.css">
<link rel="stylesheet" href="css/style2.css" media="screen" type="text/css" />	
<style>
body {
	background-color: #FEFEFE;
}
</style>
<link rel="stylesheet" type="text/css" href="css/style3.css">
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/matrix-login.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<style type="text/css">
input {
	font-family: "Microsoft Yahei";
}

.control-label {
	color: #B2DFEE;
	padding-left: 4px;
}
</style>
</head>
<body>
	<script src="js/index.js"></script>
	<div id="myDiv">
		<div id="loginbox">
			<div class="control-group normal_text">
				<h2 style="color:#B2DFEE;font-size:28px;">“尚德书院”信息中心</h2>
			</div>
			<form method="Get" id="loginform" class="form-vertical" action="login.action"
				>
				<div class="control-group">
					<label class="control-label">用户名</label>
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"><i class="icon-user"
								style="font-size:16px;"></i></span><input name="name" type="text" />
						</div>
					</div>
				</div>
				<div class="control-group2">
					<label class="control-label">登陆密码</label>
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly"><i class="icon-lock"
								style="font-size:16px;"></i></span><input name="psdMd5" type="password" />
						</div>
					</div>
				</div>
				<div class="control-group2" style="margin: 0px 0px 0px 21px">
					&nbsp;&nbsp;&nbsp;&nbsp;

					<div id="div_id_embed"></div>
				</div>
				  </form>
				<script type="text/javascript">
					
						function refreshyz() //刷新验证
						{
							gt_captcha_obj.refresh(function() {
								pass = false;

							});
						}
						window.onload = function() {
							$('#loginBtn').click(function() {
								$.ajax({
									type: "get",
									url: "login.action",
									dataType: "json",
									data: $('#loginform').serialize(),
									success: function(data) {
										if(data.result == 0) {
											location.href = 'index.jsp'
										} else {
											alert("用户名或密码错误");
										}
									},
									error: function() {
										alert("失败!");
									}
								});
							});

						};
					</script>
				<div class="form-actions">
					<span class="pull-right"> <input type="button" id="loginBtn"
						class="btn btn-success" style="width:440px;"
						value=" 登&nbsp;&nbsp;&nbsp;&nbsp;录" />
					</span>
				</div>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/matrix.login.js"></script>
	</div>
</body>

</html>