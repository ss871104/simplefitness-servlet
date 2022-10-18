const gym = document.querySelector("#gym");
const selectedCoach = document.querySelector("#coach");
const selectedDate = document.querySelector("#dateRange");
const editGym = document.querySelector("#editGym");
const editCoach = document.querySelector("#editCoach");
const editCourseDate = document.querySelector("#editCourseDate");
const editStartTime = document.querySelector("#editStartTime");
const editStatus = document.querySelector("#editStatus");
const editPubStatus = document.querySelector("#editPubStatus");
let INDEX = -1;
let CourseList = [];
(() => {


  // 預約狀態
  const statusList = [
    { status: 0, value: "已取消" },
    { status: 1, value: "可預約" },
    { status: 2, value: "已預約" }
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

  // 拿教練名稱
  fetch("http://localhost:8080/simplefitness-servlet/coachBooking/SearchCoachByJobServlet", {
    method: "POST"
    }
  )
    .then((resp) => resp.json())
    .then((emp) => {
      for (i = 0; i < emp["length"]; i++) {
        sessionStorage.setItem(`'emp${emp[i].empId}'`, `${emp[i].empName}`);
        let text = `
				<option value="${emp[i].empId}">${emp[i].empName}</option>`;
        $("#coach").append(text);
        $("#newCoach").append(text);
        $("#editCoach").append(text);
      }
    });

  // 新增教練課拿預約狀態
  for (i = 0; i < statusList.length; i++) {
    let text = `
    <option value="${statusList[i].status}">${statusList[i].value}</option>`;
    $("#newStatus").append(text);
    $("#editStatus").append(text);
  }

  // 新增教練課拿公開狀態
  for (i = 0; i < publicStatusList.length; i++) {
    let text = `
    <option value="${publicStatusList[i].status}">${publicStatusList[i].value}</option>`;
    $("#newPubStatus").append(text);
    $("#editPubStatus").append(text);
  }

  // 查詢教練課
  document.getElementById("queryCoach").addEventListener("click", () => {
    if (gym.value == 0) {
      alert("請選擇場館");
      return;
    }
    if (selectedDate.value == "") {
      alert("請選擇日期");
      return;
    }

    // 列出現有課程
    fetch("http://localhost:8080/simplefitness-servlet/coach/searchCoach", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        gymId: gym.value,
        selectedDate: selectedDate.value,
        empId: selectedCoach.value
      }),
    })
      .then((resp) => resp.json())
      .then((coach) => {
        CoachList = coach
        $(".contentBody").empty();
        successful = false;
        if (coach != "") {
          successful = coach[0].successful;
        }
        if (successful) {
          for (i = 0; i < coach["length"]; i++) {
            let gymName = sessionStorage.getItem(`'gym${coach[i].gymId}'`);
            let empName = sessionStorage.getItem(`'emp${coach[i].empId}'`);
            let date = moment(coach[i].startTime).format("YYYY-MM-DD");
            let dayOfWeek = moment(coach[i].startTime).format("dddd");
            let startTime = moment(coach[i].startTime).format("HH:mm");
            let endTime = moment(coach[i].startTime)
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
                                  <th scope="col">教練</th>
                                  <th scope="col">預約狀態</th>
                                  <th scope="col">公開狀態</th>
                                  <th scope="col"></th>
                                  <th scope="col"></th>
                              </tr>
                          </thead>
                          <tbody id="courListBody${date}">
                            <tr id=course${coach[i].coaId}>
                              <td>${gymName}</td>
                              <td>${startTime} - ${endTime}</td>
                              <td>${empName}</td>
                              <td>${statusList[coach[i].status].value}</td>
                              <td>${publicStatusList[coach[i].pubStatus].value}</td>
                              <td><button type="button" class="btn btn-secondary" id="edit${coach[i].coaId}" value="${coach[i].coaId}" data-toggle="modal" data-target="#editCoachData">編輯</button></td>
                              <td><button type="button" class="btn btn-secondary" id="delete${coach[i].coaId}" value="${coach[i].coaId}" data-toggle="modal" data-target="#deleteCoach">刪除</button></td>
                            </tr>
                          </tbody>
                      </table>
                  </div>
              </div>
            </p>`;

            let text = `
            <tr id=course${coach[i].coaId}>
              <td>${gymName}</td>
              <td>${startTime} - ${endTime}</td>
              <td>${empName}</td>
              <td>${statusList[coach[i].status].value}</td>
              <td>${publicStatusList[coach[i].pubStatus].value}</td>
              <td><button type="button" class="btn btn-secondary" id="edit${coach[i].coaId}" value="${coach[i].coaId}" data-toggle="modal" data-target="#editCoachData">編輯</button></td>
              <td><button type="button" class="btn btn-secondary" id="delete${coach[i].coaId}" value="${coach[i].coaId}" data-toggle="modal" data-target="#deleteCoach">刪除</button></td>
            </tr>`;

            // 如果日期標頭已存在就新增內容，不存在才新增標頭+內容
            if ($("#courList" + date).length > 0) {
              $("#courListBody" + date).append(text);
            } else {
              $(".contentBody").append(textWithHead);
            }

            // 點擊編輯
            let coachData = coach[i];
            INDEX = i;
            document.querySelector(`#edit${coach[i].coaId}`).onclick = () => {

                let id = document.querySelector(`#course${coachData.coaId}`)

                let date = moment(coachData.startTime).format("YYYY-MM-DD");
                let startTime = moment(coachData.startTime).format("HH:mm");

                editStartTime.value = startTime;
                editCourseDate.value = date;
                editGym.value = coachData.gymId;
                editCoach.value = coachData.empId;
                editStatus.value = coachData.status;
                editPubStatus.value = coachData.pubStatus;

                
                
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
                  if (editStatus.value == '') {
                    alert("請選擇預約狀態");
                    return;
                  }
                  if (editPubStatus.value == '') {
                    alert("請選擇公開狀態");
                    return;
                  }

                  coachData.startTime = editCourseDate.value + "T" + editStartTime.value;
                  coachData.gymId = editGym.value;
                  coachData.empId = editCoach.value;
                  coachData.status = editStatus.value;
                  coachData.pubStatus = editPubStatus.value;

                  let [
                    empId,
                    gymId,
                    startTime,
                    status,
                    pubStatus,
                    coaId
                  ] = [
                    coachData.empId,
                    coachData.gymId,
                    coachData.startTime,
                    coachData.status,
                    coachData.pubStatus,
                    coachData.coaId
                  ];

                  fetch(
                    "http://localhost:8080/simplefitness-servlet/coach/editCoach",
                    {
                      method: "POST",
                      headers: { "Content-Type": "application/json" },
                      body: JSON.stringify({
                        empId,
                        gymId,
                        startTime,
                        status,
                        pubStatus,
                        coaId
                      }),
                    }
                  )
                    .then((resp) => resp.json())
                    .then((body) => {
                      errMsg.textContent = "";
                      const { successful, message } = body;
                      if (successful) {
                        alert("編輯成功 ^_^!");

 
                        let gymName = sessionStorage.getItem(`'gym${CoachList[INDEX].gymId}'`);
                        let empName = sessionStorage.getItem(`'emp${CoachList[INDEX].empId}'`);
                        let statusData = statusList[CoachList[INDEX].status].value
                        let public = publicStatusList[CoachList[INDEX].pubStatus].value;
                        let StartTime = moment(startTime).format("HH:mm");
                        let endTime = moment(startTime).add(1, "hours").format("HH:mm");
                
                        id.innerHTML = EditTemplate(id,gymName,StartTime,endTime,empName,statusData,public)
                        CloseAlert('editCoachData')
                      } else {
                        errMsg.textContent = message;
                      }
                    });
                };
              };
            
            // 點擊刪除
            document.querySelector(`#delete${coach[i].coaId}`).onclick = () => {
              // 確定刪除
              document.querySelector("#delete").onclick = () => {
                fetch(
                  "http://localhost:8080/simplefitness-servlet/coach/deleteCoach",
                  {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                      coaId: coachData.coaId
                    }),
                  }
                )
                  .then((resp) => resp.json())
                  .then((body) => {
                    errMsg.textContent = "";
                    const { successful, message } = body;
                    if (successful) {
                      document.querySelector(`#course${body.coaId}`).innerHTML = '';
                      CloseAlert('deleteCoach')                      
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
          let text = `<div>這週還沒有安排教練課唷!</div>`;
          $(".contentBody").append(text);
        }
      });
  });

  // 點擊新增
  const newGym = document.querySelector("#newGym");
  const newCoach = document.querySelector("#newCoach");
  const newCourseDate = document.querySelector("#newCourseDate");
  const newStartTime = document.querySelector("#newStartTime");
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
    if (newStatus.value == '') {
      alert("請選擇預約狀態");
      return;
    }
    if (newPubStatus.value == '') {
      alert("請選擇公開狀態");
      return;
    }

    let start_time = newCourseDate.value + "T" + newStartTime.value;
    fetch("http://localhost:8080/simplefitness-servlet/coach/addCoach", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        gymId: newGym.value,
        empId: newCoach.value,
        startTime: start_time,
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


function EditTemplate(id,gymName,startTime,endTime,empName,statusList,public){
  return`<td>${gymName}</td>
  <td>${startTime} - ${endTime}</td>
  <td>${empName}</td>
  <td>${statusList}</td>
  <td>${public}</td>
  <td><button type="button" class="btn btn-secondary" id="edit${id}" value="${id}" data-toggle="modal" data-target="#editCoachData">編輯</button></td>
  <td><button type="button" class="btn btn-secondary" id="delete${id}" value="${id}" data-toggle="modal" data-target="#deleteCoach">刪除</button></td>`
}

function CloseAlert(id){
  // editCoachData
  // deleteCoach
  document.querySelector(`#${id}`).style.display = 'none'
  document.querySelector('.modal-backdrop').remove()
}