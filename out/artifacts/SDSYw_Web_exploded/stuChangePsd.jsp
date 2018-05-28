<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page isELIgnored="false"%>


<!DOCTYPE html>
<html>


<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>修改密码</title>
   
    <link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="jules/css/animate.min.css" rel="stylesheet">
 
    <link href="jules/css/plugins/chosen/chosen.css" rel="stylesheet">
    
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="jules/css/plugins/iCheck/custom.css" rel="stylesheet">
 <link href="jules/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
     
     
     
<link rel="stylesheet" href="css/select2.css" />

                        
<link rel="stylesheet" type="text/css" href="css/mdialog.css"> 


    
  
      
    
    
    
</head>

<body class="gray-bg">
     <div class="wrapper wrapper-content animated fadeInRight">
                     <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>修改密码<small></small></h5>
                       
                    </div>
                    <div class="ibox-content" >
                        <form method="post" name="changepsd"  action="updateStudentPsd" class="form-horizontal form-horizontal m-t">
                             
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">学生学号</label>

                                <div class="col-sm-10">
                                    <input type="text" name="updateId" id="stuid" class="form-control" style="width:280px">
                                </div>
                            </div>
                                                      
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">学生新密码</label>
                                <div class="col-sm-10">
                                    <input type="password" name="psdMd5" id="stupsd" class="form-control" style="width:280px">
                                </div>
                            </div>
                           <div class="hr-line-dashed"></div>						 

                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button id="btnsub" class="btn btn-primary" type="submit">提交</button>   
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
               
    <script src="jules/js/jquery.min.js?v=2.1.4"></script>
    <script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="jules/js/content.min.js?v=1.0.0"></script>
    <script src="jules/js/plugins/layer/laydate/laydate.js"></script>
     <script src="jules/js/plugins/chosen/chosen.jquery.js"></script>
    <script src="jules/js/plugins/cropper/cropper.min.js"></script>
    <script src="jules/js/demo/form-advanced-demo.min.js"></script>  
            <script src="jules/js/plugins/iCheck/icheck.min.js"></script>
 <script src="js/jquery.min.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 

<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript" src="js/mdialog.js"></script>

<script src="js/matrix.form_validation.js"></script>           
    <script src="jules/js/plugins/toastr/toastr.min.js"></script>                

                    
                    
 
    
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/layerdate.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>
