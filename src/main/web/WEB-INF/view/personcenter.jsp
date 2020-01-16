<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/static/assets/css/basestyle/style.css">
    <link href='https://fonts.loli.net/icon?family=Material+Icons' rel='stylesheet'>
    <link href="/static/assets/css/fontawesome/fontawesome-all.min.css" rel="stylesheet">
    <title>个人中心</title>
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
                    <h1 class="weight-300 h3 title">个人中心</h1>
                </div>
                <div class="col controls-wrapper mt-3 mt-md-0 d-none d-md-block ">
                    <div class="controls d-flex justify-content-center justify-content-md-end">
                        <a class="btn btn-primary" href="/shopadmin/shop">添加商铺</a>
                    </div>
                </div>
            </div>
            <div class="card">

                <div class="card-body">
                    <div class="text-right">
                        <span class="dropdown">
                                      <a href="#" class="text-muted" role="button" id="dropdownMenuLink"
                                         data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="material-icons">more_vert</span>
                                      </a>
                                      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                                        <a class="dropdown-item" href="#" data-toggle="modal"
                                           data-target="#modify-password">修改密码</a>
                                      </div>
                        </span>
                    </div>
                    <div class="text-center">
                        <img src="" id="user-img-card" height="100px" width="100px"
                             class="img-thumbnail img-fluid rounded-circle">
                        <h5 class="weight-400" id="user-name-card"></h5>
                        <button class="btn btn-info px-4 rounded mx-1" id="btn-update-user">修改用户</button>
                        <a href="/shopadmin/shop" class="btn btn-danger px-4 rounded mx-1">拥有商铺<span
                                id="btn-shop-count" class="badge"></span></a>
                    </div>
                    <hr class="my-4 dashed">
                    <form id="update-user-from">
                        <input type="hidden" name="userId" id="user-id">
                        <div class="form-group"><label for="user-phone">手机号 : </label>
                            <input type="text" name="phone" class="form-control" id="user-phone">
                        </div>
                        <div class="form-group"><label for="user-email">邮箱 : </label>
                            <input type="text" name="email" class="form-control" id="user-email">
                        </div>
                        <div class="form-group">
                            <legend class="col-form-label col-sm-2 pt-0">性别</legend>
                            <div class="col-sm-10">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender"
                                           id="man-radio" value="1" checked>
                                    <label class="form-check-label" for="man-radio">男</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender"
                                           id="woman-radio" value="0">
                                    <label class="form-check-label" for="woman-radio">女</label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>


<div class="modal fade" id="modify-password" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">修改密码</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form action="" id="edit-password">
                    <input type="hidden" name="userId" id="user-edit-id">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="user-modify-name">用户名</label>
                            <input type="text" readonly class="form-control" id="user-modify-name" name="userName"
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="user-password">旧密码</label>
                            <input type="password" class="form-control" id="user-old-password" name="oldPassword"
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="user-password">新密码</label>
                            <input type="password" class="form-control" id="user-password" name="newPassword"
                                   required>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn-modify-user">确定</button>
            </div>
        </div>
    </div>
</div>


<script src="/static/assets/js/lib/jquery.min.js"></script>
<script src="/static/assets/js/lib/popper.min.js"></script>
<script src="/static/assets/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/assets/js/chosen-js/chosen.jquery.js"></script>
<script src="/static/assets/js/custom.js"></script>
<script src="/static/js/common.js"></script>
<script src="/static/js/person.js"></script>

</body>
</html>
