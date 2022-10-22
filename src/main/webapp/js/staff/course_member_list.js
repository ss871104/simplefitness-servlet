(() => {

  // 預約狀態
  const gender = [
    { gender: 0, value: "男" },
    { gender: 1, value: "女" }
  ];

   // getById拿該堂課會員
  const courseId = sessionStorage.getItem('course');
	fetch("http://localhost:8080/simplefitness-servlet/course/GetCourseMemberList", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      courseId: courseId
    }),
  })
	.then(resp => resp.json())
	.then(list => {
		for (i = 0; i < list["length"]; i++){
      let bookTime = moment(list[i].coursebookTime).format("YYYY-MM-DD HH:mm");
		  let text = `
		  	<tr>
				<td>${list[i].memName}</td>
				<td>${gender[list[i].memGender].value}</td>
				<td>${list[i].memPhone}</td>
				<td>${bookTime}</td>
	  		</tr>`;

			$(".info").append(text);
		}
	
	});

})();