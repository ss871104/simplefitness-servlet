(() => {
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	});
	
	// GET ById 出現資料庫資料
	var memId = sessionStorage.getItem('member');
	memId = parseInt(memId);
	fetch("http://localhost:8080/simplefitness-servlet/member/getMemById", {
	method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memId: memId
			}),
		})
			.then(resp => resp.json())
			.then(member => {
				if (member.memPicBase64 != null) {
					document.querySelector('#profile-pic').innerHTML = `
					<img src="${member.memPicBase64}">
				`;
				} else {
					document.querySelector('#profile-pic').innerHTML = `
					<img src="../../img/no-profile.jpeg"></img>
					`;
				}
				document.querySelector('#name').textContent = member.memName;
				document.querySelector('#nickname').textContent = member.memNickname;
				document.querySelector('#username').textContent = member.memUsername;
				document.querySelector('#email').textContent = member.memEmail;
				document.querySelector('#phone').textContent = member.memPhone;
				if (member.memGender == "0") {
					document.querySelector('#gender').textContent = "男";
				} else if (member.memGender == "1") {
					document.querySelector('#gender').textContent = "女";
				} else {
					document.querySelector('#gender').textContent = "";
				}
				document.querySelector('#birth').textContent = member.memBirth;
				document.querySelector('#register').textContent = member.memRegister;
				document.querySelector('#start').textContent = member.memStart;
				document.querySelector('#expire').textContent = member.memExpire;
				if (member.memLogin == null) {
					document.querySelector('#lastlogin').textContent = "第一次登入";
				} else {
					document.querySelector('#lastlogin').textContent = member.memLogin.replace('T', ' ');
				}
				if (member.memStatus == "0") {
					document.querySelector('#status').textContent = "無會籍";
				} else if (member.memStatus == "1") {
					document.querySelector('#status').textContent = "有會籍";
				} else {
					document.querySelector('#status').textContent = "";
				}
				document.querySelector('#name-input').textContent = member.memName;
				document.querySelector('#username-input').textContent = member.memUsername;
				document.getElementById("email-input").value = member.memEmail;
				if (member.memPhone != null) {
					document.getElementById("phone-input").value = member.memPhone;
				}
				document.getElementById('start-input').value = member.memStart;
				document.getElementById('expire-input').value = member.memExpire;
				if (member.memStatus == "0") {
					document.getElementById("no-input").checked = true;
				} else if (member.memStatus == "1") {
					document.getElementById("yes-input").checked = true;
				}
				
			})

	// 編輯資料
	const email = document.querySelector('#email-input');
	const phone = document.querySelector('#phone-input');
	const start = document.querySelector('#start-input');
	const expire = document.querySelector('#expire-input');
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
		fetch('http://localhost:8080/simplefitness-servlet/member/empEditMember', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memEmail: email.value,
				memPhone: phone.value,
				memStart: start.value,
				memExpire: expire.value,
				memStatus: selected_status.value,
				memId: memId
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './emp_member_edit.html';
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