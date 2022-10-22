//登出
function logout(){
    $.ajax({
        url: "/simplefitness-servlet/member/logout",
            type: "GET",
            // async: false,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            success: function (data) {
            }
    })
}