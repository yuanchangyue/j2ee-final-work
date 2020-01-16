<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/basestyle/style.css">
    <link href='https://fonts.loli.net/icon?family=Material+Icons' rel='stylesheet'>
    <link href="${pageContext.request.contextPath}/static/assets/css/fontawesome/fontawesome-all.min.css"
          rel="stylesheet">
    <title>区域管理</title>

</head>
<body>

<section class="wrapper">

    <jsp:include page="common/sidebar.jsp"/>

    <div class="content-area">

        <jsp:include page="common/header.jsp"/>

        <div class="content-wrapper">
            <div class="row page-tilte align-items-center">
                <div class="col-md-auto">
                    <a href="#" class="mt-3 d-md-none float-right toggle-controls"><span class="material-icons">keyboard_arrow_down</span></a>
                    <h1 class="weight-300 h3 title">区域管理</h1>
                    <p class="text-muted m-0 desc">区域列表如下</p>
                </div>
                <div class="col controls-wrapper mt-3 mt-md-0 d-none d-md-block ">
                    <div class="controls d-flex justify-content-center justify-content-md-end">
                        <input type="search" class="form-control d-inline-block" id="input-area-name"
                               placeholder="输入商铺名称">
                        <button type="button" class="btn btn-secondary py-1 px-2">
                            <span class="material-icons align-text-bottom" id="btn-search-area">search</span>
                        </button>
                        <button class="btn btn-primary" data-toggle="modal" data-target="#addArea">添加区域</button>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-body table-responsive p-0">
                    <table class="table  m-0">
                        <thead>
                        <tr>
                            <th scope="col" width="1" class="border-top-0 text-center">#</th>
                            <th scope="col" class="border-top-0 text-center">区域名称</th>
                            <th scope="col" class="border-top-0 text-center">区域描述</th>
                            <th scope="col" class="border-top-0 text-center">区域优先权</th>
                            <th scope="col" class="border-top-0 text-center">创建时间</th>
                            <th scope="col" class="border-top-0 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="area-tbody">

                        </tbody>
                    </table>
                </div>
            </div>

            <nav aria-label="Page navigation">
                <div class="float-left">
                    <label class="my-1 mr-2 d-none d-md-block">
                        一共<span id="page-data-size"></span>条,目前展示了 <span id="page-data-num"></span> 条数据
                    </label>
                </div>
                <ul class="pagination mt-3 justify-content-end" id="navigation-content">

                </ul>
            </nav>
        </div>
    </div>
</section>

<div class="modal fade" id="addArea" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">新增区域</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form action="" id="area-form" data-parsley-validate="">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="areaName">区域名称</label>
                            <input type="text" class="form-control" name="areaName" id="areaName"
                                   placeholder="请输入区域名称"
                                   data-parsley-required="true"
                                   data-parsley-length="[2, 10]"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="areaDesc">区域描述</label>
                            <input type="text" class="form-control" name="areaDesc" id="areaDesc"
                                   placeholder="请输入区域描述"
                                   data-parsley-required="true"
                                   data-parsley-length="[2, 100]"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="priority">区域优先级</label>
                            <input type="number" class="form-control" name="priority" id="priority"
                                   placeholder="请输入区域优先级"
                                   data-parsley-type="integer"
                                   data-parsley-required="true"
                                   data-parsley-trigger="blur">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn-add-area">添加</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editArea" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">修改区域信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form action="" id="area-edit-form" data-parsley-validate="">
                    <div class="modal-body">
                        <input type="hidden" name="areaId" value="" id="areaEditId">
                        <div class="form-group">
                            <label for="areaEditName">区域名称</label>
                            <input type="text" class="form-control" name="areaName" id="areaEditName"
                                   placeholder="请输入区域名称"
                                   data-parsley-required="true"
                                   data-parsley-length="[2, 10]"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="areaEditDesc">区域描述</label>
                            <input type="text" class="form-control" name="areaDesc" id="areaEditDesc"
                                   placeholder="请输入区域描述"
                                   data-parsley-required="true"
                                   data-parsley-length="[2, 10]"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="priorityEdit">区域优先级</label>
                            <input type="number" class="form-control" name="priority" id="priorityEdit"
                                   placeholder="请输入区域优先级"
                                   data-parsley-type="integer"
                                   data-parsley-required="true"
                                   data-parsley-trigger="blur">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn-edit-area">修改</button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/assets/js/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/lib/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/chosen-js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/parsley.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/i18n.js"></script>
<script src="${pageContext.request.contextPath}/static/js/area.js"></script>


</body>

</html>