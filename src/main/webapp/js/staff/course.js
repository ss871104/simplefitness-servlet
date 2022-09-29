(() => {

	// 拿場館
	fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
	.then(resp => resp.json())
	.then(gym => {
		for (i = 0; i < gym["length"]; i++){
			sessionStorage.setItem(`'gym${gym[i].gymId}'`,`${gym[i].gymName}`);
		  let text = `
				<option value="${gym[i].gymId}">${gym[i].gymName}</option>`;
			$("#gym").append(text);
		}
	});

	// 拿日期
	

	// document.querySelector('#dateRange').addEventListener('click', () => {
	// 	let gymId = gym.value; 
		
	// 	if (gymId == "") {
	// 		alert('請選擇場館');
	// 		return;
	// 	}
	// });

	// fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
	// .then(resp => resp.json())
	// .then(gym => {
	// 	for (i = 0; i < gym["length"]; i++){
	// 	  let text = `
	// 			<option value="${gym[i].gymId}">${gym[i].gymName}</option>`;

	// 		$("#gym").append(text);
	// 		// console.log(document.querySelector(`#gym${gym[i].gymId}`).value);
	// 	}
	// });
	
	
	const gym = document.querySelector('#gym');
	const startTime = document.querySelector('#courseDate');
	const search = document.querySelector('#queryCourse');

 
	document.getElementById('queryCourse').addEventListener('click', () => {
		let gymId = gym.value; 
		
		if (gymId == "") {
			alert('請選擇場館');
			return;
		}
		// 列出現有課程
		fetch("http://localhost:8080/simplefitness-servlet/course/searchCourse", {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				// TODO 改參數
				gymId: gymId,
				startTime: '2022-09-23T18:00:00'
			}),
		})
		.then(resp => resp.json())
		.then(course => {
			for (i = 0; i < course["length"]; i++){
				let gymName = sessionStorage.getItem(`'gym${course[i].gymId}'`)
				let endTime = startTime
				
				let text = `<tr>
							<td>${gymName}</td>
							<td>${course[i].startTime}, ${course[i].startTime}</td>
							<td>${course[i].courseListId}</td>
							<td>${course[i].empId}</td>
							<td>${course[i].status}</td>
							<td>${course[i].pubStatus}</td>
							<td><img class="listIcon" src="../../img/pencil_black.png" alt="編輯" title="編輯"></td>
							<td><img class="listIcon" src="../../img/trash_black.png" alt="刪除" title="刪除"></td>
						</tr>`;

				$("#courList").append(text);
			}
			// <td>${gym[i].gymName}</td>
			// 		<td>${course[i].start_time}-${course[i].end_time}</td>
			// // 點擊進入
			// for (i = 0; i < gym["length"]; i++){
			// const button = document.querySelector(`#gym${gym[i].gymId}`);
			// 	button.addEventListener('click', () => {
			// 		sessionStorage.setItem('gym', button.value);
			// 		location = './gym_edit.html';
			// 	});
			// }
		});
		
	});

	
	
	// fetch('http://localhost:8080/simplefitness-servlet/member/register', {
	// 		method: 'POST',
	// 		headers: { 'Content-Type': 'application/json' },
	// 		body: JSON.stringify({
	// 			memUsername: username.value,
	// 			memPassword: password.value,
	// 			memConfirmPass: confirm_pass.value,
	// 			memName: mem_name.value,
	// 			memEmail: email.value
	// 		}),
	// 	})
	// 		.then(resp => resp.json())
	// 		.then(body => {
	// 			errMsg.textContent = '';
	// 			const { successful, message } = body;
	// 			if (successful) {
	// 				window.alert("註冊成功！跳轉至登入畫面");
	// 				location = './login.html';
	// 			} else {
	// 				errMsg.textContent = message;
	// 			}
	// 		});
	


	
	
	// document.getElementById('password').addEventListener('input', () => {
	// 	const pwdLength = password.value.length;
	// 	if (pwdLength < 8 || pwdLength > 50) {
	// 		errMsg.textContent = '密碼長度須介於8~50字元';
	// 		return;
	// 	} else {
	// 		errMsg.textContent = '';
	// 	}
	// });
	// document.getElementById('confirm_password').addEventListener('input', () => {
	// 	if (confirm_pass.value !== password.value) {
	// 		errMsg.textContent = '密碼與確認密碼不相符';
	// 		return;
	// 	} else {
	// 		errMsg.textContent = '';
	// 	}
	// });
	// document.getElementById('register').addEventListener('click', () => {
	// 	const accLength = username.value.length;
	// 	if (accLength < 8 || accLength > 50) {
	// 		errMsg.textContent = '帳號長度須介於8~50字元';
	// 		return;
	// 	}
	// 	const pwdLength = password.value.length;
	// 	if (pwdLength < 8 || pwdLength > 50) {
	// 		errMsg.textContent = '密碼長度須介於8~50字元';
	// 		return;
	// 	}
	// 	if (confirm_pass.value !== password.value) {
	// 		errMsg.textContent = '密碼與確認密碼不相符';
	// 		return;
	// 	}
	// 	fetch('http://localhost:8080/simplefitness-servlet/member/register', {
	// 		method: 'POST',
	// 		headers: { 'Content-Type': 'application/json' },
	// 		body: JSON.stringify({
	// 			memUsername: username.value,
	// 			memPassword: password.value,
	// 			memConfirmPass: confirm_pass.value,
	// 			memName: mem_name.value,
	// 			memEmail: email.value
	// 		}),
	// 	})
	// 		.then(resp => resp.json())
	// 		.then(body => {
	// 			errMsg.textContent = '';
	// 			const { successful, message } = body;
	// 			if (successful) {
	// 				window.alert("註冊成功！跳轉至登入畫面");
	// 				location = './login.html';
	// 			} else {
	// 				errMsg.textContent = message;
	// 			}
	// 		});
	// });
})();