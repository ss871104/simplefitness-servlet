$(function () {
    var mem_id;
    var bookingCourseList = [];
    var bookingCoachList = [];
    $(document).ready(function () {

        getMemberId();

        loadGymList();

        //**參數設定開始**************************************************************************/

        //SWAL按鈕設定
        swal.setDefaults({
            //確認按鈕顯示文字
            confirmButtonText: "取消預約",
            //取消按鈕顯示文字
            cancelButtonText: "返回",
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
                //點擊到團課類型
                if (date.type == "course") {
                    console.log(date)
                    swal({
                        title: "您的課程資訊",
                        html: "課程名稱:" + date.course + "<br>"
                            + "課程場館:" + date.gym + "<br>"
                            + "課程教練:" + date.emp + "<br>"
                            + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                        type: "info",//success,error,warning
                        showCancelButton: true//顯示取消按鈕
                    }).then(
                        function (result) {
                            //點擊確認事件
                            if (result.value) {
                                swal({
                                    title: "確定取消預約?",
                                    html: "課程名稱:" + date.course + "<br>"
                                        + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                                    type: "warning",//success,error,warning
                                    showCancelButton: true//顯示取消按鈕
                                }).then(
                                    function (result) {
                                        if (result.value) {
                                            cancelCourseBooking(date.resourse);
                                        }
                                    });//end then
                            }
                        });//end then
                }
                //點擊到教練課類型
                else if (date.type == "coach") {
                    console.log(date)
                    swal({
                        title: "您的課程資訊",
                        html: "課程教練:" + date.emp + "<br>"
                            + "課程場館:" + date.gym + "<br>"
                            + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                        type: "info",//success,error,warning
                        showCancelButton: true//顯示取消按鈕
                    }).then(
                        function (result) {
                            //點擊確認事件
                            if (result.value) {
                                swal({
                                    title: "確定取消預約?",
                                    html: "課程教練:" + date.emp + "<br>"
                                        + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                                    type: "warning",//success,error,warning
                                    showCancelButton: true//顯示取消按鈕
                                }).then(
                                    function (result) {
                                        if (result.value) {
                                            cancelCoachBooking(date.resourse);
                                        }
                                    });//end then
                            }
                        });//end then
                }

            },
            viewRender: function (view) {
                showCourseCalendarEvent(bookingCourseList);
                showCoachCalendarEvent(bookingCoachList);
            }

        });

        //月歷呈現團課程設定
        function showCourseCalendarEvent(courses) {
            courses.forEach(course => {
                var newEvent = new Object();
                newEvent.type = "course"
                newEvent.title = course.courseDetail.courseName;
                newEvent.course = course.courseDetail.courseName;
                newEvent.gym = course.courseDetail.gymName;
                newEvent.emp = course.courseDetail.empName;
                newEvent.start = moment(course.courseDetail.startTime).format();
                newEvent.allDay = false;
                newEvent.color = "#7ccc9e"
                newEvent.resourse = course;
                $('#example').fullCalendar('renderEvent', newEvent);

            });
        }

        //月歷呈現教練課程設定
        function showCoachCalendarEvent(coaches) {
            coaches.forEach(coach => {
                var newEvent = new Object();
                newEvent.type = "coach"
                newEvent.title= coach.coachbookStatus==1? "待審核 \n"+coach.coachClass.empName:coach.coachClass.empName;
                newEvent.gym = coach.coachClass.gymName;
                newEvent.emp = coach.coachClass.empName;
                newEvent.start = moment(coach.coachClass.startTime).format();
                newEvent.allDay = false;
                newEvent.color = coach.coachbookStatus==1?"#ff85a7" : "#83c2e5"
                newEvent.resourse = coach;
                $('#example').fullCalendar('renderEvent', newEvent);

            });
        }

        //刷新日曆
        function setEventToCalendar() {
            //step1 清空日歷
            $('#example').fullCalendar('removeEvents')
            console.log("清空日曆")

            //step2.1 取得教練課 並塞值
            getMemberCoachList();

            //step2.2 取得團課 並塞值
            getMemberCourseList();
        }

        //**參數設定結束**************************************************************************/

        //取得登入會員資料
        function getMemberId() {
            $.ajax({
                url: "http://localhost:8080/simplefitness-servlet/member/session",
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
                url: "http://localhost:8080/simplefitness-servlet/gym/getAllGym",
                type: "GET",
                dataType: "json",
                success: function (data) {
                    var optionStr = "";
                    data.forEach(gym => optionStr += '<option value="' + gym.gymId + '">' + gym.gymName + '</option>');
                    $("#classLocation").empty();
                    $("#classLocation").append('<option value="">選擇場館</option>');
                    $('#classLocation').append(optionStr);
                }
            })
        }

        //查詢按鈕觸發事件
        $("#queryBooking").click(function () {
            if (!$("#classLocation").val()) {
                swal("提醒~", "未選擇場館，請選擇欲查詢您的預約場館~", "warning");

            } else {
                //取得此會員預約課程清單
                setEventToCalendar();
            }
        })

        //點擊登出觸發事件
        $("#logout").click(function(){
            logout();
        })

        //取得此會員已預約團課清單
        function getMemberCourseList() {
            $.ajax({
                url: "http://localhost:8080/simplefitness-servlet/courseBooking/CheckCourseServlet",
                type: "POST",
                data: JSON.stringify({
                    gymId: $('#classLocation').val(),
                    memId: mem_id
                }),
                // async: false,
                contentType: 'application/json; charset=UTF-8',
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    bookingCourseList = data;
                    showCourseCalendarEvent(data);
                }
            });
        }

        //取得此會員已預約教練課清單
        function getMemberCoachList() {
            $.ajax({
                url: "http://localhost:8080/simplefitness-servlet/coachBooking/CheckCoachBookingByMemberIdServlet",
                type: "POST",
                data: JSON.stringify({
                    gymId: $('#classLocation').val(),
                    memId: mem_id
                }),
                // async: false,
                contentType: 'application/json; charset=UTF-8',
                dataType: "json",
                success: function (data) {

                    console.log("取得教練課預約清單");
                    console.log(data);
                    bookingCoachList = data;
                    showCoachCalendarEvent(data);
                }
            });
        }

        //取消團課預約
        function cancelCourseBooking(item) {
            
            if (moment(item.courseDetail.startTime).isAfter(moment().format('YYYY/MM/DD HH:mm:ss'))) {
                $.ajax({
                    url: "http://localhost:8080/simplefitness-servlet/courseBooking/CancelCourseServlet",
                    type: "POST",
                    data: JSON.stringify({
                        coursebookId: item.coursebookId,
                        courseId: item.courseId,
                        gymId: item.courseDetail.gymId,
                        memId: mem_id
                    }),
                    // async: false,
                    contentType: 'application/json; charset=UTF-8',
                    dataType: "json",
                    success: function (data) {
                        console.log(data)
                        if (data) {
                            swal({
                                title: "已取消預約",
                                html: "太可惜拉!",
                                type: "error",//success,error,warning
                                confirmButtonText: "確認",
                            });
                        }
                        setEventToCalendar()
                    }
                });
            }
            else {
                swal({
                    title: "不可取消預約",
                    html: "已過可取消時間!",
                    type: "error",//success,error,warning
                    confirmButtonText: "確認",
                });
            }

        }

        //取消教練課預約
        function cancelCoachBooking(item) {
            console.log(item)
            if (moment(item.coachClass.startTime).isAfter(moment().format('YYYY/MM/DD HH:mm:ss'))) {
                $.ajax({
                    url: "http://localhost:8080/simplefitness-servlet/coachBooking/CancelCoachServlet",
                    type: "POST",
                    data: JSON.stringify({
                        coachbookId: item.coachbookId,
                        coachId:item.coachId,
                        empId:item.coachClass.empId,
                        memId: mem_id,
                    }),
                    // async: false,
                    contentType: 'application/json; charset=UTF-8',
                    dataType: "json",
                    success: function (data) {
                        console.log(data)
                        if (data) {
                            console.log(data)
                            swal({
                                title: "已取消預約",
                                html: "太可惜拉!",
                                type: "error",//success,error,warning
                                confirmButtonText: "確認",
                            });
                        }
                        setEventToCalendar()
                    }
                });
            }
            else {
                swal({
                    title: "不可取消預約",
                    html: "已過可取消時間!",
                    type: "error",//success,error,warning
                    confirmButtonText: "確認",
                });
            }

        }

        

    });
})