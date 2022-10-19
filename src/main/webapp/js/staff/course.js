const gym = document.querySelector("#gym");
const selectedDate = document.querySelector("#dateRange");
const editGym = document.querySelector("#editGym");
const editCoach = document.querySelector("#editCoach");
const editCourseDate = document.querySelector("#editCourseDate");
const editStartTime = document.querySelector("#editStartTime");
const editCourseName = document.querySelector("#editCourseName");
const editStatus = document.querySelector("#editStatus");
const editPubStatus = document.querySelector("#editPubStatus");
let INDEX = -1;
let CourseList = [];
(() => {


  // 預約狀態
  const statusList = [
    { status: 0, value: "已取消" },
    { status: 1, value: "可預約" },
    { status: 2, value: "不可預約" },
    { status: 3, value: "額滿" }
  ];

  // 公開狀態
  const publicStatusList = [
    { status: 0, value: "未公開" },
    { status: 1, value: "公開" }
  ];

  // 拿場館
  fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
    .then((resp) => resp.json())
    .then((gym) => {
      for (i = 0; i < gym["length"]; i++) {
        sessionStorage.setItem(`'gym${gym[i].gymId}'`, `${gym[i].gymName}`);
        let text = `
				<option value="${gym[i].gymId}" id="gym${gym[i].gymId}">${gym[i].gymName}</option>`;
        $("#gym").append(text);
        $("#newGym").append(text);
        $("#editGym").append(text);
      }
    });

  // 拿課程名稱
  fetch("http://localhost:8080/simplefitness-servlet/courseList/getAllCourse")
    .then((resp) => resp.json())
    .then((courseList) => {
      for (i = 0; i < courseList["length"]; i++) {
        sessionStorage.setItem(
          `'courseList${courseList[i].courseListId}'`,
          `${courseList[i].courseName}`
        );
        let text = `
				<option value="${courseList[i].courseListId}">${courseList[i].courseName}</option>`;
        $("#newCourseName").append(text);
        $("#editCourseName").append(text);
      }
    });

  // 拿教練名稱
  fetch(
    "http://localhost:8080/simplefitness-servlet/coachBooking/SearchCoachByJobServlet",
    {
      method: "POST",
    }
  )
    .then((resp) => resp.json())
    .then((emp) => {
      for (i = 0; i < emp["length"]; i++) {
        sessionStorage.setItem(`'emp${emp[i].empId}'`, `${emp[i].empName}`);
        let text = `
				<option value="${emp[i].empId}">${emp[i].empName}</option>`;
        $("#newCoach").append(text);
        $("#editCoach").append(text);
      }
    });

  // 新增團課拿預約狀態
  for (i = 0; i < statusList.length; i++) {
    let text = `
    <option value="${statusList[i].status}">${statusList[i].value}</option>`;
    $("#newStatus").append(text);
    $("#editStatus").append(text);
  }

  // 新增團課拿公開狀態
  for (i = 0; i < publicStatusList.length; i++) {
    let text = `
    <option value="${publicStatusList[i].status}">${publicStatusList[i].value}</option>`;
    $("#newPubStatus").append(text);
    $("#editPubStatus").append(text);
  }

  // 查詢團課
  document.getElementById("queryCourse").addEventListener("click", () => {
    if (gym.value == 0) {
      alert("請選擇場館");
      return;
    }
    if (selectedDate.value == "") {
      alert("請選擇日期");
      return;
    }

    // 列出現有課程
    fetch("http://localhost:8080/simplefitness-servlet/course/searchCourse", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        gymId: gym.value,
        selectedDate: selectedDate.value,
      }),
    })
      .then((resp) => resp.json())
      .then((course) => {
        CourseList = course
        $(".contentBody").empty();
        successful = false;
        if (course != "") {
          successful = course[0].successful;
        }
        if (successful) {
          for (i = 0; i < course["length"]; i++) {
            let gymName = sessionStorage.getItem(`'gym${course[i].gymId}'`);
            let courseName = sessionStorage.getItem(`'courseList${course[i].courseListId}'`);
            let empName = sessionStorage.getItem(`'emp${course[i].empId}'`);
            let date = moment(course[i].startTime).format("YYYY-MM-DD");
            let dayOfWeek = moment(course[i].startTime).format("dddd");
            let startTime = moment(course[i].startTime).format("HH:mm");
            let endTime = moment(course[i].startTime)
              .add(1, "hours")
              .format("HH:mm");

            let textWithHead = `
            <p>
                <button class="btn btn-block" id="btndate" type="button" data-toggle="collapse" data-target="#courList${date}">
                ${date} ${dayOfWeek}
                </button>
              <div class="collapse" id="courList${date}">
                  <div class="card card-body">
                      <table class="table table-hover">
                          <thead>
                              <tr>
                                  <th scope="col">場館</th>
                                  <th scope="col">課程時間</th>
                                  <th scope="col">課程名稱</th>
                                  <th scope="col">教練</th>
                                  <th scope="col">預約狀態</th>
                                  <th scope="col">公開狀態</th>
                                  <th scope="col"></th>
                                  <th scope="col"></th>
                              </tr>
                          </thead>
                          <tbody id="courListBody${date}">
                            <tr id=course${course[i].courseId}>
                              <td>${gymName}</td>
                              <td>${startTime} - ${endTime}</td>
                              <td>${courseName}</td>
                              <td>${empName}</td>
                              <td>${statusList[course[i].status].value}</td>
                              <td>${publicStatusList[course[i].pubStatus].value}</td>
                              <td><button type="button" class="btn btn-secondary" id="edit${course[i].courseId}" value="${course[i].courseId}" data-toggle="modal" data-target="#editCourse">編輯</button></td>
                              <td><button type="button" class="btn btn-secondary" id="delete${course[i].courseId}" value="${course[i].courseId}" data-toggle="modal" data-target="#deleteCourse">刪除</button></td>
                            </tr>
                          </tbody>
                      </table>
                  </div>
              </div>
            </p>`;

            let text = `
            <tr id=course${course[i].courseId}>
              <td>${gymName}</td>
              <td>${startTime} - ${endTime}</td>
              <td>${courseName}</td>
              <td>${empName}</td>
              <td>${statusList[course[i].status].value}</td>
              <td>${publicStatusList[course[i].pubStatus].value}</td>
              <td><button type="button" class="btn btn-secondary" id="edit${course[i].courseId}" value="${course[i].courseId}" data-toggle="modal" data-target="#editCourse">編輯</button></td>
              <td><button type="button" class="btn btn-secondary" id="delete${course[i].courseId}" value="${course[i].courseId}" data-toggle="modal" data-target="#deleteCourse">刪除</button></td>
            </tr>`;

            // 如果日期標頭已存在就新增內容，不存在才新增標頭+內容
            if ($("#courList" + date).length > 0) {
              $("#courListBody" + date).append(text);
            } else {
              $(".contentBody").append(textWithHead);
            }

            // 點擊編輯
            let courseData = course[i];
            INDEX = i;
            const errMsg = document.querySelector("#editErrMsg");
            document.querySelector(`#edit${courseData.courseId}`).onclick = () => {

                let id = document.querySelector(`#course${courseData.courseId}`)

                let date = moment(courseData.startTime).format("YYYY-MM-DD");
                let startTime = moment(courseData.startTime).format("HH:mm");

                editStartTime.value = startTime;
                editCourseDate.value = date;
                editGym.value = courseData.gymId;
                editCoach.value = courseData.empId;
                editCourseName.value = courseData.courseListId.toString();
                editStatus.value = courseData.status;
                editPubStatus.value = courseData.pubStatus;

                
                
                // 點擊儲存
                document.querySelector("#editSave").onclick = () => {
          
                  // 檢查未填錯誤訊息
                  if (editGym.value == 0) {
                    alert("請選擇場館");
                    return;
                  }
                  if (editCoach.value == 0) {
                    alert("請選擇教練");
                    return;
                  }
                  if (editCourseDate.value == 0) {
                    alert("請選擇日期");
                    return;
                  }
                  if (editCourseName.value == 0) {
                    alert("請選擇課程名稱");
                    return;
                  }
                  if (editStatus.value == '') {
                    alert("請選擇預約狀態");
                    return;
                  }
                  if (editPubStatus.value == '') {
                    alert("請選擇公開狀態");
                    return;
                  }

                  courseData.startTime = editCourseDate.value + "T" + editStartTime.value;
                  courseData.gymId = editGym.value;
                  courseData.empId = editCoach.value;
                  courseData.courseListId = editCourseName.value;
                  courseData.status = editStatus.value;
                  courseData.pubStatus = editPubStatus.value;

                  let [
                    empId,
                    gymId,
                    courseListId,
                    startTime,
                    status,
                    pubStatus,
                    courseId
                  ] = [
                    courseData.empId,
                    courseData.gymId,
                    courseData.courseListId,
                    courseData.startTime,
                    courseData.status,
                    courseData.pubStatus,
                    courseData.courseId,
                  ];
                 
                  fetch("http://localhost:8080/simplefitness-servlet/course/editCourse",
                    {
                      method: "POST",
                      headers: { "Content-Type": "application/json" },
                      body: JSON.stringify({
                        empId,
                        gymId,
                        courseListId,
                        startTime,
                        status,
                        pubStatus,
                        courseId
                      }),
                    }
                  )
                    .then((resp) => resp.json())
                    .then((body) => {
                      errMsg.textContent = "";
                      const { successful, message } = body;
                      if (successful) {
                        alert("編輯成功 ^_^!");

                        let gymName = sessionStorage.getItem(`'gym${body.gymId}'`);
                        let courseName = sessionStorage.getItem(`'courseList${courseListId}'`);
                        let empName = sessionStorage.getItem(`'emp${body.empId}'`);
                        let statusData = statusList[body.status].value;
                        let public = publicStatusList[body.pubStatus].value;
                        let StartTime = moment(startTime).format("HH:mm");
                        let endTime = moment(startTime).add(1, "hours").format("HH:mm");
                
                        id.innerHTML = EditTemplate(id,gymName,StartTime,endTime,courseName,empName,statusData,public)
                        CloseAlert('editCourse');
                        errMsg.textContent = "";
                      } else {
                        errMsg.textContent = message;
                      }
                    });
                };
              };
            
              // 點擊刪除
              document.querySelector(`#delete${course[i].courseId}`).onclick = () => {
                // 確定刪除
                document.querySelector("#delete").onclick = () => {
               
                  fetch(
                    "http://localhost:8080/simplefitness-servlet/course/deleteCourse",
                    {
                      method: "POST",
                      headers: { "Content-Type": "application/json" },
                      body: JSON.stringify({
                        courseId: courseData.courseId
                      }),
                    }
                  )
                    .then((resp) => resp.json())
                    .then((body) => {
                      errMsg.textContent = "";
                      const { successful, message } = body;
                      if (successful) {
                        document.querySelector(`#course${body.courseId}`).innerHTML = '';
                        CloseAlert('deleteCourse')
                        alert("刪除成功 ^_^!");
                      } else {
                        errMsg.textContent = message;
                      }
                    });
                }
            }
              
          }
          $("#courList" + selectedDate.value).collapse("show");
        } else {
          let text = `<div>這週還沒有安排團課唷!</div>`;
          $(".contentBody").append(text);
        }
      });
  });

  // 點擊新增
  const newGym = document.querySelector("#newGym");
  const newCoach = document.querySelector("#newCoach");
  const newCourseDate = document.querySelector("#newCourseDate");
  const newStartTime = document.querySelector("#newStartTime");
  const newCourseName = document.querySelector("#newCourseName");
  const newStatus = document.querySelector("#newStatus");
  const newPubStatus = document.querySelector("#newPubStatus");
  const errMsg = document.querySelector("#errMsg");

  document.getElementById("add").addEventListener("click", () => {
    // 檢查未填錯誤訊息
    if (newGym.value == 0) {
      alert("請選擇場館");
      return;
    }
    if (newCoach.value == 0) {
      alert("請選擇教練");
      return;
    }
    if (newCourseDate.value == 0) {
      alert("請選擇日期");
      return;
    }
    if (newCourseName.value == 0) {
      alert("請選擇課程名稱");
      return;
    }
    if (newStatus.value == '') {
      alert("請選擇預約狀態");
      return;
    }
    if (newPubStatus.value == '') {
      alert("請選擇公開狀態");
      return;
    }

    let start_time = newCourseDate.value + "T" + newStartTime.value;
    fetch("http://localhost:8080/simplefitness-servlet/course/addCourse", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        gymId: newGym.value,
        empId: newCoach.value,
        startTime: start_time,
        courseListId: newCourseName.value,
        status: newStatus.value,
        pubStatus: newPubStatus.value,
      }),
    })
      .then((resp) => resp.json())
      .then((body) => {
        errMsg.textContent = "";
        const { successful, message } = body;
        if (successful) {
          alert("新增成功 ^_^!");
          history.go();
        } else {
          errMsg.textContent = message;
        }
      });
  });
})();


function EditTemplate(id,gymName,startTime,endTime,courseName,empName,statusList,public){
  return`<td>${gymName}</td>
  <td>${startTime} - ${endTime}</td>
  <td>${courseName}</td>
  <td>${empName}</td>
  <td>${statusList}</td>
  <td>${public}</td>
  <td><button type="button" class="btn btn-secondary" id="edit${id}" value="${id}" data-toggle="modal" data-target="#editCourse">編輯</button></td>
  <td><button type="button" class="btn btn-secondary" id="delete${id}" value="${id}" data-toggle="modal" data-target="#deleteCourse">刪除</button></td>`
}

function CloseAlert(id){
  // editCourse
  // deleteCourse
  document.querySelector(`#${id}`).style.display = 'none'
  document.querySelector('.modal-backdrop').remove()
}