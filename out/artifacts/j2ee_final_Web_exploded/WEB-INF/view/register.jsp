<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/basestyle/style.css">
    <link href='https://fonts.loli.net/icon?family=Material+Icons' rel='stylesheet'>
    <link href="${pageContext.request.contextPath}/static/assets/css/fontawesome/fontawesome-all.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/assets/css/pages/login.css" rel="stylesheet">

    <title>注册</title>
</head>
<body>

<section class="wrapper">

    <div class="login">
        <div class="image-placeholder">
            <h1>Lorem ipsum dolor sit amet<br>consectetur pellentesque adipiscing elit.</h1>
        </div>
        <div class="form">

            <div class="text-center mb-4"><span class="material-icons text-danger" style="font-size:6rem;">wifi_tethering</span>
            </div>

            <h3 class="h4 mb-5 text-center">用户注册</h3>

            <form id="user-from" action="${pageContext.request.contextPath}/user/register" method="post"
                  enctype="multipart/form-data" data-parsley-validate="">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="userName">名称</label>
                        <input type="text" class="form-control" name="name" id="userName"
                               placeholder="请输入名称"
                               data-parsley-required="true"
                               data-parsley-length="[2, 32]"
                               data-parsley-trigger="blur">
                    </div>

                    <div class="form-group">
                        <label for="phone">手机号</label>
                        <input type="text" class="form-control" name="phone" id="phone"
                               placeholder="请输入手机号"
                               data-parsley-pattern="/^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/"
                               data-parsley-pattern-message="请输入正确的手机号"
                               data-parsley-required="true"
                               data-parsley-trigger="blur">
                    </div>

                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="请输入密码"
                               data-parsley-required="true"
                               data-parsley-length="[2, 32]"
                               data-parsley-trigger="blur">
                    </div>

                    <div class="form-group">
                        <label for="birthDay">生日</label>
                        <input type="date" class="form-control" name="birthday" id="birthDay"
                               placeholder="请输入出生年月">
                    </div>

                    <div class="form-group">
                        <label for="birthDay">邮箱</label>
                        <input type="email" class="form-control" name="email" id="email"
                               placeholder="请输入邮箱"
                               data-parsley-required="true"
                               data-parsley-type="email"
                               data-parsley-trigger="blur">
                    </div>

                    <div class="form-group">
                        <label for="profileImg">头像</label>
                        <input type="file" class="form-control" name="profileImg" id="profileImg" multiple="multiple">
                    </div>

                    <div class="row">
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
                </div>
                <div class="modal-footer">
                    <a class="btn btn-light" href="${pageContext.request.contextPath}/tologin">直接登录</a>
                    <a class="btn btn-primary" id="btn-register">注册</a>
                </div>
            </form>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/static/assets/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/parsley.js"></script>
<script src="${pageContext.request.contextPath}/static/js/lib/i18n.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common.js"></script>

<script>
    $(function () {

        $("#user-from").parsley();

        $("#btn-register").click(function () {

            var formData = new FormData();
            formData.append("userJson", JSON.stringify(returnFromJson("#user-from")));
            formData.append("img", $("#profileImg")[0].files[0]);

            $.ajax({
                url: '/user/register',
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    console.info(data);
                    if (data.status === 'fail') {
                        alert(data.data.errMsg);
                    } else {
                        alert("注册成功");
                        window.location.href = "/tologin";
                    }
                }
            });
        });
    });

</script>


</body>

</html>
