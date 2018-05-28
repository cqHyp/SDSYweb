<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String loginname = (String) session.getAttribute("loginname"); %>
<% String id = (String) session.getAttribute("loginid"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% String user = (String) session.getAttribute("loginname");
    if(user==null )
   {
    	%>
<script>
		  parent.location.href="login.jsp"
    	</script>
<%
   }
  %>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>“尚德书院”管理系统平台</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />

<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>

<body onunload="unLogin()">
	<script type="text/javascript">
       function  unLogin(){
				window.location.href = "unLogin";
         }
  </script>
	<!--Header-part-->
	<div id="header">
		<h1>
			<a>管理平台</a>
		</h1>
	</div>

	<!--top-Header-menu-->
	<div id="user-nav" class="navbar navbar-inverse">
		<ul class="nav">
			<li class="dropdown" id="profile-messages"><a title="" href="#"
				data-toggle="dropdown" data-target="#profile-messages"
				class="dropdown-toggle"> <i class="icon icon-user"></i>&nbsp; <span
					class="text">欢迎你，<%=loginname%>
				</span>&nbsp; <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a class="menu_a" link="allActivity.action"><i
							class="icon-check"></i> 我的发布</a></li>
					<li class="divider"></li>
					<li><a class="menu_a" onclick="unLogin(this)"><i
							class="icon-key"></i> 退出系统</a></li>
				</ul></li>

		</ul>
	</div>
	<!--close-top-Header-menu-->

	<div id="search"></div>
	<div id="sidebar" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
		<ul>
			<c:if test="${sessionScope.power == 0 }">
				<li class="submenu active"><a class="menu_a"
					link="allActivity.action"><i class="icon icon-home"></i> <span>管理主页</span></a>
				</li>
			</c:if>
			<c:if test="${sessionScope.power == 1 }">
				<li class="submenu active"><a class="menu_a"
					link="allActivity.action"><i class="icon icon-check"></i> <span>活动主页</span></a>
				</li>
			</c:if>
			<c:if test="${sessionScope.power == 0 }">
				<li class="submenu"><a href="#"> <i class="icon icon-list"></i>
						<span>活动管理</span> 
				</a>
					<ul>
						<li><a class="menu_a" link="publishActivity.action"> 发布活动</a></li>
						<li><a class="menu_a" link="allActItem.action"> 活动模块</a></li>
						<li><a class="menu_a" link="allAddress.action"> 地址管理</a></li>
						<li><a class="menu_a" link="allAnnounce.action"> 公告信息</a></li>
						<li><a class="menu_a" link="importOldActivity.jsp"> 导入往期活动信息</a></li>
						<li><a class="menu_a" link="importOldActRecord.jsp"> 导入往期活动成绩</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionScope.power!= 2 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>教师管理</span>
				</a>
					<ul>
					<c:if test="${sessionScope.power == 0 }">
						<li><a class="menu_a" link="teacherlist2.jsp">教师列表</a></li>
						</c:if>
						<c:if test="${sessionScope.power == 1 }">
							<li><a class="menu_a" link="oneTeacher.action?td=<%=id%>">个人信息</a></li>
						</c:if>
						<c:if test="${sessionScope.power == 0 }">
							<li><a class="menu_a" link="changePsd.jsp">修改教师密码</a></li>
						</c:if>
						<c:if test="${sessionScope.power == 1 }">
							<li><a class="menu_a" link="teacherChangePsd.jsp">修改密码</a></li>
						</c:if>
                        <c:if test="${sessionScope.power == 3 }">
							<li><a class="menu_a" link="teacherChangePsd.jsp"><i
								class="icon icon-caret-right"></i>修改密码</a></li>
                            <li><a class="menu_a" link="stuChangePsd.jsp"><i
								class="icon icon-caret-right"></i>修改学生密码</a></li>
                             <li><a class="menu_a" link="studentslist.jsp"><i
								class="icon icon-caret-right"></i>学生列表</a></li>
								<li><a class="menu_a" link="teacherlist2.jsp"><i
								class="icon icon-caret-right"></i>教师列表</a></li>
								<li><a class="menu_a" link="toDownloadgrade.action"><i
								class="icon icon-caret-right"></i>学生成绩下载</a></li>
						</c:if>

					</ul></li>
			</c:if>
			<c:if test="${sessionScope.power == 0 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>学生管理</span> 
				</a>
					<ul>
						<li><a class="menu_a" link="studentslist.jsp"><i
								class="icon icon-caret-right"></i>学生列表</a></li>
						<li><a class="menu_a" link="toDownloadgrade.action"><i
								class="icon icon-caret-right"></i>学生成绩下载</a></li>
					    <li><a class="menu_a" link="toTongji.action"><i
								class="icon icon-caret-right"></i>模块成绩</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionScope.power == 0 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>添加</span> 
				</a>
					<ul>
						<li><a class="menu_a" link="addAnnounce.jsp"><i
								class="icon icon-caret-right"></i>发布公告</a></li>
						<li><a class="menu_a" link="addAddress.jsp"><i
								class="icon icon-caret-right"></i>添加地址</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionScope.power == 0 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>推送</span>
				</a>
					<ul>
						<li><a class="menu_a" link="addTuisong.jsp"><i
								class="icon icon-caret-right"></i>添加学生推送</a></li>
						<li><a class="menu_a" link="addTuisong2.jsp"><i
								class="icon icon-caret-right"></i>添加老师推送</a></li>
						<li><a class="menu_a" link="allJpush.action"><i
								class="icon icon-caret-right"></i>推送列表</a></li>
					</ul></li>
			</c:if>
			
					<c:if test="${sessionScope.power == 2 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>模板中心</span> 
				</a>
					<ul>
						<li><a class="menu_a" link="allActItem.action">活动模块</a></li>
						<li><a class="menu_a" link="addAcType.jsp">添加项目类型</a></li>
					</ul></li>
			</c:if>
				<c:if test="${sessionScope.power == 3 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>管理中心</span> 
				</a>
					<ul>
					    <li><a class="menu_a" link="importStudents.jsp"><i
								class="icon icon-caret-right"></i>导入学生</a></li>
					    <li><a class="menu_a" link="importTime.jsp"><i
								class="icon icon-caret-right"></i>导入教师时间</a></li>
						<li><a class="menu_a" link="importTeacher.jsp"><i
								class="icon icon-caret-right"></i>导入教师</a></li>
						<li><a class="menu_a" link="toTongji.action"><i
								class="icon icon-caret-right"></i>学院成绩</a></li>
					</ul></li>
			</c:if>
			
			<c:if test="${sessionScope.power == 4 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>各学院成绩</span> 
				</a>
					<ul>
					    <li><a class="menu_a" link="bingTu.jsp?collage=1"><i
								class="icon icon-caret-right"></i>商学院</a></li>
					    <li><a class="menu_a" link="bingTu.jsp?collage=2"><i
								class="icon icon-caret-right"></i>信息工程学院</a></li>
						<li><a class="menu_a" link="bingTu.jsp?collage=3"><i
								class="icon icon-caret-right"></i>人文学院</a></li>
						<li><a class="menu_a" link="bingTu.jsp?collage=4"><i
								class="icon icon-caret-right"></i>机械工程学院</a></li>
						<li><a class="menu_a" link="bingTu.jsp?collage=5"><i
								class="icon icon-caret-right"></i>外国语学院</a></li>
					    <li><a class="menu_a" link="bingTu.jsp?collage=6"><i
								class="icon icon-caret-right"></i>建筑学院</a></li>
						<li><a class="menu_a" link="bingTu.jsp?collage=7"><i
								class="icon icon-caret-right"></i>设计学院</a></li>
						<li><a class="menu_a" link="bingTu.jsp?collage=8"><i
								class="icon icon-caret-right"></i>理学院</a></li>
						<li><a class="menu_a" link="bingTu.jsp?collage=9"><i
								class="icon icon-caret-right"></i>中旅学院</a></li>
								<li><a class="menu_a" link="bingTuz.html"><i
								class="icon icon-caret-right"></i>学院汇总</a></li>
								<li><a class="menu_a" link="huizong.jsp"><i
								class="icon icon-caret-right"></i>学院汇总下载</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionScope.power == 4 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
						<span>各模块成绩</span> 
				</a>
					<ul>
					    <li><a class="menu_a" link="bingTu2.jsp?mokuai=1"><i
								class="icon icon-caret-right"></i>博雅读书</a></li>
					    <li><a class="menu_a" link="bingTu2.jsp?mokuai=2"><i
								class="icon icon-caret-right"></i>博雅"心晴"</a></li>
						<li><a class="menu_a" link="bingTu2.jsp?mokuai=3"><i
								class="icon icon-caret-right"></i>博雅实践</a></li>
						<li><a class="menu_a" link="bingTu2.jsp?mokuai=4"><i
								class="icon icon-caret-right"></i>博雅讲坛</a></li>
						<li><a class="menu_a" link="bingTu2.jsp?mokuai=5"><i
								class="icon icon-caret-right"></i>博雅修身</a></li>
						<li><a class="menu_a" link="bingTu2.jsp?mokuai=6"><i
								class="icon icon-caret-right"></i>博雅视野</a></li>
					</ul></li>
			</c:if>
			<c:if test="${sessionScope.power == 4 }">
				<li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
					<span>学生模块成绩</span>
				</a>
					<ul>
						<li><a class="menu_a" link="modelGrade.action"><i
								class="icon icon-caret-right"></i>模块成绩</a></li>
					</ul></li>
			</c:if>
		</ul>
	</div>
	<div id="content">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="index.jsp" title="返回主页" class="tip-bottom"><i
					class="icon-home"></i> Home</a>
			</div>
		</div>
		<c:if test="${sessionScope.power == 0 }">
			<iframe src="allActivity.action?" id="iframe-main"
				style="width:100%;"></iframe>
		</c:if>
		<c:if test="${sessionScope.power == 1 }">
			<iframe src="allActivity.action?" id="iframe-main"
				style="width:100%;"></iframe>
		</c:if>
			<c:if test="${sessionScope.power == 2 }">
			<iframe src="allActivity.action?" id="iframe-main"
				style="width:100%;"></iframe>
		</c:if>
		<c:if test="${sessionScope.power == 3 }">
			<iframe src="allActivity.action?" id="iframe-main"
				style="width:100%;"></iframe>
		</c:if>
			<c:if test="${sessionScope.power == 4 }">
			<iframe src="allActivity.action?" id="iframe-main"
				style="width:100%;"></iframe>
		</c:if>
	</div>
	<!--end-main-container-part-->

	<script src="js/excanvas.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
	<script src="js/matrix.js"></script>

	<script type="text/javascript">
		//初始化相关元素高度
		function init() {
			$("body").height($(window).height() - 80);
			$("#iframe-main").height($(window).height() - 90);
			$("#sidebar").height($(window).height() - 50);
		}

		$(function() {
			init();
			$(window).resize(function() {
				init();
			});
		});

		function goPage(newURL) {
			if(newURL != "") {
				if(newURL == "-") {
				}
				else {
					document.location.href = newURL;
				}
			}
		}
		function resetMenu() {
			document.gomenu.selector.s			// if url is empty, skip the menu dividers and reset the menu selection to default
            electedIndex = 2;
		}
	</script>

</body>

</html>