$(function () {

    function showUser() {
        $.ajax({
            url: "/getUser",
            type: "GET",
            success: function (data) {
                if (data.status == 'success') {
                    showUserInfo(data);
                }
            }
        });

        $.ajax({
            url: "/shopadmin/getshopcount",
            type: "GET",
            success: function (data) {
                console.info(data);
                if (data.status == 'success') {
                    $("#btn-shop-count").text(data.data);
                }
            }
        });
    }

    function showUserInfo(data) {
        $("#user-edit-id").val(data.data.userId);
        $("#user-modify-name").val(data.data.name);
        $("#user-id").val(data.data.userId);
        $("#user-img-card").attr("src", data.data.profileImg);
        $("#user-name-card").text(data.data.name);
        $("#user-gender").val(data.data.gender == 1 ? "男" : "女");
        $("#user-phone").val(data.data.phone);
        $("#user-email").val(data.data.email);
    }

    showUser();

    $("#btn-update-user").click(function () {
        $.ajax({
            url: "/user/modifyuser",
            type: "POST",
            data: {userInfo: JSON.stringify(returnFromJson("#update-user-from"))},
            success: function (data) {
                console.log(data);
                if (data.status === 'success') {
                    alert("更新成功");
                    showUser();
                } else {
                    alert(data);
                }
            }
        });
    });

    $("#btn-modify-user").click(function () {
        console.info($("#edit-password").serialize());
        $.ajax({
            url: "/user/modifypassword",
            type: "POST",
            data: $("#edit-password").serialize(),
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    alert("更新成功");
                    $("#modify-password").modal("hide");
                    showUser();
                } else {
                    alert(data);
                }
            }
        })
    });

});
