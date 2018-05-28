<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	request.setCharacterEncoding("gb2312");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- meta http-equiv="Content-Type" content="text/html; charset=utf-8" /-->
<title>模块成绩</title>
<script type="text/javascript" src="jules/js/jquery.min.js"></script>
<script type="text/javascript" src="echarts/dist/echarts.js"></script>
<script src="1-6-10.esl.js"></script>
<script src="jquery/jquery-1.7.2.min.js"></script>
<!--  script src="jquery/jquery-1.7.2.min.js"></script-->

</head>
<body>
<div id="wait" >
 <img src="image/wait.gif" />
 </div>
	<table>
		<tr >
			<th >
				<div id="main1" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
			<th width="100px">
				<div id="main2" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
			<th width="100px">
				<div id="main3" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
		</tr>
		<tr >
			<th width="33%">
				<div id="main4" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
			<th width="33%">
				<div id="main5" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
			<th width="33%">
				<div id="main6" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
		</tr>
		<tr >
			<th width="33%">
				<div id="main7" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
			<th width="33%">
				<div id="main8" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
			<th width="33%">
				<div id="main9" style="height:200px; width:350px;margin:0 auto;"></div>
			</th>
		</tr>
	</table>

</body>
<script type="text/javascript">
	$(function() {
		var school = new Array([ "商学院" ], [ "信息工程学院" ], [ "人文学院" ],
				[ "机械工程学院" ], [ "外国语学院" ], [ "建筑学院" ], [ "设计学院" ], [ "理学院" ],
				[ "中旅学院" ]);
	   var mokuai = new Array([ "博雅读书" ], [ "博雅心情" ], [ "博雅实践" ],
				[ "博雅讲坛" ], [ "博雅修身" ], [ "博雅视野" ]);
		var main = new Array([ document.getElementById("main1") ], [ document.getElementById("main2") ], [ "main3" ],
				[ "main4" ], [ "main5" ], [ "main6" ]);
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

		//获得后台数据
		var id = getUrlParam(url, 'mokuai');
		//alert(id);
		var datar=new Array();
		var name_data;
		var value_data;
		var data2;
		
          
		$.ajax({
			url : "bingTu2.action?id="+id,
			type : "POST",
			dataType : "json",
			async : false,
			  beforeSend:function(XMLHttpRequest){
			  
            alert("小G正在快马加鞭哦···"); 
        }, 
			success : function(data) {
				//alert(data);
				//var jdata = JSON.parse(data);
					document.getElementById("wait").style.display="none";
				datar=data.data;
				//alert(datar[0].name + "," + datar[0].value);
			//	alert(datar.lenght);
			
				name_data = data.data[0].name;
				value_data = data.data[0].value;

			}

		});

		// 路径配置
		require.config({
			paths : {
				'echarts' : 'echarts',
				'echarts/chart/pie' : 'echarts'
			}
		});
        
		// 使用
		require([ 'echarts', 'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart0 = ec.init(document.getElementById("main1"));
			var myChart1 = ec.init(document.getElementById("main2"));
			var myChart2 = ec.init(document.getElementById("main3"));
			var myChart3 = ec.init(document.getElementById("main4"));
			var myChart4 = ec.init(document.getElementById("main5"));
			var myChart5 = ec.init(document.getElementById("main6"));
		    var myChart6 = ec.init(document.getElementById("main7"));
			var myChart7 = ec.init(document.getElementById("main8"));
			var myChart8 = ec.init(document.getElementById("main9"));
			var option=new Array();
			for(var i=0;i<9;i++)
			 option[i] = {
				title : {
					text : school[i ],
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					x : 'left',
					data : datar[i].name
				},
				series : [ {
					type : 'pie',
					radius : '50%',
					data : (function() {
						var res = [];
						var len = datar[i].name.length;
						while (len--) {
							res.push({
								name : datar[i].name[len],
								value : datar[i].value[len]
							});
						}
						return res;
					})()
				} ]

			};
			

			myChart0.setOption(option[0]);
			myChart1.setOption(option[1]);
			myChart2.setOption(option[2]);
			myChart3.setOption(option[3]);
			myChart4.setOption(option[4]);
			myChart5.setOption(option[5]);
			myChart6.setOption(option[6]);
			myChart7.setOption(option[7]);
			myChart8.setOption(option[8]);
		});
	

	})
</script>
</html>
