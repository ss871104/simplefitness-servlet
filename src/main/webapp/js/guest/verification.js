(() => {
	const ver_code = document.querySelector('#ver-code');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('confirm').addEventListener('click', () => {
		fetch('http://localhost:8080/simplefitness-servlet/member/verification', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memInputCode: ver_code.value,
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					window.alert("驗證碼輸入正確！跳轉至修改密碼");
					location = './change_pass_forget.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();