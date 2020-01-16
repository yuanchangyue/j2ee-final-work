$(function () {
    var getShopCategoryListUrl = '/shopadmin/getshopcategorylist';
    var categoryUrl = '/shopadmin/shopcategory';
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
                '<a href="#" class="weight-400">' + item.shopCategoryName + '</a>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.shopCategoryDesc + '</div>' +
                '</td>' +
                '<td class="align-middle text-center"><span class="material-icons align-middle md-18 text-danger">expand_more</span>' + item.priority + '' +
                '</td>' +
                '<td class="align-middle text-center">' + changeDateFormTime(item.createTime) + '</td>' +
                '<td class="align-middle text-center">' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-primary btn-edit-category" data-id="' + item.shopCategoryId + '">编辑</button>' +
                '<button type="button" class="btn mb-2 m-1 btn-sm circle btn-danger btn-delete-category" data-id="' + item.shopCategoryId + '">删除</button>' +
                '</td>' +
                '</tr>';
        });
        $("#shop-category-tbody").html(tempHtml);
    }

    function initAreaList() {
        $("#category-form").parsley();

        $.ajax({
            url: getShopCategoryListUrl,
            type: 'POST',
            data: {
                pn: pn, shopCategoryName: $("#input-shop-category-name").val()
            },
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    showAreaList(data.data.list);
                    showNavigation(data.data);
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    }

    initAreaList();

    $("#btn-category").click(function () {
        var categoryType = $(this).data("type");
        console.log(categoryType);

        $.ajax({
            url: categoryUrl,
            type: categoryType === 1 ? "POST" : "PUT",
            data: JSON.stringify(returnFromJson("#category-form")),
            contentType: "application/json;charset=UTF-8",
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
        $("#category-modal form")[0].reset();
        $("#btn-category").attr("data-type", 1);
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

        $("#category-modal").modal("show");
        $("#btn-category").attr("data-type", 2);
        $.ajax({
            url: categoryUrl + "/" + $(this).data("id"),
            type: "GET",
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    $("#shopCategoryId").val(data.data.shopCategoryId);
                    $("#shopCategoryName").val(data.data.shopCategoryName);
                    $("#shopCategoryDesc").val(data.data.shopCategoryDesc);
                    $("#priority").val(data.data.priority);
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