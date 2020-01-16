$(function () {
    var getShopListUrl = '/shopadmin/getshoplist';
    var pn = 1;
    $.ajax({
        url: '/getUser',
        type: 'GET',
        success: function (data) {
            if (data.status == 'success') {
                $("#user-name").text(data.data.name);
            }
        }
    });

    showUser();

    function showUser() {
        $.ajax({
            url: "/getUser",
            type: "GET",
            success: function (data) {
                if (data.status === 'success') {
                    showUserInfo(data);
                }
            }
        });
        $.ajax({
            url: "/shopadmin/getshopcount",
            type: "GET",
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    $("#btn-shop-count").text(data.data);
                }
            }
        });
    }

    function initShopList() {

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
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    }

    function showUserInfo(data) {
        $("#user-img-card").attr("src", data.data.profileImg);
        $("#user-name-card").text(data.data.name);
        $("#user-gender").text(data.data.gender == 1 ? "男" : "女");
        $("#user-email").text(data.data.email);
        $("#user-phone").text(data.data.phone);
    }

    function showShopList(data) {
        console.info(data);
        var tempHtml = '';
        $.each(data, function (index, item) {
            tempHtml += ' <tr>' +
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
                '</tr>';
        });
        $("#shop-tbody").html(tempHtml);
    }

    initShopList();

});
