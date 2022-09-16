(() => {
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	})

	fetch("http://localhost:8080/simplefitness-servlet/session")
		.then(resp => resp.json())
		.then(member => {
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
			document.querySelector('#lastlogin').textContent = member.memLogin;
			if (member.memStatus == "0") {
				document.querySelector('#status').textContent = "無會籍";
			} else if (member.memStatus == "1") {
				document.querySelector('#status').textContent = "有會籍";
			} else {
				document.querySelector('#status').textContent = "";
			}
			document.getElementById("name-input").value = member.memName;
			document.getElementById("nickname-input").value = member.memNickname;
			document.getElementById("email-input").value = member.memEmail;
			document.getElementById("phone-input").value = member.memPhone;
			if (member.memGender == "0") {
				document.getElementById("male-input").checked = true;
			} else if (member.memGender == "1") {
				document.getElementById("female-input").checked = true;
			}
			document.getElementById("birth-input").value = member.memBirth;
		})

	document.getElementById('change-password').addEventListener('click', () => {
		location = './change_pass.html';
	});


	const Mname = document.querySelector('#name-input');
	const nickname = document.querySelector('#nickname-input');
	const email = document.querySelector('#email-input');
	const phone = document.querySelector('#phone-input');
	const gender = document.getElementsByName('gender');
	const birth = document.querySelector('#birth-input');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('edit').addEventListener('click', () => {
		let selected_gender;
		if (gender[0].checked == true) {
			selected_gender = gender[0];
		} else if (gender[1].checked == true) {
			selected_gender = gender[1];
		}
		fetch('http://localhost:8080/simplefitness-servlet/member/edit', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memName: Mname.value,
				memNickname: nickname.value,
				memEmail: email.value,
				memPhone: phone.value,
				memGender: selected_gender.value,
				memBirth: birth.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = './member_edit.html';
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