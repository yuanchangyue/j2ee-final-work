<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/basestyle/style.css">
    <link href='https://fonts.loli.net/icon?family=Material+Icons' rel='stylesheet'>
    <link href="${pageContext.request.contextPath}/static/assets/css/fontawesome/fontawesome-all.min.css"
          rel="stylesheet">
    <title>商品类别管理</title>
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
                    <h1 class="weight-300 h3 title">商品类别管理</h1>
                    <p class="text-muted m-0 desc">商品类别列表如下</p>
                </div>
                <div class="col controls-wrapper mt-3 mt-md-0 d-none d-md-block ">
                    <div class="controls d-flex justify-content-center justify-content-md-end">
                        <input type="search" class="form-control d-inline-block" id="input-product-category-name"
                               placeholder="输入商品类别名称">
                        <button type="button" class="btn btn-secondary py-1 px-2">
                            <span class="material-icons align-text-bottom" id="btn-search-category">search</span>
                        </button>
                        <button class="btn btn-primary" data-toggle="modal" id="btn-add-category"
                                data-target="#category-modal">
                            添加商品类别
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
                            <th scope="col" class="border-top-0 text-center">商品类别名称</th>
                            <th scope="col" class="border-top-0 text-center">商品类别描述</th>
                            <th scope="col" class="border-top-0 text-center">所属商铺</th>
                            <th scope="col" class="border-top-0 text-center">商品类别优先权</th>
                            <th scope="col" class="border-top-0 text-center">创建时间</th>
                            <th scope="col" class="border-top-0 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="product-category-tbody">

                        </tbody>
                    </table>
                </div>
            </div>

            <nav aria-label="Page navigation">
                <div class="float-left">
                    <label class="my-1 mr-2 d-none d-md-block">
                        一共<span id="page-data-size"></span>条,目前展示了 <span id="page-data-num"></span>条数据
                    </label>
                </div>
                <ul class="pagination mt-3 justify-content-end" id="navigation-content">

                </ul>
            </nav>
        </div>
    </div>
</section>

<div class="modal fade" id="category-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">商品类别</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form action="" id="category-form" data-parsley-validate="">
                    <div class="modal-body">
                        <input type="hidden" name="productCategoryId" id="productCategoryId">
                        <div class="form-group">
                            <label for="productCategoryName">商品类别名称</label>
                            <input type="text" class="form-control" name="productCategoryName" id="productCategoryName"
                                   placeholder="请输入商品类别名称"
                                   data-parsley-required="true"
                                   data-parsley-length="[2, 11]"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="productCategoryDesc">商品类别描述</label>
                            <input type="text" class="form-control" name="productCategoryDesc" id="productCategoryDesc"
                                   placeholder="请输入商品类别描述"
                                   data-parsley-required="true"
                                   data-parsley-length="[1, 100]"
                                   data-parsley-trigger="blur">
                        </div>
                        <div class="form-group">
                            <label for="shop-select">所属商铺</label>
                            <select class="form-control shop-select" id="shop-select">

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="priority">商品类别优先级</label>
                            <input type="number" class="form-control" name="priority" id="priority"
                                   placeholder="请输入商品类别优先级"
                                   data-parsley-type="integer"
                                   data-parsley-required="true"
                                   data-parsley-trigger="blur">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn-category">确定</button>
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
<script src="${pageContext.request.contextPath}/static/js/productcategory.js"></script>

</body>

</html>