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
<!--  后台主页  -->
<title>活动列表</title>

<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="jules/css/animate.min.css" rel="stylesheet">
<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">


<script type="text/javascript">
	// 删除
	function delActivity(id) {
		if (confirm("确认删除?")) {
			var url = "deleteActivity.action?id=" + id;
			window.location.href = url;
		}
	}
	function xiugai(id) {
			var url = "toOneActivity.action?id=" + id;
			window.location.href = url;
		
	}
	function qiandao(id) {
			var url = "stuQiandao.jsp?actid=" + id;
			window.location.href = url;
		
	}

	// 详情
	function detailActivity(id) {
		var url = "oneActivity.action?id=" + id;
		window.location.href = url;
	}
	//活动提醒
    function actTixing(id){
    var url = "actTuisong.jsp?id=" + id;
		window.location.href = url;
    }
	// 推荐
	function recoActivity(id) {
		var url = "torecommend?id=" + id;
		window.location.href = url;
	}
	//轮播
	function lunbo(id) {
		var url = "Lunbo?id=" + id;
		window.location.href = url;
	}
	// ping成绩
	function lookActivity(id) {
		var url = "passStu2.jsp?actid=" + id;
		window.location.href = url;
	}
	function lookGrade(id) {
		var url = "gradelist2.jsp?actid=" + id;
		window.location.href = url;
	}
</script>

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
						<h2>所有信息列表</h2>
						<div class="clients-list">
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<div class="full-height-scroll">
										<div class="table-responsive">
											<table class="table table-striped table-hover">
												<tbody>
													<c:forEach items="${activitylist}" var="res">
														<tr style="height:80px">
															<td><img src="${res.RPicture}" width="60px;"
																height="60px;" /></td>
															<td style="width: 160px">活动名称： <br /> <small>
																	${res.RName} </small>
															</td>
															<td class="project-title">活动负责人： <br /> <small>
																	${res.teacher.TName}&nbsp; </small>
															</td>
															<td class="project-title">活动地址： <br /> <small>
																	${res.address.name}&nbsp; </small>
															</td>
															<td class="project-title" ><small >举办日期：</small> <br />
																	<fmt:formatDate value="${res.RStartTime}"
																		pattern="yyyy-MM-dd HH:mm" />&nbsp;
															</small>
															</td>
															<td>人数：${res.RMaximum}&nbsp;</td>
															<td>活动类型： <c:choose>
																	<c:when test="${res.RType  == 2 }">
																		<span>博雅读书</span>
																	</c:when>
																	<c:when test="${res.RType  == 3 }">
																		<span>博雅"心晴"</span>
																	</c:when>
																	<c:when test="${res.RType  == 4 }">
																		<span>博雅实践</span>
																	</c:when>
																	<c:when test="${res.RType  == 5 }">
																		<span>博雅讲坛</span>
																	</c:when>
																	<c:when test="${res.RType  == 6 }">
																		<span>博雅修身</span>
																	</c:when>
																	<c:when test="${res.RType  == 7 }">
																		<span>博雅视野</span>
																	</c:when>
																</c:choose>
															</td>
																<c:if test="${sessionScope.power == 0}">
																<td style="text-align:center;width: 20px;">
																	<button onClick="xiugai(${res.RId})"
																		type="button" class="btn btn-primary btn-xs">
																		<span>时间编辑</span>
																	</button>
																	&nbsp;	&nbsp;	&nbsp;
																	<button onClick="qiandao(${res.RId})"
																		type="button" class="btn btn-primary btn-xs">
																		<span>签到管理</span>
																	</button>
																</td>
															</c:if>
															<c:if test="${sessionScope.power == 0}">
																<td style="text-align:center;width: 20px;">
																	<button onClick="actTixing(${res.RId})"
																		type="button" class="btn btn-primary btn-xs">
																		<span>提醒</span>
																	</button>
																</td>
															</c:if>
															
															<td style="text-align:center;width: 20px;"><c:if
																	test="${sessionScope.power == 0}">
																	<button onClick="lookActivity(${res.RId})"
																		type="button" class="btn btn-primary btn-xs">
																		<span>评分</span>
																	</button>
																	&nbsp;	&nbsp;	&nbsp;
																	<button onClick="lookGrade(${res.RId})" type="button"
																	class="btn btn-primary btn-xs">
																	<span>成绩</span>
																</button> 
																</c:if>
															</td>
															<td style="text-align:center;width: 50px;">
																<button onClick="detailActivity(${res.RId})"
																	type="button" class="btn btn-primary btn-xs">
																	<span>详情</span>
																</button>
															</td>
															<td style="text-align:center;width: 50px;"><c:if
																	test="${sessionScope.power == 0}">
																	<c:if test="${res.RStatus!=5}">
																		<button onClick="lunbo(${res.RId})" type="button"
																			class="btn btn-primary btn-xs">
																			<span>轮播图</span>
																		</button>
																	</c:if>
																	<c:if test="${res.RStatus==5}">
																		<button onClick="lunbo(${res.RId})" type="button"
																			class="btn btn-danger btn-xs">
																			<span>轮播图</span>
																		</button>
																	</c:if>
																	&nbsp;	&nbsp;	&nbsp;
																	<c:if test="${res.RRecsign==0}">
																		<button onClick="recoActivity(${res.RId})"
																			type="button" class="btn btn-primary btn-xs">
																			<span>推荐</span>
																		</button>
																	</c:if>
																	<c:if test="${res.RRecsign==1}">
																		<button onClick="recoActivity(${res.RId})"
																			type="button" class="btn btn-danger btn-xs">
																			<span>取消</span>
																		</button>
																		</c:if>
																</c:if></td>
															<td style="text-align:center;width: 50px;"><c:if
																	test="${sessionScope.power == 0}">
																	<button onClick="delActivity(${res.RId})" type="button"
																		class="btn btn-danger btn-xs">
																		<span>删除</span>
																	</button>
																</c:if></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<div style="text-align: center;">
												<s:if test="page!=1">
													<a href="allActivity.action?n=0">首页</a>
															&nbsp;
															<a href="allActivity.action?n=1">上一页</a>
												</s:if>
												<s:if test="page!=PageSum">
															&nbsp;
															<a href="allActivity.action?n=2">下一页</a>
															&nbsp;
															<a href="allActivity.action?n=3">尾页</a>
												</s:if>
												&nbsp;第${page}页/共${PageSum}页
											</div>
										</div>
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
	<script src="jules/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="jules/js/content.min.js?v=1.0.0"></script>

	<%
		Integer powernum = (Integer) session.getAttribute("power");
		if (powernum == 0) {
	%>
	<script src="jules/js/plugins/toastr/toastr.min.js"></script>
	<link href="jules/css/plugins/toastr/toastr.min.css" rel="stylesheet">

	<script type="text/javascript">
		function onon() {
			setInterval(clickButton1, 5000);
		}
	
		function clickButton1() {
			var url = 'ResReflash.action';
			jQuery.post(url, ttttttt);
		}
	
		function ttttttt(data) {
			if (data.result == 1) {
				toastrinfo();
			}
		}
		window.onload = onon;
	</script>

	<script type="text/javascript">
		function toastrinfo() {
			var shortCutFunction = "info"
			var msg = "点击查看"
			var title = "您好，有新用户需要审核！"
	
			toastr.options = {
				"closeButton" : true,
				"debug" : true,
				"progressBar" : true,
				"positionClass" : "toast-top-right",
				"showDuration" : "400",
				"hideDuration" : "1000",
				"timeOut" : "7000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			}
			toastr.options.onclick = function() {
				window.location.href = "lookalluploaderList2.action"
	
			}
	
			var $toast = toastr[shortCutFunction](msg, title);
			$toastlast = $toast;
	
		}
	</script>
	<%
		}
	%>

</body>

<!-- Mirrored from www.zi-han.net/theme/hplus/clients.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->

</html>