$(function () {
    var emp_id;
    var CourseList = [];
    var bookingCoachList = [];
    $(document).ready(function () {

        getEmpId();

        //**參數設定開始**************************************************************************/

        //SWAL按鈕設定
        swal.setDefaults({
            //確認按鈕顯示文字
            confirmButtonText: "確認",
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
                        showCancelButton: false,//顯示取消按鈕
                    })

                }
                //點擊到教練課類型
                else if (date.type == "coach") {
                    console.log(date)
                    if (date.status == "1") {
                        swal({
                            title: "您的課程資訊",
                            html: "學員:" + date.mem + "<br>"
                                + "課程場館:" + date.gym + "<br>"
                                + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                            type: "info",//success,error,warning
                            confirmButtonText: "接受預約",
                            cancelButtonText: "婉拒預約",
                            showCancelButton: true,//顯示取消按鈕
                            // showcomebackButton: true//顯示返回按鈕
                        }).then(
                            function (result) {
                                if (result.value) {
                                    swal({
                                        title: "確定接受預約?",
                                        html: "學員:" + date.mem + "<br>"
                                            + "課程場館:" + date.gym + "<br>"
                                            + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                                        type: "warning",//success,error,warning
                                        showCancelButton: true//顯示取消按鈕
                                    }).then(
                                        function (result) {
                                            if (result.value) {
                                                acceptCoachBooking(date.resourse);
                                            }
                                        });//end then
                                } else {
                                    swal({
                                        title: "確定取消預約?",
                                        html: "學員:" + date.mem + "<br>"
                                            + "課程場館:" + date.gym + "<br>"
                                            + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                                        type: "warning",//success,error,warning
                                        showCancelButton: true,//顯示取消按鈕
                                        confirmButtonText: "取消預約",
                                    }).then(
                                        function (result) {
                                            if (result.value) {
                                                cancelCoachBooking(date.resourse);
                                            }
                                        });//end then
                                }

                            }
                        )
                    } else if (date.status == "2") {
                        console.log("狀態")
                        console.log(date.status)
                        swal({
                            title: "您的課程資訊",
                            html: "學員:" + date.mem + "<br>"
                                + "課程場館:" + date.gym + "<br>"
                                + "課程時間:" + moment(date.start).format("YYYY-MM-DD HH:mm"),
                            type: "info",//success,error,warning
                            showCancelButton: true,//顯示取消按鈕
                            confirmButtonText: "取消預約",
                        }).then(
                            function (result) {
                                if (result.value) {
                                    cancelCoachBooking(date.resourse);
                                }
                            });//end then
                    }
                }

            },
            viewRender: function (view) {
                showCourseCalendarEvent(CourseList);
                showCoachCalendarEvent(bookingCoachList);
            }

        });

        //月歷呈現團課程設定
        function showCourseCalendarEvent(courses) {
            courses.forEach(course => {
                var newEvent = new Object();
                newEvent.type = "course"
                newEvent.title = course.courseName;
                newEvent.course = course.courseName;
                newEvent.gym = course.gymName;
                newEvent.emp = course.empName;
                newEvent.start = moment(course.startTime).format();
                newEvent.allDay = false;
                newEvent.color = "#7ccc9e"
                newEvent.resourse = course;
                $('#example').fullCalendar('renderEvent', newEvent);

            });
        }

        //月歷呈現教練課程設定
        function showCoachCalendarEvent(coaches) {
            console.log(coaches)
            coaches.forEach(coach => {
                var newEvent = new Object();
                newEvent.type = "coach"
                newEvent.title = coach.coachbookStatus == 1 ? "待審核 \n" + coach.memberDetail.memName : coach.memberDetail.memName;
                newEvent.gym = coach.coachClass.gymName;
                // newEvent.emp = coach.coachClass.empName;
                newEvent.mem = coach.memberDetail.memName;
                newEvent.start = moment(coach.coachClass.startTime).format();
                newEvent.status = coach.coachbookStatus;
                newEvent.allDay = false;
                newEvent.color = coach.coachbookStatus == 1 ? "#ff85a7" : "#83c2e5"
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
            getEmpCoachList();

            //step2.2 取得團課 並塞值
            getEmpCourseList();
        }

        //**參數設定結束**************************************************************************/

        //取得登入員工資料
        function getEmpId() {
            $.ajax({
                url: "http://localhost:8080/simplefitness-servlet/staff/session",
                type: "GET",
                dataType: "json",
                success: function (data) {
                    if (data.job == "1") {
                        emp_id = data.empId;
                        getEmpCourseList();
                        getEmpCoachList();
                    } else {
                        swal({
                            title: "教練專區",
                            html: "您不是教練，不能進入教練專區喔!",
                            type: "error",//success,error,warning
                            confirmButtonText: "確認",
                        }).then(
                            function () {
                                window.location.href = "staff_home.html"
                            });
                    }
                }
            })
        }

        //點擊登出觸發事件
        $("#logout").click(function(){
            logout();
        })

        //取得此教練的團課清單
        function getEmpCourseList() {
            console.log("EMP:" + emp_id)
            $.ajax({
                url: "http://localhost:8080/simplefitness-servlet/courseBooking/CheckCourseByEmpIdServlet",
                type: "POST",
                data: JSON.stringify({
                    empId: emp_id
                }),
                // async: false,
                contentType: 'application/json; charset=UTF-8',
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    CourseList = data;
                    showCourseCalendarEvent(data);
                }
            });
        }

        //取得該教練的預約清單
        function getEmpCoachList() {
            $.ajax({
                url: "http://localhost:8080/simplefitness-servlet/coachBooking/CheckCoachBookingByEmpIdServlet",
                type: "POST",
                data: JSON.stringify({
                    empId: emp_id
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

        //接受預約，回覆教練課預約成功
        function acceptCoachBooking(item) {
            console.log(item)
            if (moment(item.coachClass.startTime).isAfter(moment().format('YYYY/MM/DD HH:mm:ss'))) {
                $.ajax({
                    url: "http://localhost:8080/simplefitness-servlet/coachBooking/AcceptCoachServlet",
                    type: "POST",
                    data: JSON.stringify({
                        coachbookId: item.coachbookId,
                        // coachId: item.coachId,
                        empId: emp_id,
                        memId: item.memId
                    }),
                    // async: false,
                    contentType: 'application/json; charset=UTF-8',
                    dataType: "json",
                    success: function (data) {
                        console.log(data)
                        if (data) {
                            console.log(data)
                            swal({
                                title: "已接受預約",
                                html: "太讚拉!",
                                type: "success",//success,error,warning
                                confirmButtonText: "確認",
                            });
                        }
                        setEventToCalendar()
                    }
                });
            }
            else {
                swal({
                    title: "不可審核預約",
                    html: "已過可審核時間!",
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
                    url: "http://localhost:8080/simplefitness-servlet/coachBooking/CancelCoachClassFromCoachServlet",
                    type: "POST",
                    data: JSON.stringify({
                        coachbookId: item.coachbookId,
                        coachId: item.coachId,
                        empId: emp_id,
                        memId: item.memId
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