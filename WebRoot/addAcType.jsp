<%@page contentType="text/html; charset=UTF-8" %>
<%--<%@taglib uri="/struts-tags" prefix="s"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="jules/css/animate.min.css" rel="stylesheet">
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">

    <style>
        th{
            background-color: #F5F5F6;
        }
    </style>
</head>
<body class="gray-bg">

    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content">


                        <div class="ibox float-e-margins">
                            <div class="text-center">
                                <h3>新增形式</h3>
                            </div>

                            <div class="ibox-content">

                                <form action="addtem" name="additem" method="post" enctype="multipart/form-data">
                                    <table class="table table-bordered" style="height: auto;">
                                        <tr>I

                                            <th>项目类型</th>
                                            <td>
                                                <select class="form-control " name="type">
                                                    <option value="2">博雅读书</option>
                                                    <option value="3">博雅"心情"</option>
                                                    <option value="4">博雅实践</option>
                                                    <option value="5">博雅讲坛</option>
                                                    <option value="6">博雅修身</option>
                                                    <option value="7">博雅视野</option>
                                                </select>
                                            </td>


                                        </tr>
                                        <tr>

                                            <th>项目名称</th>
                                            <td>
                                                <input class="form-control " type="text" name="itemDesc">
                                            </td>
                                        </tr>
                                        <tr>

                                            <th>要求</th>
                                            <td>
                                                <input class="form-control " type="text" name="require">
                                            </td>
                                        </tr>

                                    </table>

                                    <div class="form-group" style="height: auto">

                                        <input class="btn btn-sm btn-primary m-t-n-xs pull-right" style="margin-left: 20px"
                                               type="submit" value="取消">
                                        </input>
                                        <input id="submit" class="btn btn-sm btn-primary m-t-n-xs pull-right" type="submit"
                                               value="确定">
                                        </input>
                                    </div>
                                </form>


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
    <script src="jules/js/layer/laydate/laydate.js"></script>

    <script>
        $(document).ready(function () {
            var start, end;
            laydate({
                elem: "#startTime",
                event: "focus",
                format: "YYYY/MM/DD hh:mm",
                min: laydate.now(),
                istime: true,
                choose: function (datas) { //选择日期完毕的回调
                    start = datas;
                }
            });

            laydate({
                elem: "#endTime",
                event: "focus",
                format: "YYYY/MM/DD hh:mm",
                min: laydate.now(),
                istime: true,
                choose: function (datas) { //选择日期完毕的回调
                    end = datas;
                }
            });

            $('#submit').click(function () {
                alert(start);
                alert(end);
            });
        });

    </script>

</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
</html>
