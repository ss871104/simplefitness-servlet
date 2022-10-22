(() => {
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	});
	
	// 一進來get gym
	fetch("http://localhost:8080/simplefitness-servlet/gym/getAllGym")
	.then(resp => resp.json())
	.then(gym => {
		for (i = 0; i < gym["length"]; i++){
			sessionStorage.setItem(`'gym${gym[i].gymId}'`, `${gym[i].gymName}`);
			
		  	let text = `
		  		<option value="${gym[i].gymId}" id="gym${gym[i].gymId}">${gym[i].gymName}</option>
		  		`;

			$(".gym").append(text);
			
		}
	});
	
	// GET ById 出現資料庫資料
	var empId = sessionStorage.getItem('emp');
	empId = parseInt(empId);
	fetch("http://localhost:8080/simplefitness-servlet/staff/getEmpById", {
	method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				empId: empId,
			}),
		})
			.then(resp => resp.json())
			.then(emp => {
				let job = "";
				if (emp.job == "0") {
					job = "行政"
				} else if (emp.job == "1") {
					job = "教練"
				}
				let status = "";
				if (emp.status == "0") {
					status = "離職"
				} else if (emp.status == "1") {
					status = "在職中"
				} else if (emp.status == "2") {
					status = "留停"
				}
				if (emp.picBase64 != null) {
					document.querySelector('#profile-pic').innerHTML = `
					<img src="${emp.picBase64}">
					`;
				} else {
					document.querySelector('#profile-pic').innerHTML = `
					<img src="../../img/no-profile.jpeg"></img>
					`;
				}
				console.log(document.querySelector('#profile-pic').innerHTML);
				
				let gymName = sessionStorage.getItem(`'gym${emp.gymId}'`);
				
				document.querySelector('#name').textContent = emp.empName;
				document.querySelector('#nickname').textContent = emp.nickname;
				document.querySelector('#gym').textContent = gymName;
				document.querySelector('#username').textContent = emp.username;
				document.querySelector('#email').textContent = emp.email;
				document.querySelector('#phone').textContent = emp.phone;
				document.querySelector('#job').textContent = job;
				if (emp.gender == "1") {
					document.querySelector('#gender').textContent = "男";
				} else if (emp.gender == "0") {
					document.querySelector('#gender').textContent = "女";
				} else {
					document.querySelector('#gender').textContent = "";
				}
				document.querySelector('#birth').textContent = emp.birth;
				document.querySelector('#employ').textContent = emp.employDate;
				document.querySelector('#intro').textContent = emp.intro;
				document.querySelector('#status').textContent = status;

				document.querySelector('#name-input').value = emp.empName;
				document.querySelector('#nickname-input').value = emp.nickname;
				if (emp.email != null) {
					document.querySelector('#email-input').value = emp.email;
				}
				if (emp.phone != null) {
					document.querySelector('#phone-input').value = emp.phone;
				}
				if (emp.job == "0") {
					document.getElementById("staff-input").checked = true;
				} else if (emp.job == "1") {
					document.getElementById("coach-input").checked = true;
				}
				if (emp.gender == "0") {
					document.getElementById("female-input").checked = true;
				} else if (emp.gender == "1") {
					document.getElementById("male-input").checked = true;
				}
				document.getElementById("birth-input").value = emp.birth;
				if (emp.status == "0") {
					document.getElementById("quit-input").checked = true;
				} else if (emp.status == "1") {
					document.getElementById("onboard-input").checked = true;
				} else if (emp.status == "2") {
					document.getElementById("hold-input").checked = true;
				}
				document.querySelector('#intro-input').value = emp.intro;
				
				document.getElementById('edit-form').addEventListener('click', () => {
					document.getElementById(`gym${emp.gymId}`).setAttribute('selected', 'selected');
					
				})
				
				document.getElementById('change-password').addEventListener('click', () => {
					location = './emp_change_pass.html';
				})
				
			})

	// 彈窗選擇圖片的預覽圖片
	window.addEventListener("load", function() {
		var the_file_element = document.getElementById("pic");
		the_file_element.addEventListener("change", function(e) {

			var preview_pic = document.getElementById("preview-pic");
			
			preview_pic.innerHTML = ""; // 讓預覽圖消失

			// 跑使用者選的檔案
			let reader = new FileReader(); // 用來讀取檔案
			reader.readAsDataURL(this.files[0]); // 讀取檔案
			reader.addEventListener("load", function() {
					console.log(reader.result);
					let pic_html = `
                <img src="${reader.result}" id="new-pic">
              `;
			  		preview_pic.insertAdjacentHTML("beforeend", pic_html); // 加進節點
				});
				
		});
		
	});

	// 編輯資料
	const Ename = document.querySelector('#name-input');
	const nickname = document.querySelector('#nickname-input');
	const gym = document.getElementById('gym-input');
	const email = document.querySelector('#email-input');
	const phone = document.querySelector('#nickname-input');
	const job = document.getElementsByName('job');
	const gender = document.getElementsByName('gender');
	const birth = document.querySelector('#birth-input');
	const status = document.getElementsByName('status');
	const intro = document.querySelector('#intro-input');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('edit').addEventListener('click', () => {
		let picBase64 = document.querySelector('#new-pic');
		const preview_pic = document.querySelector('#preview-pic');
		if (preview_pic.innerHTML == "") {
			picBase64 = null;
		} else {
			picBase64 = picBase64.src;
		}
		if (gym.value == "0") {
			errMsg.textContent = '所屬健身房未選';
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
		} else if (status[2].checked == true) {
			selected_status = status[2];
		}
		if (selected_status == null) {
			errMsg.textContent = '狀態未選';
			return;
		}
		if (birth.value == "") {
			errMsg.textContent = '生日未填';
			return;
		}
		fetch('http://localhost:8080/simplefitness-servlet/staff/edit', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				picBase64: picBase64,
				empName: Ename.value,
				nickname: nickname.value,
				gymId: gym.value,
				email: email.value,
				phone: phone.value,
				job: selected_job.value,
				gender: selected_gender.value,
				birth: birth.value,
				status: selected_status.value,
				intro: intro.value,
				empId: empId
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './employee_edit.html';
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