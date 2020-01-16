$(function () {

    var getShopListUrl = '/shopadmin/getshoplist';
    var shopUrl = '/shopadmin/shop';
    var modifyShopUrl = '/shopadmin/modifyshop';
    var queryShopUrl = '/shopadmin/shopbyname';
    var pn = 1;
    var shopType;


    function showShopList(data) {
        console.info(data);
        var tempHtml = '';
        $.each(data, function (index, item) {
            tempHtml += '<tr>' +
                '<td class=" align-middle text-center">' +
                '    <span class="user-initials bg-success-light25 text-success">' + (index + 1) + '</span>' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<a href="#" class="weight-400">' + item.shopName + '</a>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '<span class="material-icons align-middle md-18 text-danger">expand_more</span>' + item.priority + '' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<div class="weight-400">' + item.shopCategoryDO.shopCategoryName + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<div class="weight-400">' + item.userDO.name + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '    <div class="weight-400">' + item.areaDO.areaName + '</a>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.shopDesc + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.phone + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.shopAddr + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' + new Date(item.createTime).getFullYear() + '</td>' +
                '<td class="align-middle text-center" id="shop-operation">' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-primary btn-edit-shop" data-id="' + item.shopId + '">编辑</button>' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-danger btn-delete-shop" data-id="' + item.shopId + '">删除</button>' +
                '</td>' +
                '</tr>';
        });
        $("#shop-tbody").html(tempHtml);
    }

    function showShopCondition(data) {
        var categoryOptionHtml = '<option value="">请选择商铺类别</option>';
        $.each(data.shopCategory, function (index, item) {
            categoryOptionHtml += '<option value="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>';
        });
        $(".shop-select-category").html(categoryOptionHtml);

        var areaOptionHtml = '<option value="">请选择商铺区域</option>';
        $.each(data.ares, function (index, item) {
            areaOptionHtml += '<option value="' + item.areaId + '">' + item.areaName + '</option>';
        });
        $(".shop-select-area").html(areaOptionHtml);
    }

    function initShopList() {

        $('#shop-form').parsley().validate();
        var areaVal = $("#shop-select-area option:selected").val();
        var categoryVal = $("#shop-select-category option:selected").val();

        var formData = new FormData();
        formData.append("shopCondition", JSON.stringify(returnFromJson("#shop-condition-form")));
        formData.append("areaId", areaVal === undefined ? "" : areaVal);
        formData.append("categoryId", categoryVal === undefined ? "" : categoryVal);
        formData.append("pn", pn);

        console.info(formData.get("areaId"));
        console.info(formData.get("categoryId"));
        console.info(formData.get("shopCondition"));

        $.ajax({
            url: getShopListUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showShopList(data.data.shopPageInfo.list);
                    showNavigation(data.data.shopPageInfo);
                    showShopCondition(data.data);
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    }

    function shopEditModal(data) {
        $("#addShop").modal("show");
        $("#shop-id").val(data.shopId);
        $("#shop-name").val(data.shopName);
        $("#shop-addr").val(data.shopAddr);
        $("#shop-phone").val(data.phone);
        $("#shop-desc").val(data.shopDesc);
        $("#priority").val(data.priority);
        $("#shop-category option[value=" + data.shopCategoryDO.shopCategoryId + "]").attr("selected", "selected");
        $("#shop-area option[value=" + data.areaDO.areaId + "]").attr("selected", "selected");
    }

    $("#btn-condition").click(function () {
        initShopList();
        $("#gridFilters").modal("hide");
    });

    $("#shop-modal").click(function () {
        $("#addShop form")[0].reset();
        shopType = 1;
        console.info("reset");
    });

    $("#btn-add-shop").click(function () {

        var formData = new FormData();
        formData.append("shopInfo", JSON.stringify(returnFromJson("#shop-form")));
        formData.append("categoryId", $("#shop-category option:selected").val());
        formData.append("areaId", $("#shop-area option:selected").val());
        formData.append("img", $("#profileImg")[0].files[0]);

        console.info("shopInfo" + formData.get("shopInfo"));

        $.ajax({
            url: shopType === 1 ? shopUrl : modifyShopUrl,
            type: "POST",
            contentType: false,
            processData: false,
            data: formData,
            success: function (data) {
                console.log(data);
                if (data.status === 'success') {
                    alert("操作成功");
                    $("#addShop").modal('hide');
                    initShopList();
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $("#btn-search-shop").click(function () {
        $.ajax({
            url: queryShopUrl,
            type: "GET",
            data: {shopName: $("#input-shop-name").val()},
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showShopList(data.data.list);
                    showNavigation(data.data);
                } else {
                    alert(data.data.errMsg);
                }
            }
        })
    });

    $(document).on("click", ".btn-delete-shop", function () {
        if (confirm("此操作不可逆,确定删除吗？")) {
            console.info($(this).data("id"));
            $.ajax({
                url: shopUrl + "/" + $(this).data("id"),
                type: "DELETE",
                success: function (data) {
                    console.log(data);
                    if (data.status === 'success') {
                        alert("删除成功");
                        initShopList();
                    } else {
                        alert(data.data.errMsg);
                    }
                }
            });
        }
    });

    $(document).on("click", ".btn-edit-shop", function () {
        shopType = 0;
        console.info($(this).data("id"));
        $.ajax({
            url: shopUrl + "/" + $(this).data("id"),
            type: "GET",
            success: function (data) {
                console.log(data);
                if (data.status === 'success') {
                    shopEditModal(data.data);
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $(document).on("click", ".page-num", function () {
        pn = $(this).data("id");
        initShopList();
    });

    initShopList();

});