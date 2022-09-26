(() => {
	// 一進來get all
	fetch("http://localhost:8080/simplefitness-servlet/staff/getAllEmp")
	.then(resp => resp.json())
	.then(emp => {
		for (i = 0; i < emp["length"]; i++){
			let job = "";
			if (emp[i].job == "0") {
				job = "行政"
			} else if (emp[i].job == "1") {
				job = "教練"
			}
			let status = "";
			if (emp[i].status == "0") {
				status = "離職"
			} else if (emp[i].status == "1") {
				status = "在職中"
			} else if (emp[i].status == "2") {
				status = "留停"
			}
		  let text = `
		  	<tr>
				<td>${emp[i].empName}</td>
				<td>${emp[i].nickname}</td>
				<td>${job}</td>
				<td>${emp[i].employDate}</td>
				<td>${status}</td>
				<td><button type="button" class="btn btn-secondary" id="emp${emp[i].empId}" value="${emp[i].empId}">進入</button></td>
	  		</tr>`;

			$(".info").append(text);
		}
		// 點擊進入
		for (i = 0; i < emp["length"]; i++){
		const button = document.querySelector(`#emp${emp[i].empId}`);
			button.addEventListener('click', () => {
				sessionStorage.setItem('emp', button.value);
				location = './employee_edit.html';
			});
		}
	});
	
	// 一進來get gym
	fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
	.then(resp => resp.json())
	.then(gym => {
		for (i = 0; i < gym["length"]; i++){
		  let text = `
		  	<option value="${gym[i].gymId}">${gym[i].gymName}</option>
		  `;

			$(".gym").append(text);
		}
	});
	

	// 點擊新增
	const name = document.querySelector('#name-input');
	const nickname = document.querySelector('#nickname-input');
	const username = document.querySelector('#username-input');
	const gym = document.getElementById('gym');
	const email = document.querySelector('#email-input');
	const job = document.getElementsByName('job');
	const gender = document.getElementsByName('gender');
	const birth = document.querySelector('#birth-input');
	const status = document.getElementsByName('status');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('add').addEventListener('click', () => {
		const accLength = username.value.length;
		if (accLength < 8 || accLength > 50) {
			errMsg.textContent = '帳號長度須介於8~50字元';
			return;
		}
		let selected_job;
		if (job[0].checked == true) {
			selected_job = job[0];
		} else if (job[1].checked == true) {
			selected_job = job[1];
		}
		if (selected_job == null) {
			errMsg.textContent = '職位未選';
			return;
		}
		let selected_gender;
		if (gender[0].checked == true) {
			selected_gender = gender[0];
		} else if (gender[1].checked == true) {
			selected_gender = gender[1];
		}
		if (selected_gender == null) {
			errMsg.textContent = '性別未選';
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
		fetch('http://localhost:8080/simplefitness-servlet/staff/addEmp', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				empName: name.value,
				nickname: nickname.value,
				username: username.value,
				gymId: gym.value,
				email: email.value,
				job: selected_job.value,
				gender: selected_gender.value,
				birth: birth.value,
				status: selected_status.value,
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './employee.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
	
})();