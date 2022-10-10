//登出
function logout(){
    $.ajax({
        url: "http://localhost:8080/simplefitness-servlet//member/logout",
            type: "GET",
            // async: false,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            success: function (data) {
            }
    })
}