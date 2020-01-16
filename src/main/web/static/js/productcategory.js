$(function () {
    var getProductCategoryListUrl = '/shopadmin/getproductcategorylist';
    var categoryUrl = '/shopadmin/productcategory';
    var modifyCategoryUrl = '/shopadmin/modifyproductcategory';
    var categoryType;
    var pn = 1;


    function showAreaList(data) {
        console.info(data);
        var tempHtml = '';
        $.each(data, function (index, item) {
            tempHtml += ' <tr>' +
                '<td class=" align-middle text-center">' +
                '    <span class="user-initials bg-success-light25 text-success">' + (index + 1) + '</span>' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<a href="#" class="weight-400">' + item.productCategoryName + '</a>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.productCategoryDesc + '</div>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.shop.shopName + '</div>' +
                '</td>' +
                '<td class="align-middle text-center"><span class="material-icons align-middle md-18 text-danger">expand_more</span>' + item.priority + '' +
                '</td>' +
                '<td class="align-middle text-center">' + changeDateFormTime(item.createTime) + '</td>' +
                '<td class="align-middle text-center">' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-primary btn-edit-category" data-id="' + item.productCategoryId + '">编辑</button>' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-danger btn-delete-category" data-id="' + item.productCategoryId + '">删除</button>' +
                '</td>' +
                '</tr>';
        });
        $("#product-category-tbody").html(tempHtml);
    }

    function showShopCategory(shopList) {
        var shopSelectHtml = '<option value="">请选择商铺</option>';
        $.each(shopList, function (index, item) {
            shopSelectHtml += '<option value="' + item.shopId + '">' + item.shopName + '</option>';
        });
        $(".shop-select").html(shopSelectHtml);
    }

    function initAreaList() {
        $("#category-form").parsley();
        var productCategoryByInput = $("#input-product-category-name").val();
        console.log(productCategoryByInput);

        $.ajax({
            url: getProductCategoryListUrl,
            type: 'GET',
            data: {
                pn: pn, productCategoryName: productCategoryByInput
            },
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showAreaList(data.data.shopCategoryPageInfo.list);
                    showNavigation(data.data.shopCategoryPageInfo);
                    showShopCategory(data.data.shopList);
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    }

    initAreaList();

    $("#btn-category").click(function () {

        console.log(categoryType);

        var formData = new FormData();
        formData.append("productCategoryInfo", JSON.stringify(returnFromJson("#category-form")));
        formData.append("shopId", $("#shop-select option:selected").val());

        console.log(formData.get("productCategoryInfo"));
        console.log(formData.get("shopId"));

        $.ajax({
            url: categoryType === 1 ? categoryUrl : modifyCategoryUrl + "/" + $(this).data("id"),
            type: "POST",
            contentType: false,
            processData: false,
            data: formData,
            success: function (data) {
                console.log(data);
                if (data.status === 'success') {
                    alert("操作成功");
                    $("#category-modal").modal('hide');
                    initAreaList();
                } else {
                    alert(data.data.errMsg);
                }
            }
        });


    });

    $("#btn-search-category").click(function () {
        initAreaList();
    });

    $("#btn-add-category").click(function () {
        categoryType = 1;
        $("#category-modal form")[0].reset();
    });

    $(document).on("click", ".btn-delete-category", function () {
        if (confirm("确定删除吗？")) {
            console.info($(this).data("id"));
            $.ajax({
                url: categoryUrl + "/" + $(this).data("id"),
                type: "DELETE",
                success: function (data) {
                    console.log(data);
                    if (data.status === 'success') {
                        alert("删除成功");
                        initAreaList();
                    } else {
                        alert(data.data.errMsg);
                    }
                }
            });
        }
    });

    $(document).on("click", ".btn-edit-category", function () {
        categoryType = 2;
        $("#category-modal").modal("show");
        $("#btn-category").attr("data-type", 2);
        $.ajax({
            url: categoryUrl + "/" + $(this).data("id"),
            type: "GET",
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    $("#productCategoryId").val(data.data.productCategoryId);
                    $("#btn-category").attr("data-id", data.data.productCategoryId);
                    $("#productCategoryName").val(data.data.productCategoryName);
                    $("#productCategoryDesc").val(data.data.productCategoryDesc);
                    $("#priority").val(data.data.priority);
                    $("#shop-select option[value=" + data.data.shop.shopId + "]").attr("selected", "selected");
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $(document).on("click", ".page-num", function () {
        pn = $(this).data("id");
        initAreaList();
    });

});