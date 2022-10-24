(() => {

  // 預約狀態
  const gender = [
    { gender: 0, value: "男" },
    { gender: 1, value: "女" }
  ];

   // getById拿有預約該團課的會員
  const courseId = parseInt(sessionStorage.getItem('course'));
	fetch("/simplefitness-servlet/course/GetCourseMemberList", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      courseId: courseId
    }),
  })
	.then(resp => resp.json())
	.then(list => {
		successful = false;
    if (list != "" ) {
      successful = list[0].successful;
    }
    if (successful) {
      for (i = 0; i < list["length"]; i++){
        let gym = sessionStorage.getItem(`'gym${list[i].gymId}'`);
        let emp = sessionStorage.getItem(`'emp${list[i].empId}'`);
        let bookTime = moment(list[i].coursebookTime).format("YYYY-MM-DD HH:mm");
        let startTime = moment(list[i].startTime).format("YYYY-MM-DD HH:mm");
        let text = `
          <tr>
            <td>${list[i].memName}</td>
            <td>${gender[list[i].memGender].value}</td>
            <td>${list[i].memPhone}</td>
            <td>${bookTime}</td>
          </tr>`;
        document.querySelector('#gym').textContent = gym;
        document.querySelector('#startTime').textContent = startTime;
        document.querySelector('#name').textContent = list[i].courseName;
        document.querySelector('#emp').textContent = emp;
        $(".info").append(text);
      }
    } else {
      let text = `<div>目前還沒有人預約!</div>`;
      $(".info").append(text);
    }
	});

})();