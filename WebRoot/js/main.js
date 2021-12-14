$(document).ready(function() {
    $("#btQuery").click(
        function() {
            currentPage = 1;
            $("#currentPage").text(currentPage);
            query();
        }
    );
    $("#btClear").click(
        function() {
            clear();
        }
    );
});
var currentPage = 1;

function query() {
    $.ajax({
        type: "post",
        url: "queryUser.do",
        data: {
            username: $("#username").val(),
            chrname: $("#chrname").val(),
            province: $("#province").val(),
            page: $("#page").val(),
            currentPage: currentPage
        },
        dataType: "json",
        success: function(response) {
            console.log(response);
            var total = response.total; //整形
            var result = response.result; //数组
            $("#tbData").empty();
            //将数组result中每一个数据增加到表格的每一行
            for (var i = 0; i < result.length; i++) {
                var str = "<tr>";
                str += "<td><input type=checkbox></td>";
                str += "<td>" + result[i].username + "</td>";
                str += "<td>" + result[i].chrName + "</td>";
                str += "<td>" + result[i].provinceName + "</td>";
                str += "<td>" + result[i].cityName + "</td>";
                str += "<td><a href=''>删除</a><a href=''>修改</a></td>";
                str += "</tr>";

                $("#tbData").append(str);
            }
            //利用total 更新id=total的元素
            //计算总页数，更新id=totalpage的元素
            $("#total").text(total);
            var totalPage = Math.ceil(total / parseInt($("#page").val()));
            $("#totalPage").text(totalPage);
        }
    });
}

function first() {
    currentPage = 1;
    $("#currentPage").text(currentPage);
    query();
}

function prev() {
    if (currentPage > 1) {
        currentPage--;
        $("#currentPage").text(currentPage);
        query();
    }
}

function next() {
    var totalPage = parseInt($("#totalPage").text());
    if (currentPage < totalPage) {
        currentPage++;
        $("#currentPage").text(currentPage);
        query();
    }
}

function last() {
    var totalPage = parseInt($("#totalPage").text());
    currentPage = totalPage;
    $("#currentPage").text(currentPage);
    query();
}