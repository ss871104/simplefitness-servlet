(() => {
	const username = document.querySelector('#username');
	const email = document.querySelector('#email');
	const errMsg = document.querySelector('#errMsg');

	document.getElementById('send').addEventListener('click', () => {
		console.log(username.value);
		console.log(email.value);
		fetch('http://localhost:8080/simplefitness-servlet/member/forgetpass', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memUsername: username.value,
				memEmail: email.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					window.alert("驗證碼已寄出，請至信箱查詢！點擊確認跳轉至驗證碼頁面");
					location = './verifivation.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();