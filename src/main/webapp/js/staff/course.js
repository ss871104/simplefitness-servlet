(() => {

  const gym = document.querySelector("#gym");
  const selectedDate = document.querySelector("#dateRange");
  // 預約狀態
  const statusList = [
    {status: 0, value: "已取消"},
    {status: 1, value: "可預約"},
    {status: 2, value: "不可預約"},
    {status: 3, value: "額滿"}
  ];

  // 公開狀態
  const publicStatusList = [
    {status: 0, value: "未公開"},
    {status: 1, value: "公開"}
  ];
  
  // 拿場館
  fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
    .then((resp) => resp.json())
    .then((gym) => {
      for (i = 0; i < gym["length"]; i++) {
        sessionStorage.setItem(`'gym${gym[i].gymId}'`, `${gym[i].gymName}`);
        let text = `
				<option value="${gym[i].gymId}">${gym[i].gymName}</option>`;
        $("#gym").append(text);
        $("#newGym").append(text);
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
      }
    });

  // 拿教練名稱
  fetch("http://localhost:8080/simplefitness-servlet/coachBooking/SearchCoachByJobServlet", {
    method: "POST"
  })
    .then((resp) => resp.json())
    .then((emp) => {
      for (i = 0; i < emp["length"]; i++) {
        sessionStorage.setItem(`'emp${emp[i].empId}'`, `${emp[i].empName}`);
        let text = `
				<option value="${emp[i].empId}">${emp[i].empName}</option>`;
        $("#newCoach").append(text);
      }
    });

  // 新增團課拿預約狀態
  for (i = 0; i < statusList.length; i++) {
    let text = `
    <option value="${statusList[i].status}">${statusList[i].value}</option>`;
    $("#newStatus").append(text); 
  };

  // 新增團課拿公開狀態
  for (i = 0; i < publicStatusList.length; i++) {
    let text = `
    <option value="${publicStatusList[i].status}">${publicStatusList[i].value}</option>`;
    $("#newPubStatus").append(text); 
  };


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
        $(".contentBody").empty();
        successful = false;
        if (course != "") {
          successful = course[0].successful;
        }
        if (successful) {
          for (i = 0; i < course["length"]; i++) {
            let gymName = sessionStorage.getItem(`'gym${course[i].gymId}'`);
            let courseName = sessionStorage.getItem(
              `'courseList${course[i].courseListId}'`
            );
            let empName = sessionStorage.getItem(`'emp${course[i].empId}'`);
            let date = moment(course[i].startTime).format("YYYY-MM-DD");
            let dayOfWeek = moment(course[i].startTime).format("dddd");
            let startTime = moment(course[i].startTime).format("HH:mm");
            let endTime = moment(course[i].startTime)
              .add(1, "hours")
              .format("HH:mm");

            let text = `
            <p>
                <button class="btn btn-block" id="btndate" type="button" data-toggle="collapse" data-target="#courList${date}">${date} ${dayOfWeek}
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
                            <tr>
                            <td>${gymName}</td>
                            <td>${startTime} - ${endTime}</td>
                            <td>${courseName}</td>
                            <td>${empName}</td>
                            <td>${statusList[course[i].status].value}</td>
                            <td>${publicStatusList[course[i].pubStatus].value}</td>
                            <td><img class="listIcon" src="../../img/pencil_black.png" alt="編輯" title="編輯"></td>
                            <td><img class="listIcon" src="../../img/trash_black.png" alt="刪除" title="刪除"></td>
                            </tr>
                          </tbody>
                      </table>
                  </div>
              </div>
            </p>`;

            let textWithoutBtn = `
            <tr>
              <td>${gymName}</td>
              <td>${startTime} - ${endTime}</td>
              <td>${courseName}</td>
              <td>${empName}</td>
              <td>${statusList[course[i].status].value}</td>
              <td>${publicStatusList[course[i].pubStatus].value}</td>
              <td><img class="listIcon" src="../../img/pencil_black.png" alt="編輯" title="編輯"></td>
              <td><img class="listIcon" src="../../img/trash_black.png" alt="刪除" title="刪除"></td>
            </tr>`;
            
            // 如果日期標頭已存在就新增內容，不存在才新增標頭+內容
            if($("#courList2022-09-04").length > 0) {
              $("#courListBody2022-09-04").append(textWithoutBtn);
            } else {
              $(".contentBody").append(text);
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
  const newGym = document.querySelector('#newGym');
  const newCoach = document.querySelector('#newCoach');
  const newCourseDate = document.querySelector('#newCourseDate');
  const newStartTime = document.querySelector('#newStartTime');
  const newCourseName = document.querySelector('#newCourseName');
  const newStatus = document.querySelector('#newStatus');
  const errMsg = document.querySelector('#errMsg');

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
    
    let start_time = newCourseDate.value + 'T' + newStartTime.value;
		fetch('http://localhost:8080/simplefitness-servlet/course/addCourse', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				gymId: newGym.value,
				empId: newCoach.value,
				startTime: start_time,
				courseListId: newCourseName.value,
				status: newStatus.value,
				pubStatus: newPubStatus.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
        errMsg.textContent = '';
				const { successful, message } = body;
        console.log(successful);
        if(successful) {
          alert('新增成功 ^_^!')
		      history.go()
        } else {
					errMsg.textContent = message;
				}
			});
    
  });

})();
