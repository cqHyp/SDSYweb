<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生成绩下载</title>

    <link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="jules/css/animate.min.css" rel="stylesheet">
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="jules/css/dataTables.bootstrap.css" rel="stylesheet">

    <style type="text/css">
        .selected {
            background-color: #a7aaab;
            cursor: pointer;
        }
    </style>
</head>

<body onload="changePro()" class="gray-bg">
<div class="row">
    <div class="col-sm-12">
        <div class="tabs-container">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#tab-1"
                                      aria-expanded="true"> 学生成绩下载列表</a></li>
            </ul>
            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <div class="panel-body">
                        <table class="table table-bordered" style="height: auto;">
                            <tbody>
                            <tr>
                                <th>学院</th>
                                <td>
                                    <select class="form-control " name="college" id="college" required="required"
                                            onChange="changePro()">
                                        <c:if test="${sessionScope.loginid.equals('000007') }">
                                            <option value="商学院">商学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000008') }">
                                            <option value="信息工程学院">信息工程学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000009') }">
                                            <option value="人文学院">人文学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000010') }">
                                            <option value="机械工程学院">机械工程学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000011') }">
                                            <option value="外国语学院">外国语学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000012') }">
                                            <option value="建筑学院">建筑学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000013') }">
                                            <option value="设计学院">设计学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000014') }">
                                            <option value="理学院">理学院</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000015') }">
                                            <option value="中旅学院">中旅学院</option>
                                        </c:if>

                                        <c:if test="${!sessionScope.loginid.equals('000007')&&!sessionScope.loginid.equals('000008')
										&&!sessionScope.loginid.equals('000009')&&!sessionScope.loginid.equals('000010')
										&&!sessionScope.loginid.equals('000011')&&!sessionScope.loginid.equals('000012')
										&&!sessionScope.loginid.equals('000013')&&!sessionScope.loginid.equals('000014')
										&&!sessionScope.loginid.equals('000015')}">
                                            <option value="all">所有学院</option>
                                            <c:forEach items="${listcollege}" var="res">
                                                <option value="${res.SCollege}">${res.SCollege}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </td>
                                <th>年级</th>
                                <td>
                                    <select class="form-control " name="nianji" id="nianji" required="required"
                                            onChange="changeCla()" " >
                                    <option value="all">所有年级</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    </select>
                                </td>
                                <th>专业</th>
                                <td>
                                    <select class="form-control " name="profession" id="profession" required="required"
                                            onChange="changeCla()" " >
                                    <option value="all">所有专业</option>

                                    </select>
                                </td>
                                <th>班级</th>
                                <td>
                                    <select class="form-control " name="classz" id="classz" required="required"
                                            onChange="changStu()">
                                        <option value="all">所有班级</option>
                                    </select>
                                </td>
                                <th>模块</th>
                                <td>
                                    <select class="form-control " name="mokuai" id="mokuai" required="required">
                                        <c:if test="${sessionScope.loginid.equals('000001') }">
                                            <option value="2">博雅读书</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000002') }">
                                            <option value="3">博雅心晴</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000003') }">
                                            <option value="4">博雅实践</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000004') }">
                                            <option value="5">博雅讲坛</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000005') }">
                                            <option value="6">博雅修身</option>
                                        </c:if>
                                        <c:if test="${sessionScope.loginid.equals('000006') }">
                                            <option value="7">博雅视野</option>
                                        </c:if>
                                        <c:if test="${!sessionScope.loginid.equals('000001')&&!sessionScope.loginid.equals('000002')
										&&!sessionScope.loginid.equals('000003')&&!sessionScope.loginid.equals('000004')
										&&!sessionScope.loginid.equals('000005')&&!sessionScope.loginid.equals('000006')}">
                                            <option value="2">博雅读书</option>
                                            <option value="3">博雅心晴</option>
                                            <option value="4">博雅实践</option>
                                            <option value="5">博雅讲坛</option>
                                            <option value="6">博雅修身</option>
                                            <option value="7">博雅视野</option>
                                        </c:if>
                                    </select>
                                </td>
                                <td>

                                    <button class="btn btn-sm btn-primary m-t-n-xs" type="submit"
                                            id="shengcheng">
                                        <strong>生成表格</strong>
                                    </button>
                                    <button class="btn btn-sm btn-primary m-t-n-xs" type="submit"
                                            id="xiazai">
                                        <strong>下载</strong>
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <table class="table table-bordered" id="datatable1">
                            <thead>
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>学院</th>
                                <th>专业</th>
                                <th>班级</th>
                                <th style="text-align:center">班级类型</th>
                                <th style="text-align:center">任职</th>
                                <th style="text-align:center">导师</th>
                                <th style="text-align:center">联系方式</th>
                                <th style="text-align:center">身份证</th>

                            </tr>
                            </thead>

                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>


</div>

<script src="jules/js/jquery.min.js?v=2.1.4"></script>
<script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
<script src="jules/js/content.min.js?v=1.0.0"></script>
<script src="jules/js/jquery.dataTables.js"></script>
<script src="jules/js/dataTables.bootstrap.js"></script>

<script type="text/javascript">
    var right;
    window.onload = function () {
        //alert(document.getElementById('college').value);
        right = $('#datatable1').DataTable(
            {
                // 及格
                //bFilter: true, //过滤功能
                //  bSort: false, //排序功能
                destory: true,
                searching: true,
                bLengthChange: true,
                ordering: true,
                bScrollInfinite: true,
                bScrollCollapse: true,
                //ajax:"findStrudents",
                ajax: "findStudents.action?twocollege=" + document.getElementById('college').value
                + "&nianji=" + document.getElementById('nianji').value + "&profession=" + document.getElementById('profession').value
                + "&classz=" + document.getElementById('classz').value,
                oLanguage: {
                    sLoadingRecords: "你猜我在干什么...",
                    sProcessing: "你猜我在干什么...",
                    sSearch: "搜索:",
                    sZeroRecords: "没有找到符合条件的数据",
                    sEmptyTable: "没有找到符合条件的数据",
                },
                aoColumns: [
                    {
                        "data": "id"
                    },
                    {
                        "data": "name"
                    },
                    {
                        "data": "sex"
                    },
                    {
                        "data": "college"
                    },
                    {
                        "data": "pr"
                    },
                    {
                        "data": "clasz"
                    },
                    {
                        "data": "type"
                    }, {
                        "data": "job"
                    }, {
                        "data": "te"
                    }, {
                        "data": "phone"
                    }, {
                        "data": "scard"
                    }
                    //		,{
                    //	        "data" :   function (data, type, full, meta) {
                    //  return '<input type="checkbox" name="checkbox" class="checkchild"/>';
                    //               }
                    //		}
                ],
            });
        $('#datatable1 tbody').on('click', 'tr', function () {
            $(this).toggleClass('selected');
        });
    }
    //搜索
    var sql;

    function changStu() {
        if (document.getElementById('classz').value != "all") {
            sql = $('#datatable1').DataTable().column(5).search(document.getElementById('classz').value);
        } else {
            if (document.getElementById('profession').value != "all") {
                if (document.getElementById('nianji').value != "all") {
                    sql = $('#datatable1').DataTable().column(5).search(document.getElementById('nianji').value)
                        .column(4).search(document.getElementById('profession').value, false, false)

                } else {
                    sql = $('#datatable1').DataTable().column(5).search("")
                    $('#datatable1').DataTable().column(4).search(document.getElementById('profession').value, false, false)
                    //var a=$('#datatable1').DataTable().select("pr!="+document.getElementById('profession').value).data()

                }
            } else {
                if (document.getElementById('college').value != "all") {
                    if (document.getElementById('nianji').value != "all") {
                        sql = $('#datatable1').DataTable().column(3).search(document.getElementById('college').value)
                            .column(5).search(document.getElementById('nianji').value)
                            .column(4).search("")
                    } else {
                        sql = $('#datatable1').DataTable().column(3).search(document.getElementById('college').value)
                            .column(4).search("").column(5).search("")
                    }
                } else {
                    if (document.getElementById('nianji').value != "all") {
                        sql = $('#datatable1').DataTable().column(3).search("")
                            .column(5).search(document.getElementById('nianji').value)
                    } else {
                        sql = $('#datatable1').DataTable().column(3).search("").column(4).search("").column(5).search("")
                    }

                }

            }
        }

        sql.draw()
        //sql.draw(false)//不重置页面
    }

    //自定义search
    $.fn.dataTable.ext.search.push(
        function (settings, data, dataIndex) {
            var pr = document.getElementById('profession').value
            if (pr == 'all') {
                return true;
            }
            var prs = data[4]  // use data for the age column
            if (pr == prs) {
                return true;
            }
            return false;
        }
    );

    $("#shengcheng").click(function () {
        //$("input[name='checkbox']").attr("checked","true");
        var stus = "";
        if ($('#datatable1').DataTable().rows('.selected').data().length <= 0) {
        } else {
            $('#datatable1').DataTable().rows('.selected').data().each(function (value, index) {
                stus += value['id'] + ",";
            });
            alert("已选中" + $('#datatable1').DataTable().rows('.selected').data().length + "人:" + stus)
        }
        sc(stus);

    });

    function sc(stus) {
        $.ajax({
            url: "exportRecord",
            data: {
                "exportType": document.getElementById('mokuai').value,
                "students": stus,
                "twocollege": document.getElementById('college').value,
                "nianji": document.getElementById('nianji').value,
                "profession": document.getElementById('profession').value,
                "classz": document.getElementById('classz').value,
            },
            type: "POST",
            async: false,
            dataType: "json",
            success: function () {
                alert("表格生成完可下载");
            }
        });
    }

    $("#xiazai").click(function () {
        window.location.href = "https://sdsy.zzjc.edu.cn/SDSYw/excleFile/cj.xls";
        // window.location.href = "/excleFile/cj.xls";
    });


</script>
<script type="text/javascript">

    function changePro() {
        var s = document.getElementById('college').value
        document.getElementById("profession").innerHTML = ""
        document.getElementById("profession").options.add(new Option('所有专业', 'all'))
        <c:forEach items='${listpro}' var='res'>
        if (s == '${res.SCollege}')
            document.getElementById("profession").options.add(new Option('${res.SProfession}', '${res.SProfession}'))
        </c:forEach>
        changStu()
    }

    function changeCla() {
        var s = document.getElementById('profession').value
        var n = document.getElementById('nianji').value
        document.getElementById("classz").innerHTML = ""
        document.getElementById("classz").options.add(new Option('所有班级', 'all'))
        <c:forEach items='${listcla}' var='res'>
        if (n == 'all') {
            if (s == '${res.SProfession}')
                document.getElementById("classz").options.add(new Option('${res.SClass}', '${res.SClass}'))
        } else {
            if (s == '${res.SProfession}' & '${res.SClass}'.indexOf(n) >= 0)
                document.getElementById("classz").options.add(new Option('${res.SClass}', '${res.SClass}'))
        }
        </c:forEach>
        changStu()
    }


</script>

</body>

<!-- Mirrored from www.zi-han.net/theme/hplus/clients.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->

</html>