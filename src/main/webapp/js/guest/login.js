(() => {
	const username = document.querySelector('#username');
	const password = document.querySelector('#password');
	const errMsg = document.querySelector('#errMsg');
	window.addEventListener('keyup', function (e) {
	  if (e.code === 'Enter') {
	     document.getElementById('login').click();
	  }
	});
	document.getElementById('login').addEventListener('click', () => {
		fetch('/simplefitness-servlet/member/login', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				memUsername: username.value,
				memPassword: password.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = '../member/member_home.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();