<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->

	<head>
		<meta charset="UTF-8">
		<title>发布活动</title>
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

		<style>
			th {
				background-color: #F5F5F6;
			}
		</style>
		<script type="text/javascript">
			// 判断图片大小和格式
			function getPhotoSize(obj) {
				photoExt = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
				if(photoExt != '.jpg' && photoExt != '.png' && photoExt != '.jpeg') {
					alert("文件格式错误！");
					var nf = obj.cloneNode(true);
					nf.value = '';
					obj.parentNode.replaceChild(nf, obj);
					return false;
				}
				var fileSize = 0;
				var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
				if(isIE && !obj.files) {
					var filePath = obj.value;
					var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
					var file = fileSystem.GetFile(filePath);
					fileSize = file.Size;
				} else {
					fileSize = obj.files[0].size;
				}
				fileSize = Math.round(fileSize / 1024 * 100) / 100; //µ¥Î»ÎªKB
				if(fileSize > 2048) {
					alert("不接受超过2m的图片！");
					return false;
				}
			}
			function a(){
			alert("weaw");
			windows.location.href="xuanTeacher";
			}
		</script>
	</head>

	<body class="gray-bg">
		<div class="wrapper wrapper-content  animated fadeInRight article">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1">
					<div class="ibox">
						<div class="ibox-content">
							<div class="ibox float-e-margins">
								<div class="text-center">
									<h3>发布活动详情</h3>
								</div>
								<div class="ibox-content">
									<form action="addActivity" name="cform" method="post" enctype="multipart/form-data" id="activityFrom">
										<table class="table table-bordered" style="height: auto;">
										 <tbody>
											<tr>
												<th>活动形式</th>
												<td>
												    <select class="form-control " name="item" id="acitem" required="required">
														<% String user = (String) session.getAttribute("loginname");
                                                        if("博雅读书".equals(user)){
													%>
														<option value="1">书评报告会</option>
														<option value="2">读书沙龙</option>
														<option value="3">书评撰写</option>
														 <input  name="type" type="hidden" value=2>
														<%
														}
														else if("博雅心情".equals(user)){
													%>
													
														<option value="4">理论教学</option>
														<option value="5">随堂训练</option>
														<option value="6">实践锻炼</option>
														 <input  name="type" type="hidden" value=3>
														<%
														}
														else if("博雅实践".equals(user)){
													%>
													
														<option value="7">社会实践</option>
														<option value="8">志愿服务</option>
														 <input  name="type" type="hidden" value=4>
														<%
														}
														else if("博雅讲坛".equals(user)){
													%>
												
														<option value="9">浙江人文大讲堂之江分讲堂</option>
														<option value="10">之江大讲堂</option>
														<option value="11">师友讲堂</option>
														<option value="12">院长下午茶，书记有约</option>
														<option value="13">主题沙龙</option>
														<option value="21">新青年月谈</option>
														<option value="22">梦湖讲堂</option>
														<option value="23">青春有约</option>
															 <input  name="type" type="hidden" value=5>
														<%
														}
														else if("博雅修身".equals(user)){
                                                    %>
                                                  
														<option value="14">兴趣社团</option>
														<option value="15">阳光长跑</option>
														<option value="16">文明寝室建设</option>
														<option value="17">体育文化活动</option>
														   <input  name="type" type="hidden" value=6>
														<%
														}
														else if("博雅视野".equals(user)){
													%>
														<option value="18">出国深造</option>
														<option value="19">交换学习</option>
														<option value="20">游学访学</option>
														   <input  name="type" type="hidden" value=7>
														<%
														}
													%>
													</select>
												</td>
												<th>活动名称</th>
												<td><input class="form-control" type="text" name="name" required="required"></td>
											</tr>
											
											<tr>
												<th>起始时间</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="startTime1" placeholder="开始时间" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
												<th>结束时间</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="endTime1" placeholder="结束时间" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
											</tr>
											
											<tr>
												<th>起始时间2</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="startTime2" placeholder="开始时间" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
												<th>结束时间2</th>
												<td><input required="required" class="laydate-icon form-control layer-date" name="endTime2" placeholder="结束时间" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',min: laydate.now(0)})">
												</td>
											</tr>
											
											<tr>
												<th>可获得学分</th>
												<td><input required="required" class="form-control " type="text" name="credit"></td>
												<th>活动人数</th>
												<td><input required="required" class="form-control " type="text" name="peopleNum"></td>
											</tr>
											
											<tr>
												<th>参与导师</th>
												<td>
												 <input type="text"  class="form-control " id="testInput" />  
                                                  <input type="hidden"  name="fkTeacherId" id="ruleOrgCode" required="required" />
												</td>
												<th>举办学院</th>
												<td>
													<select class="form-control " name="college" id="college" required="required">
														<option value="1">全部学院</option>
														<option value="2">之江学院</option>
														<option value="3">信息学院</option>
														<option value="4">人文学院</option>
														<option value="5">商学院</option>
														<option value="6">设计学院</option>
														<option value="7">外国语学院</option>
														<option value="8">机械学院</option>
														<option value="9">中旅学院</option>
														<option value="10">建筑学院</option>
														<option value="11">理学院</option>
														<option value="12">创新创业学院</option>
													</select>
												</td>
											</tr>
											
											<tr>
												<th>活动地址</th>
												<td>
												<select class="form-control " name="addressId" required="required">
													 <c:forEach items="${addresslist}" var="res">
														<option value="${res.id}">${res.name}</option>
													</c:forEach>
											</select>
												</td>
												<th>指派学院</th>
												<td>
													<select class="form-control " name="zdcollege" id="college" required="required">
														<option value="1">全部学院</option>
														<option value="2">商学院</option>
														<option value="3">信息工程学院</option>
														<option value="4">人文学院</option>
														<option value="5">机械工程学院</option>
														<option value="6">外国语学院</option>
														<option value="7">建筑学院</option>
														<option value="8">设计学院</option>
														<option value="9">理学院</option>
														<option value="10">中旅学院</option>
													</select>
												</td>
											</tr>
											<tr>
											<th>活动主图片</th>
												<td class="uploader white">
													<input required="required" type="file" name="file1" accept="image/*" onchange="getPhotoSize(this)" size=40"/>
												</td>
											</tr>
											<tr>
												<th>活动详情</th>
												<td colspan="3">
													<textarea required="required" class="form-control " rows="3" type="text" id="desc" name="desc"></textarea>
												</td>
											</tr>
											</tbody>
									</table>

									<div class="form-group" style="height: auto">
										<input class="btn btn-sm btn-primary m-t-n-xs pull-right" style="margin-left: 20px" type="submit" value="取消 " />
										<input id="submit" class="btn btn-sm btn-primary m-t-n-xs pull-right" type="submit" value="确定 " />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
var arraytea=new Array();
 
	<c:forEach var="res" items="${teacherlist}">
  arraytea.push({name:'${res.TName}'+'>>'+'${res.id.TTeacherId}',code:'${res.id.TTeacherId}'});
</c:forEach>

      //  alert(datas);
      //   alert(arraytea);
        /** 
        * 搜索下拉列表 
        **/  
        $('#testInput').bind('input propertychange',  
            function() {  
                var items = inputChange();//搜索数据并获取搜索结果  
                if(items != undefined){  
                    _initItems(items);  
            }  
              
        });  
  
  
        //1：搜索数据  
        var inputChange = function() {  
            var inputValue = $('#testInput').val();  
              
            if(inputValue != "" && inputValue.indexOf("000") == -1){  
                var matcher = new RegExp(inputValue, "i");  
                return $.grep(arraytea,  
                    function(value) {  
                    return matcher.test(value.name);  
                });  
            }else if(inputValue != "" && inputValue.indexOf("000") != -1){  
                var matcher = new RegExp(inputValue, "i");  
                return $.grep(arraytea,  
                    function(value) {  
                    return matcher.test(value.code);  
                });  
            }  
              
        };  
        var maxFontNumber = 0;//最大字数  
        var suggestContainer = $('<div></div>'); //创建一个子<div>  
        suggestContainer.attr('id', "testInput-suggest");  
        suggestContainer.attr('tabindex', '0');  
        suggestContainer.hide();  
                  
        //2：初始化搜索到的数据进行显示  
        var _initItems = function(items) {  
            $('#ruleOrgCode').val("");  
            suggestContainer.empty();  
            for (var i = 0; i < items.length; i++) {  
                if(items[i].name.length > maxFontNumber){  
                    maxFontNumber = items[i].name.length;  
                }  
                var suggestItem = $('<div></div>'); //创建一个子<div>  
                suggestItem.attr('code', items[i].code);  
                suggestItem.append(items[i].name);  
                suggestItem.css({  
                    'padding':'3px',//item间距  
                    'white-space':'nowrap',  
                    'cursor': 'pointer',  
                    'background-color': 'RGB(199,237,204)',//默认背景颜色  
                    'color': '#000000'//默认字体颜色  
                });  
                suggestItem.bind("mouseover",  
                function() {  
                    $(this).css({  
                        'background-color': '#C9302C',//选中背景颜色  
                        'color': '#ffffff'//选中字体颜色  
                    });  
                });  
                suggestItem.bind("mouseout",  
                function() {  
                    $(this).css({  
                        'background-color': 'RGB(199,237,204)',//默认背景颜色  
                        'color': '#000000'//默认字体颜色  
                    });  
                });  
                suggestItem.bind("click", showClickTextFunction);//选中后处理数据  
                //suggestItem.bind("click", itemSelectFunction);  
                suggestItem.appendTo(suggestContainer);  
                suggestContainer.appendTo(document.body);  
            }  
            suggestContainer.removeAttr("style");  
            suggestContainer.css({  
                'border': '1px solid #ccc',  
                'max-height': '200px',  
                'top': $('#testInput').offset().top + $('#testInput').outerHeight(),  
                'left': $('#testInput').offset().left,  
                'width': 12*maxFontNumber + 2 * 3 + 30,  
                'position': 'absolute',  
                'font-size': '12px' ,//默认字体大小  
                'font-family':'Arial',  
                'z-index': 99999,  
                'background-color': '#FFFFFF',  
                'overflow-y': 'auto',  
                'overflow-x': 'hidden',  
                'white-space':'nowrap'  
  
            });  
            maxFontNumber = 0;  
            suggestContainer.show();  
        };  
          
        //3.选中后处理数据  
        var showClickTextFunction = function() {  
            //alert(this.innerText + "---" + this.getAttribute("code"));  
            $('#testInput').val(this.innerText);  
            $('#ruleOrgCode').val(this.getAttribute("code"));  
            suggestContainer.hide();  
        };  
    </script>  
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

	<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->

</html>