<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="sidebar">
    <nav class="navbar navbar-dark bg-primary">
        <a class="navbar-brand m-0 py-2 brand-title" href="#">管理后台</a>
        <span></span>
        <a class="navbar-brand py-2 material-icons toggle-sidebar" href="#">menu</a>
    </nav>
    <nav class="navigation">
        <ul>
            <li><a href="${pageContext.request.contextPath}/home"><span class="nav-icon material-icons">public</span> 首页</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/user/topersoncenter"><span class="nav-icon material-icons">person</span>
                个人中心</a></li>
            <li><a href="#"><span class="nav-icon material-icons">store_mall_directory</span>商铺中心<span
                    class="toogle-sub-nav material-icons">keyboard_arrow_right</span></a>
                <ul class="sub-nav">
                    <li><a href="${pageContext.request.contextPath}/shopadmin/shop">商铺管理</a></li>
                    <li><a href="${pageContext.request.contextPath}/shopadmin/toshopcategory">商铺类别管理</a></li>
                    <li><a href="${pageContext.request.contextPath}/shopadmin/area">区域管理</a></li>
                </ul>
            </li>
            <li><a href="#"><span class="nav-icon material-icons">card_giftcard</span>商品中心<span
                    class="toogle-sub-nav material-icons">keyboard_arrow_right</span></a>
                <ul class="sub-nav">
                    <li><a href='${pageContext.request.contextPath}/shopadmin/toproduct'>商品管理</a></li>
                    <li><a href='${pageContext.request.contextPath}/shopadmin/toproductcategory'>商品类别管理</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</aside>