(() => {
	const password = document.querySelector('#nPassword');
	const confirm_password = document.querySelector('#confirm-nPassword');
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
	confirm_password.addEventListener('input', () => {
		if (confirm_password.value !== password.value) {
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
		if (confirm_password.value !== password.value) {
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		}
		fetch('http://localhost:8080/simplefitness-servlet/member/changeforgetpass', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memNewPassword: password.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					window.alert("密碼修改成功！跳轉至登入畫面");
					location = './login.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();