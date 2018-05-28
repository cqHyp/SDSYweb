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


<title>活动详情</title>

<link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link href="jules/css/animate.min.css" rel="stylesheet">
<link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<style type="text/css">
</style>
<script type="text/javascript" src="js/jquery.min.js">
      </script>
<script src="js/qrcode.min.js">
    </script>
<script type="text/javascript">   
    $(document).ready(function () {
		var act_id = ${detailactivity.RId};
		var json = '{"act_id":'+act_id+'}';
		console.log("act_id="+json);
        var qrcode = new QRCode('qrcode_2', {
                    text:json ,//放入你想传的json串
                    width: 256,
                    height: 256,
                    colorDark: '#000000',
                    colorLight: '#ffffff',
                    correctLevel: QRCode.CorrectLevel.H
                }
        );
		
		function print() {
            var img = document.getElementById("image"); /// get image
            var canvas = document.getElementsByTagName("qrcode_2")[0];  /// get canvas element
            img.src = canvas.toDataURL();                     /// update image7475
            $("#image").jqprint({
                debug: false,
                importCSS: true,
                printContainer: true,
                operaSupport: false
            });
        }
		
    });
</script>
</head>

<body class="gray-bg">
	<div id="showLargeImage"
		style="display:none;z-index:9999;width:500px;height:400px;position:absolute;left:50%;">
		<img id="ShowImageBox"
			src="http://img.52fuqing.com/upload/news/20160125/201601251216541264.jpg"
			width="500px;" height="380px;" />
	</div>
	<div class="wrapper wrapper-content  animated fadeInRight article">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1">
				<div class="ibox">
					<div class="ibox-content">

						<div class="text-center ">
							<h1>${detailactivity.RName}</h1>


						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>活动详情</h5>
							</div>
							<div class="ibox-content">
								<div class="form-group" style="height: 95px">
									<label class="col-sm-2" style="a">活动简介</label>
									<textarea disabled="disabled" rows="4" type="text"
										name="feature" class="form-control" style="width:450px">${detailactivity.RIntroduction}</textarea>
								</div>
								<div class="form-group" style="height: 95px">
									<label class="col-sm-2" style="a">第一次活动时间</label>
									<fmt:formatDate value="${detailactivity.RStartTime}" pattern="yyyy-MM-dd HH:mm" />
									<fmt:formatDate value="${detailactivity.REndTime}" pattern="yyyy-MM-dd HH:mm" />
								</div><div class="form-group" style="height: 95px">
								 <label class="col-sm-2" style="a">第二次活动时间</label>
									<fmt:formatDate value="${detailactivity.RStartTimeTwo}" pattern="yyyy-MM-dd HH:mm" />
									<fmt:formatDate value="${detailactivity.REndTimeTwo}" pattern="yyyy-MM-dd HH:mm" />
									&nbsp;
								</div>
									<div class="col-sm-10" ><button onclick="xiugai(${detailactivity.RId})">修改活动信息</button></div>
								
								<div class="hr-line-dashed"></div>
								<div class="form-group" style="height: 95px">
									<label class="col-sm-2 control-label">导师简介</label>
									<textarea disabled="disabled" rows="4" type="text"
										name="feature" class="form-control" style="width:450px">${detailactivity.teacher.TIntroduction}</textarea>

								</div>


								<div class="hr-line-dashed"></div>

								<div class="form-group" style="height: 80px">
									<label class="col-sm-2 control-label" style="width:50px;height:50px">活动二维码</label>

									<div class="col-sm-10" id="qrcode_2"><button onclick="print()">打印二维码</button></div>
									
								</div>
							</div>
						</div>

<div class="ibox float-e-margins">
								<div class="ibox-title">
									<h5>导师详情</h5>
								</div>

								<div class="ibox-content">
									<div class="form-group" style="height: 95px;">
										<label class="col-sm-2" style="a">导师姓名</label>
										<span>${res.TName}</span>
									</div>

									<div class="hr-line-dashed"></div>

									<div class="form-group" style="height: 95px">
										<label class="col-sm-2 control-label">所属分院</label>
										<span>${res.TCollege}</span>

									</div>

									<div class="hr-line-dashed"></div>

									<div class="form-group" style="height: 80px;">
										<label class="col-sm-2 control-label">导师简介</label>
										<textarea disabled="disabled" rows="4" type="text" name="feature" class="form-control" style="width:450px">${res.TIntroduction}</textarea>
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
	<script src="jules/js/content.min.js?v=1.0.0"></script>
	<%
        Integer powernum=(Integer)session.getAttribute("power");
    if(powernum==0)
    {
    	%>

	<script src="jules/js/plugins/toastr/toastr.min.js"></script>
	<link href="jules/css/plugins/toastr/toastr.min.css" rel="stylesheet">

	<script type="text/javascript">
	function xiugai(id){
	  
	}
	  
  function largeImage(obj){
  
  var e = event || window.event;
            var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
            var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
            var x = e.pageX || e.clientX + scrollX;
            var y = e.pageY || e.clientY + scrollY;
         
$('#showLargeImage').css({top: y-400, left: x-200});


 document.getElementById("showLargeImage").style.display="";
 var imagebox = document.getElementById("ShowImageBox");
 imagebox.src = obj.src;

 }
 function largeimagehide(obj){

  document.getElementById("showLargeImage").style.display="none";
 }
 
 
  function onon()
            {   
				 setInterval(clickButton1,5000);
				}
				
				
	 function clickButton1()
            {   
			
                var url = 'ResReflash.action';
         
                jQuery.post(url,ttttttt);
				 
				}
				
            
        
			 function ttttttt(data)
            {	
				
				if(data.result == 1){
							toastrinfo()	}
            }
	
		
	
	window.onload=onon;		
  </script>





	<script type="text/javascript">
       
  
  
  
 function toastrinfo() {
		
        var shortCutFunction = "info"	
        var msg = "点击查看"
        var title = "您好，有新用户需要审核！"
       
      
       toastr.options = {
  "closeButton": true,
  "debug": true,
  "progressBar": true,
  "positionClass": "toast-top-right",
  "showDuration": "400",
  "hideDuration": "1000",
  "timeOut": "7000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}
       
     toastr.options.onclick=function(){ 
	 window.location.href="lookalluploaderList2.action"
	 
	 }  
    
       
        var $toast = toastr[shortCutFunction](msg, title);
        $toastlast = $toast;
      
        

 }
    </script>
	<%
    }
	
    %>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
</html>
