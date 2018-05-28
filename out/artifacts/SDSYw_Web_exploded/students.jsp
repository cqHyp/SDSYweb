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


    <title>学生列表</title>
   

    <link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="jules/css/animate.min.css" rel="stylesheet">
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">

    
    
       <script type="text/javascript">
 var goodid;
    function zf1(id){
		
			
			if (confirm("确认删除?"))  {  
			var url = "deleteactivity.action?deleteid="+id;

			window.location.href=url;
			}else{
			}
				
  
		}

  function zf2(id){
		
			
		
			var url = "getoneactivity.action?deleteid="+id;

			window.location.href=url;
			
		}

	
	</script>

</head>



<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                       
                        <h2>学生列表</h2>
                      
                        
                        <div class="clients-list">
                           
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane active">
                                    <div class="full-height-scroll">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
                                                <tbody>
                                                
                                                  <c:forEach items="${studentlist}" var="res">
                                                    <tr style="height:80px">
                                                        <td class="project-title">
                                                             <a href="">序号：</a>
                                                             <br/>
                                                                <small>
																    ${res.id.SId}&nbsp;
                                                                </small>
                                                        </td>
                                                        <td class="project-title">
                                                             <a href="">学生姓名：</a>
                                                             <br/>
                                                                <small>
																    ${res.SName}&nbsp;
                                                                </small>
                                                        </td>														  
                                                        <td class="project-title">
                                                             <a href="">性别：</a>
                                                             <br/>
                                                                <small>
																    ${res.SSex}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                             <a href="">学号：</a>
                                                             <br/>
                                                                <small>
																	${res.id.SStudentId}&nbsp;
                                                                </small>
                                                        </td>
                                                        <td class="project-title">
                                                            <a href="">学校：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SSchool}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">学院：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SCollege}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">班级：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SClass}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">专业：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SProfession}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">班级类别：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SClasstype}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">任职情况：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SJobstatus}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">导师：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.STutor}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">联系方式：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SPhone}&nbsp;
                                                                </small>
                                                        </td>
														<td class="project-title">
                                                            <a href="">身份证号：</a>
                                                            <br/>
                                                                <small>
                                                                    ${res.SIdcard}&nbsp;
                                                                </small>
                                                        </td>
                                                    </tr>
                                               	  </c:forEach>
                                              </td>
                                                   
                                                </tbody>
                                            </table>
                                            <td style="text-align:center">
                                            <div style="text-align: center;">
                       <s:if test="page5!=1">
<a href = "allStudent.action?n=0" >首页</a>  

&nbsp;<a href="allStudent.action?n=1">上一页</a>
</s:if>
<s:if test="page5!=PageSum5">
&nbsp;<a href="allStudent.action?n=2">下一页</a>

&nbsp;<a href = "allStudent.action?n=3" >尾页</a>  
</s:if>
&nbsp;第${page5}页/共${PageSum5}页
      </div>
      </td>
      
                                            
                                        </div>
                                    </div>
                                </div>
                               
           
    <script src="jules/js/jquery.min.js?v=2.1.4"></script>
    <script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="jules/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="jules/js/content.min.js?v=1.0.0"></script>


 <%
        Integer powernum=(Integer)session.getAttribute("power");
    if(powernum==0)
    {
    	%>
    
 <script src="jules/js/plugins/toastr/toastr.min.js"></script>
    <link href="jules/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    
      <script type="text/javascript">
	  
  
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
	
    %></body>


<!-- Mirrored from www.zi-han.net/theme/hplus/clients.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>
