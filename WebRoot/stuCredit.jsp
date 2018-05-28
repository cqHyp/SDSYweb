<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>尚德书院</title>
    <link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="jules/css/animate.min.css" rel="stylesheet">
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="jules/css/dataTables.bootstrap.css" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="row">
    <div class="col-sm-12">
        <div class="tabs-container">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#tab-1"
                                      aria-expanded="true"> 尚德书院</a></li>
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
                                            id="search">
                                        <strong>查询</strong>
                                    </button>
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
                                <th>类别</th>
                                <th>总人数</th>
                                <th> 0个学分</th>
                                <th>未完成率</th>
                                <th>1个学分</th>
                                <th>百分比</th>
                                <th style="text-align:center">2个及以上个学分</th>
                                <th style="text-align:center">百分比</th>
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
        right = $('#datatable1').DataTable(
            {
                // 及格
                //bFilter: true, //过滤功能
                bSort: false, //排序功能
                ordering: false,
                destory: true,
                searching: false,
                bLengthChange: true,
                ordering: true,
                bScrollInfinite: true,
                bScrollCollapse: true,
                //ajax:"findStrudents",
                ajax: encodeURI("allActing?twocollege=" + document.getElementById('college').value
                    + "&nianji=" + document.getElementById('nianji').value + "&profession=" + document.getElementById('profession').value
                    + "&classz=" + document.getElementById('classz').value + "&type=" + document.getElementById('mokuai').value),
                oLanguage: {
                    sLoadingRecords: "一点就坏，你敢试试？。。。",
                    sProcessing: "一点就坏，你敢试试？。。。",
                    sSearch: "光速搜索中:",
                    sZeroRecords: "光速搜索中",
                    sEmptyTable: "光速搜索中",
                },
                aoColumns: [
                    {
                        "data": "name"
                    },
                    {
                        "data": "number"
                    },
                    {
                        "data": "number0"
                    },
                    {
                        "data": "bnumber0"
                    },
                    {
                        "data": "number1"
                    },
                    {
                        "data": "bnumber1"
                    }, {
                        "data": "number2"
                    }, {
                        "data": "bnumber2"
                    }
                ],
            });
        right.order([1, 'asc']).draw();
    }

    function changePro() {
        var s = document.getElementById('college').value
        document.getElementById("profession").innerHTML = ""
        document.getElementById("profession").options.add(new Option('所有专业', 'all'))
        <c:forEach items='${listpro}' var='res'>
        if (s == '${res.SCollege}')
            document.getElementById("profession").options.add(new Option('${res.SProfession}', '${res.SProfession}'))
        </c:forEach>
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
    }

    $("#xiazai").click(function () {
        window.location.href = "https://sdsy.zzjc.edu.cn/SDSYw/excleFile/typeRecord.xls";
        // window.location.href = "/excleFile/cj.xls";
    });
    $("#shengcheng").click(function () {
        $.ajax({
            url: "exportTypeRecord",
            data: {
                "exportType": document.getElementById('mokuai').value,
                "twocollege": document.getElementById('college').value,
                "nianji": document.getElementById('nianji').value,
                "profession": document.getElementById('profession').value,
                "classz": document.getElementById('classz').value,
            },
            type: "Post",
            async: false,
            dataType: "json",
            success: function () {
                alert("表格生成完,可下载");
            },
            error: function () {
                alert("失败了");
            }
        });

    });
    $("#search").click(function () {
        var url = "allActing?twocollege=" + encodeURI(document.getElementById('college').value)
            + "&nianji=" + document.getElementById('nianji').value + "&profession=" + encodeURI(document.getElementById('profession').value)
            + "&classz=" + encodeURI(document.getElementById('classz').value) + "&type=" + document.getElementById('mokuai').value
        //alert(url)
        var url2 = "allActing?twocollege=" + document.getElementById('college').value
            + "&nianji=" + document.getElementById('nianji').value + "&profession=" + document.getElementById('profession').value
            + "&classz=" + document.getElementById('classz').value + "&type=" + document.getElementById('mokuai').value
        // right.ajax.url(url2).load()
        //        alert(url2+"2")
        right.ajax.url(encodeURI(url2)).load();
        alert("数据更新中...请耐心等待数据")
    });
</script>


</body>
</html>