<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header sticky-top">
    <nav class="navbar navbar-light bg-white px-sm-4 ">
        <a class="navbar-brand py-2 d-md-none  m-0 material-icons toggle-sidebar" href="#">menu</a>
        <ul class="navbar-nav flex-row ml-auto">
            <li class="nav-item ml-sm-3 user-logedin dropdown">
                <a href="#" id="userLogedinDropdown" data-toggle="dropdown"
                   class="nav-link weight-400 dropdown-toggle">
                    <img src="/static/assets/images/user.png" id="user-img" class="mr-2 rounded"
                         width="28"><span
                        id="user-name"></span>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userLogedinDropdown">
                    <a class="dropdown-item" href="/user/topersoncenter">个人中心</a>
                    <a class="dropdown-item" href="help.html">帮助与支持</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" id="btn-out">退出</a>
                </div>
            </li>
        </ul>
    </nav>
</header>
