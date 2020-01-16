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
    <link href="${pageContext.request.contextPath}/static/assets/css/fontawesome/fontawesome-all.min.css" rel="stylesheet">
    <title>商铺管理</title>
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
                    <h1 class="weight-300 h3 title">商铺管理</h1>
                    <p class="text-muted m-0 desc">商铺列表如下</p>
                </div>
                <div class="col controls-wrapper mt-3 mt-md-0 d-none d-md-block ">
                    <div class="controls d-flex justify-content-center justify-content-md-end">
                        <input type="search" class="form-control d-inline-block" id="input-shop-name"
                               placeholder="输入商铺名称">
                        <button type="button" class="btn btn-secondary py-1 px-2">
                            <span class="material-icons align-text-bottom" id="btn-search-shop">search</span>
                        </button>
                        <button type="button" class="btn btn-info py-1 px-2" data-toggle="modal"
                                data-target="#gridFilters"><span
                                class="material-icons align-text-bottom">filter_list</span></button>
                        <button class="btn btn-primary" data-toggle="modal" id="shop-modal" data-target="#addShop">
                            添加商铺
                        </button>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-body table-responsive p-0">
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
                            <th scope="col" class="border-top-0 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="shop-tbody">

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

<div class="modal fade" id="addShop" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">商铺操作</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form action="" id="shop-form" data-parsley-validate="">
                    <input type="hidden" name="shopId" id="shop-id">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="profileImg">商铺缩略图</label>
                            <input type="file" class="form-control" name="shopImg" id="profileImg"
                                   multiple="multiple">
                        </div>
                        <div class="form-group">
                            <label for="shop-name">商铺名称</label>
                            <input type="text" class="form-control" id="shop-name" name="shopName"
                                   placeholder="请输入商铺名称"
                                   data-parsley-required="true"
                                   data-parsley-length="[2, 10]"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="shop-phone">商铺联系方式</label>
                            <input type="text" class="form-control" id="shop-phone" name="phone"
                                   placeholder="输入商铺手机号"
                                   data-parsley-pattern="/^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/"
                                   data-parsley-pattern-message="请输入正确的手机号"
                                   data-parsley-required="true"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="priority">商铺优先级</label>
                            <input type="number" class="form-control" name="priority" id="priority"
                                   placeholder="请输入商铺优先级"
                                   data-parsley-type="integer"
                                   data-parsley-required="true"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="shop-select-category">商铺类别</label>
                            <select class="form-control shop-select-category" id="shop-category">

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="shop-select-area">商铺区域</label>
                            <select class="form-control shop-select-area" id="shop-area">

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="shop-desc">商铺描述</label>
                            <textarea class="form-control"
                                      id="shop-desc"
                                      name="shopDesc"
                                      placeholder="请输入商铺描述"
                                      data-parsley-required="true"
                                      data-parsley-length="[1, 100]"
                                      data-parsley-trigger="blur"
                                      rows="2"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="shop-addr">商铺地址</label>
                            <textarea class="form-control"
                                      id="shop-addr"
                                      name="shopAddr"
                                      placeholder="请输入商铺的地址"
                                      data-parsley-required="true"
                                      data-parsley-length="[1, 100]"
                                      data-parsley-trigger="blur"
                                      rows="3"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn-add-shop">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade leftright-slide right-align" id="gridFilters" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header px-4">
                <h5 class="modal-title" id="exampleModalLabel">条件筛选</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span class="material-icons ">close</span>
                </button>
            </div>
            <div class="modal-body p-4">
                <form id="shop-condition-form">
                    <div class="form-group">
                        <label>商铺名称</label>
                        <input type="text" class="form-control d-inline-block" name="shopName"
                               placeholder="输入商铺名称">
                    </div>
                    <div class="form-group">
                        <label for="shop-name">商铺联系方式</label>
                        <input type="text" class="form-control d-inline-block" name="phone"
                               placeholder="输入商铺手机号">
                    </div>
                    <div class="form-group">
                        <label for="shop-address">商铺地址</label>
                        <input type="text" class="form-control d-inline-block" id="shop-address" name="shopAddr"
                               placeholder="输入商铺地址">
                    </div>
                    <div class="form-group">
                        <label for="shop-select-category">商铺类别</label>
                        <select class="form-control shop-select-category" id="shop-select-category">

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="shop-select-area">商铺区域</label>
                        <select class="form-control shop-select-area" id="shop-select-area">

                        </select>
                    </div>
                    <div class="row">
                        <div class="col-sm-10">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="enableStatus"
                                       id="man-radio" value="1">
                                <label class="form-check-label" for="man-radio">上架</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="enableStatus"
                                       id="woman-radio" value="0">
                                <label class="form-check-label" for="woman-radio">下架</label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer px-4">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary " id="btn-condition">筛选</button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/assets/js/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/lib/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/chosen-js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/parsley.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/i18n.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script src="${pageContext.request.contextPath}/static/js/shop.js"></script>

</body>

</html>