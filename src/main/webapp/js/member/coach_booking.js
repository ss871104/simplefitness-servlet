$(function () {
    var mem_id;
    var canBookingCoachList = [];
    $(document).ready(function () {

        getMemberId();

        loadGymList();

        loadCoachList();

        //**參數設定開始**************************************************************************/

        //SWAL按鈕設定
        swal.setDefaults({
            //確認按鈕顯示文字
            confirmButtonText: "確定",
            //取消按鈕顯示文字
            cancelButtonText: "取消",
            // 畫面鎖定設定
            allowOutsideClick: false
        });

        $("#example").fullCalendar({
            // 參數設定
            header: { // 頂部排版
                left: "prev,next today", // 左邊放置上一頁、下一頁和今天
                center: "title", // 中間放置標題
                right: "" // 右邊放置月、周、天
            },
            // defaultDate: "2022-09-12", // 起始日期
            weekends: true, // 顯示星期六跟星期日
            //editable: true,  // 啟動拖曳調整日期
            //allDay: true
            locale: 'zh-cn', //中文设置
            timeFormat: "HH:mm",
            eventClick: function (date, event, view) {
                swal({
                    title: "請確認是否預約",
                    html: "課程教練:" + date.emp + "<br>"
                        + "課程場館:" + date.gym + "<br>"
                        + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                    type: "info",//success,error,warning
                    showCancelButton: true//顯示取消按鈕
                }).then(
                    function (result) {
                        //點擊確認事件
                        if (result.value) {
                            checkBooking(date.resourse);
                            getCoachClassList();
                        }
                        else {
                            swal("尚未預約", "太可惜拉~", "warning");
                        }
                    });//end then
            },
            viewRender: function (view) {
                showCalendarEvent(canBookingCoachList);
            }
        });

        //月歷呈現課程設定
        function showCalendarEvent(coaches) {
            $('#example').fullCalendar('removeEvents')
            console.log("清空日曆")
            coaches.forEach(coach => {
                var newEvent = new Object();
                newEvent.title = coach.empName;
                newEvent.gym = coach.gymName;
                newEvent.emp = coach.empName;
                newEvent.start = moment(coach.startTime).format();
                newEvent.allDay = false;
                newEvent.color = "#ff85a7"
                newEvent.resourse = coach;
                $('#example').fullCalendar('renderEvent', newEvent);

            });
            console.log("填入日曆")
        }

        //**參數設定結束**************************************************************************/ 

        //取得登入會員資料
        function getMemberId() {
            $.ajax({
                url: "/simplefitness-servlet/member/session",
                type: "GET",
                dataType: "json",
                success: function (data) {
                    mem_id = data.memId;
                    // console.log(mem_id)
                }
            })
        }

        // 取得健身房下拉選單資訊
        function loadGymList() {
            $.ajax({
                url: "/simplefitness-servlet/gym/getAllGym",
                type: "GET",
                dataType: "json",
                success: function (data) {
                    
                    gymList=data;
                    console.log(gymList)
                    var optionStr = "";
                    data.forEach(gym => optionStr += '<option value="' + gym.gymId + '">' + gym.gymName + '</option>');
                    console.log(optionStr);
                    $("#classLocation").empty();
                    $("#classLocation").append('<option value="">選擇場館</option>');
                    $('#classLocation').append(optionStr);
                }
            })
        }

        // 取得教練課下拉選單資訊
        function loadCoachList() {
            $.ajax({
                url: "/simplefitness-servlet/coachBooking/SearchCoachByJobServlet",
                type: "POST",
                dataType: "json",
                success: function (data) {
                    var optionStr = "";
                    data.forEach(coach => optionStr += '<option value="' + coach.empId + '">' + coach.empName + '</option>');
                    console.log(optionStr);
                    $("#personalCoach").empty();
                    $("#personalCoach").append('<option value="">選擇教練</option>');
                    $('#personalCoach').append(optionStr);
                }
            })
        }

        //查詢按鈕觸發事件
        $("#queryBooking").click(function () {
            if (!$("#classLocation").val()) {
                swal("提醒~", "未選擇場館，請選擇欲預約場館~", "warning");

            } else if (!$("#personalCoach").val()) {
                swal("提醒~", "未選擇教練，請選擇欲預約教練~", "warning");
            } else {
                //取得可預約課程清單
                getCoachClassList();
            }
        })

        //點擊登出觸發事件
        $("#logout").click(function(){
            logout();
        })

        //取得可預約課程清單
        function getCoachClassList() {
            $.ajax({
                url: "/simplefitness-servlet/coachBooking/SearchCoachServlet",
                type: "POST",
                data: JSON.stringify({
                    gymId: $('#classLocation').val(),
                    empId: $('#personalCoach').val(),
                    memId: mem_id
                }),
                // async: false,
                contentType: 'application/json; charset=UTF-8',
                dataType: "json",
                success: function (data) {
                    // console.log(data)
                    canBookingCoachList = data;
                    showCalendarEvent(data);
                }
            });
        }

        //預約課程
        function checkBooking(item) {
            $.ajax({
                url: "/simplefitness-servlet/coachBooking/CreateCoachBookingServlet",
                type: "POST",
                data: JSON.stringify({
                    coachId: item.coaId,
                    empId: item.empId,
                    gymId: item.gymId,
                    coachbookStatus: "1",
                    memId: mem_id
                }),
                // async: false,
                contentType: 'application/json; charset=UTF-8',
                dataType: "json",
                success: function (data) {
                    if (data) {
                        console.log(data)
                        swal("已送出預約邀請", "太棒拉~", "success");
                        
                        getCoachClassList();
                    }
                    else {
                        swal("預約失敗", "太雖拉~", "error");
                    }
                }
            });
        }
    })
})