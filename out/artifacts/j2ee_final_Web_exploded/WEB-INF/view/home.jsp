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
    <title>管理界面</title>
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
                    <h1 class="weight-300 h3 title">主页</h1>
                    <p class="text-muted m-0 desc">快速展示</p>
                </div>
                <div class="col controls-wrapper mt-3 mt-md-0 d-none d-md-block ">
                    <div class="controls d-flex justify-content-center justify-content-md-end">
                        <input type="search" class="form-control d-inline-block" placeholder="输入商铺名称">
                        <button type="button" class="btn btn-secondary py-1 px-2" data-toggle="modal"
                                data-target="#gridFilters"><span
                                class="material-icons align-text-bottom">filter_list</span></button>
                        <a href="${pageContext.request.contextPath}/shopadmin/shop" class="btn btn-danger">添加商铺</a>
                    </div>
                </div>
            </div>

            <div class="row ">
                <div class="col-lg-4 mb-4">
                    <div class="card h-100">
                        <div class="card-header">个人中心</div>
                        <div class="card-body">
                            <div class="text-center">
                                <img src="" id="user-img-card" height="100px" width="100px"
                                     class="img-thumbnail img-fluid rounded-circle">
                                <h5 class="weight-400" id="user-name-card"></h5>
                                <a href="${pageContext.request.contextPath}/user/topersoncenter"
                                   class="btn btn-info px-4 rounded mx-1"
                                   id="btn-update-user">修改用户</a>
                                <a href="${pageContext.request.contextPath}/shopadmin/shop"
                                   class="btn btn-danger px-4 rounded mx-1">拥有商铺 <span
                                        id="btn-shop-count" class="badge"></span></a>
                            </div>
                            <hr class="my-4 dashed">
                            <p><span class="weight-400">手机号 : </span><span id="user-phone"
                                                                           class="text-muted"></span>
                            </p>
                            <p><span class="weight-400">邮箱 : </span><span id="user-email" class="text-muted"></span>
                            </p>
                            <p><span class="weight-400">性别 : </span><span id="user-gender" class="text-muted"></span>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-8 mb-4">
                    <div class="card h-100">

                        <div class="card-body  table-responsive p-0">

                            <table class="table  m-0">
                                <thead>
                                <tr>
                                    <th scope="col" width="1" class="border-top-0 text-center">#</th>
                                    <th scope="col" class="border-top-0 text-center">名称</th>
                                    <th scope="col" class="border-top-0 text-center">优先权</th>
                                    <th scope="col" class="border-top-0 text-center">商铺类别</th>
                                    <th scope="col" class="border-top-0 text-center">拥有人</th>
                                    <th scope="col" class="border-top-0 text-center">所在区域</th>
                                    <th scope="col" class="border-top-0 text-center">描述</th>
                                    <th scope="col" class="border-top-0 text-center">联系方式</th>
                                    <th scope="col" class="border-top-0 text-center">地址</th>
                                    <th scope="col" class="border-top-0 text-center">创建时间</th>
                                </tr>
                                </thead>
                                <tbody id="shop-tbody">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/static/assets/js/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/lib/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/chosen-js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/static/js/home.js"></script>

</body>

</html>