(() => {
	// 一進來get all
	fetch("http://localhost:8080/simplefitness-servlet/courseList/getAllCourse")
	.then(resp => resp.json())
	.then(course => {
		for (i = 0; i < course["length"]; i++){
		  let text = `
		  	<tr>
				<td>${course[i].courseListId}</td>
				<td>${course[i].courseName}</td>
				<td>${course[i].courseMaxP}</td>
				<td id="status${course[i].courseListId}"></td>
				<td><button type="button" class="btn btn-secondary" id="course${course[i].courseListId}" value="${course[i].courseListId}">進入</button></td>
	  		</tr>`;

			$(".info").append(text);
			
			if (course[i].courseStatus == "0") {
				document.querySelector(`#status${course[i].courseListId}`).textContent = "下架";
			} else if (course[i].courseStatus == "1") {
				document.querySelector(`#status${course[i].courseListId}`).textContent = "上架";
			} else {
				document.querySelector(`#status${course[i].courseListId}`).textContent = "";
			}
		}
		// 點擊進入
		for (i = 0; i < course["length"]; i++){
		const button = document.querySelector(`#course${course[i].courseListId}`);
			button.addEventListener('click', () => {
				sessionStorage.setItem('course', button.value);
				location = './course_list_edit.html';
			});
		}
	});

	

	// 點擊新增
	const name = document.querySelector('#name-input');
	const count = document.querySelector('#count-input');
	const intro = document.querySelector('#intro-input');
	const status = document.getElementsByName('status');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('add').addEventListener('click', () => {
		if (count.value == "") {
			errMsg.textContent = '人數上限未填';
			return;
		}
		let selected_status;
		if (status[0].checked == true) {
			selected_status = status[0];
		} else if (status[1].checked == true) {
			selected_status = status[1];
		}
		if (selected_status == null) {
			errMsg.textContent = '狀態未選';
			return;
		}
		fetch('http://localhost:8080/simplefitness-servlet/courseList/addCourseList', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				courseName: name.value,
				courseMaxP: count.value,
				courseintro: intro.value,
				courseStatus: selected_status.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './course_list.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
	
})();