(() => {
	const username = document.querySelector('#username');
	const password = document.querySelector('#password');
	const confirm_pass = document.querySelector('#confirm_password');
	const mem_name = document.querySelector('#name');
	const email = document.querySelector('#email');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('username').addEventListener('input', () => {
		const accLength = username.value.length;
		if (accLength < 8 || accLength > 50) {
			errMsg.textContent = '帳號長度須介於8~50字元';
			return;
		} else {
			errMsg.textContent = '';
		}

	});
	document.getElementById('password').addEventListener('input', () => {
		const pwdLength = password.value.length;
		if (pwdLength < 8 || pwdLength > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		} else {
			errMsg.textContent = '';
		}
	});
	document.getElementById('confirm_password').addEventListener('input', () => {
		if (confirm_pass.value !== password.value) {
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		} else {
			errMsg.textContent = '';
		}
	});
	document.getElementById('register').addEventListener('click', () => {
		const accLength = username.value.length;
		if (accLength < 8 || accLength > 50) {
			errMsg.textContent = '帳號長度須介於8~50字元';
			return;
		}
		const pwdLength = password.value.length;
		if (pwdLength < 8 || pwdLength > 50) {
			errMsg.textContent = '密碼長度須介於8~50字元';
			return;
		}
		if (confirm_pass.value !== password.value) {
			errMsg.textContent = '密碼與確認密碼不相符';
			return;
		}
		fetch('/simplefitness-servlet/member/register', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memUsername: username.value,
				memPassword: password.value,
				memConfirmPass: confirm_pass.value,
				memName: mem_name.value,
				memEmail: email.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					window.alert("註冊成功！跳轉至登入畫面");
					location = './login.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();