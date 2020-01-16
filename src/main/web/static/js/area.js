$(function () {

    var getAreaListUrl = '/shopadmin/getarealist';
    var addAreaUrl = '/shopadmin/registerarea';
    var deleteAreaUrl = '/shopadmin/deletearea';
    var modifyAreaUrl = '/shopadmin/modifyarea';
    var getAreaUrl = '/shopadmin/getarea';
    var pn = 1;

    function showAreaList(data) {
        console.info(data);
        var tempHtml = '';
        $.each(data, function (index, item) {
            tempHtml +=
                '<tr>' +
                '<td class=" align-middle text-center">' +
                '    <span class="user-initials bg-success-light25 text-success">' + (index + 1) + '</span>' +
                '</td>' +
                '<td class="align-middle text-center">' + '' +
                '<a href="#" class="weight-400">' + item.areaName + '</a>' +
                '</td>' +
                '<td class="align-middle text-center">' +
                '    <div class="weight-400">' + item.areaDesc + '</div>' +
                '</td>' +
                '<td class="align-middle text-center"><span class="material-icons align-middle md-18 text-danger">expand_more</span>' + item.priority + '' +
                '</td>' +
                '<td class="align-middle text-center">' + changeDateFormTime(item.createTime) + '</td>' +
                '<td class="align-middle text-center">' +
                '<button type="button" data-toggle="modal" data-target="#editArea" class="btn mb-2 m-1 btn-sm circle btn-primary btn-edit-area" data-id="' + item.areaId + '">编辑</button>' +
                '<button type="button"  class="btn mb-2 m-1 btn-sm circle btn-danger btn-delete-area" data-id="' + item.areaId + '">删除</button>' +
                '</td>' +
                '</tr>';
        });
        $("#area-tbody").html(tempHtml);
    }

    function initAreaList() {
        $("#addArea form")[0].reset();
        $.ajax({
            url: getAreaListUrl,
            type: 'POST',
            data: {
                pn: pn, areaName: $("#input-area-name").val()
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

    $("#btn-add-area").click(function () {
        $('#area-form').parsley().validate();
        $.ajax({
            url: addAreaUrl,
            type: "POST",
            data: JSON.stringify(returnFromJson("#area-form")),
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                console.log(data);
                if (data.status === 'success') {
                    alert("添加成功");
                    $("#addArea").modal('hide');
                    initAreaList();
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $("#btn-search-area").click(function () {
        initAreaList();
    });

    $("#btn-edit-area").click(function () {
        $('#area-edit-form').parsley().validate();
        $.ajax({
            url: modifyAreaUrl,
            type: "POST",
            data: JSON.stringify(returnFromJson("#area-edit-form")),
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                if (data.status === 'success') {
                    alert("更新成功");
                    $("#editArea").modal("hide");
                    initAreaList();
                } else {
                    alert(data.data.errMsg);
                }
            }
        });
    });

    $(document).on("click", ".btn-delete-area", function () {
        if (confirm("确定删除吗？")) {
            console.info($(this).data("id"));
            $.ajax({
                url: deleteAreaUrl,
                type: "POST",
                data: {
                    areaId: $(this).data("id")
                },
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

    $(document).on("click", ".btn-edit-area", function () {
        var id = $(this).data("id");
        $("#areaEditId").val(id);
        $.ajax({
            url: getAreaUrl,
            type: 'GET',
            data: {areaId: id},
            success: function (data) {
                console.info(data);
                if (data.status === 'success') {
                    $("#areaEditName").val(data.data.areaName);
                    $("#areaEditDesc").val(data.data.areaDesc);
                    $("#priorityEdit").val(data.data.priority);
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
