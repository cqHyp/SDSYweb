<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>推送管理</title>

		<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
		<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
		<link href="jules/css/animate.min.css" rel="stylesheet">
		<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">

	</head>
	<script type="text/javascript">
	   function zf1(id){		
			if (confirm("确认删除?"))  {  
			var url = "deleteJpush.action?id="+id;
			window.location.href=url;
			}else{
			}
		}
	</script>

	<body class="gray-bg">
		<div class="wrapper wrapper-content  animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox">
						<div class="ibox-content">
							<h2>推送列表</h2>
							<div class="clients-list">
								<div class="tab-content">
									<div id="tab-1" class="tab-pane active">
										<div class="full-height-scroll">
											<div class="table-responsive">
												<table class="table table-striped table-hover">
													<tbody>
														<c:forEach items="${jpushlist}" var="res">
															<tr style="height:80px">
															  <td class="project-title">
																	<a >对象：</a><br />
									                     	<c:if test="${res.JSt == 0 }">
									                     	老师
									                     	</c:if>
									                     	<c:if test="${res.JSt == 1 }">
									                     	学生
									                     	</c:if>
																</td>
																<td class="project-title">
																	<a >内容：</a><br />
																	<small >
																		${res.JMessage}
                                                                	</small>
																</td>
																<td style="text-align:center;width: 50px;">
																	<a >时间：</a><br />
																	<small >
																		${res.createTime}
                                                                	</small>
																</td>
																 <td>
											            <c:if test="${sessionScope.power == 0}">      
											 			    <button  onClick="zf1(${res.JId})"  type="button" class="btn btn-danger btn-xs"><span>删除</span></button>
                                          	            </c:if>
											            </td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
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
	</body>

</html>