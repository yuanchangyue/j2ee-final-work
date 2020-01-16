function returnFromJson(ele) {
    var fromSerializeData = $(ele).serializeArray();
    var data = {};
    $(fromSerializeData).each(function (index, obj) {
        data[obj.name] = obj.value;
    });
    console.info(data);
    return data;
}

var loginOutUrl = '/loginout';

$("#btn-out").click(function () {
    $.ajax({
        url: loginOutUrl,
        type: "GET",
        success: function () {
            alert("已经退出");
            window.location.href = '/tologin';
        }
    });
});

function showUser() {
    $.ajax({
        url: '/getUser',
        type: 'GET',
        success: function (data) {
            if (data.status == 'success') {
                $("#user-name").text(data.data.name);
                $("#user-img").attr("src", data.data.profileImg);
            }
        }
    });
}

showUser();

/*分页*/
function showNavigation(data) {

    $("#navigation-content").empty();

    $("#page-data-size").text(data.total);
    $("#page-data-num").text(data.size);

    var startPageHtml = '';
    var navigationHtml = '';
    var endPageHtml = '';

    if (data.hasPreviousPage) {
        startPageHtml = '<li class="page-item"><a class="page-link page-num" href="#" data-id="' + data.firstPage + '">首页</a></li>';
    }

    $.each(data.navigatepageNums, function (index, item) {
        if (item === data.pageNum) {
            navigationHtml += '<li class="page-item active"><a class="page-link page-num" href="#" data-id="' + item + '">' + item + '</a></li>';
        } else {
            navigationHtml += '<li class="page-item"><a class="page-link page-num" href="#" data-id="' + item + '" >' + item + '</a></li>';
        }
    });

    if (data.hasNextPage) {
        endPageHtml = '<li class="page-item"><a class="page-link page-num" href="#" data-id="' + data.lastPage + '">尾页</a></li>';
    }

    $("#navigation-content").append(startPageHtml).append(navigationHtml).append(endPageHtml);
}

function changeDateFormTime(time) {
    var date = new Date(time);
    var y = date.getFullYear();
    var m = date.getMonth();
    var d = date.getDay();
    return y + "-" + m;
}

window.ParsleyConfig = $.extend(window.ParsleyConfig || {}, {
    successClass: 'alert-success',
    errorClass: 'alert-danger',
    errorsWrapper: '<div></div>',
    errorTemplate: '<br><p class="text-danger"></p>'
});


