(() => {
	var empId = sessionStorage.getItem('emp');
	empId = parseInt(empId);
	const old_password = document.querySelector('#oPassword');
	const password = document.querySelector('#nPassword');
	const confirm_pass = document.querySelector('#confirm-nPassword');
	const errMsg = document.querySelector('#errMsg');

	password.addEventListener('input', () => {
		const pwdLength = password.value.length;
		if (pwdLength < 8 || pwdLength > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		} else {
			errMsg.textContent = '';
		}
	});
	confirm_pass.addEventListener('input', () => {
		if (confirm_pass.value !== password.value) {
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		} else {
			errMsg.textContent = '';
		}
	});

	document.getElementById('change').addEventListener('click', () => {
		const pwdLength = password.value.length;
		if (pwdLength < 8 || pwdLength > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		}
		if (confirm_pass.value !== password.value) {
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		}
		fetch('http://localhost:8080/simplefitness-servlet/staff/changePass', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				password: old_password.value,
				newPassword: password.value,
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