(() => {
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	});
	
	// GET session 出現資料庫資料
	var courseListId = sessionStorage.getItem('course');
	courseListId = parseInt(courseListId);
	console.log(courseListId);
	fetch("http://localhost:8080/simplefitness-servlet/courseList/getOneCourse", {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				courseListId: courseListId,
			}),
		})
			.then(resp => resp.json())
			.then(course => {
				document.querySelector('#name').textContent = course.courseName;
				document.querySelector('#count').textContent = course.courseMaxP;
				document.querySelector('#intro').textContent = course.courseIntro;
				if (course.courseStatus == "0") {
					document.querySelector('#status').textContent = "下架";
				} else if (course.courseStatus == "1") {
					document.querySelector('#status').textContent = "上架";
				} else {
					document.querySelector('#status').textContent = "";
				}

				document.querySelector('#name-input').value = course.courseName;
				document.querySelector('#count-input').value = course.courseMaxP;
				document.querySelector('#intro-input').value = course.courseIntro;
				if (course.courseStatus == "0") {
					document.getElementById("down-input").checked = true;
				} else if (course.courseStatus == "1") {
					document.getElementById("up-input").checked = true;
				}
				
			})

	// 編輯資料
	const Cname = document.querySelector('#name-input');
	const count = document.querySelector('#count-input');
	const intro = document.querySelector('#intro-input');
	const status = document.getElementsByName('status');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('edit').addEventListener('click', () => {
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
		fetch('http://localhost:8080/simplefitness-servlet/courseList/edit', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				courseName: Cname.value,
				courseMaxP: count.value,
				courseIntro: intro.value,
				courseStatus: selected_status.value,
				courseListId: courseListId
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './course_list_edit.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});

	document.getElementById('logout').addEventListener('click', () => {
		fetch("http://localhost:8080/simplefitness-servlet/member/logout")
			.then(body => {
				location = '../guest/home.html';
			});
	});
})();