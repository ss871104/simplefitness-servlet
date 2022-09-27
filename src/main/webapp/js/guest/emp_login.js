(() => {
	const username = document.querySelector('#username');
	const password = document.querySelector('#password');
	const errMsg = document.querySelector('#errMsg');
	document.getElementById('login').addEventListener('click', () => {
		fetch('http://localhost:8080/simplefitness-servlet/staff/login', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				username: username.value,
				password: password.value
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				errMsg.textContent = '';
				const { successful, message } = body;
				if (successful) {
					location = '../staff/staff_home.html';
				} else {
					errMsg.textContent = message;
				}
			});
	});
})();