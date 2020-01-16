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
    <title>登录</title>
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

            <h3 class="h4 mb-5 text-center">用户登录</h3>

            <form id="user-from" data-parsley-validate="">
                <div class="modal-body">
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
                        <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码"
                               data-parsley-required="true"
                               data-parsley-length="[2, 32]"
                               data-parsley-trigger="blur">
                    </div>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-light" href="${pageContext.request.contextPath}/toregister">注册</a>
                    <a class="btn btn-primary" id="btn-login">登录</a>
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

        $("#btn-login").click(function () {
            $.ajax({
                url: '/login',
                type: 'POST',
                data: $("#user-from").serialize(),
                success: function (data) {
                    console.info(data);
                    if (data.status === 'fail') {
                        alert(data.data.errMsg);
                    } else {
                        alert("登录成功");
                        window.location.href = "/home";
                    }
                }
            });
        });
    });

</script>

</body>

</html>
