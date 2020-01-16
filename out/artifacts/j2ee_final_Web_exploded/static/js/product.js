$(function () {

    var getProductListUrl = '/shopadmin/getproductlist';
    var getProductCategoryListUrl = '/shopadmin/getproductcategorylist';
    var productUrl = '/shopadmin/product';
    var modifyShopUrl = "/shopadmin/modifyproduct";
    var productType;
    var pn = 1;

    function showProductList(data) {
        console.info(data);
        var tempHtml = '';
        $.each(data, function (index, item) {
            tempHtml += '<tr>' +
                '<td class="align-middle text-center">' +
                '<img width="45px" height="45px" src="' + item.imgAddr + '" alt="' + item.productName + '">' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<a href="#" class="weight-400">' + item.productName + '</a>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '<span class="material-icons align-middle md-18 text-danger">expand_more</span>' + item.priority + '' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<div class="weight-400">' + item.shopDO.shopName + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<div class="weight-400">' + item.productCategoryDO.productCategoryName + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<div class="weight-400">' + item.userDO.name + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.productDesc + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + '￥' + item.normalPrice + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + '￥' + item.promotionPrice + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' + changeDateFormTime(item.createTime) + '</td>' +
                '<td class="align-middle text-center" id="product-operation">' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-primary btn-edit-product" data-id="' + item.productId + '">编辑</button>' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-danger btn-delete-product" data-id="' + item.productId + '">删除</button>' +
                '</td>' +
                '</tr>';
        });
        $("#product-tbody").html(tempHtml);
    }

    function showShopCondition(data) {
        var shopOptionHtml = '<option value="">请选择商铺</option>';
        $.each(data.shopList, function (index, item) {
            shopOptionHtml += '<option value="' + item.shopId + '">' + item.shopName + '</option>';
        });
        $(".shop-select").html(shopOptionHtml);
    }

    function showProductCategory(data, ele) {
        var productCategoryOptionHtml = '<option value="">请选择商品类型</option>';
        $.each(data.list, function (index, item) {
            productCategoryOptionHtml += '<option value="' + item.productCategoryId + '">' + item.productCategoryName + '</option>';
        });
        $(ele).html(productCategoryOptionHtml);
    }

    function initProductList() {

        $('#product-form').parsley();

        var shopId = $("#shop-select option:selected").val();
        var categoryVal = $("#product-category-select option:selected").val();
        var productName = $("#input-product-name").val();

        var formData = new FormData();
        formData.append("categoryId", categoryVal === undefined ? "" : categoryVal);
        formData.append("shopId", shopId === undefined ? "" : shopId);
        formData.append("productName", productName);
        formData.append("pn", pn);

        $.ajax({
            url: getProductListUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showProductList(data.data.productPageInfo.list);
                    showNavigation(data.data.productPageInfo);
                    //showShopCondition(data.data);
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    }

    function productEditModal(data) {
        $("#shop-img").css("display", "none");
        $("#addShop").modal("show");
        $("#product-id").val(data.productId);
        $("#product-name").val(data.productName);
        $("#product-addr").val(data.productAddr);
        $("#normal-price").val(data.normalPrice);
        $("#promotion-price").val(data.promotionPrice);
        $("#product-desc").val(data.productDesc);
        $("#priority").val(data.priority);
        $("#shop-select-add option[value=" + data.shopDO.shopId + "]").attr("selected", "selected");
        $("#shop-select-add").trigger("change", data.productCategoryDO.productCategoryId);
    }

    function editProdutCategory() {
    }

    function showShopList() {
        var formData = new FormData();
        $.ajax({
            url: getProductListUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showShopCondition(data.data);
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    }

    $("#shop-select").change(function () {
        var shopId = $("#shop-select option:selected").val();
        console.log(shopId);
        $.ajax({
            url: getProductCategoryListUrl,
            type: 'GET',
            data: {
                shopId: shopId
            },
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showProductCategory(data.data.shopCategoryPageInfo, "#product-category-select");
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $("#shop-select-add").change(function (e, id) {
        var shopId = $("#shop-select-add option:selected").val();
        console.log(id);
        $.ajax({
            url: getProductCategoryListUrl,
            type: 'GET',
            data: {
                shopId: shopId
            },
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showProductCategory(data.data.shopCategoryPageInfo, "#product-category-select-add");
                    if (id !== undefined) {
                        $("#product-category-select-add option[value=" + id + "]").attr("selected", "selected");
                    }
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $("#btn-condition").click(function () {
        initProductList();
    });

    $("#product-modal").click(function () {
        productType = 1;
        $("#shop-img").css("display", "display");
        $("#addShop").modal("show");
        $("#addShop form")[0].reset();
    });

    $("#btn-add-product").click(function () {

        var formData = new FormData();
        formData.append("productInfo", JSON.stringify(returnFromJson("#product-form")));
        formData.append("shopId", $("#shop-select-add option:selected").val());
        formData.append("categoryId", $("#product-category-select-add option:selected").val());
        formData.append("img", $("#profileImg")[0].files[0]);


        $.ajax({
            url: productType === 1 ? productUrl : modifyShopUrl,
            type: "POST",
            contentType: false,
            processData: false,
            data: formData,
            success: function (data) {
                console.log(data);
                if (data.status === 'success') {
                    alert("操作成功");
                    $("#addShop").modal('hide');
                    initProductList();
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $("#btn-search-product").click(function () {
        initProductList();
    });

    $(document).on("click", ".btn-delete-product", function () {
        if (confirm("此操作不可逆,确定删除吗？")) {
            console.info($(this).data("id"));
            $.ajax({
                url: productUrl + "/" + $(this).data("id"),
                type: "DELETE",
                success: function (data) {
                    console.log(data);
                    if (data.status === 'success') {
                        alert("删除成功");
                        initProductList();
                    } else {
                        alert(data.data.errMsg);
                    }
                }
            });
        }
    });

    $(document).on("click", ".btn-edit-product", function () {
        productType = 0;

        console.info($(this).data("id"));
        $.ajax({
            url: productUrl + "/" + $(this).data("id"),
            type: "GET",
            success: function (data) {
                console.log(data);
                if (data.status === 'success') {
                    productEditModal(data.data);
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $(document).on("click", ".page-num", function () {
        pn = $(this).data("id");
        initProductList();
    });

    initProductList();

    showShopList();
});